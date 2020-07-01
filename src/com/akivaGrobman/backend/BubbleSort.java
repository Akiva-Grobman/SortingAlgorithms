package com.akivaGrobman.backend;

import java.util.List;

public class BubbleSort implements SortingAlgorithm{

    private final List<Integer> list;
    private int lastUnsorted;
    private int i;
    private boolean swapped;
    private boolean checkedWholeList;

    public BubbleSort(List<Integer> list) {
        this.list = list;
        lastUnsorted = list.size();
        i = 1;
        swapped = true;
        checkedWholeList = false;
    }

    @Override
    public void sort() {
        if(i == lastUnsorted) {
            i = 1;
            swapped = true;
            checkedWholeList = true;
            lastUnsorted--;
        }
        if(list.get(i - 1) > list.get(i)) {
            int temp = list.get(i - 1);
            list.set(i - 1, list.get(i));
            list.set(i, temp);
            swapped = true;
        } else if(checkedWholeList) {
            swapped = false;
        }
        i++;
    }

    @Override
    public boolean isSorted() {
        return !swapped;
    }

}
