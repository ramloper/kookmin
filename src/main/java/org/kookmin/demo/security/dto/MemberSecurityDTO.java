package org.kookmin.demo.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberSecurityDTO extends User {

    private String username;

    private String password;

    private String name;

    private String phoneNumber;

    public MemberSecurityDTO(String username, String password,
                             String name, String phoneNumber,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
