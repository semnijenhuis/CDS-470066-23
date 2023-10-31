package modal;

public class Track {

    private String fromStationCode;
    private String toStationCode;
    private int distanceKmFrom;
    private int distanceTimeTo;
    private int trackNumber3;

    public Track(String fromStationCode, String toStationCode, int distanceKmFrom, int distanceTimeTo, int trackNumber3) {
        this.fromStationCode = fromStationCode;
        this.toStationCode = toStationCode;
        this.distanceKmFrom = distanceKmFrom;
        this.distanceTimeTo = distanceTimeTo;
        this.trackNumber3 = trackNumber3;
    }

    public String getFromStationCode() {
        return fromStationCode;
    }

    public String getToStationCode() {
        return toStationCode;
    }

    public int getDistanceKmFrom() {
        return distanceKmFrom;
    }

    @Override
    public String toString() {
        return "Track {From: "+ fromStationCode +", to: "+ toStationCode +", distance in km: "+ distanceKmFrom +"}" +'\n';

    }
}
