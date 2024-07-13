package lord.dic1.iot.autobus.entities;

import java.util.Date;

public class NextBusInfo {
    private Date date;
    private Bus bus;
    private int distance;

    public NextBusInfo (Bus bus, int distance) {
        this.bus = bus;
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
