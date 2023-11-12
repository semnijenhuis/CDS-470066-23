package modal.sorting;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MergeSort {

    SortingComparator sortingComparator = new SortingComparator();

    public ArrayList<Station> stationMergeSort(ArrayList<Station> stationList, int listSize, int input) {
        // Base case: If the list has one element or is empty, it is already sorted.
        if (listSize < 2) {
            return stationList;
        }

        // Determine the midpoint of the list.
        int mid = listSize / 2;

        // Create left and right arrays to hold the divided portions of the stationList.
        ArrayList<Station> leftArray = new ArrayList<>();
        ArrayList<Station> rightArray = new ArrayList<>();

        // Populate the leftArray with elements from the beginning to the midpoint.
        for (int i = 0; i < mid; i++) {
            leftArray.add(i, stationList.get(i));
        }

        // Populate the rightArray with elements from the midpoint to the end.
        for (int i = mid; i < listSize; i++) {
            rightArray.add(i - mid, stationList.get(i));
        }

        // Recursively apply mergeSort to the left and right arrays.
        stationMergeSort(leftArray, mid, input);
        stationMergeSort(rightArray, listSize - mid, input);

        // Merge the sorted left and right arrays back into the original stationList.
        mergeStation(stationList, leftArray, rightArray, mid, listSize - mid, input);

        // Return the sorted stationList.
        return stationList;
    }

    void mergeStation(ArrayList<Station> a, ArrayList<Station> l, ArrayList<Station> r, int left, int right, int input) {
        // Retrieve the comparator based on the specified attribute for sorting.
        Comparator<Station> clientComparator = sortingComparator.getStationComparator(input);

        // Initialize pointers for the left (l) and right (r) arrays, and the main array (a).
        int i = 0, j = 0, k = 0;

        // Compare elements from the left and right arrays and merge them into the main array.
        while (i < left && j < right) {
            // Use the comparator to determine the order of elements based on the specified attribute.
            if (clientComparator.compare(l.get(i), r.get(j)) < 0) {
                a.set(k++, l.get(i++));
            } else {
                a.set(k++, r.get(j++));
            }
        }

        // Copy any remaining elements from the left array to the main array.
        while (i < left) {
            a.set(k++, l.get(i++));
        }

        // Copy any remaining elements from the right array to the main array.
        while (j < right) {
            a.set(k++, r.get(j++));
        }
    }


    public List<Map.Entry<String, Integer>> mergeSortStationsByDistance(List<Map.Entry<String, Integer>> stationsWithWeights) {
        int listSize = stationsWithWeights.size();
        if (listSize < 2) {
            return stationsWithWeights;
        }

        int mid = listSize / 2;
        List<Map.Entry<String, Integer>> leftArray = new ArrayList<>(stationsWithWeights.subList(0, mid));
        List<Map.Entry<String, Integer>> rightArray = new ArrayList<>(stationsWithWeights.subList(mid, listSize));

        mergeSortStationsByDistance(leftArray);
        mergeSortStationsByDistance(rightArray);

        mergeStationsDistance(stationsWithWeights, leftArray, rightArray);
        return stationsWithWeights;
    }

    private void mergeStationsDistance(List<Map.Entry<String, Integer>> a, List<Map.Entry<String, Integer>> l, List<Map.Entry<String, Integer>> r) {
        int i = 0, j = 0, k = 0;

        while (i < l.size() && j < r.size()) {
            if (l.get(i).getValue() < r.get(j).getValue()) {
                a.set(k++, l.get(i++));
            } else {
                a.set(k++, r.get(j++));
            }
        }

        while (i < l.size()) {
            a.set(k++, l.get(i++));
        }

        while (j < r.size()) {
            a.set(k++, r.get(j++));
        }
    }


}
