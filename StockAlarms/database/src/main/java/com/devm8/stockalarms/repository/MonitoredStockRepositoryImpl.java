package com.devm8.stockalarms.repository;

import com.devm8.stockalarms.mapper.MonitoredStockRowMapper;
import com.devm8.stockalarms.model.MonitoredStockDO;
import com.devm8.stockalarms.repository.factory.KeyHolderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MonitoredStockRepositoryImpl implements MonitoredStockRepository {

    private static final String CREATE_MONITORED_STOCK_QUERY = "INSERT INTO MONITORED_STOCKS" +
            "(stock_id,user_id,start_alarm,alarm_type,percentage) VALUES (?,?,?,?,?)";
    private final static String GET_MONITORED_STOCK_BY_ID_QUERY = "SELECT * FROM MONITORED_STOCKS WHERE monitored_stock_id = ?";
    private static final String DELETE_MONITORED_STOCK = "DELETE from MONITORED_STOCKS WHERE monitored_stock_id = ?";
    private static final String GET_MONITORED_STOCK_BY_USER_ID_AND_STOCK_ID_QUERY = "Select * from MONITORED_STOCKS " +
            "WHERE user_id = ? and stock_id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;
    private final MonitoredStockRowMapper rowMapper;

    @Autowired
    public MonitoredStockRepositoryImpl(JdbcTemplate jdbcTemplate, KeyHolderFactory keyHolderFactory, MonitoredStockRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
        this.rowMapper = rowMapper;
    }

    @Override
    public MonitoredStockDO createMonitoredStock(MonitoredStockDO monitoredStockDO) {
        KeyHolder keyHolder = keyHolderFactory.generateKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MONITORED_STOCK_QUERY,
                    new String[]{"monitored_stock_id"});
            preparedStatement.setInt(1, monitoredStockDO.getStockId());
            preparedStatement.setInt(2, monitoredStockDO.getUserId());
            preparedStatement.setFloat(3, monitoredStockDO.getStartAlarm());
            preparedStatement.setInt(4, monitoredStockDO.getAlarmType().asInt());
            preparedStatement.setInt(5, monitoredStockDO.getPercentage());
            return preparedStatement;
        }, keyHolder);
        monitoredStockDO.setMonitoredStockId(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return monitoredStockDO;
    }

    @Override
    public Optional<MonitoredStockDO> getMonitoredStockById(int monitoredStockId) {
        try {
            MonitoredStockDO monitoredStock = jdbcTemplate.queryForObject(GET_MONITORED_STOCK_BY_ID_QUERY, rowMapper,
                    monitoredStockId);

            return Optional.of(monitoredStock);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        }
    }

    @Override
    public void deleteMonitoredStock(int id) {
        jdbcTemplate.update(DELETE_MONITORED_STOCK, id);
    }

    @Override
    public Optional<MonitoredStockDO> getMonitoredStocksByUserIdAndStockId(int userId, int stockId) {
        try {
            MonitoredStockDO monitoredStock = jdbcTemplate.queryForObject(GET_MONITORED_STOCK_BY_USER_ID_AND_STOCK_ID_QUERY, rowMapper,
                    userId, stockId);

            return Optional.of(monitoredStock);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        } catch (DataAccessException dataAccessException) {
            throw dataAccessException;
        }
    }
}
