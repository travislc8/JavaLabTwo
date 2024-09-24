package src;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;

public class EventListPanel extends JPanel implements ItemListener {
    ArrayList<Event> allEvents = new ArrayList<>();
    ArrayList<Event> filteredEvents = new ArrayList<>();

    ArrayList<EventPanel> eventPanels = new ArrayList<>();
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox<String> sortDropDown;
    String[] dropDownBoxOptions = { "", "Name", "Date" };
    JPanel filterPanel;
    ArrayList<JCheckBox> filterDisplayboxes = new ArrayList<JCheckBox>();
    JButton addEventButton;
    boolean[] filterFlags = {false,false,false,false,false};

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

        eventsAdd(displayPanel, "a", LocalDateTime.now());
        eventsAdd(displayPanel, "g", LocalDateTime.now().plusSeconds(10));
        eventsAdd(displayPanel, "b", LocalDateTime.now().plusSeconds(60));
        eventsAdd(displayPanel, "c", LocalDateTime.now().plusSeconds(40));
        eventsAdd(displayPanel, "e", LocalDateTime.now().plusSeconds(50));
        eventsAdd(displayPanel, "d", LocalDateTime.now().plusSeconds(30));
        eventsAdd(displayPanel, "f", LocalDateTime.now().plusSeconds(20));
        eventsAdd2(displayPanel, "meeting a", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5), "here");

        filteredEvents = allEvents;
        System.out.println("all size: " + allEvents.size() + " filtered: " + filteredEvents.size());
        
        setDisplayPanel();
        this.add(displayPanel, constraints);

        // adds panels to EventListPanel
        this.add(displayPanel);
    }
    
    private void eventsAdd2(JPanel panel, String name, LocalDateTime start, LocalDateTime end, String location) {
        Meeting meeting = new Meeting( name, start, end, location);
        allEvents.add(meeting);
    }

    private void eventsAdd(JPanel panel, String name, LocalDateTime dateTime) {
        Deadline deadline = new Deadline(name, dateTime);
        allEvents.add(deadline);

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
        sortDropDown = new JComboBox<String>(dropDownBoxOptions);
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
            box.addItemListener(this);
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

    private void setDisplayPanel() {
        eventPanels.clear();
        for (Event event: filteredEvents) {
            EventPanel event_panel = new EventPanel(event);
            eventPanels.add(event_panel);
            displayPanel.add(event_panel);
        }

    }

    private void sortEvents() {
            if (sortDropDown.getSelectedItem() == dropDownBoxOptions[1]) {
                filteredEvents.sort((a, b) -> {
                    return a.getName().compareTo(b.getName());
                });
            }
            else if (sortDropDown.getSelectedItem() == dropDownBoxOptions[2]) {
                filteredEvents.sort((a, b) -> {
                    return a.compareTo(b);
                });
            }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == sortDropDown) {
            sortEvents();
        }
        else if (e.getSource() == filterDisplayboxes.get(0)) {
            if (e.getStateChange() == 1) {
                filterFlags[0] = true;
                filterEvents();
            }
            else {
                filterFlags[0] = false;
                filterEvents();
            }
        }
        else if (e.getSource() == filterDisplayboxes.get(1)) {
            if (e.getStateChange() == 1) {
                filterFlags[1] = true;
                filterEvents();
            }
            else {
                filterFlags[1] = false;
                filterEvents();
            }
        }
        else if (e.getSource() == filterDisplayboxes.get(2)) {
            if (e.getStateChange() == 1) {
                filterFlags[2] = true;
                filterEvents();
            }
            else {
                filterFlags[2] = false;
                filterEvents();
            }
        }
        else if (e.getSource() == filterDisplayboxes.get(3)) {
            if (e.getStateChange() == 1) {
                filterFlags[3] = true;
                filterEvents();
            }
            else {
                filterFlags[3] = false;
                filterEvents();
            }
        }
        else if (e.getSource() == filterDisplayboxes.get(4)) {
            if (e.getStateChange() == 1) {
                filterFlags[4] = true;
                filterEvents();
            }
            else {
                filterFlags[4] = false;
                filterEvents();
            }
        }
        else 
            return;

        System.out.println("complete");
        displayPanel.removeAll();
        setDisplayPanel();
        displayPanel.revalidate();
    }

    private void filterEvents() {

        System.out.println("in filter");
        System.out.println("all size: " + allEvents.size() + " filtered: " + filteredEvents.size());
        filteredEvents = new ArrayList<>();
        System.out.println("all size: " + allEvents.size() + " filtered: " + filteredEvents.size());
        Event temp;
        for (int i = 0; i < allEvents.size(); i++) {
            temp = allEvents.get(i);
            // filters out the completed tasks
            if (temp.isComplete() == filterFlags[0] && filterFlags[0]) 
                continue;
            //filters out events that are not overdue
            if (temp.getDateTime().compareTo(LocalDateTime.now()) >= 0 && filterFlags[1])
                continue;
            // filters out events that are not completed
            if (temp.isComplete() != filterFlags[2] && filterFlags[2]) 
                continue;
            // filters out everything but deadlines
            if (!(temp instanceof Deadline) && filterFlags[3]) 
                continue;
            // filters out everything but meetings
            if (!(temp instanceof Meeting) && filterFlags[4]) 
                continue;

            filteredEvents.add(allEvents.get(i));
        }
        sortEvents();
        displayPanel.revalidate();
        displayPanel.repaint();
        
    }
}
