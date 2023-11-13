package com.simsimhi.controllers.members;

import com.simsimhi.commons.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
        private final Utils utils;

        @GetMapping("/join")
        public String join(){
            return utils.tpl("member/join"); //모바일
        }


    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
            model.addAttribute("redirectURL" ,redirectURL);

        return utils.tpl("member/login");
    }

}
