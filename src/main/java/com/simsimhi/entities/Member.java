package com.simsimhi.entities;

import com.simsimhi.commons.constants.MemberType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Member {

    @Id
    private Long userNo;
    private String email;
    private String password;

    private String userNm;
    private String mobile;

    private MemberType mtype =MemberType.USER;


    private LocalDateTime regDt;
    private LocalDateTime modDt;








}
