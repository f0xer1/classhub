package com.classhub.api.config;

import com.classhub.api.model.Administrator;
import com.classhub.api.model.Student;
import com.classhub.api.model.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl<T> implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(T user) {
        if (user instanceof Student) {
            this.username = ((Student) user).getUsername();
            this.password = ((Student) user).getPwd();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"));
        } else if (user instanceof Teacher) {
            this.username = ((Teacher) user).getUsername();
            this.password = ((Teacher) user).getPwd();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_TEACHER"));
        } else if (user instanceof Administrator) {
            this.username = ((Administrator) user).getUsername();
            this.password = ((Administrator) user).getPwd();
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
