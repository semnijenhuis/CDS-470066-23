package modal.algorithm;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.generic.MyGraph;
import modal.utils.Rectangle;

import java.util.*;

public class MCST {


    public void mcstPrimFindStations(ArrayList<Station> stations, ArrayList<Track> tracks, Station start, Station end, int input) {
        // Create a map to store stations using their codes as keys
        HashMap<String, Station> stationMap = new HashMap<>();

        // Populate the station map with stations from the input list
        for (Station station : stations) {
            stationMap.put(station.getCode(), station);
        }

        // Create an instance of MyGraph to represent the station network
        MyGraph graph = new MyGraph(tracks.size(), stationMap);

        // Add connections between stations using tracks
        graph.addConnections(tracks);

        // Define a bounding rectangle based on start and end stations
        Rectangle boundingRectangle = new Rectangle(start, end);

        // Apply Prim's algorithm to find the Minimum-Cost Spanning Tree
        graph.primAllStations(start.getCode().toLowerCase(), boundingRectangle, input);
    }


}
