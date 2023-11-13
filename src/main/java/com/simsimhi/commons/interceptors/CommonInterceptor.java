package com.simsimhi.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response,
                             Object handler) throws Exception {
        init(request);
        return true;

    }
    private void init(HttpServletRequest request) {
        HttpSession session =request.getSession();

        /** PC Mobile 수동 변경 처리 S */  // >? 가입력된 모바일값을 가져옴

        String device =request.getParameter("device");
        System.out.println("device:" + device) ;
        if (device !=null && !device.isBlank()){
            session.setAttribute("device",
                    device.toLowerCase().equals("mobile")?
                    "mobile":"pc");

        }
        /** PC Mobile 수동 변경 처리 E*/

    }
}
