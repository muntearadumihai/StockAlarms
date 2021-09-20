package com.devm8.stockalarms.mapper;

import com.devm8.stockalarms.model.AlarmType;
import com.devm8.stockalarms.model.MonitoredStockDO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MonitoredStockRowMapper implements RowMapper<MonitoredStockDO> {

    @Override
    public MonitoredStockDO mapRow(ResultSet resultSet, int i) throws SQLException {

        return new MonitoredStockDO.MonitorStockDOBuilder().monitoredStockId(resultSet.getInt("monitored_stock_id"))
                .stockId(resultSet.getInt("stock_id"))
                .userId(resultSet.getInt("user_id"))
                .startAlarm(resultSet.getFloat("start_alarm"))
                .alarmType(AlarmType.of(resultSet.getInt("alarm_type")))
                .percentage(resultSet.getInt("percentage"))
                .build();
    }
}

