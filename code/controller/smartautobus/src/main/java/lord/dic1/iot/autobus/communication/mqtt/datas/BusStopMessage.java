package lord.dic1.iot.autobus.communication.mqtt.datas;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;

public class BusStopMessage implements Data {
    private static final Gson gson = new Gson();
    public static Data jsonParse(String jsonString) {
        return BusStopMessage.gson.fromJson(jsonString, BusStopMessage.class);
    }

    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public BusStopMessage(String message) {
        this.message = message;
    }

    @Override
    public String toJson() {
        return BusStopMessage.gson.toJson(
                Map.of(
                        "message", this.message
                )
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BusStopMessage that = (BusStopMessage) object;
        return Objects.equals(message, that.message) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

}
