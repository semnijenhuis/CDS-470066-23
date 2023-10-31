package modal;

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

    public MyLinkedList addStationNode(MyLinkedList list, Station data) {

        Node newNextStation = new Node(data);


        if (list.head == null) {
            list.head = newNextStation;
        } else {

            Node lastItemOfList = list.head;
            while (lastItemOfList.nextStation != null) {
                lastItemOfList = lastItemOfList.nextStation;
            }

            lastItemOfList.nextStation = newNextStation;
        }

        return list;
    }


    public void printList(MyLinkedList list) {
        Node currentStationNode = list.head;

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


