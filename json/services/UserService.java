package com.softuni.json.services;


import com.softuni.json.dtos.*;
import com.softuni.json.entities.*;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDto);

    User findById(Long id);

    long userCount();
}
