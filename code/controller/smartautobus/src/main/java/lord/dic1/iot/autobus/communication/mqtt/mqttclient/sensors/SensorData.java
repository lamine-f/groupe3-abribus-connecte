package lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors;


import java.io.Serializable;

public interface SensorData extends Serializable {
    String toJson ();
}
