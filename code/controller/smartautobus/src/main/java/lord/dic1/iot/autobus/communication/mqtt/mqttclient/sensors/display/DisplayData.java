package lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.display;

import com.google.gson.Gson;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.SensorData;

public class DisplayData implements SensorData {
    private static final Gson gson = new Gson();
    private final String id;
    private final String message;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public static SensorData jsonParse(String jsonString) {
        return DisplayData.gson.fromJson(jsonString, DisplayData.class);
    }

    public DisplayData(String id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toJson() {
        return DisplayData.gson.toJson(this);
    }

    @Override
    public String toString() {
        return "DisplayData{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}