package com.devm8.stockalarms.service;

import com.devm8.stockalarms.model.MonitoredStockDTO;

public interface MonitoredStockService {

    MonitoredStockDTO createMonitoredStock(MonitoredStockDTO monitoredStockDTO);

    MonitoredStockDTO getMonitoredStockById(int monitoredStockId);

    void deleteMonitoredStock(int id);
}
