package com.devm8.stockalarms.model;

import java.util.Objects;

public class MonitoredStockDO {
    private int monitoredStockId;
    private int stockId;
    private int userId;
    private float startAlarm;
    private AlarmType alarmType;
    private int percentage;

    private MonitoredStockDO(MonitorStockDOBuilder monitorStockDOBuilder) {
        this.monitoredStockId = monitorStockDOBuilder.monitoredStockId;
        this.stockId = monitorStockDOBuilder.stockId;
        this.userId = monitorStockDOBuilder.userId;
        this.startAlarm = monitorStockDOBuilder.startAlarm;
        this.alarmType = monitorStockDOBuilder.alarmType;
        this.percentage = monitorStockDOBuilder.percentage;
    }

    public MonitoredStockDO() {
    }

    public int getMonitoredStockId() {
        return monitoredStockId;
    }

    public void setMonitoredStockId(int monitoredStockId) {
        this.monitoredStockId = monitoredStockId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getStartAlarm() {
        return startAlarm;
    }

    public void setStartAlarm(float startAlarm) {
        this.startAlarm = startAlarm;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoredStockDO that = (MonitoredStockDO) o;
        return monitoredStockId == that.monitoredStockId && stockId == that.stockId && userId == that.userId && Float.compare(that.startAlarm, startAlarm) == 0 && percentage == that.percentage && alarmType == that.alarmType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitoredStockId, stockId, userId, startAlarm, alarmType, percentage);
    }

    @Override
    public String toString() {
        return "MonitoredStockDO{" +
                "monitoredStockId=" + monitoredStockId +
                ", stockId=" + stockId +
                ", userId=" + userId +
                ", start_alarm=" + startAlarm +
                ", alarmType=" + alarmType +
                ", percentage=" + percentage +
                '}';
    }
    public static class MonitorStockDOBuilder {
        private int monitoredStockId;
        private int stockId;
        private int userId;
        private float startAlarm;
        private AlarmType alarmType;
        private int percentage;

        public MonitorStockDOBuilder monitoredStockId(int monitoredStockId) {
            this.monitoredStockId = monitoredStockId;
            return this;
        }

        public MonitorStockDOBuilder stockId(int stockId) {
            this.stockId = stockId;
            return this;
        }
        public MonitorStockDOBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }
        public MonitorStockDOBuilder startAlarm(float start_alarm) {
            this.startAlarm = start_alarm;
            return this;
        }
        public MonitorStockDOBuilder alarmType(AlarmType alarmType) {
            this.alarmType = alarmType;
            return this;
        }
        public MonitorStockDOBuilder percentage(int percentage) {
            this.percentage = percentage;
            return this;
        }

        public MonitoredStockDO build() {
            return new MonitoredStockDO(this);
        }
    }
}
