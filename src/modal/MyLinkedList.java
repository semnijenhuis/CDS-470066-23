package modal;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList {

    Node head;


    static class Node {

        Station currentStation;
        Node nextStation;

        Node(Station station) {
            currentStation = station;
            nextStation = null;
        }

        @Override
        public String toString() {
            return currentStation.toString();
        }
    }

    class Edge {
        Node start;
        Node end;
        int weight;

        public Edge(Node start, Node end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    class Graph {
        List<Node> nodes;
        List<Edge> edges;

        public Graph() {
            nodes = new ArrayList<>();
            edges = new ArrayList<>();
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }





    public MyLinkedList addStationNode( Station data) {

        Node newNextStation = new Node(data);


        if (this.head == null) {
            this.head = newNextStation;
        } else {

            Node lastItemOfList = this.head;
            while (lastItemOfList.nextStation != null) {
                lastItemOfList = lastItemOfList.nextStation;
            }

            lastItemOfList.nextStation = newNextStation;
        }

        return this;
    }



    public void printList() {
        Node currentStationNode = this.head;

//        System.out.print("modal.MyLinkedList: ");

        // Traverse through the modal.MyLinkedList
        while (currentStationNode != null) {
            // Print the data at current node
            System.out.println(currentStationNode.currentStation);
//            System.out.print(currNode.currentStation.getName_long() + " ");

            // Go to next node
            currentStationNode = currentStationNode.nextStation;
        }
    }

    public Station findStationLinear(MyLinkedList list, String name) {
        Node currentStationNode = list.head;

        // Traverse through the modal.MyLinkedList
        while (currentStationNode != null) {
            // Print the data at current node
            if (currentStationNode.currentStation.getName_long().equals(name)) {
                System.out.println("Found the bastard");
                return currentStationNode.currentStation;
            } else {
                currentStationNode = currentStationNode.nextStation;
            }
        }
        return null;
    }

}


