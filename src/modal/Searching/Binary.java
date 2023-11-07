package modal.Searching;

import modal.Objects.Station;

import java.util.ArrayList;

public class Binary {

    public Station searchStationIDBin(ArrayList<Station> stationList, int stationID, boolean sorted) throws Exception {

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

            throw new Exception("Station " + stationID + " cant be found in our system");

        }

    }

    public Station searchStationCodeBin(ArrayList<Station> stationList, String stationCode, boolean sorted) throws Exception {
        if (!sorted) {
            throw new Exception("stationList is not sorted yet");
        } else {
            int first = 0;
            int last = stationList.size() - 1;

            while (first <= last) {
                int mid = first + (last - first) / 2;
                String currentStationCode = stationList.get(mid).getCode(); // Assuming 'getCode' retrieves the station code

                // Case-insensitive comparison for station code
                int compareResult = currentStationCode.compareToIgnoreCase(stationCode);

                if (compareResult < 0) {
                    first = mid + 1;
                } else if (compareResult == 0) {
                    return stationList.get(mid);
                } else {
                    last = mid - 1;
                }
            }

            throw new Exception("Station code '" + stationCode + "' not found in our system");
        }
    }

    public Station searchStationNameBin(ArrayList<Station> stationList, String stationName, boolean sorted) throws Exception {
        if (!sorted) {
            throw new Exception("stationList is not sorted yet");
        } else {
            int first = 0;
            int last = stationList.size() - 1;

            while (first <= last) {
                int mid = first + (last - first) / 2;
                String currentStationName = stationList.get(mid).getName_long(); // Assuming 'getName' retrieves the station name

                int compareResult = currentStationName.compareToIgnoreCase(stationName);

                if (compareResult < 0) {
                    first = mid + 1;
                } else if (compareResult == 0) {
                    return stationList.get(mid);
                } else {
                    last = mid - 1;
                }
            }

            throw new Exception("Station '" + stationName + "' not found in our system");
        }
    }




}
