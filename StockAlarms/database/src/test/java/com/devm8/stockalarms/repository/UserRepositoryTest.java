package com.devm8.stockalarms.repository;

import com.devm8.stockalarms.mapper.UserRowMapper;
import com.devm8.stockalarms.model.UserDO;
import com.devm8.stockalarms.repository.factory.KeyHolderFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private KeyHolderFactoryImpl keyHolderFactory;

    @Mock
    private KeyHolder keyHolder;

    @Mock
    private UserRowMapper rowMapper;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    void whenGetUserAndIdIsCorrect_ThenUserIsReturned() {
        int existingUserId = 1;
        UserDO expectedUser = new UserDO.UserDOBuilder()
                .userId(existingUserId)
                .firstName("Cristian")
                .lastName("Murza")
                .email("admin@yahoo.com")
                .password("12345678")
                .build();

        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Integer.class)))
                .thenReturn(expectedUser);

        Optional<UserDO> actualUser = userRepository.getUserById(existingUserId);

        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    void getUserByIdWhenThrowingDataAccessExceptionTest() {
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), eq(1)))
                .thenThrow(new DataAccessException("...") {
                });

        assertThrows(DataAccessException.class, () ->
                userRepository.getUserById(1));
    }

    @Test
    void testGetUserByIdThrowsEmptyResultDataAccessException() {
        final int nonExistingUserId = 2;
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Integer.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        Optional<UserDO> actualUser = userRepository.getUserById(nonExistingUserId);

        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    void getUserByEmailTest() {
        UserDO expectedUser = new UserDO.UserDOBuilder()
                .userId(1)
                .firstName("Cristian")
                .lastName("Murza")
                .email("admin@yahoo.com")
                .password("12345678")
                .build();

        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(String.class)))
                .thenReturn(expectedUser);

        Optional<UserDO> actualUser = userRepository.getUserByEmail("admin@yahoo.com");

        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    void testGetUserByEmailThrowsEmptyResultDataAccessException() {
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(String.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        Optional<UserDO> actualUser = userRepository.getUserByEmail("admin@yahoo.com");

        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    void getUserByEmailWhenThrowingDataAccessExceptionTest() {
        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), eq("myMail@gmail.com")))
                .thenThrow(new DataAccessException("...") {
                });

        assertThrows(DataAccessException.class, () ->
                userRepository.getUserByEmail("myMail@gmail.com"));
    }

    @Test
    void testCreateUser() {
        UserDO user = new UserDO.UserDOBuilder()
                .userId(1)
                .firstName("Cristian")
                .lastName("Murza")
                .password("admin")
                .email("admin@mail.com")
                .build();

        when(keyHolderFactory.generateKeyHolder()).thenReturn(keyHolder);
        when(keyHolder.getKey()).thenReturn(1);
        when(jdbcTemplate.update(any(PreparedStatementCreator.class), eq(keyHolder))).thenReturn(1);

        UserDO createdUser = userRepository.createUser(user);

        assertEquals(user, createdUser);
    }

    @Test
    void whenGetUserAndEmailIsCorrect_ThenUserIsReturned() {
        UserDO expectedUser = new UserDO.UserDOBuilder()
                .userId(1)
                .firstName("Cristian")
                .lastName("Murza")
                .email("admin@yahoo.com")
                .password("12345678")
                .build();

        when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(String.class)))
                .thenReturn(expectedUser);

        Optional<UserDO> actualUser = userRepository.getUserByEmail("admin@yahoo.com");

        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    public void testUserDelete() {
        userRepository.deleteUser(2);

        verify(jdbcTemplate, times(1)).update(UserRepositoryImpl.DELETE_USER_QUERY, 2);
    }
}
