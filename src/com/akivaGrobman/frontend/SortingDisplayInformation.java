package com.akivaGrobman.frontend;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortingDisplayInformation {

    final Dimension preferredSizeOfDisplayPanel;
    final String sortName;
    List<Integer> list;
    int barBeingMovedPosition;
    List<Integer> evaluatedBarPositions;

    SortingDisplayInformation(String sortName) {
        this.sortName = sortName;
        list = new ArrayList<>();
        preferredSizeOfDisplayPanel = new Dimension();
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

}
