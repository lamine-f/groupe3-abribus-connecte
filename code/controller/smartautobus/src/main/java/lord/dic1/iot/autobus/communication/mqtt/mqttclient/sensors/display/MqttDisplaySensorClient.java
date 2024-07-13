package lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.display;

import lord.dic1.iot.autobus.communication.mqtt.mqttclient.Message;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClientImpl;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import org.eclipse.paho.client.mqttv3.*;

public class MqttDisplaySensorClient extends MqttGlobalClientImpl {

//    private BusService busService = new BusServiceImpl();
    public MqttDisplaySensorClient(String broker, String clientId) throws MqttException {
        super(broker, clientId);
    }

    protected void onMessageReceived(String topic, MqttMessage message) {
        Message response = Message.jsonParser(
                MqttClientType.DISPLAY,
                new String(message.getPayload())
        );

        System.out.println("cc");
//        DisplayData displayData = (DisplayData) response.getValue();
//        System.out.println(displayData);
//        busService.notify(positionSensorData);

    }

}
