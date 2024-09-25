package src;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventListPanel extends JPanel {
    ArrayList<Event> allEvents = new ArrayList<>();
    ArrayList<Event> filteredEvents = new ArrayList<>();

    ArrayList<EventPanel> eventPanels = new ArrayList<>();
    JPanel controlPanel;
    JPanel displayPanel;
    JComboBox<String> sortDropDown;
    String[] dropDownBoxOptions = { "", "Name", "Date", "Revers Name" };
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
        controlPanel.setBackground(Color.lightGray);
        setControlPanel(controlPanel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(controlPanel, constraints);

        // sets up the display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        displayPanel.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(displayPanel, c);

        eventsAdd(displayPanel, "a", LocalDateTime.now());
        eventsAdd(displayPanel, "g", LocalDateTime.now().plusSeconds(10));
        eventsAdd(displayPanel, "b", LocalDateTime.now().plusSeconds(60));
        eventsAdd(displayPanel, "d", LocalDateTime.now().plusSeconds(30));
        eventsAdd(displayPanel, "f", LocalDateTime.now().plusSeconds(20));
        eventsAdd2(displayPanel, "meeting a", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5), "here");
        eventsAdd2(displayPanel, "meeting b", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5), "here");

        filteredEvents = allEvents;

        setDisplayPanel();

    }

    private void eventsAdd2(JPanel panel, String name, LocalDateTime start, LocalDateTime end, String location) {
        Meeting meeting = new Meeting(name, start, end, location);
        allEvents.add(meeting);
    }

    private void eventsAdd(JPanel panel, String name, LocalDateTime dateTime) {
        Deadline deadline = new Deadline(name, dateTime);
        allEvents.add(deadline);

    }

    private void setControlPanel(JPanel panel) {
        // sets the title
        JLabel title = new JLabel("Event List");
        title.setFont(new Font("", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints title_constraints = new GridBagConstraints();
        title_constraints.anchor = GridBagConstraints.PAGE_START;
        title_constraints.fill = GridBagConstraints.HORIZONTAL;
        title_constraints.weightx = .3;
        title_constraints.weighty = 0;
        title_constraints.gridx = 0;
        title_constraints.gridy = 0;
        title_constraints.gridwidth = 4;
        panel.add(title, title_constraints);

        // sets the sorting label
        JLabel sort_label = new JLabel("Sort by: ");
        sort_label.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 5;
        panel.add(sort_label, c);

        // sets the sorting drop down
        sortDropDown = new JComboBox<String>(dropDownBoxOptions);
        sortDropDown.addItemListener(itemListener);
        GridBagConstraints sort_c = new GridBagConstraints();
        sort_c.fill = GridBagConstraints.HORIZONTAL;
        sort_c.weightx = .1;
        sort_c.weighty = 0;
        sort_c.gridx = 1;
        sort_c.gridy = 1;
        c.ipadx = 50;
        panel.add(sortDropDown, sort_c);

        // sets the filter label
        JLabel filter_label = new JLabel("  Filter by: ");
        filter_label.setHorizontalAlignment(SwingConstants.RIGHT);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        panel.add(filter_label, c);

        // sets the filter box panel with the filter boxes
        filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout());
        GridBagConstraints filter_c = new GridBagConstraints();
        filter_c.fill = GridBagConstraints.HORIZONTAL;
        filter_c.weightx = .1;
        filter_c.weighty = 0;
        filter_c.gridx = 3;
        filter_c.gridy = 1;
        filter_c.ipadx = 50;
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
            box.addItemListener(itemListener);
            filterPanel.add(box);
        }

        // sets the new event button
        addEventButton = new JButton("New Event");
        GridBagConstraints add_c = new GridBagConstraints();
        add_c.weighty = 0;
        add_c.gridx = 0;
        add_c.gridy = 2;
        add_c.gridwidth = 4;
        addEventButton.addActionListener(e -> {
            new AddEventModal(this);
        });
        controlPanel.add(addEventButton, add_c);

    }

    public void addNewEvent(Event event) {
        allEvents.add(event);
        filteredEvents = allEvents;
        removeFilters();
        setDisplayPanel();
        filterEvents();
    }

    private void setDisplayPanel() {
        displayPanel.removeAll();
        eventPanels.clear();
        for (Event event : filteredEvents) {
            EventPanel event_panel = new EventPanel(event);
            eventPanels.add(event_panel);
            displayPanel.add(event_panel);
        }

    }

    private void removeFilters() {
        for (JCheckBox box : filterDisplayboxes) {
            box.setSelected(false);
        }
    }

    private void sortEvents() {
        // case sort by name
        if (sortDropDown.getSelectedItem() == dropDownBoxOptions[1]) {
            filteredEvents.sort((a, b) -> {
                return a.getName().compareTo(b.getName());
            });
        }
        // case sort by date
        else if (sortDropDown.getSelectedItem() == dropDownBoxOptions[2]) {
            filteredEvents.sort((a, b) -> {
                return a.compareTo(b);
            });
        }
        // case sort by revers name
        else if (sortDropDown.getSelectedItem() == dropDownBoxOptions[3]) {
            filteredEvents.sort((a, b) -> {
                return b.getName().compareTo(a.getName());
            });
        }
    }

    ItemListener itemListener = new ItemListener() {

        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == sortDropDown) {
                sortEvents();
            } else if (filterDisplayboxes.contains(e.getSource())) {
                filterEvents();
            } else
                return;

            displayPanel.removeAll();
            setDisplayPanel();
            displayPanel.revalidate();
        }
    };

    private void filterEvents() {

        filteredEvents = new ArrayList<>();
        Event temp;
        boolean flag;
        for (int i = 0; i < allEvents.size(); i++) {
            temp = allEvents.get(i);
            // filters out the completed tasks
            flag = filterDisplayboxes.get(0).isSelected();
            if (temp.isComplete() == flag && flag)
                continue;
            // filters out events that are not overdue
            flag = filterDisplayboxes.get(1).isSelected();
            if (temp.getDateTime().compareTo(LocalDateTime.now()) >= 0 && flag)
                continue;
            // filters out events that are not completed
            flag = filterDisplayboxes.get(2).isSelected();
            if (temp.isComplete() != flag && flag)
                continue;
            // filters out everything but deadlines
            flag = filterDisplayboxes.get(3).isSelected();
            if (!(temp instanceof Deadline) && flag)
                continue;
            // filters out everything but meetings
            flag = filterDisplayboxes.get(4).isSelected();
            if (!(temp instanceof Meeting) && flag)
                continue;

            filteredEvents.add(allEvents.get(i));
        }
        sortEvents();
        displayPanel.revalidate();
        displayPanel.repaint();

    }
}
