package com.simsimhi.configs;

import com.simsimhi.entities.Member;
import com.simsimhi.models.member.MemberInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.util.Optional;

@Component //자동 삽입
public class AuditorAwareImpl implements AuditorAware<String > {

    @Override
    public Optional<String> getCurrentAuditor() {

        String email =null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //    Object principal =auth.getPrincipal();

        if(auth != null && auth.getPrincipal() instanceof MemberInfo){
            MemberInfo member =(MemberInfo)auth.getPrincipal();
            email=member.getEmail();

        }

       // System.out.println("principal: " + principal);

        return Optional.ofNullable(email);
    }
}
