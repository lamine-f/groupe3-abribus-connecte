package lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.position;

import com.google.gson.Gson;
import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.SensorData;

public class PositionSensorData implements SensorData {
    private static final Gson gson = new Gson();
    private final String id;
    private final BusPosition busPosition;

    public String getId() {
        return id;
    }

    public BusPosition getBusPosition() {
        return busPosition;
    }

    public static SensorData jsonParse(String jsonString) {
        return PositionSensorData.gson.fromJson(jsonString, PositionSensorData.class);
    }

    public PositionSensorData(String id, BusPosition busPosition) {
        this.id = id;
        this.busPosition = busPosition;
    }


    @Override
    public String toJson() {
        return PositionSensorData.gson.toJson(this);
    }

    @Override
    public String toString() {
        return "PositionSensorData{" +
                "id='" + id + '\'' +
                ", position=" + busPosition +
                '}';
    }
}
