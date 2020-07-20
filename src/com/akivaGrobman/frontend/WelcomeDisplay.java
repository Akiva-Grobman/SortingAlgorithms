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
        Font font = new Font("arial", Font.ITALIC, 50);
        String welcomeString = "Welcome to the sorting simulator";
        String instructionsString = "Please select how many numbers you want to sort";
        // draws welcome string
        int number_of_rows = 6;
        g.setFont(font);
        int textWidth = g.getFontMetrics().stringWidth(welcomeString);
        int width = (getWidth() - textWidth) / 2;
        int height = (getHeight() - (int) font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), instructionsString).getVisualBounds().getHeight()) / number_of_rows;
        g.setColor(blue);
        g.drawString(welcomeString, width, height);
        // set the slider (this is done before the string that's rendered above so we can draw the string relative to this height
        width = (getWidth() - slider.getWidth()) / 2;
        height = (getHeight() - slider.getHeight()) / 2;
        slider.setLocation(width, height);
        // draws instructions string
        font = new Font("arial", Font.ITALIC, 20);
        g.setFont(font);
        textWidth = g.getFontMetrics().stringWidth(instructionsString);
        width = (getWidth() - textWidth) / 2;
        height -= (int) font.createGlyphVector(g.getFontMetrics().getFontRenderContext(), instructionsString).getVisualBounds().getHeight();
        g.drawString(instructionsString, width, height);
        // set the button
        width = (getWidth() - startButton.getWidth()) / 2;
        height = (2 * (getHeight() - startButton.getHeight())) / number_of_rows;
        startButton.setLocation(width,height + 2 * (getHeight() / number_of_rows));
    }

    private void addButton() {
        startButton = new JButton("Start the simulation");
        startButton.setFont(new Font("arial", Font.ITALIC, 35));
        startButton.setSize(350,100);
        startButton.setForeground(blue);
        startButton.addActionListener(e -> Main.startSorting(slider.getValue()));
        add(startButton);
    }

    private void addSlider() {
        slider = new JSlider();
        slider.setSize(350,100);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setMaximum(100);
        slider.setMinimum(20);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setValue(60);
        slider.setBackground(green);
        slider.setForeground(blue);
        add(slider);
    }

}
