package modal.sorting;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.Comparator;

public class QuickSort {

    SortingComparator sortingComparator = new SortingComparator();

    public ArrayList<Station> stationQuickSort(ArrayList<Station>  stationList, int low, int high, int input) {

        if (low < high) {
            int pi = stationPartition(stationList, low, high, input);
            stationQuickSort(stationList, low, pi - 1, input);
            stationQuickSort(stationList, pi + 1, high, input);
        }

        return stationList;
    }

    int stationPartition(ArrayList<Station>  stationList, int low, int high, int input) {
        /** initiate the comparator so to check which compare the system has to make, the pivot based on the highest/last client in the list and the index. */
        Comparator<Station> stationComparator = sortingComparator.getStationComparator(input);
        Station pivot = stationList.get(high);
        int index = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            /** If current element is smaller than the pivot */
            if (j >= 0 && stationComparator.compare(stationList.get(j), pivot) < 0) {
                index++;
                stationSwap(stationList, index, j);
            }
        }

        stationSwap(stationList, index + 1, high);
        return (index + 1);
    }

    void stationSwap(ArrayList<Station> clientList, int index, int j) {
        Station temp = clientList.get(index);
        clientList.set(index, clientList.get(j));
        clientList.set(j, temp);
    }

}
