package com.hl.myblog.config;

import java.util.ArrayList;

import org.apache.shiro.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * 
 * @author huanglin
 * @data   2020年7月11日-下午5:42:03
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile("!prod")
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    public Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                   .apiInfo(apiInfo())
                   .pathMapping("/")
                   .select() // 选择那些路径和api会生成document
                   .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                   //不显示错误的接口地址
                   .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                   .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                   .build();
    }

    public ApiInfo apiInfo() {
        Contact contact = new Contact("myBlog","localhost:8081/swagger-ui.html","www.2512467648@qq.com");
        return new ApiInfo("myBlog",
                           "个人博客网站",
                           "1.0",
                           "localhost:8081/swagger-ui.html",
                           contact,
                           "tomcat",
                           "huanglin",
                           new ArrayList<>());
    }
}