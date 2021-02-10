package com.example.banksystem.services;

import com.example.banksystem.models.Authority;
import com.example.banksystem.models.User;
import com.example.banksystem.repositories.UserRepository;
import com.example.banksystem.repositories.interfaces.IUserRepository;
import com.example.banksystem.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void add(User entity) {
        this.userRepository.add(entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        this.userRepository.update(entity);
    }

    @Override
    @Transactional
    public void remove(int id) {
        this.userRepository.remove(id);
    }

    @Override
    @Transactional
    public User select(int id) {
        return this.userRepository.select(id);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        return this.userRepository.getUserList();
    }

    @Override
    @Transactional
    public User getUserByPhone(String phoneNumber) {
        return this.userRepository.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByPhoneNumber(phoneNumber);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Authority> authorities = user.getRole().getAuthorities();
        authorities.forEach(authority -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        });
        GrantedAuthority roleAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());
        grantedAuthorities.add(roleAuthority);
        return buildUserForAuthentication(user, grantedAuthorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), true, true, true, true, authorities);
    }
}
