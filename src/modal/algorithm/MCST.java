package modal.algorithm;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.generic.MyGraph;
import modal.utils.Rectangle;

import java.util.*;

public class MCST {


    public void mcstPrimFindStations(ArrayList<Station> stations, ArrayList<Track> tracks, Station start, Station end, int input) {

        HashMap<String, Station> stationMap = new HashMap<>();

        for (Station station : stations) {
            stationMap.put(station.getCode(), station);

        }

        MyGraph graph = new MyGraph(tracks.size(), stationMap);
        graph.addConnections(tracks);

        Rectangle boundingRectangle = new Rectangle(start, end);

        graph.primAllStations(start.getCode().toLowerCase(), boundingRectangle, input);


    }


}
