package com.btoy.trial.web.security.userdetails;

import com.btoy.trial.persistence.dao.user.UserRepository;
import com.btoy.trial.persistence.exception.TriAuthEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.btoy.trial.web.security.userdetails.UserDetailsServiceImpl.USER_DETAILS_SERVICE_BEAN;

/*
 * @created 08/03/2026 ~~ 14:36
 * author: batu
 */

@Service(value = USER_DETAILS_SERVICE_BEAN)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public static final String USER_DETAILS_SERVICE_BEAN = "userDetailsService";

    @Override
    public UserDetails loadUserByUsername(String subject) throws UsernameNotFoundException {
        return userRepository.findByUserName((subject))
                .map(CustomUserDetails::fromUser)
                .orElseThrow(TriAuthEntityNotFoundException::new);
    }
}
