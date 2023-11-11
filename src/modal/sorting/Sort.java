package modal.sorting;

import modal.Objects.Station;

import java.util.ArrayList;

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
            sortingTimer(startTime);

            return sortedStation;

        } else if (sortingType == 2) {

            sortedStation = mergeSort.stationMergeSort(stationList, stationList.size(), sortingOption);
            sortingTimer(startTime);
            return sortedStation;

        } else if (sortingType == 3) {

            sortedStation = quickSort.stationQuickSort(stationList, 0, stationList.size() - 1, sortingOption);
            sortingTimer(startTime);
            return sortedStation;

        } else if (sortingType == 4) {

            sortedStation = selectionSort.stationSelectionSort(stationList, sortingOption);
            sortingTimer(startTime);
            return sortedStation;


        }

        return stationList;
    }

    void sortingTimer(long startTime) {
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }


}
