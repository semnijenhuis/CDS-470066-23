package modal;

import modal.Objects.Station;

public class StationEntry {

    private final String code;
    private final Station station;
    private boolean deleted;

    public StationEntry(Station station) {
        this.code = station.getCode();
        this.station = station;
        this.deleted = false;
    }

    public String getKey() {
        return code;
    }

    public Station getValue() {
        return station;
    }

    public boolean isDeleted() {
        return deleted;
    }




}
