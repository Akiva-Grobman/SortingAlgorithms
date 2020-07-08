package com.akivaGrobman.backend;

import java.util.List;

public class BubbleSort extends SortingAlgorithm{

    public BubbleSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    swap(j, j + 1);
                    setEvaluatedElementPosition(j);
                    setSortedElementPosition(j + 1);
                    updateDisplay();
                }
            }
        }
    }

}
