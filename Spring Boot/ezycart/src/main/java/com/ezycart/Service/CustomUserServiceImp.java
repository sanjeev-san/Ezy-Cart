package com.ezycart.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezycart.Entity.User;
import com.ezycart.Respository.UserRepo;

@Service
public class CustomUserServiceImp  implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;


    // private UserRepo userRepo;
    // public CustomUserServiceImp(UserRepo userRepo){
    //     this.userRepo=userRepo;
    // }


    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not found with email : "+username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    
}