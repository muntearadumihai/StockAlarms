package com.devm8.stockalarms.repository;

import com.devm8.stockalarms.model.UserDO;

import java.util.Optional;

public interface UserRepository {

    UserDO createUser(UserDO userDO);

    Optional<UserDO> getUserByEmail(String email);

    Optional<UserDO> getUserById(int userId);

    void deleteUser(int id);
}
