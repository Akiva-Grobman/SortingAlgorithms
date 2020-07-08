package com.akivaGrobman.backend;

import java.util.List;

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < list.size(); j++) {
                addToEvaluatedElements(j);
                addToEvaluatedElements(jMin);
                updateDisplay();
                if(list.get(j) < list.get(jMin)) {
                    jMin = j;
                }
            }
            if(jMin != i) {
                swap(i, jMin);
                setSortedElementPosition(i);
                updateDisplay();
            }
        }
    }

}
