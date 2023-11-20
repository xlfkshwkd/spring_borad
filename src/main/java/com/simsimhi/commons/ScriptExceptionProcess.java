package com.simsimhi.commons;

import jakarta.servlet.http.HttpServletResponse;
import com.simsimhi.commons.exceptions.AlertBackException;
import com.simsimhi.commons.exceptions.AlertException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ScriptExceptionProcess {

    @ExceptionHandler(AlertException.class)
    default String scriptHandler(AlertException e, Model model, HttpServletResponse response) {
        // 공통 템플릿 에러 처리 핸들러
        response.setStatus(e.getStatus().value());
        String script = String.format("alert('%s');", e.getMessage());
        if (e instanceof AlertBackException) {
            script += "history.back();";
        }

        model.addAttribute("script", script);

        return "commons/_execute_script";

    }
}