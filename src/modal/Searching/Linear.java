package modal.Searching;

import modal.Station;

import java.util.ArrayList;

public class Linear {

    public Station searchStation(ArrayList<Station> clientList, int clientID) {
        for (Station station : clientList) {
            if (station.getId() == clientID) {
                return station;
            }
        }
        return null;
    }


}
