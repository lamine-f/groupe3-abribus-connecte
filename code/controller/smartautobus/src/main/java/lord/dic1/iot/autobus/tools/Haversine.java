package lord.dic1.iot.autobus.tools;

public class Haversine {
    private static final double R = 6371; // Rayon de la Terre en kilomètres

    // Convertir les degrés en radians
    private static double toRadians(double degree) {
        return degree * Math.PI / 180;
    }

    /**
     * Calcule la distance entre deux points (latitude et longitude) en utilisant la formule de Haversine.
     *
     * @param lat1 Latitude du premier point
     * @param lon1 Longitude du premier point
     * @param lat2 Latitude du deuxième point
     * @param lon2 Longitude du deuxième point
     * @return Distance entre les deux points en kilomètres
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = toRadians(lat2 - lat1);
        double dLon = toRadians(lon2 - lon1);

        lat1 = toRadians(lat1);
        lat2 = toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance en kilomètres
    }

//    public static void main(String[] args) {
//        double lat1 = 48.8566; // Latitude de Paris
//        double lon1 = 2.3522;  // Longitude de Paris
//        double lat2 = 51.5074; // Latitude de Londres
//        double lon2 = -0.1278; // Longitude de Londres
//
//        double distance = calculateDistance(lat1, lon1, lat2, lon2);
//        System.out.println("Distance: " + distance + " km");
//    }
}

