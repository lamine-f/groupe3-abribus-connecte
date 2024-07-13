package lord.dic1.iot.autobus.simulator.busstop;

import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;

public interface BusStopCommunication {
    MqttGlobalClient getMqttClient ();
    String getTopic();
}
