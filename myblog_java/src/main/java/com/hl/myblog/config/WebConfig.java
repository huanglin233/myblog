package com.hl.myblog.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 拦截器和过滤器的配置文件
 * 
 * @author huanglin 
 * @date   2020年4月10日
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        /**
         * 替换默认的MappingJackson2HttpMessageConverter，过滤(json请求参数)xss
         */
        ListIterator<HttpMessageConverter<?>> listIterator = messageConverters.listIterator();
        while(listIterator.hasNext()) {
            HttpMessageConverter<?> next = listIterator.next();
            if(next instanceof MappingJackson2HttpMessageConverter) {
                listIterator.remove();
                break;
            }
        }
        messageConverters.add(getMappingJackson2HttpMessageConverter());
 
    }
 
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        // 创建自定义ObjectMapper
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new JsonHtmlXssDeserializer(String.class));
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(this.getApplicationContext()).build();
        objectMapper.registerModule(module);
        // 创建自定义消息转换器
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }
 
}
 
/**
 * 对入参的json进行转义
 */
class JsonHtmlXssDeserializer extends JsonDeserializer<String> {
 
    public JsonHtmlXssDeserializer(Class<String> string) {
        super();
    }
 
    @Override
    public Class<String> handledType() {
        return String.class;
    }
 
    @SuppressWarnings("deprecation")
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String                   value      = jsonParser.getValueAsString();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest       request    = attributes.getRequest();
        String requestURI = request.getRequestURI();
        // 加上博客markdown中的html标签不用过滤
        if (value != null && !requestURI.contains("/admin/blog") && !requestURI.contains("/admin/update")) {
            return StringEscapeUtils.escapeHtml4(value.toString());
        }
        return value;
    }
}