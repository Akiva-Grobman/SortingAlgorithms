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

    // every sorting algorithm will need to be able to sort(duh)
    public abstract void sort();

    protected void updateDisplay() {
        // calls the main update method that will update the onscreen display
        Main.updateDisplay(list);
        try {
            //adds a small delay to rendering so the human eye can appreciate it
            Thread.sleep(SPEED);
        } catch (InterruptedException ignored) {}
    }

    // swaps two positions in the array and increments the swap count
    protected void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        swapCount++;
    }

    // simple getter
    public int getSwapCount() {
        return swapCount;
    }
}
