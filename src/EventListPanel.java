package src;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;

    public EventListPanel() {
        // sets up the EventListPanel
        this.setBackground(Color.orange);
        this.setLayout(new GridLayout(1, 0));

        // sets up the display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout());
        displayPanel.setBackground(Color.gray);
        eventsAdd();

        // adds panels to EventListPanel
        this.add(displayPanel);
    }

    private void eventsAdd() {
        GridBagConstraints constraints = new GridBagConstraints();
        EventPanel panel = new EventPanel(new Deadline());
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        displayPanel.add(panel, constraints);

        EventPanel panel2 = new EventPanel(new Meeting());
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        displayPanel.add(panel2, constraints);

    }

    private void gridTest() {
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.red);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        displayPanel.add(panel, constraints);

        panel = new JPanel();
        panel.setBackground(Color.white);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        displayPanel.add(panel, constraints);

        panel = new JPanel();
        panel.setBackground(Color.blue);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        displayPanel.add(panel, constraints);

        this.add(displayPanel);
    }
}
