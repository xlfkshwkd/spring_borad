package com.simsimhi.configs;
import com.simsimhi.commons.interceptors.CommonInterceptor;
import com.simsimhi.commons.interceptors.SiteConfigInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;
//import com.simsimhi.commons.interceptors.SiteConfigInterceptor;

@Configuration
@EnableJpaAuditing @EnableScheduling
@EnableConfigurationProperties(FileUploadConfig.class)
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Autowired
    private CommonInterceptor commonInterceptor;

    @Autowired
    private SiteConfigInterceptor siteConfigInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(fileUploadConfig.getUrl() + "**")
                .addResourceLocations("file:///" + fileUploadConfig.getPath());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(siteConfigInterceptor)
                .addPathPatterns("/**");

    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {

        return new HiddenHttpMethodFilter();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.addBasenames("messages.commons", "messages.validations", "messages.errors");

        return ms;
    }

}