package modal.generic;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList {

    // The head node of the linked list
    public Node head;


    public static class Node {

        public Station currentStation;
        public Node nextStation;
        int nextNodeDistance;

        public Node(Station station) {
            currentStation = station;
            nextStation = null;
        }

        @Override
        public String toString() {
            return currentStation.toString();
        }


    }

    public static class Edge {
        public Node start;
        public Node end;
        public int weight;

        public Edge(Node start, Node end, int weight) {

            if (start == null || end == null) {
                throw new IllegalArgumentException("Data cannot be null");
            }

            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


    // Add a node to the end of the list
    public MyLinkedList addStationNode(Station data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

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

    // Add a node to the beginning of the list for path purpose
    public MyLinkedList addStationNodeAsPath(Station data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        Node newNextStation = new Node(data);

        if (this.head == null) {
            this.head = newNextStation;
        } else {
            newNextStation.nextStation = this.head;
            this.head = newNextStation;

            this.head.nextNodeDistance = this.head.currentStation.getDistanceToNextNode(this.head.nextStation.currentStation);
        }

        return this;
    }

    // prints the path
    public void printPath(Station start, Station end) {
        System.out.println("--- " + start.getName_long() + " to " + end.getName_long() + " ---");
        Node currentStationNode = this.head;
        while (currentStationNode != null) {
//            System.out.println(currentStationNode.currentStation);

            if (currentStationNode.nextStation != null) {
                System.out.println(currentStationNode.currentStation.getName_long() + " --(" + currentStationNode.nextNodeDistance + "km)-> " + currentStationNode.nextStation.currentStation.getName_long());
            } else {
                System.out.println(currentStationNode.currentStation.getName_long());
            }

            // Go to next node
            currentStationNode = currentStationNode.nextStation;
        }
        System.out.println(" --- End of path ---");

    }

    // prints the path/ list
    public void printList() {
        Node currentStationNode = this.head;

        // Traverse through the modal.generic.MyLinkedList
        while (currentStationNode != null) {

            // Print the data at current node
            System.out.println(currentStationNode.currentStation);
//            System.out.print(currNode.currentStation.getName_long() + " ");

            // Go to next node
            currentStationNode = currentStationNode.nextStation;
        }
    }

    // finds the station within the list
    public Station findStationLinear(MyLinkedList list, String name) {
        Node currentStationNode = list.head;

        // Traverse through the modal.generic.MyLinkedList
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


