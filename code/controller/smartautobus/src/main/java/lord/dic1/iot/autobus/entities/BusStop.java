package lord.dic1.iot.autobus.entities;

import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;
import lord.dic1.iot.autobus.simulator.busstop.BusStopCommunication;

public class BusStop {
    private final int id;
    private final BusPosition position;
    private NextBusInfo nextBusInfo;
    private final BusStopCommunication communication;

    public NextBusInfo getNextBusInfo() {
        return nextBusInfo;
    }

    public BusStopCommunication getSimulator() {
        return communication;
    }

    public NextBusInfo getNextBus() {
        return nextBusInfo;
    }

    public void setNextBus(NextBusInfo nextBus) {
        this.nextBusInfo = nextBus;
    }

    public BusPosition getPosition() {
        return position;
    }

    public int getId() {
        return this.id;
    }

    public BusStop(int id, BusPosition position, BusStopCommunication communication ) {
        this.id = id;
        this.position = position;
        this.communication = communication ;
    }
}
