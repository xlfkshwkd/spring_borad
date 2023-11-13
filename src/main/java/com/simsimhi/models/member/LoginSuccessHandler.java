package com.simsimhi.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Objects;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        /**
         * 로그인 성공시 페이지 이동
         * 요청 데이터 redirectUrl 값이 있음녀 이동 없으면 메인 페이지
         * */
        String redirectURL = Objects.requireNonNullElse(request.getParameter
                ("redirectURL"),"/");
        response.sendRedirect(request.getContextPath()+redirectURL);

    }
}
