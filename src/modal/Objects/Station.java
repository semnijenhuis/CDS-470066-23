package modal.Objects;

import java.util.ArrayList;

public class Station {

    private int id;
    private String code;
    private int uic;
    private String name_short;
    private String name_medium;
    private String name_long;
    private String slug;
    private String country;
    private String type;
    private double geo_lat;
    private double geo_lng;



    public ArrayList<Track> arrivalTracks = new ArrayList<>();
    public ArrayList<Track> departureTracks = new ArrayList<>();

    public void printDepartureTracks() {
        for (Track track : departureTracks) {
            System.out.println(track);
        }
    }



    public Station(int id, String code, int uic, String name_short, String name_medium, String name_long, String slug, String country, String type, double geo_lat, double geo_lng) {
        this.id = id;
        this.code = code;
        this.uic = uic;
        this.name_short = name_short;
        this.name_medium = name_medium;
        this.name_long = name_long;
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.geo_lat = geo_lat;
        this.geo_lng = geo_lng;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getUic() {
        return uic;
    }

    public String getName_short() {
        return name_short;
    }

    public String getName_medium() {
        return name_medium;
    }

    public String getName_long() {
        return name_long;
    }

    public String getSlug() {
        return slug;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public double getGeo_lng() {
        return geo_lng;
    }

    public void addArrivalTrack(Track track) {
        arrivalTracks.add(track);
    }
    public void addDepartureTrack(Track track) {
        departureTracks.add(track);
    }



    @Override
    public String toString() {
        return "Station (ID:" +id+ ", Code:" +code +", UIC:" + uic + ", Name:"+ name_long +", Slug:"+ slug+", Country:" + country+ ", Type:" + type+ ", Latitude:" + geo_lat + ", Longitude:" + geo_lng
                + ")" +'\n'
//        + "ArrivalTracks: "+ "\n" + arrivalTracks +'\n'
        + "Departure: "+ "\n" + departureTracks +'\n';

    }

    public int getDistanceToNextNode(Station newNextStation) {

        for (Track track : departureTracks) {

            if (track.getToStationCode().equalsIgnoreCase(newNextStation.getCode())) {
                return track.getDistanceKmFrom();
            }
        }

        return 401;
    }
}
