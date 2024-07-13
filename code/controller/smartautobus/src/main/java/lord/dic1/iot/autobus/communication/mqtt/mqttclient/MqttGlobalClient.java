package lord.dic1.iot.autobus.communication.mqtt.mqttclient;

import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.display.MqttDisplaySensorClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.position.MqttPositionSensorClient;
import org.eclipse.paho.client.mqttv3.MqttException;


public abstract class MqttGlobalClient {

    public abstract void publish(String topic, Message message) throws MqttException;
    public abstract void subscribe(String topic) throws MqttException;

    public abstract void disconnect() throws MqttException;

    public static MqttGlobalClient build (MqttClientType clientType, String broker, String clientId, String itineraryPathKey) throws MqttException {
        switch (clientType) {
            case POSITION_SENSOR:
                return new MqttPositionSensorClient(broker, clientId, itineraryPathKey);
            case DISPLAY:
                return new MqttDisplaySensorClient(broker, clientId);
            default:
                throw new IllegalStateException("Unexpected value: " + clientType);
        }

    }

//    protected void onMessageReceived(String topic, MqttMessage message);


}
