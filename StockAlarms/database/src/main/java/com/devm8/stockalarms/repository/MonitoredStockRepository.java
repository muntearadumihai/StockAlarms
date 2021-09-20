package com.devm8.stockalarms.repository;

import com.devm8.stockalarms.model.MonitoredStockDO;

import java.util.Optional;

public interface MonitoredStockRepository {

    MonitoredStockDO createMonitoredStock(MonitoredStockDO monitoredStockDO);

    Optional<MonitoredStockDO> getMonitoredStockById(int monitoredStockId);

    void deleteMonitoredStock(int id);

    Optional<MonitoredStockDO> getMonitoredStocksByUserIdAndStockId(int userId, int stockId);
}
