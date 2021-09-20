package com.devm8.stockalarms.model;

import java.util.Objects;

public class StockDTO {
    private int stockId;
    private String name;
    private float value;

    private StockDTO(StockDTOBuilder stockDTOBuilder) {
        this.stockId = stockDTOBuilder.stockId;
        this.name = stockDTOBuilder.name;
        this.value = stockDTOBuilder.value;
    }

    StockDTO() {

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
        StockDTO stockDO = (StockDTO) o;
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

    public static class StockDTOBuilder {
        private int stockId;
        private String name;
        private float value;

        public StockDTOBuilder stockId(int stockId) {
            this.stockId = stockId;
            return this;
        }

        public StockDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StockDTOBuilder value(float value) {
            this.value = value;
            return this;
        }

        public StockDTO build() {
            return new StockDTO(this);
        }
    }
}
