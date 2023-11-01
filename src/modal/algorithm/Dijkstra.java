package modal.algorithm;

import modal.MyLinkedList;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;

import java.util.*;

public class Dijkstra {

    Linear search = new Linear();

    public MyLinkedList shortestPath(ArrayList<Station> stations, Station start, Station end){

        Map<Station, Integer> distances = new HashMap<>();
        Map<Station, Station> previousStations = new HashMap<>();
        PriorityQueue<Station> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        distances.put(start, 0);

        // Initialization
        for (Track track : start.departureTracks) {
            Station neighborStation = search.searchStationCode(stations,track.getToStationCode().toUpperCase()); // Assuming a method to fetch Station by code

            if (neighborStation != null) {
                distances.put(neighborStation, track.getDistanceKmFrom());
                priorityQueue.offer(neighborStation);
                previousStations.put(neighborStation, start);
            }
        }


        while (!priorityQueue.isEmpty()) {

            Station currentStation = priorityQueue.poll();
            if (currentStation == end) {
                Station station = end;

                MyLinkedList path = new MyLinkedList();

                while (station != null) {
                    path.addStationNode(station);
                    station = previousStations.get(station);
                }

//                System.out.println("Shortest Path: " + path);
//                path.printList();
                System.out.println("Total distance: " + distances.get(end) +"km");
                return path;
            }

            for (Track track : currentStation.departureTracks) {
                Station neighborStation = search.searchStationCode(stations,track.getToStationCode().toUpperCase());

                if (neighborStation != null) {
                    int newDistance = distances.get(currentStation) + track.getDistanceKmFrom();
                    if (newDistance < distances.getOrDefault(neighborStation, Integer.MAX_VALUE)) {
                        distances.put(neighborStation, newDistance);
                        previousStations.put(neighborStation, currentStation);
                        priorityQueue.offer(neighborStation);
                    }

                }



            }
        }
        System.out.println("No path found!");
        return null;



    }

}
