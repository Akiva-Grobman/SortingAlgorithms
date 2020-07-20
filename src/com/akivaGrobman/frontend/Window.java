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
    private final JPanel mainDisplayPanel;
    private final CardLayout cardLayout;

    public Window(int numberOfAlgorithms) {
        // will open the window to the full screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // will set the window title
        setTitle("Sorting visualizer - By Akiva Grobman");
        // will make the x button close the window and stop the program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // when the window is minimized this will be the default size(the smallest size that the opening display still looks normal)
        setMinimumSize(new Dimension(800, 438));
        // will locate the window in the middle of the screen when minimized
        setLocationRelativeTo(null);
        // we use the card layout so we can switch the panel on display
        mainDisplayPanel = new JPanel();
        cardLayout = new CardLayout();
        mainDisplayPanel.setLayout(cardLayout);
        // will add the welcome display to the mainDisplayPanel (and by default will be the first visible)
        WelcomeDisplay welcomeDisplay = new WelcomeDisplay();
        mainDisplayPanel.add(welcomeDisplay, welcomeDisplayKey);
        // set display panel(that will contain all of the display panels
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(green);
        GridLayout sortingDisplayLayout = new GridLayout(getLayoutRowCount(numberOfAlgorithms), getLayoutColumnCount(numberOfAlgorithms));
        displayPanel.setLayout(sortingDisplayLayout);
        // will determent the preferred panel size for when the window is minimized(based on the number of elements and full screen size
        Dimension preferredDisplayPanelSize = new Dimension(getWidth() / sortingDisplayLayout.getColumns(), getHeight() / sortingDisplayLayout.getRows());
        // initialize display panels and display info objects
        sortingDisplays = new SortingDisplay[numberOfAlgorithms];
        sortingDisplayInformation = new SortingDisplayInformation[numberOfAlgorithms];
        for (int i = 0; i < numberOfAlgorithms; i++) {
            sortingDisplayInformation[i] = new SortingDisplayInformation(Main.getSortName(i));
            sortingDisplays[i] = new SortingDisplay(sortingDisplayInformation[i], preferredDisplayPanelSize);
        }
        // add display panels to displayPanel
        for (SortingDisplay sortingDisplay : sortingDisplays) {
            sortingDisplay.setBorder(BorderFactory.createLineBorder(blue));
            displayPanel.add(sortingDisplay);
        }
        mainDisplayPanel.add(displayPanel, sortingDisplayKey);
        add(mainDisplayPanel);
        setVisible(true);
    }

    /*
     *  The next four methods take an index parameter.
     *  This is so we don't need to update the whole window
     *  We only want to update the one that had it's date has changed.
     */

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
        cardLayout.show(mainDisplayPanel, sortingDisplayKey);
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
