package com.club.config;

//import com.club.interceptor.JwtTokenAdminInterceptor;
import com.club.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenAdminInterceptor;
    public void addCorsMappings(CorsRegistry registry) {
        log.info( "开始注册跨域配置...");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/user/user/registry")
                .excludePathPatterns("/user/user/findPassword")
                .excludePathPatterns("/user/user/getVerification");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }

    /**
     * 通过knife4j生成接口文档
     * @return
     */
    //校团委端接口展示
    @Bean
    public Docket docket_admin() {
        log.info("准备生成接口文档......");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("社团管理项目接口文档")
                .version("2.0")
                .description("社团管理项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin端接口测试")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.club.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    //机关部处接口展示
    @Bean
    public Docket docket_league() {
        log.info("准备生成接口文档......");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("社团管理项目接口文档")
                .version("2.0")
                .description("社团管理项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("league端接口测试")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.club.controller.league"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    //管理端接口展示
    @Bean
    public Docket docket_manager() {
        log.info("准备生成接口文档......");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("社团管理项目接口文档")
                .version("2.0")
                .description("社团管理项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("manager端接口测试")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.club.controller.manager"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    //用户端接口展示
    @Bean
    public Docket docket_user() {
        log.info("准备生成接口文档......");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("社团管理项目接口文档")
                .version("2.0")
                .description("社团管理项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("user端接口测试")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.club.controller.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始设置静态资源映射...");

        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
