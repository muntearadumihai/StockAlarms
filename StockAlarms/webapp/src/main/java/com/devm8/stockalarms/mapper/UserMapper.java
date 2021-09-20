package com.devm8.stockalarms.mapper;

import com.devm8.stockalarms.model.UserDO;
import com.devm8.stockalarms.model.UserDTO;

public class UserMapper {

    public static UserDO toDO(UserDTO userDTO) {
        return new UserDO.UserDOBuilder().userId(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
    }

    public static UserDTO toDTO(UserDO userDO) {
        return new UserDTO.UserDTOBuilder().userId(userDO.getUserId())
                .firstName(userDO.getFirstName())
                .lastName(userDO.getLastName())
                .password(userDO.getPassword())
                .email(userDO.getEmail())
                .build();
    }
}
