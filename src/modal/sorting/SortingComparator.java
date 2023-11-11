package modal.sorting;

import modal.Objects.Station;

import java.util.Comparator;

public class SortingComparator {

    static Comparator<Station> getStationComparator(int input) {

        Comparator<Station> StationComparator = null;

        if (input == 1) {
            // Compares the clients ID's
            StationComparator = Comparator.comparingInt(Station::getId);
        } else if (input == 2) {
            // Compares the stations names
            StationComparator = Comparator.comparing(Station::getCode);
        } else if (input == 3) {
            // Compares the stations initials
            StationComparator = Comparator.comparingInt(Station::getUic);
        } else if (input == 4) {
            // Compares the stations name short
            StationComparator = Comparator.comparing(Station::getName_short);
        } else if (input == 5) {
            // Compares the stations name medium
            StationComparator = Comparator.comparing(Station::getName_medium);
        } else if (input == 6) {
            // Compares the stations name long
            StationComparator = Comparator.comparing(Station::getName_long);
        } else if (input == 7) {
            // Compares the stations slug
            StationComparator = Comparator.comparing(Station::getSlug);
        } else if (input == 8) {
            // Compares the stations country
            StationComparator = Comparator.comparing(Station::getCountry);
        } else if (input == 9) {
            // Compares the stations type
            StationComparator = Comparator.comparing(Station::getType);
        } else if (input == 10) {
            // Compares the stations geo_lng
            StationComparator = Comparator.comparing(Station::getGeo_lat);
        } else if (input == 11) {
            // Compares the stations geo_lng
            StationComparator = Comparator.comparing(Station::getGeo_lng);
        }

        // returns the client comparator based on the input
        return StationComparator;
    }

}
