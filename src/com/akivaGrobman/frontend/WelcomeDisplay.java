package com.akivaGrobman.frontend;

import com.akivaGrobman.Main;

import javax.swing.*;
import java.awt.*;

public class WelcomeDisplay extends JPanel {
    final int number_of_rows=6;//make it to kind of a box layout
    final JButton startButton;
    final JSlider slider;
    final String s1="Welcome to the sorting simulator";
    final String s2="Please select how much numbers you want to sort";
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("arial", Font.ITALIC, 50);
        g.setFont(font);
        int textWidth = g.getFontMetrics().stringWidth(s1);
        int width = (getWidth()-textWidth)/2;
        int height = getHeight()/number_of_rows;
        g.drawString(s1,width ,height );
        //set the button
        width = (getWidth()-startButton.getWidth())/2;
        height = (2*(getHeight()-startButton.getHeight()))/number_of_rows;//here it's 2*(...) because I want it to be on the second row
        startButton.setLocation(width,height + 200);
        //set the slider
        width = (getWidth()-slider.getWidth())/2;
        height = (int) ((2*(getHeight()-slider.getHeight()))/number_of_rows);
        slider.setLocation(width,height);
        font = new Font("arial", Font.ITALIC, 20);
        g.setFont(font);
        textWidth = g.getFontMetrics().stringWidth(s2);
        width = (getWidth()-textWidth)/2;
        g.drawString(s2,width ,height );


    }

    public WelcomeDisplay(){
        this.setLayout(null);//set as absolute layout

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
        this.add(slider);

        //create and set the start button
        startButton = new JButton("Start the simulation");
        startButton.setFont(new Font("arial",Font.ITALIC,35));
        startButton.setSize(350,100);
        startButton.addActionListener(e -> {
            //Main.setListSize(slider.getValue());
            try {
                Main.startSorting(slider.getValue());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        this.add(startButton);

    }
}
