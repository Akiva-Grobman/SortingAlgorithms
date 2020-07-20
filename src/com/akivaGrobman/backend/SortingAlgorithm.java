package com.akivaGrobman.backend;

import com.akivaGrobman.Main;

import java.util.ArrayList;
import java.util.List;

public abstract class SortingAlgorithm {

    public final int Id;
    private static final int SORTING_SPEED = 5;
    private int elementBeingSortedPosition;
    private List<Integer> evaluatedElementsPosition;
    protected List<Integer> list;

    public SortingAlgorithm(List<Integer> list, int Id) {
        this.Id = Id;
        this.list = list;
        evaluatedElementsPosition = new ArrayList<>();
    }

    // every sorting algorithm will need to be able to sort(duh)
    public abstract void sort();

    // swaps two positions in the array and increments the swap count
    protected void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // simple setter
    protected void setSortedElementPosition(int position) {
        elementBeingSortedPosition = position;
    }

    // simple setter
    protected void addToEvaluatedElements(int position) {
        evaluatedElementsPosition.add(position);
    }

    // this method should always be call right after the swap method is called
    protected void updateDisplay() {
        // calls the main update method that will update the specific display that has this algorithm
        Main.updateDisplay(list, elementBeingSortedPosition, evaluatedElementsPosition, Id);
        // reset this list
        evaluatedElementsPosition = new ArrayList<>();
        try {
            //adds a small delay to rendering so the human eye can appreciate it
            Thread.sleep(SORTING_SPEED);
        } catch (InterruptedException ignored) {}
    }

}
