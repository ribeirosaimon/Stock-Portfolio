package com.saimon.Stock.portfolio.Services.impl;

import com.saimon.Stock.portfolio.Database.Model.MyUser;
import com.saimon.Stock.portfolio.Database.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserDetails authenticated(MyUser user) {
        UserDetails myuser = loadUserByUsername(user.getLogin());
        boolean rightPassword = passwordEncoder.matches(user.getPassword(), myuser.getPassword());
        if (rightPassword) {
            return myuser;
        }
        throw new RuntimeException("no have User");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public MyUser saveUser(MyUser user) {
        String criptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(criptPassword);
        return userRepository.save(user);
    }
}
