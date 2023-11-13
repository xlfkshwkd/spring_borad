package com.simsimhi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Member {

    @Id
    private Long user_No;
    private String email;
    private String password;

    private String user_Nm;
    private String mobile;
    private LocalDateTime regDt;
    private LocalDateTime modDt;



}
