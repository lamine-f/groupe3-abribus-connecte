package lord.dic1.iot.autobus.simulator.autobus;

import lord.dic1.iot.autobus.entities.Bus;
import lord.dic1.iot.autobus.entities.Itinerary;
import lord.dic1.iot.autobus.configs.MqttConfigurationManager;
import lord.dic1.iot.autobus.itinerary.ItineraryManagement;
import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.MqttGlobalClient;
import lord.dic1.iot.autobus.communication.mqtt.mqttclient.enums.MqttClientType;
import lord.dic1.iot.autobus.services.BusService;
import lord.dic1.iot.autobus.services.impl.BusServiceImpl;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AutoBusSimulatorImpl implements AutoBusSimulator {
    private final BusService busService;
    private final MqttGlobalClient autobusMqttClient;
    private final String autobusDataTransferTopic;
    private final Itinerary itinerary;
    private final Bus bus;
    private final ExecutorService executorService;

    public AutoBusSimulatorImpl(String topicKey, String id, String itineraryPathKey, Bus bus) throws MqttException {
        MqttConfigurationManager mqttConfigurationManager = MqttConfigurationManager.getInstance();
        this.autobusDataTransferTopic = mqttConfigurationManager.getConfigValue(topicKey);

        this.autobusMqttClient = MqttGlobalClient.build(
                MqttClientType.POSITION_SENSOR,
                mqttConfigurationManager.getConfigValue("broker"),
                mqttConfigurationManager.getConfigValue("clientId") + id,
                itineraryPathKey
        );

        this.busService = new BusServiceImpl(itineraryPathKey);
        this.bus = bus;

        ItineraryManagement itineraryManagement = new ItineraryManagement(itineraryPathKey);
        this.itinerary = itineraryManagement.getItinerary();

        this.executorService = Executors.newFixedThreadPool(4); // You can adjust the number of threads as needed

    }

    @Override
    public void run() throws MqttException {
        this.autobusMqttClient.subscribe(this.autobusDataTransferTopic);
        this.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                autobusMqttClient.disconnect();
                executorService.shutdownNow();
            } catch (MqttException e) {
                System.err.println(e.getMessage());
            }
        }));
    }

    @Override
    public void start() {
        for (int i = 0; i < 4; i++) { // Create 4 tasks for demonstration
            executorService.submit(new BusTask());
        }
    }

    private class BusTask implements Runnable {
        @Override
        public void run() {
            int i = 0;
//            while (i < 100000) {
                int count = 0;
//                System.err.println(itinerary.getPositions().size()*500/(60*60));
                for (BusPosition busPosition : itinerary.getPositions()) {
                    bus.setPosition(busPosition);
                    try {

                        Thread.sleep(1000);

//                        count++;
//                        if (count == 50) {

                            busService.sharePosition(
                                    autobusMqttClient,
                                    autobusDataTransferTopic,
                                    bus
                            );
//                            count = 0;
//                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
//                i++;
//            }
        }
    }
}
