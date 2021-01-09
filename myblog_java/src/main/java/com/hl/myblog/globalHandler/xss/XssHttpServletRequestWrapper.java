package com.hl.myblog.globalHandler.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;

/**
 * 重写请求参数处理函数
 * 
 * @author huanglin
 * @data   2021年1月8日-下午8:05:57
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

    HttpServletRequest orgRequest = null;

    private boolean isIncludeRichTest = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichTest) {
        super(request);
        orgRequest = request;
        this.isIncludeRichTest = isIncludeRichTest;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤.
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        if(("content".equals(name) || name.endsWith("withHtml")) && !isIncludeRichTest) {
            return super.getParameter(name);
        }

        name = XssFilterUtil.clean(name);
        String value = super.getParameter(name);
        if(StringUtils.isNotBlank(value)) {
            value =  XssFilterUtil.clean(value);
        }

        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = XssFilterUtil.clean(arr[i]);
            }
        }
        return arr;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        name = XssFilterUtil.clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }
}