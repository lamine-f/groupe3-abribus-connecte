package lord.dic1.iot.autobus.simulator.autobus;

import java.util.List;

public class ItineraryGenDatas {
    private List<PositionGenData> points;
    public List<PositionGenData> getPoints () {
        return points;
    }

    @Override
    public String toString() {
        return "ItineraryDatas{" +
                "points=" + points +
                '}';
    }

}
