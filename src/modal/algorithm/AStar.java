package modal.algorithm;

import modal.MyLinkedList;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;

import java.util.*;

public class AStar {

    Linear search = new Linear();

    public MyLinkedList shortestPath(ArrayList<Station> stations, Station start, Station end){

        Map<Station, Integer> currentPath = new HashMap<>();
        Map<Station, Integer> futurePath = new HashMap<>();
        Map<Station, Station> cameFrom = new HashMap<>();

        currentPath.put(start, 0);
        futurePath.put(start, heuristicCostEstimate(start, end));

        PriorityQueue<Station> nextNeighbours = new PriorityQueue<>(Comparator.comparingInt(futurePath::get));
        nextNeighbours.offer(start);

        while (!nextNeighbours.isEmpty()) {
            Station current = nextNeighbours.poll();

            if (current == end) {
                MyLinkedList path = reconstructPath(cameFrom, current);
                System.out.println("Total distance: " + currentPath.get(end) + "km");
//                path.printList();
                return path;
            }

            for (Track track : current.departureTracks) {
                Station neighbor = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

                if (neighbor != null) {
                    int tentativeGScore = currentPath.get(current) + track.getDistanceKmFrom();

                    if (tentativeGScore < currentPath.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current);
                        currentPath.put(neighbor, tentativeGScore);
                        futurePath.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));

                        if (!nextNeighbours.contains(neighbor)) {
                            nextNeighbours.offer(neighbor);
                        }
                    }
                }
            }
        }

        System.out.println("No path found!");
        return null;
    }

    // might be a issue with the double
    private int heuristicCostEstimate(Station start, Station end) {

        double lat1 = start.getGeo_lat();
        double lon1 = start.getGeo_lng();
        double lat2 = end.getGeo_lat();
        double lon2 = end.getGeo_lng();

        // Calculate Euclidean distance (straight-line distance) between two geographic points
        double distance = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
        return (int) distance;

    }

    private MyLinkedList reconstructPath(Map<Station, Station> cameFrom, Station current) {
        MyLinkedList path = new MyLinkedList();
        while (current != null) {
            path.addStationNode(current);
            current = cameFrom.get(current);
        }
        return path;
    }


}
