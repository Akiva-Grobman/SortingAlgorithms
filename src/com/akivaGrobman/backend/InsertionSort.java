package com.akivaGrobman.backend;

import com.akivaGrobman.Main;

import java.util.List;

public class InsertionSort implements SortingAlgorithm{

    private final List<Integer> list;
    private int i;
    private int j;

    public InsertionSort(List<Integer> list) {
        this.list = list;
        i = 1;
        j = 1;
    }

    @Override
    public void sort() {
        if(j > 0 && list.get(j - 1) > list.get(j)) {
            int temp = list.get(j - 1);
            list.set(j - 1, list.get(j));
            list.set(j, temp);
            j--;
        } else {
            i++;
            j = i;
        }
    }


    @Override
    public boolean isSorted() {
        return i == list.size();
    }

}
