package com.hl.myblog.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hl.myblog.globalHandler.xss.XssFilter;

/**
 * xss过滤拦截器配置文件
 * 
 * @author huanglin
 * @data   2021年1月8日-下午8:41:30
 */
@Configuration
public class XssFilterConfigurer {

    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistrationBean() {
        FilterRegistrationBean<XssFilter> filterRegistrationBean = new FilterRegistrationBean<XssFilter>();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        //excludes用于配置不需要参数过滤的请求url
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*,/upload,/admin/blog/*");
        //isIncludeRichText主要用于设置富文本内容是否需要过滤
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}