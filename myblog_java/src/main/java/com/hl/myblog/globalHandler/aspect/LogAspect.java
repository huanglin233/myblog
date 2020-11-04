package com.hl.myblog.globalHandler.aspect;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hl.myblog.annotation.RecordLog;
import com.hl.myblog.common.utils.PrefixString;
import com.hl.myblog.pojo.RecordLogObj;
import com.hl.myblog.security.jwt.JWTUtil;

/**
 * 用户所有提交请求
 * 
 * @author huanglin
 * @Data   2020年2月6日
 */
@Aspect
@Component
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.hl.myblog.web..*.*(..))")
    public void log() {}

    @Pointcut("@annotation(com.hl.myblog.annotation.RecordLog)")
    public void recordLog() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest       request    = attributes.getRequest();

        String     url           = request.getRequestURL().toString();
        String     ip            = request.getRemoteAddr();
        String     classMethod   = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[]   args          = joinPoint.getArgs();
        RequestLog requestLog    = new RequestLog(url, ip, classMethod, args);

        logger.info("\n" + "Request :{}", requestLog.toString(PrefixString.Indent1()));
    }

    @After("log()")
    public void doAfter() {}

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturing(Object result) {
        logger.info("\n" + "Result :" +"        \n " + "{}", result);
    }

    private class RequestLog {
        private String   url;
        private String   ip;
        private String   classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            super();
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        public String toString(String prefix) {
            StringBuilder sb = new StringBuilder();

            sb.append("\n");
            sb.append(prefix + "url         = " + url + "\n");
            sb.append(prefix + "ip          = " + ip + "\n");
            sb.append(prefix + "classMethod = " + classMethod + "\n");
            sb.append(prefix + "args        = " + Arrays.toString(args));

            return sb.toString();
        }
    }

    @Around("recordLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res  = null;
        long   time = System.currentTimeMillis();
        try {
            res  = joinPoint.proceed();
            time = System.currentTimeMillis() - time;

            return res;
        } finally {
            try {
                // 方法执行完后增加日志
                addRecordLog(joinPoint, res, time);
            } catch (Exception e) {
                logger.info("\n" + "LogAspect 操作失败: " + e.getMessage());
            }
        }
    }

    private void addRecordLog(JoinPoint joinPoint, Object res, long time) throws JsonProcessingException {
        MethodSignature signature    = (MethodSignature)joinPoint.getSignature();
        RecordLogObj    recordLogObj = new RecordLogObj();
        ObjectMapper    objectMapper = new ObjectMapper();
        recordLogObj.setRunTime(time);
        recordLogObj.setReturnValue(objectMapper.writeValueAsString(res));
        recordLogObj.setArgs(objectMapper.writeValueAsString(joinPoint.getArgs()));
        recordLogObj.setCreateTime(new Date());
        recordLogObj.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        recordLogObj.setUserName(getUserName());
        RecordLog recordLog = signature.getMethod().getAnnotation(RecordLog.class);
        if(recordLog != null) {
            recordLogObj.setDescribe(getDetail(signature.getParameterNames(), joinPoint.getArgs(), recordLog));
            recordLogObj.setRecordType(recordLog.recordType().getValue());
            recordLogObj.setRecoredObject(recordLog.recordObject().getValue());
        } else {
            logger.info("recordLog is null");
        }

        logger.info(recordLogObj.toString(PrefixString.Indent1()));
    }

    private String getUserName() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest       request    = attributes.getRequest();
        String                   token      = request.getHeader("Authorization");
        String                  userName    = JWTUtil.getUserName(token);

        return (userName != null && !userName.equals("")) ? userName : "游客";
    }

    /**
     * 对当前登录用户和占位符处理
     * @param  argNames 方法参数名称数组
     * @param  args 方法参数数组
     * @param  annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args, RecordLog annotation){

        Map<Object, Object> map = new HashMap<>(4);
        for(int i = 0;i < argNames.length;i++){
            map.put(argNames[i],args[i]);
        }

        String       detail       = annotation.detail();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            detail = "" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", objectMapper.writeValueAsString(v));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }
}