package modal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    @Override
    public String toString() {
        return "Station (ID:" +id+ ", Code:" +code +", UIC:" + uic + ", Name:"+ name_long +", Slug:"+ slug+", Country:" + country+ ", Type:" + type+ ", Latitude:" + geo_lat + ", Longitude:" + geo_lng + ")" +'\n';
    }
}
