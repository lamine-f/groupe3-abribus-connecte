package lord.dic1.iot.autobus.entities;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<Bus> busList = new ArrayList<>();
    private final List<BusStop> busStopList = new ArrayList<>();
    private String lineNumber;

    public List<Bus> getBusList() {
        return busList;
    }

    public void addBus(Bus bus) {
        this.busList.add(bus);
    }

    public List<BusStop> getBusstopList() {
        return busStopList;
    }

    public void addBusStop(BusStop busstop) {
        this.busStopList.add(busstop);
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Line (String lineNumber) {
        this.lineNumber = lineNumber;

    }
}
