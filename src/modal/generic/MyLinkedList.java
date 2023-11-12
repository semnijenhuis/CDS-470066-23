package modal.generic;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList {

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




    public MyLinkedList addStationNode( Station data) {

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
//            lastItemOfList.nextNodeDistance = lastItemOfList.currentStation.getDistanceToNextNode(newNextStation.currentStation);
        }

        return this;
    }

    public MyLinkedList addStationNodeAsPath( Station data) {

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

    public void printPath(Station start, Station end) {
        System.out.println("--- " +start.getName_long() +" to "+end.getName_long() +" ---");
        Node currentStationNode = this.head;
        while (currentStationNode != null) {
//            System.out.println(currentStationNode.currentStation);

            if (currentStationNode.nextStation != null) {
                System.out.println(currentStationNode.currentStation.getName_long() + " --(" + currentStationNode.nextNodeDistance + "km)-> " + currentStationNode.nextStation.currentStation.getName_long());
            }
            else {
                System.out.println(currentStationNode.currentStation.getName_long());
            }

            // Go to next node
            currentStationNode = currentStationNode.nextStation;
        }
        System.out.println(" --- End of path ---");

    }





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


