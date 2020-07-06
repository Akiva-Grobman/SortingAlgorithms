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
        // going off screen can happen if the number of elements is to large if this does happen we divided all y's by our resize var which will grow relatively to the sum of elements
        while (minY < 20) {
            yResize++;
            minY = getY(minY) / yResize;
        }
        // drawing the black "floor"
        g.setColor(Color.black);
        g.fillRect(0, getHeight() - FLOOR_HEIGHT, getWidth(), FLOOR_HEIGHT);
        // drawing the elements
        g.setColor(blue);
        for (Integer integer : list) {
            // setting the starter y for each element
            y = getY(integer);
            // resize if necessary(see longer explanation above
            if(yResize != 1) {
                y += (integer * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize;
            }
            // drawing a rectangle relative to the element size
            g.fillRect(x, y, (int)WIDTH_MARGIN, (integer * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize);
            // if there's room on screen will draw the element value above the rectangle
            if(list.size() < 100) {
                g.drawString(String.valueOf(integer), x, y - 20);
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
    public void displayFinish(String sortName, int swapCount) {
        isSorted = true;
        this.swapCount = swapCount;
        this.sortName = sortName;
    }

    public void resetDisplay() {
        isSorted = false;
    }
}
