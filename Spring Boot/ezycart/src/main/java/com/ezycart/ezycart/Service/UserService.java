package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.UserException;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
