package modal.sorting;

import modal.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class SelectionSort {

    SortingComparator sortingComparator = new SortingComparator();

    public ArrayList<Station> stationSelectionSort(ArrayList<Station> stationList, int input) {
        /** initiate the comparator so to check which compare the system has to make, and starts the timer with a loop based on the clients list size */
        Comparator<Station> stationComparator = sortingComparator.getStationComparator(input);
        int size = stationList.size();


        /** One by one move boundary of unsorted subarray */
        for (int i = 0; i < size - 1; i++) {
            int index = i;

            /** Find the minimum element in unsorted array */
            for (int j = i + 1; j < size; j++)
                if (j >= 0 && stationComparator.compare(stationList.get(j), stationList.get(index)) < 0) {
                    index = j;
                }

            // Swaps the old clients position with the new position */
            Station smaller = stationList.get(index);
            stationList.set(index, stationList.get(i));
            stationList.set(i, smaller);
        }

        /** ends the timer and returns the sorted client list */
        return stationList;
    }


}
