package modal.sorting;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Sort {


    InsertionSort insertionSort = new InsertionSort();
    MergeSort mergeSort = new MergeSort();
    QuickSort quickSort = new QuickSort();
    SelectionSort selectionSort = new SelectionSort();

    Boolean sorted;
    int typeSortedOn;


    public ArrayList<Station> stationSorting(ArrayList<Station> stationList, int sortingOption, int sortingType) {

        ArrayList<Station> sortedStation;
        long startTime = System.currentTimeMillis();

        if (sortingType == 1) {
            sortedStation = insertionSort.stationInsertionSort(stationList, sortingOption);
        } else if (sortingType == 2) {
            sortedStation = mergeSort.stationMergeSort(stationList, stationList.size(), sortingOption);
        } else if (sortingType == 3) {
            sortedStation = quickSort.stationQuickSort(stationList, 0, stationList.size() - 1, sortingOption);
        } else if (sortingType == 4) {
            sortedStation = selectionSort.stationSelectionSort(stationList, sortingOption);
        } else {
            // Invalid sorting type, return the original list
            System.out.println("Invalid sorting type. The list remains unsorted.");
            sortingTimer(startTime);
            return stationList;
        }

        // Continue with the sorting timer and return the sorted list
        sortingTimer(startTime);
        return sortedStation;

    }

    // calculates how long it takes
    public void sortingTimer(long startTime) {
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    // Checks if its sorted
    public boolean isSorted(ArrayList<Station> stations, int sortingOption) {
        for (int i = 1; i < stations.size(); i++) {
            int compareResult = compareStations(stations.get(i - 1), stations.get(i), sortingOption);
            if (compareResult > 0) {
                return false;
            }
        }
        return true;
    }

    // compares the stations
    private int compareStations(Station station1, Station station2, int sortingOption) {
        SortingComparator sortingComparator = new SortingComparator();
        Comparator<Station> comparator = sortingComparator.getStationComparator(sortingOption);
        return comparator.compare(station1, station2);
    }


    public boolean isSortedByDistance(List<Map.Entry<String, Integer>> stationsWithWeights) {
        for (int i = 0; i < stationsWithWeights.size() - 1; i++) {
            int currentDistance = stationsWithWeights.get(i).getValue();
            int nextDistance = stationsWithWeights.get(i + 1).getValue();

            // Ensure that distances are in ascending order
            if (currentDistance > nextDistance) {
                return false;
            }
        }

        return true;
    }
}
