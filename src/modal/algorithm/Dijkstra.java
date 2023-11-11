package modal.algorithm;

import modal.generic.MyLinkedList;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.sorting.InsertionSort;

import java.util.*;

public class Dijkstra {

    Linear search = new Linear();

    public MyLinkedList shortestPath(ArrayList<Station> stations, Station start, Station end){

        Map<Station, Integer> distances = new HashMap<>();
        Map<Station, Station> previousStations = new HashMap<>();
        PriorityQueue<Station> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        ArrayList<Station> unsortedStationList = new ArrayList<>(stations);
        InsertionSort insertionSort = new InsertionSort();

        distances.put(start, 0);


        // Initialization
        for (Track track : start.departureTracks) {
            Station neighborStation = search.searchStationCode(stations,track.getToStationCode().toUpperCase());

            int looptime = 0;
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
                    path.addStationNodeAsPath(station);
                    station = previousStations.get(station);
                }

                path.printPath(start,end);
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
