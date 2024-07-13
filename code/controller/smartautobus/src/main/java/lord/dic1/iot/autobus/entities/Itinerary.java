package lord.dic1.iot.autobus.entities;

import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;

import java.util.List;

public class Itinerary {
    private final List<BusPosition> positions;
    public Itinerary(List<BusPosition> positions) {
        this.positions = positions;
    }

    public List<BusPosition> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "positions=" + positions +
                '}';
    }

}
