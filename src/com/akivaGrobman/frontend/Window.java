package com.akivaGrobman.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Window extends JFrame {

    public static final Color blue = new Color(11, 93, 137);
    public static final Color green = new Color(3, 43, 23);
    private static final String sortingDisplayKey = Window.class.getSimpleName();
    private static final String welcomeDisplayKey = WelcomeDisplay.class.getSimpleName();
    private final SortingDisplay[] sortingDisplays;
    private final JPanel panelCont;
    private final CardLayout cardLayout;

    public Window(int numberOfAlgorithms) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Sorting visualizer - By Akiva Grobman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // we use the card layout so we can switch the panel on display
        panelCont = new JPanel();
        cardLayout = new CardLayout();
        panelCont.setLayout(cardLayout);
        WelcomeDisplay welcomeDisplay = new WelcomeDisplay();
        panelCont.add(welcomeDisplay, welcomeDisplayKey);

        sortingDisplays = new SortingDisplay[numberOfAlgorithms];
        Arrays.fill(sortingDisplays, new SortingDisplay());

        JPanel panel = new JPanel();
        for (SortingDisplay sortingDisplay : sortingDisplays) {
            sortingDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(sortingDisplay);

        }

        panelCont.add(panel, sortingDisplayKey);
        add(panelCont);
        setVisible(true);
    }

    // simple setter that will call the refresh method after updating the list of elements
    public void updateList(List<Integer> list, int index) {
        sortingDisplays[index].setList(list);
//        sortingDisplay.setList(list);
    }

    // will update the bar being moved (so it can be displayed differently)
    public void updateBarBeingMoved(int position, int index) {
        sortingDisplays[index].setBarBeingMovedPosition(position);
//        sortingDisplay.setBarBeingMovedPosition(position);
    }

    // will update the bar being compared to the one being moved (so it can be displayed differently)
    public void updateBarBeingEvaluated(List<Integer> evaluatedBarPositions, int index) {
        sortingDisplays[index].setEvaluatedBarPositions(evaluatedBarPositions);
//        sortingDisplay.setEvaluatedBarPositions(evaluatedBarPositions);
    }

    // will update on screen display
    public void refresh(int index) {
        sortingDisplays[index].repaint();
    }

    // simple setter
    public void displayFinish(String sortName, int swapCount, int index) {
        sortingDisplays[index].displayFinish(sortName, swapCount);
//        sortingDisplay.displayFinish(sortName, swapCount);
    }

    public void resetDisplay(int index) {
        sortingDisplays[index].resetDisplay();
    }

    // will make the sorting display panel the one being seen
    public void replacePanels(){
        cardLayout.show(panelCont, sortingDisplayKey);
    }

}
