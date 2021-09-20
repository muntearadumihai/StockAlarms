package com.devm8.stockalarms.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AlarmType {
    UP(1),
    DOWN(2);

    private int alarmTypeId;

    AlarmType(int alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    private static final Map<Integer, AlarmType> fromIntMap = Stream.of(values())
            .collect(Collectors.toMap(AlarmType::asInt, Function.identity()));

    public int asInt() {
        return alarmTypeId;
    }

    public static AlarmType of(int alarmTypeId) {
        return fromIntMap.get(alarmTypeId);
    }
}
