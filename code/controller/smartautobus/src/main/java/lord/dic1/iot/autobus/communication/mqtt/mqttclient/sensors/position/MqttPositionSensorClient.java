package lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.position;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.Message;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClientImpl;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import lord.dic1.iot.autobus.services.BusService;
import lord.dic1.iot.autobus.services.impl.BusServiceImpl;
import lord.dic1.iot.autobus.utils.Store;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPositionSensorClient extends MqttGlobalClientImpl {

    private final BusService busService;

    public MqttPositionSensorClient(String broker, String clientId, String itineraryPathKey) throws MqttException {
        super(broker, clientId);
        this.busService = new BusServiceImpl(itineraryPathKey);

    }

    protected void onMessageReceived(String topic, MqttMessage message) {
        Message response = Message.jsonParser(
                MqttClientType.POSITION_SENSOR,
                new String(message.getPayload())
        );

        PositionSensorData positionSensorData = (PositionSensorData) response.getValue();
        Bus bus = Store.findBusById( Integer.parseInt(positionSensorData.getId()) );
        this.busService.notify(positionSensorData, bus);

    }

}
