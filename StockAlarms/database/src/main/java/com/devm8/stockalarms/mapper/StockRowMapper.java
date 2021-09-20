package com.devm8.stockalarms.mapper;

import com.devm8.stockalarms.model.StockDO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StockRowMapper implements RowMapper<StockDO> {

    @Override
    public StockDO mapRow(ResultSet resultSet, int i) throws SQLException {

        return new StockDO.StockDOBuilder().stockId(resultSet.getInt("stock_id"))
                .name(resultSet.getString("name"))
                .value(resultSet.getFloat("value"))
                .build();
    }
}

