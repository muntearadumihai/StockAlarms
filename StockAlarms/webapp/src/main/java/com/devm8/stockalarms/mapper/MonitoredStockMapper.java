package com.devm8.stockalarms.mapper;

import com.devm8.stockalarms.model.MonitoredStockDO;
import com.devm8.stockalarms.model.MonitoredStockDTO;

public class MonitoredStockMapper {

    public static MonitoredStockDO toDO(MonitoredStockDTO monitoredStockDTO) {
        return new MonitoredStockDO.MonitorStockDOBuilder().monitoredStockId(monitoredStockDTO.getMonitoredStockId())
                .stockId(monitoredStockDTO.getStockId())
                .userId(monitoredStockDTO.getUserId())
                .startAlarm(monitoredStockDTO.getStartAlarm())
                .alarmType(monitoredStockDTO.getAlarmType())
                .percentage(monitoredStockDTO.getPercentage())
                .build();
    }

    public static MonitoredStockDTO toDTO(MonitoredStockDO monitoredStockDO) {
        return new MonitoredStockDTO.MonitorStockDTOBuilder().monitoredStockId(monitoredStockDO.getMonitoredStockId())
                .stockId(monitoredStockDO.getStockId())
                .userId(monitoredStockDO.getUserId())
                .startAlarm(monitoredStockDO.getStartAlarm())
                .alarmType(monitoredStockDO.getAlarmType())
                .percentage(monitoredStockDO.getPercentage())
                .build();
    }
}
