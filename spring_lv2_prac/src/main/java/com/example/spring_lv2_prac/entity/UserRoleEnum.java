package com.example.spring_lv2_prac.entity;

//enum은 관련 있는 상수들을 모아놓는 애들
public enum UserRoleEnum {
    //객체를 만드는 생성자를 호출
    USER("ROLE_USER"),  // 사용자 권한
    ADMIN("ROLE_ADMIN");  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }
}
