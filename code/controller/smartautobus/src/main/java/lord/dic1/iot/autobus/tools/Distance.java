package lord.dic1.iot.autobus.tools;

import lord.dic1.iot.autobus.entities.Itinerary;
import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;

import java.util.List;

public class Distance {
    public static int parseDistanceToTime () {
        return 0;
    }

    public static int normalise (int distance, Itinerary itinerary) {
        if (distance < 0) return -1*distance;
        return itinerary.getPositions().size() - distance;
    }

    public static int getDistance ( BusPosition busPosition, BusPosition busStopPosition, Itinerary itinerary ) {
        return itinerary.getPositions().indexOf(busPosition) - itinerary.getPositions().indexOf(busStopPosition);
    }

    public static Double getHaversineDistance ( BusPosition busPosition, BusPosition busStopPosition, Itinerary itinerary ) {
        List<BusPosition> positions = itinerary.getPositions();
        double distance = 0.0;

        int ecart = getDistance ( busPosition, busStopPosition, itinerary );
//        System.err.println(ecart);
        if (ecart <= 0) {
            boolean running = false;
            for ( int i=0; i<positions.size(); i++ ) {
                if (busPosition.equals(positions.get(i))) running = true;
                if (running) {
                    while ( !busStopPosition.equals(positions.get(i)) ) {
                        distance += Haversine.calculateDistance(
                                Double.parseDouble( positions.get(i).getLatitude() ),
                                Double.parseDouble( positions.get(i).getLongitude() ),
                                Double.parseDouble( positions.get(i+1).getLatitude() ),
                                Double.parseDouble( positions.get(i+1).getLongitude() )
                            );
                        i++;
                    }
                }
                running = false;
            }

        }else {
//            boolean running = false;
//            double back = 0.0;
//            for ( int i=0; i<positions.size(); i++ ) {
//                if ( positions.get(i).equals(busStopPosition) ) running = true;
//                if ( running && i+1 < positions.size() ) {
//                    back += Haversine.calculateDistance(
//                            Double.parseDouble( positions.get(i).getLatitude() ),
//                            Double.parseDouble( positions.get(i).getLongitude() ),
//                            Double.parseDouble( positions.get(i+1).getLatitude() ),
//                            Double.parseDouble( positions.get(i+1).getLongitude() )
//                    );
//                    i++;
//                }
//            }
//
//            double restant = 0.0;
//            running = false;
//
//
//
//            for ( int i=0; i<positions.size(); i++ ) {
//                if ( positions.get(i).equals(busPosition) ) running = true;
//                if (running && i+1 < positions.size() ) {
////                    System.out.println("OK");
//                    restant += Haversine.calculateDistance(
//                            Double.parseDouble( positions.get(i).getLatitude() ),
//                            Double.parseDouble( positions.get(i).getLongitude() ),
//                            Double.parseDouble( positions.get(i+1).getLatitude() ),
//                            Double.parseDouble( positions.get(i+1).getLongitude() )
//                    );
//                    i++;
//                }
//            }
//
//            distance = restant;

        }

        System.err.println(busPosition + " => " + distance );


        return distance;
    }

}
