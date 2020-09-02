package com.softuni.json.services;

import com.softuni.json.dtos.*;
import com.softuni.json.entities.*;
import com.softuni.json.repositories.*;
import com.softuni.json.utils.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.validation.*;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Random random) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDto) {

        if(this.userRepository.count() != 0) {
            return;
        }

        Arrays.stream(userSeedDto).forEach(u -> {
            if(this.validationUtil.isValid(u)){
              User user = this.modelMapper.map(u, User.class);
              this.userRepository.saveAndFlush(user);
            }else {
                this.validationUtil.violations(u).stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public long userCount() {
        return this.userRepository.count();
    }
}
