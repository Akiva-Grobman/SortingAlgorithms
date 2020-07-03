package com.akivaGrobman.backend;

import com.akivaGrobman.Main;

import java.util.List;

public abstract class SortingAlgorithm {

    private static final int SPEED = 10;
    protected List<Integer> list;
    private int swapCount;

    public SortingAlgorithm(List<Integer> list) {
        this.list = list;
        swapCount = 0;
    }

    public abstract void sort();

    protected void updateDisplay() {
        Main.updateDisplay(list);
        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException ignored) {}
    }

    protected void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        swapCount++;
    }

    public int getSwapCount() {
        return swapCount;
    }
}
