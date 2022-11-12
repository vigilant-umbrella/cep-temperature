package org.bda.temperature.cep.monitoring.events;

public class InsideAlert {
    private double temp;

    public InsideAlert(double temp) {
        this.temp = temp;
    }

    public InsideAlert() {
        this(-1);
    }

    public double getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "Inside the room";
    }
}
