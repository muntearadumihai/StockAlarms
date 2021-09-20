package com.devm8.stockalarms.model;

import java.util.Objects;

public class StockDO {
    private int stockId;
    private String name;
    private float value;

    private StockDO(StockDOBuilder stockDOBuilder) {
        this.stockId = stockDOBuilder.stockId;
        this.name = stockDOBuilder.name;
        this.value = stockDOBuilder.value;
    }

    StockDO() {

    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockDO stockDO = (StockDO) o;
        return stockId == stockDO.stockId && Float.compare(stockDO.value, value) == 0 && Objects.equals(name, stockDO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, name, value);
    }

    @Override
    public String toString() {
        return "StockDO{" +
                "stockId=" + stockId +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public static class StockDOBuilder {
        private int stockId;
        private String name;
        private float value;

        public StockDO.StockDOBuilder stockId(int stockId) {
            this.stockId = stockId;
            return this;
        }

        public StockDO.StockDOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StockDO.StockDOBuilder value(float value) {
            this.value = value;
            return this;
        }

        public StockDO build() {
            return new StockDO(this);
        }
    }
}
