package com.akivaGrobman.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JFrame {

    public static final Color blue = new Color(11, 93, 137);
    public static final Color green = new Color(3, 43, 23);
    private static final String sortingDisplayKey = SortingDisplay.class.getSimpleName();
    private final SortingDisplay sortingDisplay;
    private static JPanel panelCont;
    private static CardLayout cardLayout;

    public Window() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Sorting visualizer - By Akiva Grobman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelCont = new JPanel();
        // we use the card layout so we can switch the panel on display
        cardLayout = new CardLayout();
        panelCont.setLayout(cardLayout);
        sortingDisplay = new SortingDisplay();
        WelcomeDisplay welcomeDisplay = new WelcomeDisplay();
        panelCont.add(welcomeDisplay, welcomeDisplay.getClass().getSimpleName());
        panelCont.add(sortingDisplay, sortingDisplayKey);
        add(panelCont);
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
    public void updateBarBeingEvaluated(List<Integer> evaluatedBarPositions) {
        sortingDisplay.setEvaluatedBarPositions(evaluatedBarPositions);
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

    // will make the sorting display panel the one being seen
    public static void replacePanels(){
        cardLayout.show(panelCont, sortingDisplayKey);
    }

}
