import modal.MyLinkedList;
import modal.Objects.Station;

public class TestingLinkList {

    public static void main(String[] args) {
        System.out.println("Hello World");

        MyLinkedList list = new MyLinkedList();

        Station station = new Station(1, "AAA", 001, "ams", "Amsterdam", "Amsterdam", "Amsterdam", "NL", "Stoptreinstation", 111.12, 222.23);
        Station station2 = new Station(2, "BBB", 002, "rot", "Rotterdam", "Rotterdam", "Rotterdam", "NL", "Stoptreinstation", 112.23, 223.34);
        Station station3 = new Station(3, "CCC", 003, "den", "Den-Haag", "Den-Haag", "Den-Haag", "NL", "Stoptreinstation", 113.34, 224.45);

        list.addStationNode( station);
        list.addStationNode(station2);
        list.addStationNode( station3);




      list.printList();

        System.out.println("now finding a station trough linklist");

        Station searchStation = list.findStationLinear(list, "Rotterdam");
        System.out.println(searchStation);
//        list.linearSearch(list, "Rotterdam");
//        list.printList(list);


    }

    public void findStationBinary(MyLinkedList list, String stationName) {



    }

}
