package com.btoy.trial.web.security.userdetails;

import com.btoy.trial.persistence.dao.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * @created 08/03/2026 ~~ 14:36
 * author: batu
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail((email))
                .map(CustomUserDetails::fromUser)
                .orElseThrow(); // TODO throw an exception in here.
    }
}
