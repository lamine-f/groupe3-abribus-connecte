package lord.dic1.iot.autobus.simulator.autobus;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.Itinerary;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface AutoBusSimulator {

    public void start ();

    public void run () throws MqttException;
}