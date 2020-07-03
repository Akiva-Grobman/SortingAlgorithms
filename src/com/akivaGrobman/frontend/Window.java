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

    public void updateList(List<Integer> list) {
        sortingDisplay.setList(list);
        refresh();
    }

    public void refresh() {
        sortingDisplay.repaint();
    }

    public void displayFinish(String sortName, int swapCount) {
        sortingDisplay.displayFinish(sortName, swapCount);
    }

}
