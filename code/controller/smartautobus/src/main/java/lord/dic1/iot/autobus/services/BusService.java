package lord.dic1.iot.autobus.services;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.position.PositionSensorData;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface BusService {
    void sharePosition(MqttGlobalClient mqttClient, String topic,  Bus bus) throws MqttException;
    void notify(PositionSensorData positionSensorData, Bus bus);

}
