package modal.sorting;

import modal.Objects.Station;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort {

    SortingComparator sortingComparator = new SortingComparator();

    public ArrayList<Station> stationMergeSort(ArrayList<Station> stationList, int listSize, int input) {
        // beschrijving nodig
        if (listSize < 2) {
            return stationList;
        }

        // Beschrijving nodig
        int mid = listSize / 2;
        ArrayList<Station> leftArray = new ArrayList<>();
        ArrayList<Station> rightArray = new ArrayList<>();

//        modal.generic.MyLinkedList<Client> leftArray = new modal.generic.MyLinkedList<>(mid);
//        modal.generic.MyLinkedList<Client> rightArray = new modal.generic.MyLinkedList<>(listSize - mid);

        for (int i = 0; i < mid; i++) {
            leftArray.add(i, stationList.get(i));
        }
        for (int i = mid; i < listSize; i++) {
            rightArray.add(i - mid, stationList.get(i));
        }

        stationMergeSort(leftArray, mid, input);
        stationMergeSort(rightArray, listSize - mid, input);

        mergeStation(stationList, leftArray, rightArray, mid, listSize - mid, input);


//        System.out.println(stationList);
        return stationList;
    }

    void mergeStation(ArrayList<Station> a, ArrayList<Station> l, ArrayList<Station> r, int left, int right, int input) {

        Comparator<Station> clientComparator = sortingComparator.getStationComparator(input);
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (j >= 0 && clientComparator.compare(l.get(i), r.get(j)) < 0) {
                a.set(k++, l.get(i++));
            } else {
                a.set(k++, r.get(j++));
            }
        }


        while (i < left) {
            a.set(k++, l.get(i++));
        }
        while (j < right) {
            a.set(k++, r.get(j++));
        }
    }

}
