package com.ezycart.Service;

import com.ezycart.Entity.User;
import com.ezycart.Exception.UserException;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
