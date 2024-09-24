package src;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EventListPanel extends JPanel implements ItemListener {
    private ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox<String> sortDropDown;
    JPanel filterPanel;
    ArrayList<JCheckBox> filterDisplayboxes = new ArrayList<JCheckBox>();
    JButton addEventButton;

    public EventListPanel() {
        // sets up the EventListPanel
        this.setBackground(Color.orange);
        this.setLayout(new GridBagLayout());

        // sets up control panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBackground(Color.white);
        setControlPanel(controlPanel);
        GridBagConstraints control_constraints = new GridBagConstraints();
        control_constraints.fill = GridBagConstraints.BOTH;
        control_constraints.weightx = 0.1;
        control_constraints.weighty = .5;
        control_constraints.gridx = 1;
        control_constraints.gridy = 0;
        this.add(controlPanel, control_constraints);

        // sets up the display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 5));
        displayPanel.setBackground(Color.gray);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = .3;
        constraints.weighty = .5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(displayPanel, constraints);

        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);
        eventsAdd(displayPanel);

        // adds panels to EventListPanel
        this.add(displayPanel);
    }

    private void eventsAdd(JPanel panel) {
        EventPanel event = new EventPanel(new Deadline());
        panel.add(event);

    }

    private void setControlPanel(JPanel panel) {
        JLabel title = new JLabel("Control Panel");
        GridBagConstraints title_constraints = new GridBagConstraints();
        title_constraints.weightx = .3;
        title_constraints.weighty = 0;
        title_constraints.gridx = 0;
        title_constraints.gridy = 0;
        panel.add(title, title_constraints);

        // sets the sorting drop down
        String[] options = { "date", "name" };
        sortDropDown = new JComboBox<String>(options);
        sortDropDown.addItemListener(this);
        GridBagConstraints sort_c = new GridBagConstraints();
        sort_c.weightx = .3;
        sort_c.weighty = 0;
        sort_c.gridx = 0;
        sort_c.gridy = 1;
        panel.add(sortDropDown, sort_c);

        // sets the filter box panel with the filter boxes
        filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout());
        GridBagConstraints filter_c = new GridBagConstraints();
        filter_c.weightx = .3;
        filter_c.weighty = 0;
        filter_c.gridx = 0;
        filter_c.gridy = 2;
        panel.add(filterPanel, filter_c);
        // adds the check boxes to the panel
        JCheckBox remove_box = new JCheckBox("Remove Complete");
        filterDisplayboxes.add(remove_box);
        JCheckBox overdue_box = new JCheckBox("Overdue");
        filterDisplayboxes.add(overdue_box);
        JCheckBox complete_box = new JCheckBox("Complete");
        filterDisplayboxes.add(complete_box);
        JCheckBox deadline_box = new JCheckBox("Deadlines");
        filterDisplayboxes.add(deadline_box);
        JCheckBox meeting_box = new JCheckBox("Meetings");
        filterDisplayboxes.add(meeting_box);

        for (JCheckBox box : filterDisplayboxes) {
            filterPanel.add(box);
        }

        // sets the new event button
        addEventButton = new JButton("New Event");
        GridBagConstraints add_c = new GridBagConstraints();
        add_c.weightx = .3;
        add_c.weighty = 0;
        add_c.gridx = 0;
        add_c.gridy = 3;
        controlPanel.add(addEventButton, add_c);

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

    public void itemStateChanged(ItemEvent e) {

        events.sort((a, b) -> {
            return a.compareTo(b);
        });
        System.out.println("state changed");
    }
}
