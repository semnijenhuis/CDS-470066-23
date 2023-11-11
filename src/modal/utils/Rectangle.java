package modal.utils;

import modal.Objects.Station;

public class Rectangle {
    private Station startStation;
    private Station endStation;

    public Rectangle(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public boolean isCoordinateWithinRectangle(double latitude, double longitude) {
        double minLat = Math.min(startStation.getGeo_lat(), endStation.getGeo_lat());
        double maxLat = Math.max(startStation.getGeo_lat(), endStation.getGeo_lat());
        double minLng = Math.min(startStation.getGeo_lng(), endStation.getGeo_lng());
        double maxLng = Math.max(startStation.getGeo_lng(), endStation.getGeo_lng());

        if (latitude >= minLat && latitude <= maxLat && longitude >= minLng && longitude <= maxLng) {
            return true;
        } else {
            System.out.println("Can't go to this station");
            return false;
        }

    }
}
