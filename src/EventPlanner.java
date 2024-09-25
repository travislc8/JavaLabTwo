package src;

import java.awt.*;
import javax.swing.*;

/**
 * Class that creates a JFrame and Sets a EventListPanel inside of it
 */
public class EventPlanner {

    public static void main(String[] args) {
        // creates a JFrame that the GUI runs in
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        // creates and adds the EventListPanel to the JFrame
        EventListPanel event_test = new EventListPanel();
        frame.getContentPane().add(event_test);

        // displays the frame
        frame.pack();
        frame.setVisible(true);
    }
}
