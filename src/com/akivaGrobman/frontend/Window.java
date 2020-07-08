package com.akivaGrobman.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JFrame {

    public static final Color blue = new Color(11, 93, 137);
    public static final Color green = new Color(3, 43, 23);
    private final SortingDisplay sortingDisplay;

    public Window() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Sorting visualizer - By Akiva Grobman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sortingDisplay = new SortingDisplay();
        add(sortingDisplay);
        setVisible(true);
    }

    // simple setter that will call the refresh method after updating the list of elements
    public void updateList(List<Integer> list) {
        sortingDisplay.setList(list);
    }

    // will update the bar being moved (so it can be displayed differently)
    public void updateBarBeingMoved(int position) {
        sortingDisplay.setBarBeingMovedPosition(position);
    }

    // will update the bar being compared to the one being moved (so it can be displayed differently)
    public void updateBarBeingEvaluated(int position) {
        sortingDisplay.setBarBeingEvaluatedPosition(position);
    }

    // will update on screen display
    public void refresh() {
        sortingDisplay.repaint();
    }

    // simple setter
    public void displayFinish(String sortName, int swapCount) {
        sortingDisplay.displayFinish(sortName, swapCount);
    }

    public void resetDisplay() {
        sortingDisplay.resetDisplay();
    }

}
