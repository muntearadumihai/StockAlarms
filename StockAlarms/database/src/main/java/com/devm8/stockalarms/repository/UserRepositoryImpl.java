package com.devm8.stockalarms.repository;

import com.devm8.stockalarms.mapper.UserRowMapper;
import com.devm8.stockalarms.model.UserDO;
import com.devm8.stockalarms.repository.factory.KeyHolderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String CREATE_USER_QUERY = "INSERT INTO USERS(first_name,last_name,password,email) VALUES (?,?,?,?)";
    private static final String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM USERS WHERE email = ?";
    final static String GET_USER_BY_ID_QUERY = "SELECT * FROM USERS WHERE user_id = ?";
    static final String DELETE_USER_QUERY = "DELETE from USERS WHERE user_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;
    private final UserRowMapper rowMapper;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, KeyHolderFactory keyHolderFactory, UserRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
        this.rowMapper = rowMapper;
    }

    @Transactional
    @Override
    public UserDO createUser(UserDO userDO) {
        KeyHolder keyHolder = keyHolderFactory.generateKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, new String[]{"user_id"});
            preparedStatement.setString(1, userDO.getFirstName());
            preparedStatement.setString(2, userDO.getLastName());
            preparedStatement.setString(3, userDO.getPassword());
            preparedStatement.setString(4, userDO.getEmail());
            return preparedStatement;
        }, keyHolder);
        userDO.setUserId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return userDO;
    }
    @Override
    public Optional<UserDO> getUserByEmail(String email) {
        try {
            UserDO user = jdbcTemplate.queryForObject(GET_USER_BY_EMAIL_QUERY, rowMapper, email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        }
    }

    @Override
    public Optional<UserDO> getUserById(int userId) {
        try {
            UserDO user = jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, rowMapper, userId);

            return Optional.of(user);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        }
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);
    }
}
