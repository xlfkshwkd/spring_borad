package com.simsimhi.controllers.members;

import com.simsimhi.commons.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

}
