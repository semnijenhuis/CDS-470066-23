package modal.Searching;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Linear {

    // Linear search for station ID
    public Station searchStationID(ArrayList<Station> stationList, int stationID) {
        for (Station station : stationList) {
            if (station.getId() == stationID) {
                return station;
            }
        }
        return null;
    }

    // Linear search for station Code
    public Station searchStationCode(ArrayList<Station> stationList, String stationCode) {

        for (Station station : stationList) {
            if (Objects.equals(station.getCode().toUpperCase(), stationCode.toUpperCase())) {
                return station;
            }
        }
        return null;
    }

    // Linear search for station name
    public Station searchStationString(ArrayList<Station> stationList, String stationName) {

        for (Station station : stationList) {
            if (Objects.equals(station.getName_long().toUpperCase(), stationName.toUpperCase())) {
                return station;
            }
        }
        return null;
    }


}
