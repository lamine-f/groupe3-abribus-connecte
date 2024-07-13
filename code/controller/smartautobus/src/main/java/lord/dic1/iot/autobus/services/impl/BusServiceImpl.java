package lord.dic1.iot.autobus.services.impl;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.Line;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.Message;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.position.PositionSensorData;
import lord.dic1.iot.autobus.services.BusService;
import lord.dic1.iot.autobus.services.BusStopService;
import lord.dic1.iot.autobus.utils.Store;
import org.eclipse.paho.client.mqttv3.MqttException;

public class BusServiceImpl implements BusService {

    private final BusStopService busStopService;

    public BusServiceImpl (String itineraryPathKey) {
        this.busStopService = new BusStopServiceImpl(itineraryPathKey);
    }

    @Override
    public void sharePosition(MqttGlobalClient mqttClient, String topic, Bus bus) throws MqttException {

        Message message = new Message(
                MqttClientType.POSITION_SENSOR,
                new PositionSensorData(
                        String.valueOf(bus.getId()),
                        bus.getPosition()
                )
        );
        mqttClient.publish(topic, message);

    }

    @Override
    public void notify(PositionSensorData positionSensorData, Bus bus) {
        bus.setPosition( positionSensorData.getBusPosition() );

        // get line
        Line line = Store.findLineByBus( bus );
        if (line == null) throw new RuntimeException("No line for bus "+bus.getId());
        line.getBusstopList().forEach(busstop -> {
            try {
                this.busStopService.update(busstop, bus);
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
