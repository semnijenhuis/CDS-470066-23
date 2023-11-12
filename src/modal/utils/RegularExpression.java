package modal.utils;

import java.io.IOException;


public class RegularExpression {

    // List of all regexs
    private static final String ID_REGEX = "^[1-9][0-9]{0,2}$";
    private static final String CODE_REGEX = "^[a-zA-Z]{1,8}$";
    private static final String UIC_REGEX = "^[0-9]+$";
    private static final String LATITUDE_REGEX = "[-+]?\\d*\\.\\d+";
    private static final String LONGITUDE_REGEX = "[-+]?\\d*\\.\\d+";
    private static final String DISTANCE_REGEX = "^[0-9]+$";

    // Filters the station file
    public boolean filterStation(String id, String code, String uic, String latitude, String longitude) throws IOException {
        int count = 0;
        count += RegexFilter.filter(id, ID_REGEX);
        count += RegexFilter.filter(code, CODE_REGEX);
        count += RegexFilter.filter(uic, UIC_REGEX);
        count += RegexFilter.filter(latitude, LATITUDE_REGEX);
        count += RegexFilter.filter(longitude, LONGITUDE_REGEX);

        if (count > 0) {
            System.out.println("Number of lines : " + count);
            return false;
        }

        return true;
    }

    // Filters the track file
    public boolean filterTrack(String fromCode, String toCode, String distance) throws IOException {
        int count = 0;
        count += RegexFilter.filter(fromCode, CODE_REGEX);
        count += RegexFilter.filter(toCode, CODE_REGEX);
        count += RegexFilter.filter(distance, DISTANCE_REGEX);

        if (count > 0) {
            System.out.println("Number of lines : " + count);
            return false;
        }

        return true;
    }
}
