package com.akivaGrobman.frontend;

import static com.akivaGrobman.frontend.Window.*;
import javax.swing.*;
import java.awt.*;

class SortingDisplay extends JPanel {

    private final SortingDisplayInformation sortingDisplayInformation;
    private final int MINIMUM_HEIGHT = 10; // todo might want to change this from a literal value
    private double HEIGHT_MARGIN;
    private int FLOOR_HEIGHT;

    SortingDisplay(SortingDisplayInformation sortingDisplayInformation, Dimension preferredDisplayPanelSize) {
        this.sortingDisplayInformation = sortingDisplayInformation;
        setPreferredSize(preferredDisplayPanelSize);
        setBackground(green);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FLOOR_HEIGHT = getHeight() / 20;
        HEIGHT_MARGIN = (getHeight() - (getHeight() / (double)sortingDisplayInformation.list.size())) / (double)sortingDisplayInformation.list.size();
        // draw sort name
        g.setColor(blue);
        Font font = new Font(g.getFont().getFontName(), g.getFont().getStyle(), getHeight() / 15);
        g.setFont(font);
        g.drawString(sortingDisplayInformation.sortName, (getWidth() - g.getFontMetrics().stringWidth(sortingDisplayInformation.sortName)) / 2, getHeight() / 5);
        // draw "floor"
        drawFloor(g);
        // draw elements
        drawElements(g);
    }

    // draws the floor in black and writes which sort is being used
    private void drawFloor(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, getHeight() - FLOOR_HEIGHT, getWidth(), FLOOR_HEIGHT);
    }

    // draws the elements
    private void drawElements(Graphics g) {
        final int listSize = sortingDisplayInformation.list.size();
        final double WIDTH_MARGIN = (getWidth() / (double) listSize) / 2d;
        int yResize = getYResize();
        int x = 2 * (int) WIDTH_MARGIN;
        int y;
        g.setColor(blue);
        for (int i = 0; i < listSize; i++) {
            int element = sortingDisplayInformation.list.get(i);
            // setting the starter y for each element
            y = getY(element);
            // resize if necessary(see longer explanation above
            if(yResize != 1) {
                y += (element * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize;
            }
            // if this is the bar being moved(aka being sorted) we change the color
            if(i == sortingDisplayInformation.barBeingMovedPosition) {
                g.setColor(new Color(187, 17, 45));
            } else if(sortingDisplayInformation.evaluatedBarPositions.contains(i)) {
                g.setColor(new Color(23, 255, 35));
            }
            // drawing a rectangle relative to the element size
            g.fillRect(x, y, (int) WIDTH_MARGIN, (int) ((element * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize));
            g.setColor(blue);
            // if there's room on screen will draw the element value above the rectangle
            if(yResize == 1) {
                g.drawString(String.valueOf(element), x, y - 20);
            }
            // moving next x by the size of the rectangle X 2, one for the actual rectangle and one for the space between the rectangles
            x += (2 * WIDTH_MARGIN);
        }
    }

    // will get the resize factor (this will change with the screen size and sum of elements)
    private int getYResize() {
        int yResize = 1;
        // going off screen can happen if the number of elements is to large if this does happen we divided all y's by our resize var which will grow relatively to the sum of elements
        int minY = Integer.MAX_VALUE;
        // find the tallest element in the array
        for (Integer i: sortingDisplayInformation.list) {
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

}
