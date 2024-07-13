package lord.dic1.iot.autobus.services.impl;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.BusStop;
import lord.dic1.iot.autobus.entities.NextBusInfo;
import lord.dic1.iot.autobus.itinerary.ItineraryManagement;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.Message;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.sensors.display.DisplayData;
import lord.dic1.iot.autobus.services.BusStopService;
import lord.dic1.iot.autobus.tools.Distance;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BusStopServiceImpl implements BusStopService {

    private final ItineraryManagement itineraryManagement;
    public BusStopServiceImpl (String itineraryPathKey) {
        this.itineraryManagement = new ItineraryManagement(itineraryPathKey);
    }

    @Override
    public void update(BusStop busstop, Bus bus) throws MqttException {
        //update next bus
        NextBusInfo newNextBusInfo;
        int distance = Distance.getDistance(bus.getPosition(), busstop.getPosition(), this.itineraryManagement.getItinerary() );

        if ( busstop.getNextBus() == null  ) {
            newNextBusInfo = new NextBusInfo(bus, distance);
        }else {
            if ( distance >= busstop.getNextBus().getDistance()  ) {
                newNextBusInfo = new NextBusInfo(bus, distance);
            }else {
                newNextBusInfo = busstop.getNextBus();
            }
        }
        busstop.setNextBus(newNextBusInfo);

        //send message
        this.notify(busstop, bus);

    }

    @Override
    public void notify(BusStop busstop, Bus bus) throws MqttException {
//        System.out.println(busstop.getNextBus().getDistance());
        Double distanceTest = Distance.getHaversineDistance(bus.getPosition(), busstop.getPosition(), this.itineraryManagement.getItinerary() );

        BigDecimal bd = new BigDecimal(Double.toString(distanceTest));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double rounded = bd.doubleValue();

//        System.err.println(distanceTest);

        Message message = new Message(
                MqttClientType.DISPLAY,
                new DisplayData(
                        String.valueOf(busstop.getId()),
                        String.valueOf( rounded )
                )
        );
        MqttGlobalClient mqttClient = busstop.getSimulator().getMqttClient();
        String topic = busstop.getSimulator().getTopic();
        mqttClient.publish(topic, message);
    }


}
