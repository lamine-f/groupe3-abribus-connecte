package lord.dic1.iot.autobus.communication.mqtt.datas;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;

public class BusPosition implements Data {
    private static final Gson gson = new Gson();
    public static Data jsonParse(String jsonString) {
        return BusPosition.gson.fromJson(jsonString, BusPosition.class);
    }

    private String longitude;
    private String latitude;

    public BusPosition(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toJson() {
        return BusPosition.gson.toJson(
                Map.of(
                        "longitude", this.longitude,
                        "latitude", this.latitude
                )
        );
    }

    @Override
    public String toString() {
        return "Position{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BusPosition that = (BusPosition) object;
        return Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

}
