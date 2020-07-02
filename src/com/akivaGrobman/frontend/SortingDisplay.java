package com.akivaGrobman.frontend;

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
    private int changeCount;
    private String sortName;

    SortingDisplay() {
        isSorted = false;
        setBackground(green);
    }

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
        // todo find a better dividing mechanism
        while (minY < 20) {
            yResize++;
            minY = getY(minY) / yResize;
        }
        g.setColor(Color.black);
        g.fillRect(0, getHeight() - FLOOR_HEIGHT, getWidth(), FLOOR_HEIGHT);
        g.setColor(blue);
        for (Integer integer : list) {
            y = getY(integer);
            if(yResize != 1) {
                y += (integer * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize;
            }
            g.fillRect(x, y, (int)WIDTH_MARGIN, (integer * HEIGHT_MARGIN + MINIMUM_HEIGHT) / yResize);
            if(integer < 100) {
                g.drawString(String.valueOf(integer), x, y - 20);
            }
            x += (2 * WIDTH_MARGIN);
        }
        if(isSorted) {
            Font font = new Font("arial", Font.ITALIC, 50);
            g.setFont(font);
            g.drawString("Done!", 50, 100);
            g.drawString(sortName + " took " + changeCount + " times to sort", 50, 175);
        }
    }

    private int getY(int i) {
        return (getHeight() - FLOOR_HEIGHT) - (i * HEIGHT_MARGIN) - MINIMUM_HEIGHT;
    }

    public void displayFinish(String sortName, int changeCount) {
        isSorted = true;
        this.changeCount = changeCount;
        this.sortName = sortName;
    }

}
