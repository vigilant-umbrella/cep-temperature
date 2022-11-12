package org.bda.temperature.cep.monitoring.events;

public class MonitoringEvent {
    public double temp;

    public MonitoringEvent(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Temperature is " + temp;
    }

    public double getTemp() {
        return temp;
    }
}
