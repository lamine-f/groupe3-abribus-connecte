import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.BusStop;
import lord.dic1.iot.autobus.entities.Line;
import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;
import lord.dic1.iot.autobus.simulator.autobus.AutoBusSimulatorImpl;
import lord.dic1.iot.autobus.simulator.autobus.AutoBusSimulator;
import lord.dic1.iot.autobus.simulator.busstop.BusStopCommunicationImpl;
import lord.dic1.iot.autobus.utils.Store;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
    public static void main(String[] args) throws MqttException {
        Bus bus1A = new Bus(1, "71");
        Bus bus1B = new Bus(2, "71");
        Bus bus2  = new Bus(3, "72");
        Store.buses.add(bus1A);
        Store.buses.add(bus1B);
        Store.buses.add(bus2);
        Line line1 = new Line("71");
        Line line2 = new Line("72");
        Line line3 = new Line("73");
        BusStop busStop1 = new BusStop(1, new BusPosition("-17.25116", "14.81079"), new BusStopCommunicationImpl("BUS-STOP-1", "busStop1DataTransferTopic", "testItinerary1FilePath") );
//        BusStop busStop1A = new BusStop(1, new BusPosition("-17.24788", "14.84027"), new BusStopCommunicationImpl("BUS-STOP-1A", "busStop1ADataTransferTopic", "testItinerary1FilePath") );
        BusStop busStop2 = new BusStop(2, new BusPosition("-17.25116", "14.81079"), new BusStopCommunicationImpl("BUS-STOP-2", "busStop2DataTransferTopic", "testItinerary1FilePath") );
        Store.busStops.add(busStop1);
        line1.addBus(bus1A);
        line1.addBus(bus1B);
        line1.addBusStop(busStop1);
        Store.lines.add(line1);
        AutoBusSimulator autoBus1ASimulator = new AutoBusSimulatorImpl(
                "autobus1ADataTransferTopic",
                "BUS-1A",
                "testItinerary1FilePath",
                bus1A
        );
        AutoBusSimulator autoBus1BSimulator = new AutoBusSimulatorImpl(
                "autobus1BDataTransferTopic",
                "BUS-1B",
                "testItinerary1FilePath",
                bus1B
        );
        autoBus1ASimulator.run();
        autoBus1BSimulator.run();
    }
}