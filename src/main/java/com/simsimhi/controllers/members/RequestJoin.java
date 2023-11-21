package com.simsimhi.controllers.members;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin {
    @NotBlank @Email
    private String email;

    @NotBlank @Column(length = 16)
    @Size(min=8)
    private String password;

    @NotBlank @Column(length = 16)
    @Size(min=8)
    private String confirmPassword;

    @NotBlank
    private String userNm;

    private String mobile;

    @AssertTrue
    boolean agree;
}