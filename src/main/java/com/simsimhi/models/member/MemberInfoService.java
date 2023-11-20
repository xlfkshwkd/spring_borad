package com.simsimhi.models.member;

import com.simsimhi.entities.Member;
import com.simsimhi.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor //DB 조회..
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member =repository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException(username));

        List<GrantedAuthority> authorities = Arrays
                .asList(new SimpleGrantedAuthority(member.getMtype().toString()));



        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .member(member)
                .build();

    }
}
