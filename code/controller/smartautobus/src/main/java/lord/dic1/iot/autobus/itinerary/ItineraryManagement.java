package lord.dic1.iot.autobus.itinerary;

import com.google.gson.Gson;
import lord.dic1.iot.autobus.configs.ConfigurationManager;
import lord.dic1.iot.autobus.entities.Itinerary;
import lord.dic1.iot.autobus.simulator.autobus.AutoBusSimulatorImpl;
import lord.dic1.iot.autobus.simulator.autobus.ItineraryGenDatas;
import lord.dic1.iot.autobus.simulator.autobus.PositionGenData;
import lord.dic1.iot.autobus.tools.FileManager;
import lord.dic1.iot.autobus.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ItineraryManagement {

    private final Itinerary itinerary;
    public ItineraryManagement (String itineraryPathKey) {
        try {
            String itineraryPath = FileManager.getInstance().getAbsolutePath() + ConfigurationManager.getInstance().getConfigValue(itineraryPathKey);
            String content = new String(Files.readAllBytes(Path.of(itineraryPath)));
            ItineraryGenDatas itineraryGenDatas = Utils.GSON.fromJson( content, ItineraryGenDatas.class );
            this.itinerary = new Itinerary( itineraryGenDatas.getPoints().stream().map(PositionGenData::getBusPosition).toList() );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}
