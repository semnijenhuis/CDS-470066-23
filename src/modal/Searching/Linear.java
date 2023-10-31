package modal.Searching;

import modal.Station;

import java.util.ArrayList;
import java.util.Objects;

public class Linear {

    public Station searchStationID(ArrayList<Station> stationList, int stationID) {
        for (Station station : stationList) {
            if (station.getId() == stationID) {
                return station;
            }
        }
        return null;
    }

    public Station searchStationCode(ArrayList<Station> stationList, String stationCode) {

        for (Station station : stationList) {
            if (Objects.equals(station.getCode().toUpperCase(), stationCode.toUpperCase())) {
                return station;
            }
        }
        return null;
    }


}
