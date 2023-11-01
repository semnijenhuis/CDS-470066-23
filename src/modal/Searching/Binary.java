package modal.Searching;

import modal.Objects.Station;

import java.util.ArrayList;

public class Binary {

    public Station searchStaton(ArrayList<Station> stationList, int stationID, boolean sorted) throws Exception {

        if (!sorted) {
            throw new Exception("stationList is not sorted yet bro");

            // keep it running
        } else {

            int first = stationList.indexOf(stationList.get(0));
            int last = stationList.indexOf(stationList.get(stationList.size() - 1));
            int mid = (first + last) / 2;

            while (first <= last) {

                if (stationList.get(mid).getId() < stationID) {
                    first = mid + 1;
                } else if (stationList.get(mid).getId() == stationID) {
                    return stationList.get(mid);
                } else {
                    last = mid - 1;
                }

                mid = (first + last) / 2;

            }

            throw new Exception("client " + stationID + " cant be found in our system");


        }

    }


}
