package com.devm8.stockalarms.service;

import com.devm8.stockalarms.model.AuthenticationRequest;
import com.devm8.stockalarms.model.AuthenticationResponse;
import com.devm8.stockalarms.model.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(int id);

    AuthenticationResponse createUserAuthenticationToken(AuthenticationRequest request);

    public void deleteUser(int id);
}
