package com.btoy.trial.web.security.userdetails;

/*
 * @created 08/03/2026 ~~ 14:36
 * author: batu
 */

import com.btoy.trial.persistence.entity.role.Role;
import com.btoy.trial.persistence.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.btoy.trial.constants.ApplicationConstants.BLANK;

@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {

    @Getter
    private final String email;
    private final String userName;
    private final String password;
    private final List<String> authorities;

    public static CustomUserDetails fromUser(User user) {
        return CustomUserDetails.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(Role::getName).toList())
                .email(user.getEmail())
                .build();
    }

    public static List<String> toRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
}
