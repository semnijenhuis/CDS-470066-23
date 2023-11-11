package modal.algorithm;

import modal.generic.MyLinkedList;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.generic.MyMinHeap;

import java.util.*;

public class AStar {

    Linear search = new Linear();

    public void shortestPath(ArrayList<Station> stations, Station start, Station end) {

        Map<Station, Integer> currentPath = new HashMap<>();
        Map<Station, Integer> futurePath = new HashMap<>();
        Map<Station, Station> cameFrom = new HashMap<>();

        MyMinHeap nextNeighbours = new MyMinHeap(stations.size(), Comparator.comparingInt(s -> futurePath.getOrDefault(s, Integer.MAX_VALUE)));
        nextNeighbours.insert(start);

        currentPath.put(start, 0);
        futurePath.put(start, heuristicCostEstimate(start, end));

        while (nextNeighbours.getSize() > 0) {
            Station current = nextNeighbours.pop();

            if (current == end) {
                MyLinkedList path = reconstructPath(cameFrom, current);

                path.printPath(start, end);
                System.out.println("Total distance: " + currentPath.get(end) + "km");
                return;
            }
            if (current != null) {

                for (Track track : current.departureTracks) {
                    Station neighbor = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

                    if (neighbor != null) {
                        int tentativeGScore = currentPath.get(current) + track.getDistanceKmFrom();

                        if (tentativeGScore < currentPath.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                            cameFrom.put(neighbor, current);
                            currentPath.put(neighbor, tentativeGScore);
                            futurePath.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));
                            nextNeighbours.insert(neighbor);
                        }
                    }
                }

            }


        }

        System.out.println("No path found!");
    }

    private int heuristicCostEstimate(Station start, Station end) {
        double lat1 = start.getGeo_lat();
        double lon1 = start.getGeo_lng();
        double lat2 = end.getGeo_lat();
        double lon2 = end.getGeo_lng();

        // Calculate Euclidean distance (straight-line distance) between two geographic points
        double distance = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));

        // Returning the distance as an estimate
        return (int) distance;

//


    }

    private MyLinkedList reconstructPath(Map<Station, Station> cameFrom, Station current) {
        MyLinkedList path = new MyLinkedList();
        while (current != null) {
            path.addStationNodeAsPath(current);
            current = cameFrom.get(current);
        }
        return path;
    }
}
