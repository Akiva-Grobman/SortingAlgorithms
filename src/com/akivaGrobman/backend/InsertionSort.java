package com.akivaGrobman.backend;

import java.util.List;

public class InsertionSort extends SortingAlgorithm{

    public InsertionSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        for (int i = 0; i < list.size(); i++) {
            int j = i;
            while (j > 0 && list.get(j - 1) > list.get(j)) {
                swap(j, j - 1);
                setSortedElementPosition(j - 1);
                addToEvaluatedElements(j);
                updateDisplay();
                j--;
            }
        }
    }

}
