package com.devm8.stockalarms.service;

import com.devm8.stockalarms.exception.CreationException;
import com.devm8.stockalarms.exception.NotFoundException;
import com.devm8.stockalarms.model.MonitoredStockDO;
import com.devm8.stockalarms.model.MonitoredStockDTO;
import com.devm8.stockalarms.repository.MonitoredStockRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.devm8.stockalarms.mapper.MonitoredStockMapper.toDO;
import static com.devm8.stockalarms.mapper.MonitoredStockMapper.toDTO;

@Service
public class MonitoredStockServiceImpl implements MonitoredStockService {

    private static final String MONITORED_STOCK_NOT_FOUND_MESSAGE = "This monitored stock does not exist!";
    private static final String STOCK_ALREADY_MONITORED_MESSAGE = "This monitored is already monitored!!";

    @Autowired
    private MonitoredStockRepositoryImpl monitoredStockRepository;

    @Override
    public MonitoredStockDTO createMonitoredStock(MonitoredStockDTO monitoredStockDTO) {
        if (checkIfStockAlreadyMonitored(monitoredStockDTO)) {
            throw new CreationException(STOCK_ALREADY_MONITORED_MESSAGE);
        }
        return toDTO(monitoredStockRepository.createMonitoredStock(toDO(monitoredStockDTO)));
    }

    @Override
    public MonitoredStockDTO getMonitoredStockById(int monitoredStockId) {
        Optional<MonitoredStockDO> monitoredStockDO = monitoredStockRepository.getMonitoredStockById(monitoredStockId);
        if (monitoredStockDO.isEmpty()) {
            throw new NotFoundException(MONITORED_STOCK_NOT_FOUND_MESSAGE);
        }
        return toDTO(monitoredStockDO.get());
    }

    @Override
    public void deleteMonitoredStock(int id) {
        monitoredStockRepository.deleteMonitoredStock(id);
    }

    private boolean checkIfStockAlreadyMonitored(MonitoredStockDTO monitoredStockDTO) {
        return monitoredStockRepository.getMonitoredStocksByUserIdAndStockId(monitoredStockDTO.getUserId(),
                monitoredStockDTO.getStockId()).isPresent();
    }
}
