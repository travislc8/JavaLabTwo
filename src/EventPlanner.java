package src;

import java.awt.*;
import javax.swing.*;

public class EventPlanner {

    public static void main(String[] args) {
        // creats a JFrame that the GUI runs in
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));

        EventListPanel event_test = new EventListPanel();
        frame.getContentPane().add(event_test);

        // displays the frame
        frame.pack();
        frame.setVisible(true);
    }
}
