package com.payflow.user.service;

import com.payflow.user.dto.UserResponse;
import com.payflow.user.entity.User;
import com.payflow.user.mapper.UserMapper;
import com.payflow.user.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    public Page<UserResponse> getUsers(String name, int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<User> users;

        if (name == null){
            users = userRepository.findAll(pageable);
        }else {
            users = userRepository.findByNameContainingIgnoreCase(name,pageable);
        }
        return users.map(USER_MAPPER::toDTO);
    }
}
