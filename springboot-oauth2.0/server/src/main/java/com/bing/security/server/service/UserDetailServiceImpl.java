package com.bing.security.server.service;

import com.bing.security.server.model.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException(" username can't be null");
        }
        System.out.println("-ew----");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LoginUser user = new LoginUser(username, passwordEncoder.encode("123456"));
        return user;
    }
}
