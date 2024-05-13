package com.demo.springboot.SpringBootApp.securityExample;

import com.demo.springboot.SpringBootApp.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUserName = userRepository.findByUserName(username);
        byUserName.orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return byUserName.map(MyUserDetails::new).get();
    }
}
