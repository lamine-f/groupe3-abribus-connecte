package lord.dic1.iot.autobus.simulator.autobus;

import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;

import java.util.List;

public class PositionGenData {
    private String addr;
    private String name;
    private List<String> details;
    private Double lat;
    private Double lng;
    @Override
    public String toString() {
        return "ItineraryData{" +
                "addr='" + addr + '\'' +
                ", name='" + name + '\'' +
                ", details=" + details +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public BusPosition getBusPosition () {
        return new BusPosition(
                String.valueOf(this.lng),
                String.valueOf(this.lat)
        );
    }
}

