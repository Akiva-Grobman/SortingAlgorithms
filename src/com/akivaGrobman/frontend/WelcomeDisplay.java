package com.akivaGrobman.frontend;

import static com.akivaGrobman.frontend.Window.*;
import com.akivaGrobman.Main;
import javax.swing.*;
import java.awt.*;

class WelcomeDisplay extends JPanel {

    private JButton startButton;
    private JSlider slider;

    WelcomeDisplay(){
        setBackground(green);
        setLayout(null);
        addSlider();
        addButton();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // will set the font and text values
        Font font = new Font("arial", Font.ITALIC, 50);
        g.setFont(font);
        String welcomeString = "Welcome to the sorting simulator";
        String instructionsString = "Please select how many numbers you want to sort";
        int number_of_rows = 6;
        // will draw the welcomeString at the top center of the window
        int textWidth = g.getFontMetrics().stringWidth(welcomeString);
        int width = (getWidth() - textWidth) / 2;
        int height = (getHeight() - (int) font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), instructionsString).getVisualBounds().getHeight()) / number_of_rows;
        g.setColor(blue);
        g.drawString(welcomeString, width, height);
        // set the slider (this is done before the string that's rendered above so we can draw the string relative to this height
        width = (getWidth() - slider.getWidth()) / 2;
        height = (getHeight() - slider.getHeight()) / 2;
        slider.setLocation(width, height);
        // draws instructions string (above the slider)
        font = new Font("arial", Font.ITALIC, 20);
        g.setFont(font);
        textWidth = g.getFontMetrics().stringWidth(instructionsString);
        width = (getWidth() - textWidth) / 2;
        height -= (int) font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), instructionsString).getVisualBounds().getHeight();
        g.drawString(instructionsString, width, height);
        // set the button that will start sorting
        width = (getWidth() - startButton.getWidth()) / 2;
        height = (2 * (getHeight() - startButton.getHeight())) / number_of_rows;
        startButton.setLocation(width,height + 2 * (getHeight() / number_of_rows));
    }

    // will add the button
    private void addButton() {
        // will set the button text
        startButton = new JButton("Start the simulation");
        // will set the text font
        startButton.setFont(new Font("arial", Font.ITALIC, 35));
        // will set the button size
        startButton.setSize(350,100);
        // will set the button text and background color
        startButton.setForeground(green);
        startButton.setBackground(blue);
        // will add a listener to the button that will call the start sorting method in main when clicked
        startButton.addActionListener(e -> Main.startSorting(slider.getValue()));
        add(startButton);
    }

    // will add the slider
    private void addSlider() {
        slider = new JSlider();
        // will set the size
        slider.setSize(350,100);
        // will set the space between the lines on the slider
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        // will set the maximum and minimum numbers on the slider
        slider.setMaximum(100);
        slider.setMinimum(20);
        // will draw the numbers and lines
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        // will set the default value
        slider.setValue(60);
        // will set the slider colors
        slider.setBackground(green);
        slider.setForeground(blue);
        add(slider);
    }

}
