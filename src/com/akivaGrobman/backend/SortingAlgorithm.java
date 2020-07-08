package com.akivaGrobman.backend;

import com.akivaGrobman.Main;

import java.util.List;

public abstract class SortingAlgorithm {

    private static final int SORTING_SPEED = 500;
    private int elementBeingSortedPosition;
    private int evaluatedElementPosition;
    private int swapCount;
    protected List<Integer> list;

    public SortingAlgorithm(List<Integer> list) {
        this.list = list;
        swapCount = 0;
    }

    // every sorting algorithm will need to be able to sort(duh)
    public abstract void sort();

    // swaps two positions in the array and increments the swap count
    protected void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        updateSwapCount();
    }

    protected void setSortedElementPosition(int position) {
        elementBeingSortedPosition = position;
    }

    protected void setEvaluatedElementPosition(int position) {
        evaluatedElementPosition = position;
    }

    // this method should always be call right after the swap method is called
    protected void updateDisplay() {
        // calls the main update method that will update the onscreen display
        Main.updateDisplay(list, elementBeingSortedPosition, evaluatedElementPosition);
        try {
            //adds a small delay to rendering so the human eye can appreciate it
            Thread.sleep(SORTING_SPEED);
        } catch (InterruptedException ignored) {}
    }

    // will add one to the swap count
    protected void updateSwapCount() {
        swapCount++;
    }

    // simple getter
    public int getSwapCount() {
        return swapCount;
    }
}
