package com.akivaGrobman.frontend;

import java.util.ArrayList;
import java.util.List;

public class SortingDisplayInformation {

    List<Integer> list;
    int barBeingMovedPosition;
    int swapCount;
    List<Integer> evaluatedBarPositions;
    boolean isSorted;
    String sortName;

    SortingDisplayInformation() {
        list = new ArrayList<>();
    }

    // simple setter
    void setList(List<Integer> list) {
        this.list = list;
    }

    // simple setter
    void setBarBeingMovedPosition(int position) {
        barBeingMovedPosition = position;
    }

    // simple setter
    void setEvaluatedBarPositions(List<Integer> position) {
        evaluatedBarPositions = position;
    }

    // will reset the display
    void resetDisplay() {
        isSorted = false;
    }

    // simple setter
    void displayFinish(String sortName, int swapCount) {
        isSorted = true;
        this.swapCount = swapCount;
        this.sortName = sortName;
    }

}
