package com.akivaGrobman.frontend;

import static com.akivaGrobman.frontend.Window.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class SortingDisplay extends JPanel {

    private final int MINIMUM_HEIGHT = 10;
    private double HEIGHT_MARGIN;
    private int FLOOR_HEIGHT;
    private List<Integer> list;
    private int barBeingMovedPosition;
    private List<Integer> evaluatedBarPositions;
    private boolean isSorted;
    private int swapCount;
    private String sortName;

    SortingDisplay() {
        isSorted = false;
        list = new ArrayList<>();
        setBackground(green);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FLOOR_HEIGHT = getHeight() / 20;
        HEIGHT_MARGIN = (getHeight() - (getHeight() / (double)list.size())) / (double)list.size();
        int yResize = 1;
        // this will make sure we don't go off screen
        yResize = getYResize(yResize);
        // drawing the black "floor"
        g.setColor(Color.black);
        g.fillRect(0, getHeight() - FLOOR_HEIGHT, getWidth(), FLOOR_HEIGHT);
        // draw elements
        drawElements(g, yResize);
        if(isSorted) {
            drawEndingDisplay(g);
        }
    }

    // draws the elements
    private void drawElements(Graphics g, int yResize) {
        final double WIDTH_MARGIN = (getWidth() / 2d) / (double) list.size();
        int x = (int) WIDTH_MARGIN;
        int y;
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
            } else if(evaluatedBarPositions.contains(i)) {
                g.setColor(new Color(23, 255, 35));
            }
            // drawing a rectangle relative to the element size
            g.fillRect(x, y, (int) WIDTH_MARGIN, (int) ((element * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize));
            g.setColor(blue);
            // if there's room on screen will draw the element value above the rectangle
            if(list.size() < 100) {
                g.drawString(String.valueOf(element), x, y - 20);
            }
            // moving next x by the size of the rectangle X 2, one for the actual rectangle and one for the space between the rectangles
            x += (2 * WIDTH_MARGIN);
        }
    }

    // will get the resize factor (this will change with the screen size and sum of elements)
    private int getYResize(int yResize) {
        // going off screen can happen if the number of elements is to large if this does happen we divided all y's by our resize var which will grow relatively to the sum of elements
        int minY = Integer.MAX_VALUE;
        // find the tallest element in the array
        for (Integer i: list) {
            minY = Integer.min(minY, getY(i));
        }
        while (minY < 20) {
            yResize++;
            minY = getY(minY) / yResize;
        }
        return yResize;
    }

    // will calculate the y for each element
    private int getY(int i) {
        // setting y relative to the "floor", element height, and minimum height(in case the elements value is 0 it will still get represented
        return (int) ((getHeight() - FLOOR_HEIGHT) - (i * HEIGHT_MARGIN) - MINIMUM_HEIGHT);
    }

    // draws ending display
    private void drawEndingDisplay(Graphics g) {
        Font font = new Font("arial", Font.ITALIC, 50);
        g.setFont(font);
        g.drawString("Done!", 50, 100);
        g.drawString(sortName + " took " + swapCount + " swaps to sort", 50, 175);
    }

}
