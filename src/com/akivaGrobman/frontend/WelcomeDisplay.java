package com.akivaGrobman.frontend;

import com.akivaGrobman.Main;

import javax.swing.*;
import java.awt.*;

class WelcomeDisplay extends JPanel {

    private final JButton startButton;
    private final JSlider slider;

    WelcomeDisplay(){
        //set as absolute layout
        setLayout(null);
        //set the slider
        slider = new JSlider();
        slider.setSize(350,100);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setMaximum(100);
        slider.setMinimum(20);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setValue(60);
        add(slider);
        //set the start button
        startButton = new JButton("Start the simulation");
        startButton.setFont(new Font("arial", Font.ITALIC, 35));
        startButton.setSize(350,100);
        startButton.addActionListener(e -> {
            try {
                Main.startSorting(slider.getValue());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("arial", Font.ITALIC, 50);
        g.setFont(font);
        String welcomeString = "Welcome to the sorting simulator";
        String instructionsString = "Please select how much numbers you want to sort";
        int textWidth = g.getFontMetrics().stringWidth(welcomeString);
        int width = (getWidth()-textWidth) / 2;
        int number_of_rows = 6;
        int height = getHeight() / number_of_rows;
        // draws welcome string
        g.drawString(welcomeString, width, height);
        //set the button
        width = (getWidth() - startButton.getWidth()) / 2;
        height = (2 * (getHeight() - startButton.getHeight())) / number_of_rows;
        startButton.setLocation(width,height + 200);
        //set the slider
        width = (getWidth() - slider.getWidth()) / 2;
        height = (2 * (getHeight() - slider.getHeight())) / number_of_rows;
        slider.setLocation(width, height);
        // draws instructions string
        font = new Font("arial", Font.ITALIC, 20);
        g.setFont(font);
        textWidth = g.getFontMetrics().stringWidth(instructionsString);
        width = (getWidth() - textWidth) / 2;
        g.drawString(instructionsString, width, height);
    }

}
