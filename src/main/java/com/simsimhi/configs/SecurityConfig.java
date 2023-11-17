package com.simsimhi.configs;

import com.simsimhi.models.member.LoginFailureHandler;
import com.simsimhi.models.member.LoginSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.class)
public class SecurityConfig {
    @Autowired
    private FileUploadConfig fileUploadConfig;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /** 인증설정  로그인  */
        http.formLogin(f ->{
            f.loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());

        });  //DSL
        http.logout(c ->{
            c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");

        });
        /** 인증설정  로그인  E */
        http.headers(c ->{
            c.frameOptions(o ->o.sameOrigin());
            
        });

        /** 인가설정  접근통제  S*/
        http.authorizeHttpRequests(c ->{
           c.requestMatchers("/mypage/**").authenticated() //로그인한 회원만 접근 가능하다.
                   .requestMatchers("/admin/**").hasAnyAuthority("ADMIN") //관리자만 접근
                   .anyRequest().permitAll(); // 나머지 페이지는 권한 필요 X

        });
        http.exceptionHandling(c ->{
           c.authenticationEntryPoint((req,resp,e)->{
              String URI =req.getRequestURI();
              if(URI.indexOf("/admin") != -1){ // 관리자 페이지 -401 응답 코드
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"NOT AUTHORIZED");
              }else { //회원 정보 페이지 /mypage
                  String url =req.getContextPath() + "/member/login";
                  resp.sendRedirect(url);

              }
           });
        });

        /** 인가설정  접근통제  E*/
        

        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer(){
        // security 경로가 적용될 필요가 없는 경로 설정
        return w -> w.ignoring().requestMatchers(
                "/front/css/**",
                "/front/js/**",
                "/front/images/**",

                "/mobile/js/**",
                "/mobile/css/**",
                "/mobile/images/**",

                "/admin/js/**",
                "/admin/css/**",
                "/admin/images/**",

                "/common/js/**",
                "/common/css/**",
                "/common/images/**",
                fileUploadConfig.getUrl() + "**");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}