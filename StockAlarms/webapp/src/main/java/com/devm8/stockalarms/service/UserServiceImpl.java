package com.devm8.stockalarms.service;

import com.devm8.stockalarms.encoder.PasswordEncoder;
import com.devm8.stockalarms.exception.AuthenticationException;
import com.devm8.stockalarms.exception.NotFoundException;
import com.devm8.stockalarms.exception.UserCreationException;
import com.devm8.stockalarms.factory.UserJWTGenerator;
import com.devm8.stockalarms.model.*;
import com.devm8.stockalarms.repository.UserRepository;
import com.devm8.stockalarms.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.devm8.stockalarms.mapper.UserMapper.toDO;
import static com.devm8.stockalarms.mapper.UserMapper.toDTO;

@Service
public class UserServiceImpl implements UserService{

    private static final String USER_NOT_FOUND_MESSAGE = "This user does not exist!";
    private static final String NAME_ERROR_MESSAGE = "Name can not be empty!";
    private static final String PASSWORD_ERROR_MESSAGE = "Password can not be empty!";
    private static final String EMAIL_ERROR_MESSAGE = "Email is not valid!";
    private static final String AUTHENTICATION_ERROR_MESSAGE = "The email or password is invalid!";
    private static final String EMAIL_EXISTS_MESSAGE = "Email already exists!";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJWTGenerator userJWTGenerator;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateUser(userDTO);
        userDTO.setPassword(passwordEncoder.getEncoder().encode(userDTO.getPassword()));
        UserDO userDO = toDO(userDTO);
        return toDTO(userRepository.createUser(userDO));
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<UserDO> userDO = userRepository.getUserById(id);
        if (userDO.isEmpty()) {
            throw new NotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return toDTO(userDO.get());
    }

    private void validateUser(UserDTO userDTO) {
        if (!UserValidator.isValidName(userDTO.getFirstName())) {
            throw new UserCreationException(NAME_ERROR_MESSAGE);
        }
        if (!UserValidator.isValidName(userDTO.getFirstName())) {
            throw new UserCreationException(NAME_ERROR_MESSAGE);
        }
        if (!UserValidator.isValidPassword(userDTO.getPassword())) {
            throw new UserCreationException(PASSWORD_ERROR_MESSAGE);
        }
        if (!UserValidator.isValidEmail(userDTO.getEmail())) {
            throw new UserCreationException(EMAIL_ERROR_MESSAGE);
        }
        if (userRepository.getUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserCreationException(EMAIL_EXISTS_MESSAGE);
        }
    }
    @Override
    public AuthenticationResponse createUserAuthenticationToken(AuthenticationRequest request) {
        if (!UserValidator.isValidEmail(request.getEmail()) || !UserValidator.isValidPassword(request.getPassword())) {
            throw new AuthenticationException(AUTHENTICATION_ERROR_MESSAGE);
        }
        Optional<UserDO> optionalUser = userRepository.getUserByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new AuthenticationException(AUTHENTICATION_ERROR_MESSAGE);
        }
        UserDO user = optionalUser.get();
        if (!passwordEncoder.getEncoder().matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException(AUTHENTICATION_ERROR_MESSAGE);
        }
        AuthenticatedUser authenticatedUser = new AuthenticatedUser.AuthenticatedUserDTOBuilder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
        String jwt = userJWTGenerator.generate(authenticatedUser);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
}
