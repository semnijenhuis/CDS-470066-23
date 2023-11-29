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

    // Calculates the index of the parent node for the given position.
    private int parent(int pos) {
        return pos / 2;
    }

    // Calculates the index of the left child node for the given position.
    private int leftChild(int pos) {
        return 2 * pos;
    }

    // Calculates the index of the right child node for the given position.
    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    // Checks if the given position represents a leaf node in the heap.
    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        // Perform the swap by exchanging the elements at the specified positions.
        Station tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    // Recursive function to min heapify the subtree at index pos
    private void minHeapify(int pos) {
        // Check if the heap is empty, and return without further processing if so.
        if (isEmpty()) {
            return;
        }

        // Check if the current position is not a leaf node.
        if (!isLeaf(pos)) {
            // Compare the current element with its left and right children.
            // If the current element is greater than any of its children, perform swaps accordingly.
            if (stationComparator.compare(heap[pos], heap[leftChild(pos)]) > 0 ||
                    stationComparator.compare(heap[pos], heap[rightChild(pos)]) > 0) {

                // Check which child has a smaller value and swap with that child.
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

    // Returns true if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Inserts a station into the heap
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

    // Returns the station at the head of the heap
    public Station peek() {
        if (size > 0) {
            return heap[1];
        }
        return null; // Indicating heap is empty
    }

    // Removes a station from the heap
    public Station pop() {
        if (size == 0 || isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }

        Station popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    // returns the size of min heap
    public int getSize() {
        return size;
    }

    // prints the min heap
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 1, size + 1));
    }
}

