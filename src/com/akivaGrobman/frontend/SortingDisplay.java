package com.akivaGrobman.frontend;

import com.akivaGrobman.backend.MergeSort;

import static com.akivaGrobman.frontend.Window.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class SortingDisplay extends JPanel {

    private final int FLOOR_HEIGHT = 40;
    private final int MINIMUM_HEIGHT = 10;
    private final int HEIGHT_MARGIN = 10;
    private List<Integer> list;
    private int barBeingMovedPosition;
    private int barBeingEvaluatedPosition;
    private boolean isSorted;
    private int swapCount;
    private String sortName;

    SortingDisplay() {
        isSorted = false;
        setBackground(green);
    }

    // simple setter
    void setList(List<Integer> list) {
        this.list = list;
    }

    void setBarBeingMovedPosition(int position) {
        barBeingMovedPosition = position;
    }

    void setBarBeingEvaluatedPosition(int position) {
        barBeingEvaluatedPosition = position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final double WIDTH_MARGIN = (getWidth() / 2.0) / (double) list.size();
        int x = (int) WIDTH_MARGIN;
        int y;
        int yResize = 1;
        int minY = Integer.MAX_VALUE;
        for (Integer i: list) {
            minY = Integer.min(minY, getY(i));
        }
        // this will make sure we don't go off screen
        // going off screen can happen if the number of elements is to large if this does happen we divided all high by our resize var which will grow relatively to the some of elements
        while (minY < 20) {
            yResize++;
            minY = getY(minY) / yResize;
        }
        // drawing the black "floor"
        g.setColor(Color.black);
        g.fillRect(0, getHeight() - FLOOR_HEIGHT, getWidth(), FLOOR_HEIGHT);
        // drawing the elements
        g.setColor(blue);
        for (int i = 0; i < list.size(); i++) {
            int element = list.get(i);
            // setting the starter y for each element
            y = getY(element);
            // resize if necessary(see longer explanation above
            if(yResize != 1) {
                y += (element * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize;
            }
            // if this is the bar being moved(aka being sorted) we change the color
            if(i == barBeingMovedPosition) {
                g.setColor(new Color(187, 17, 45));
            } else if(i == barBeingEvaluatedPosition) {
                g.setColor(new Color(23, 255, 35));
            }
            // drawing a rectangle relative to the element size
            g.fillRect(x, y, (int) WIDTH_MARGIN, (element * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize);
            // if we changed the bar color we reset it
            if(i == barBeingMovedPosition || i == barBeingEvaluatedPosition) {
                g.setColor(blue);
            }
            // if there's room on screen will draw the element value above the rectangle
            if(list.size() < 100) {
                g.drawString(String.valueOf(element), x, y - 20);
            }
            // moving next x by the size of the rectangle X 2, one for the actual rectangle and one for the space between the rectangles
            x += (2 * WIDTH_MARGIN);
        }
        // ending display
        if(isSorted) {
            Font font = new Font("arial", Font.ITALIC, 50);
            g.setFont(font);
            g.drawString("Done!", 50, 100);
            g.drawString(sortName + " took " + swapCount + " swaps to sort", 50, 175);
        }
    }

    private int getY(int i) {
        // setting y relative to the "floor", element height, and minimum height(in case the elements value is 0 it will still get represented
        return (getHeight() - FLOOR_HEIGHT) - (i * HEIGHT_MARGIN) - MINIMUM_HEIGHT;
    }

    // simple setter
    void displayFinish(String sortName, int swapCount) {
        isSorted = true;
        this.swapCount = swapCount;
        this.sortName = sortName;
    }

    void resetDisplay() {
        isSorted = false;
    }

}
