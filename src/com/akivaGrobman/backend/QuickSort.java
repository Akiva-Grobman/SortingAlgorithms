package com.akivaGrobman.backend;

import java.util.List;

public class QuickSort extends SortingAlgorithm {

    public QuickSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
      quickSort(0, list.size() - 1);
    }

    private void quickSort(int low, int high) {
        if(low < high) {
            int partition = getPartition(low, high);
            quickSort(low, partition - 1);
            quickSort(partition + 1, high);
        }
    }

    private int getPartition(int low, int high) {
        int pivot = list.get(high);
        int i = low;
        for (int j = low; j < high; j++) {
            addToEvaluatedElements(j);
            addToEvaluatedElements(pivot);
            updateDisplay();
            if(list.get(j) < pivot) {
                swap(i, j);
                setSortedElementPosition(j);
                addToEvaluatedElements(pivot);
                updateDisplay();
                i++;
            }
        }
        swap(i, high);
        setSortedElementPosition(high);
        addToEvaluatedElements(i);
        updateDisplay();
        return i;
    }

}
