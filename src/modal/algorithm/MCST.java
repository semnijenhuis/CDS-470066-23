package modal.algorithm;

import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;

import java.util.*;

public class MCST {

    Linear search = new Linear();

    public LinkedList<Station> prim(ArrayList<Station> stations, Station start, Station end) {

        Set<Station> visited = new HashSet<>();

        PriorityQueue<Edge> queEdges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        LinkedList<Station> minSpanningTree = new LinkedList<>();


        Station startingPoint = stations.get(0);
        visited.add(startingPoint);

        for (Track track: startingPoint.departureTracks) {
            queEdges.add(new Edge(startingPoint, search.searchStationCode(stations, track.getToStationCode().toUpperCase()), track.getDistanceKmFrom()));

        }

        while (!queEdges.isEmpty()) {
            Edge currentEdge = queEdges.poll();
            Station currentStation = currentEdge.toStation;

            if (visited.contains(currentStation)) {
                continue;
            }

            visited.add(currentStation);
            minSpanningTree.add(currentStation);


            for (Track track : currentStation.departureTracks) {

                Station neighborStation = search.searchStationCode(stations, track.getToStationCode().toUpperCase());

                if (neighborStation != null) {
                    if (!visited.contains(neighborStation)) {
                        queEdges.add(new Edge(currentStation, neighborStation, track.getDistanceKmFrom()));
                    }
                }


            }

        }


        return minSpanningTree;

    }

    public LinkedList<Station> shortestPath(ArrayList<Station> stations, Station start, Station end) {
        LinkedList<Station> path = new LinkedList<>();
        LinkedList<Station> minSpanningTree = prim(stations, start, end);

        if (minSpanningTree.isEmpty() || !minSpanningTree.getLast().equals(end)) {
            System.out.println("No path found!");
            return path;
        }

        Station current = end;
        while (current != null) {
            path.addFirst(current);
            if (current.equals(start)) {
                break;
            }

            for (Station station : minSpanningTree) {
                for (Track track : station.departureTracks) {
                    if (track.getToStationCode().equalsIgnoreCase(current.getCode())) {
                        current = station;
                        break;
                    }
                }
            }
        }

        System.out.println("Shortest Path: " + path);
//        System.out.println("Total distance: " + calculateTotalDistance(path) + "km");

        return path;
    }




    public class Edge{
        Station fromStation;
        Station toStation;
        int weight;

        public Edge(Station fromStation, Station toStation, int weight) {
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.weight = weight;
        }
    }

    public void kruskal() {

    }


}
