package com.akivaGrobman.backend;

import java.util.List;

public class MergeSort extends SortingAlgorithm {

    public MergeSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        int m = (list.size() - 1) / 2;
        sort(0, m);
        sort(m + 1, list.size() - 1);
        merge(0, m, list.size() - 1);
    }

    private void sort(int i, int j) {
        if(i < j) {
            int middle = (i + j) / 2;
            sort(i, middle);
            sort(middle + 1, j);
            merge(i, middle, j);
        }
    }

    private void merge(int left, int middle, int right) {
        int newLeftMiddle = middle - left + 1;
        int newRightMiddle = right - middle;
        int[] leftArray = new int[newLeftMiddle];
        int[] rightArray = new int[newRightMiddle];
        for (int i = 0; i < newLeftMiddle; i++) {
            leftArray[i] = list.get(left + i);
        }
        for (int j = 0; j < newRightMiddle; ++j) {
            rightArray[j] = list.get(middle + 1 + j);
        }
        int i = 0, j = 0;
        int k = left;
        while (i < newLeftMiddle && j < newRightMiddle) {
            if (leftArray[i] <= rightArray[j]) {
                list.set(k, leftArray[i]);
                i++;
            }
            else {
                list.set(k, rightArray[j]);
                j++;
            }
            updateDisplay();
            k++;
        }
        while (i < newLeftMiddle) {
            list.set(k, leftArray[i]);
            updateDisplay();
            i++;
            k++;
        }
        while (j < newRightMiddle) {
            list.set(k, rightArray[j]);
            updateSwapCount();
            updateDisplay();
            j++;
            k++;
        }
    }

}
