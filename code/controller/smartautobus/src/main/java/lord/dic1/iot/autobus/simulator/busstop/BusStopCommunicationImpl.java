package lord.dic1.iot.autobus.simulator.busstop;

import lord.dic1.iot.autobus.configs.MqttConfigurationManager;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import org.eclipse.paho.client.mqttv3.MqttException;

public class BusStopCommunicationImpl implements BusStopCommunication {
    private final String topic;
    private final MqttGlobalClient mqttClient;

    public BusStopCommunicationImpl (String id, String topicKey, String itineraryPathKey) {

        MqttConfigurationManager mqttConfigurationManager = MqttConfigurationManager.getInstance();
        this.topic = mqttConfigurationManager.getConfigValue(topicKey);

        try {
            this.mqttClient = MqttGlobalClient.build(
                    MqttClientType.POSITION_SENSOR,
                    mqttConfigurationManager.getConfigValue("broker"),
                    mqttConfigurationManager.getConfigValue("clientId") + id,
                    itineraryPathKey

            );

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    this.mqttClient.disconnect();
                } catch (MqttException e) {
                    System.err.println(e.getMessage());
                }
            }));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public MqttGlobalClient getMqttClient() {
        return this.mqttClient;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }
}
