package com.simsimhi.controllers.members;

import com.simsimhi.commons.CommonProcess;
import com.simsimhi.commons.MemberUtil;
import com.simsimhi.commons.Utils;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.Member;
import com.simsimhi.models.member.MemberInfo;
import com.simsimhi.models.member.MemberSaveService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Security;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess {

    private final Utils utils;
    private final MemberSaveService saveService;

    @GetMapping("/join")
    public String join(@ModelAttribute Model model) {
        commonProcess(model, Utils.getMessage("회원가입","common"));

        return utils.tpl("member/join");
    }
    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model) {
        commonProcess(model, Utils.getMessage("회원가입","common"));

        saveService.join(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        commonProcess(model, Utils.getMessage("로그인","common"));

        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }
}

