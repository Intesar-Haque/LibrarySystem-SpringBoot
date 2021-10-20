package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class Users implements UserDetails {
    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "users")
    private List<Books> books = new ArrayList<>();
    @Id
    @GeneratedValue
    Long userId;
    @NotNull
    String name;
    String pass;
    String uname;
    @Enumerated(EnumType.STRING)
    UsersRole usersRole;
    Boolean locked = false;
    Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usersRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return uname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
