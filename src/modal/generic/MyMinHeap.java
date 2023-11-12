package modal.generic;

import modal.Objects.Station;
import java.util.Comparator;
import java.util.Arrays;

public class MyMinHeap {
    private Station[] heap;
    private int size;
    private int maxSize;
    private Comparator<Station> stationComparator;

    public MyMinHeap(int maxSize, Comparator<Station> stationComparator) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new Station[this.maxSize + 1];
        this.heap[0] = null; // Sentinel value at the root
        this.stationComparator = stationComparator;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        Station tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void minHeapify(int pos) {
        if (isEmpty()) {
            return; // Return without performing min heapify if the heap is empty
        }

        if (!isLeaf(pos)) {
            if (stationComparator.compare(heap[pos], heap[leftChild(pos)]) > 0 ||
                    stationComparator.compare(heap[pos], heap[rightChild(pos)]) > 0) {

                if (stationComparator.compare(heap[leftChild(pos)], heap[rightChild(pos)]) < 0) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Station element) {
        if (size >= maxSize) {
            return;
        }
        heap[++size] = element;
        int current = size;

        while (current > 1 && stationComparator.compare(heap[current], heap[parent(current)]) < 0) {
            System.out.println("Swapping: " + heap[current] + " with " + heap[parent(current)]);
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Station peek() {
        if (size > 0) {
            return heap[1];
        }
        return null; // Indicating heap is empty
    }

    public Station pop() {
        if (size == 0 || isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }

        Station popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 1, size + 1));
    }
}
