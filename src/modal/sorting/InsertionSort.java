package modal.sorting;

import modal.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class InsertionSort {

    SortingComparator sortingComparator = new SortingComparator();

    public ArrayList<Station> stationInsertionSort(ArrayList<Station> stationList, int input) {

        /**  initiate the comparator so to check which compare the system has to make, and starts the timer with a loop based on the clients list size */
        Comparator<Station> stationComparator = sortingComparator.getStationComparator(input);
        int size = stationList.size();
        long startTime = System.currentTimeMillis();

        /**  One by one move boundary of unsorted subarray */
        for (int i = 1; i < size; ++i) {
            Station foundStation = stationList.get(i);
            int j = i - 1;

            /**  Find the correct position in sorted array */
            while (j >= 0 && stationComparator.compare(foundStation, stationList.get(j)) < 0) {
                stationList.set((j + 1), stationList.get(j));
                j = j - 1;
            }
            stationList.set((j + 1), foundStation);
        }

        /**  ends the timer and returns the sorted client list */
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return stationList;
    }

}
