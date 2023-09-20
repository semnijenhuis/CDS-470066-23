package modal.sorting;

import modal.Station;

import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort {

    public ArrayList<Station> sortStationName(ArrayList<Station> stations, int input) {

        int size = stations.size();
        Comparator<Station> clientComparator = SortingComparator.getStationComparator(input);

        for (int posUnSortedArray = 0; posUnSortedArray < size; posUnSortedArray++) {
           Station currentStation = stations.get(posUnSortedArray);
            int posSortedArray = posUnSortedArray - 1;

            while (posSortedArray >= 0 && clientComparator.compare(currentStation, stations.get(posSortedArray)) < 0) {
                stations.set((posSortedArray + 1), stations.get(posSortedArray));
                posSortedArray = posSortedArray - 1;
            }
            stations.set((posSortedArray + 1), currentStation);

        }
        return stations;

    }


}
