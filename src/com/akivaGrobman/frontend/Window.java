package com.akivaGrobman.frontend;

import com.akivaGrobman.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Window extends JFrame {

    public static final Color blue = new Color(11, 93, 137);
    public static final Color green = new Color(3, 43, 23);
    private static final String sortingDisplayKey = Window.class.getSimpleName();
    private static final String welcomeDisplayKey = WelcomeDisplay.class.getSimpleName();
    private final SortingDisplay[] sortingDisplays;
    private final SortingDisplayInformation[] sortingDisplayInformation;
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

        // set display panel(that will contain all of the display panels
        JPanel panel = new JPanel();
        panel.setBackground(green);
        GridLayout sortingDisplayLayout = new GridLayout(getLayoutRowCount(numberOfAlgorithms), getLayoutColumnCount(numberOfAlgorithms));
        panel.setLayout(sortingDisplayLayout);
        Dimension preferredDisplayPanelSize = new Dimension(getWidth() / sortingDisplayLayout.getColumns(), getHeight() / sortingDisplayLayout.getRows());

        // initialize display panels and display info objects
        sortingDisplays = new SortingDisplay[numberOfAlgorithms];
        sortingDisplayInformation = new SortingDisplayInformation[numberOfAlgorithms];
        for (int i = 0; i < numberOfAlgorithms; i++) {
            sortingDisplayInformation[i] = new SortingDisplayInformation(Main.getSortName(i));
            sortingDisplays[i] = new SortingDisplay(sortingDisplayInformation[i], preferredDisplayPanelSize);
        }

        // add display panels to panel
        for (SortingDisplay sortingDisplay : sortingDisplays) {
            sortingDisplay.setBorder(BorderFactory.createLineBorder(blue));
            panel.add(sortingDisplay);
        }

        panelCont.add(panel, sortingDisplayKey);
        add(panelCont);
        setVisible(true);
    }

    // simple setter that will call the refresh method after updating the list of elements
    public void updateList(List<Integer> list, int index) {
        sortingDisplayInformation[index].setList(list);
    }

    // will update the bar being moved (so it can be displayed differently)
    public void updateBarBeingMoved(int position, int index) {
        sortingDisplayInformation[index].setBarBeingMovedPosition(position);
    }

    // will update the bar being compared to the one being moved (so it can be displayed differently)
    public void updateBarBeingEvaluated(List<Integer> evaluatedBarPositions, int index) {
        sortingDisplayInformation[index].setEvaluatedBarPositions(evaluatedBarPositions);
    }

    // will update on screen display
    public void refresh(int index) {
        sortingDisplays[index].repaint();
    }

    // will set the sorting display panel to the on visible
    public void replacePanels() {
        cardLayout.show(panelCont, sortingDisplayKey);
    }

    // these two methods keep this "matrix" as geometrical as possible
    private int getLayoutColumnCount(int numberOfAlgorithms) {
        return (int) Math.floor(Math.sqrt(numberOfAlgorithms));
    }

    private int getLayoutRowCount(int numberOfAlgorithms) {
        int height = (int) Math.floor(Math.sqrt(numberOfAlgorithms));
        if(numberOfAlgorithms % height != 0) {
            height++;
        }
        return height;
    }

}
