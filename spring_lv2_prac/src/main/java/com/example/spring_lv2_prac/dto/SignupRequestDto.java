package com.example.spring_lv2_prac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[0-9a-z]+$")
    private String username;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
