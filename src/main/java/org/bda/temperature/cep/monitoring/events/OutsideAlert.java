package org.bda.temperature.cep.monitoring.events;

public class OutsideAlert {
    private double temp;

    public OutsideAlert(double temp) {
        this.temp = temp;
    }

    public OutsideAlert() {
        this(-1);
    }

    public double getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "Outside the room";
    }
}
