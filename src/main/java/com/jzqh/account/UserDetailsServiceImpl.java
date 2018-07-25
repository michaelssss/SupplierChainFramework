package com.jzqh.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 本来是spring-security扩展，实现了自己的UserDetail的查询
 *
 * @author Michaelssss
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserCatalog repository;

    @Autowired
    public UserDetailsServiceImpl(UserCatalog repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserImpl> users = this.repository.findAll();
        for (UserDetails user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException(username + " Not Found");
    }
}
