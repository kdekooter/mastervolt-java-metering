package org.boplicity.mv.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class MasterVoltMeasurement implements Serializable {

    private BigDecimal powerGrid;
    private BigDecimal powerSolar;
    private BigDecimal voltageSolar;
    private BigDecimal voltageGrid;
    private BigDecimal currentSolar;
    private BigDecimal currentGrid;
    private BigDecimal inverterTemperature;
    private BigDecimal totalProductionKwh;
    private BigDecimal dailyProductionKwh;
    private Integer totalRuntime;
    private Integer dailyRuntime;
    private DateTime timeStamp;

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getDailyRuntime() {
        return dailyRuntime;
    }

    public void setDailyRuntime(Integer dailyRuntime) {
        this.dailyRuntime = dailyRuntime;
    }

    public BigDecimal getDailyProductionKwh() {
        return dailyProductionKwh;
    }

    public void setDailyProductionKwh(BigDecimal dailyProductionKwh) {
        this.dailyProductionKwh = dailyProductionKwh;
    }

    public Integer getTotalRuntime() {
        return totalRuntime;
    }

    public void setTotalRuntime(Integer totalRuntime) {
        this.totalRuntime = totalRuntime;
    }

    public BigDecimal getPowerGrid() {
        return powerGrid;
    }

    public void setPowerGrid(BigDecimal powerGrid) {
        this.powerGrid = powerGrid;
    }

    public BigDecimal getCurrentGrid() {
        return currentGrid;
    }

    public void setCurrentGrid(BigDecimal currentGrid) {
        this.currentGrid = currentGrid;
    }

    public BigDecimal getCurrentSolar() {
        return currentSolar;
    }

    public void setCurrentSolar(BigDecimal currentSolar) {
        this.currentSolar = currentSolar;
    }

    public BigDecimal getInverterTemperature() {
        return inverterTemperature;
    }

    public void setInverterTemperature(BigDecimal inverterTemperature) {
        this.inverterTemperature = inverterTemperature;
    }

    public BigDecimal getPowerSolar() {
        return powerSolar;
    }

    public void setPowerSolar(BigDecimal powerSolar) {
        this.powerSolar = powerSolar;
    }

    public BigDecimal getTotalProductionKwh() {
        return totalProductionKwh;
    }

    public void setTotalProductionKwh(BigDecimal totalProductionKwh) {
        this.totalProductionKwh = totalProductionKwh;
    }

    public BigDecimal getVoltageGrid() {
        return voltageGrid;
    }

    public void setVoltageGrid(BigDecimal voltageGrid) {
        this.voltageGrid = voltageGrid;
    }

    public BigDecimal getVoltageSolar() {
        return voltageSolar;
    }

    public void setVoltageSolar(BigDecimal voltageSolar) {
        this.voltageSolar = voltageSolar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MasterVoltMeasurement that = (MasterVoltMeasurement) o;

        if (currentGrid != null ? !currentGrid.equals(that.currentGrid) : that.currentGrid != null) return false;
        if (currentSolar != null ? !currentSolar.equals(that.currentSolar) : that.currentSolar != null) return false;
        if (inverterTemperature != null ? !inverterTemperature.equals(that.inverterTemperature) : that.inverterTemperature != null)
            return false;
        if (powerGrid != null ? !powerGrid.equals(that.powerGrid) : that.powerGrid != null) return false;
        if (powerSolar != null ? !powerSolar.equals(that.powerSolar) : that.powerSolar != null) return false;
        if (totalProductionKwh != null ? !totalProductionKwh.equals(that.totalProductionKwh) : that.totalProductionKwh != null)
            return false;
        if (voltageGrid != null ? !voltageGrid.equals(that.voltageGrid) : that.voltageGrid != null) return false;
        if (voltageSolar != null ? !voltageSolar.equals(that.voltageSolar) : that.voltageSolar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = powerGrid != null ? powerGrid.hashCode() : 0;
        result = 31 * result + (powerSolar != null ? powerSolar.hashCode() : 0);
        result = 31 * result + (voltageSolar != null ? voltageSolar.hashCode() : 0);
        result = 31 * result + (voltageGrid != null ? voltageGrid.hashCode() : 0);
        result = 31 * result + (currentSolar != null ? currentSolar.hashCode() : 0);
        result = 31 * result + (currentGrid != null ? currentGrid.hashCode() : 0);
        result = 31 * result + (inverterTemperature != null ? inverterTemperature.hashCode() : 0);
        result = 31 * result + (totalProductionKwh != null ? totalProductionKwh.hashCode() : 0);
        return result;
    }
}
