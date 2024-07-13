package lord.dic1.iot.autobus.services;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.BusStop;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface BusStopService {
    void update(BusStop busstop, Bus bus) throws MqttException;
    void notify(BusStop busstop, Bus bus) throws MqttException;

}
