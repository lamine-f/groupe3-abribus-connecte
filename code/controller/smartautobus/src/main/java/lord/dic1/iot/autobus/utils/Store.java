package lord.dic1.iot.autobus.utils;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.BusStop;
import lord.dic1.iot.autobus.entities.Line;
import lord.dic1.iot.autobus.services.BusService;
import lord.dic1.iot.autobus.simulator.autobus.AutoBusSimulator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static List<Bus> buses = new ArrayList<>();
    public static List<BusStop> busStops = new ArrayList<>();
    public static List<Line> lines = new ArrayList<>();

    public static Bus findBus(Bus busP) {
        Bus busR = buses.stream().filter(bus -> busP.getId() == bus.getId()).toList().get(0);
        System.err.println(busR);
        return null;
    }
    public static Bus findBusById(int id) {
        for (Bus bus: buses) {
            if (bus.getId() == id) return bus;
        }
        return null;
//        return buses.stream().filter(bus -> id == bus.getId()).toList().get(0);
    }

    public static Line findLineByBus(Bus bus) {
        for (Line line: lines) {
            for (Bus busL: line.getBusList()) {
                if (busL.getId() == bus.getId()) return line;
            }

        }
        return null;
//         Line line1 = lines.stream().filter(
//                line -> line.getBusList().stream().filter(busL -> bus.getId() == busL.getId() ).toList().get(0) != null
//        ).toList().get(0);

    }

}
