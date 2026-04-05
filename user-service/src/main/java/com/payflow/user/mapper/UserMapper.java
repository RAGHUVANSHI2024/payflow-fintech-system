package com.payflow.user.mapper;

import com.payflow.user.dto.UserResponse;
import com.payflow.user.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDTO(User user);
}
