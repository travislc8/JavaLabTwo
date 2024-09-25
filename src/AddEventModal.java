package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.DateTimeException;

/**
 * Class that creates a new JFrame that allows a user to input
 * data for a new deadline object of meeting object
 */
public class AddEventModal extends JFrame {

    AddEventModal modal;
    JPanel panel;
    Event event;
    EventListPanel eventListPanel;
    JButton meeting_button;
    JButton deadline_button;
    JButton add_button;
    JTextField nameEntry;
    JTextField locationEntry;
    JTextField startYearEntry;
    JTextField endYearEntry;
    JTextField startDayEntry;
    JTextField endDayEntry;
    JTextField startMonthEntry;
    JTextField endMonthEntry;
    JTextField startHourEntry;
    JTextField endHourEntry;
    JTextField startMinuteEntry;
    JTextField endMinuteEntry;
    JLabel errorMessage;
    boolean isMeeting = true;

    /**
     * Constructor of AddEventModel
     * Creates and displays the window
     *
     * @param eventListPanel Holds the calling panel to be able to send back data
     */
    AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        modal = this;
        // sets the event to a default of a meeting event
        event = new Meeting();

        // sets the charactereistics of the JFrame
        this.setTitle("New Event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLocation(200, 200);

        // sets the JPanel that holds the labels and boxes for input
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 500));
        setButtons();
        setMeetingPanel();
        setPadding();
        this.add(panel);

        // displays the frame
        this.pack();
        this.setVisible(true);
    }

    /**
     * Sets the buttons that let the user decide between
     * creating a meeting or a deadline
     */
    private void setButtons() {
        // sets meeting button
        meeting_button = new JButton("Meeting");
        meeting_button.addActionListener(actionListener);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(meeting_button, constraints);

        // sets deadline button
        deadline_button = new JButton("Deadline");
        deadline_button.addActionListener(actionListener);
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(deadline_button, constraints);

    }

    /**
     * Sets the add event button and adds padding to the screen
     */
    private void setPadding() {

        // sets a JLabel that holds the error message and makes it large to
        // pad the page
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        errorMessage = new JLabel();
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 4;
        panel.add(errorMessage, constraints);

        // sets and adds a button to add the event and close the JFrame
        add_button = new JButton("Add Event");
        add_button.addActionListener(actionListener);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.gridwidth = 4;
        panel.add(add_button, constraints);
    }

    /**
     * Sets the input boxes that are specific to the meeting event
     */
    private void setMeetingPanel() {
        // sets the default characteristics for the gird bag layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // sets the elements that are shared between the meeting and deadline events
        setDeadlinePanel();

        // sets the title for the end date time entries
        JLabel end_label = new JLabel("End Date and Time");
        end_label.setFont(new Font("", Font.PLAIN, 20));
        end_label.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(end_label, constraints);

        // label for day input
        JLabel end_date_label = new JLabel("Day: ");
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(end_date_label, constraints);

        // input box for day
        endDayEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(endDayEntry, constraints);

        // label for month input
        JLabel end_month_label = new JLabel("Month: ");
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(end_month_label, constraints);

        // input box for month
        endMonthEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 7;
        panel.add(endMonthEntry, constraints);

        // label for year input
        JLabel end_year_label = new JLabel("Year: ");
        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        panel.add(end_year_label, constraints);

        // input box for year
        endYearEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 8;
        panel.add(endYearEntry, constraints);

        // label for hour input
        JLabel end_hour_label = new JLabel("Hour: ");
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        panel.add(end_hour_label, constraints);

        // input box for hour
        endHourEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(endHourEntry, constraints);

        // label for minute
        JLabel end_minute_label = new JLabel("Minute: ");
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        panel.add(end_minute_label, constraints);

        // input box for minute
        endMinuteEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 9;
        panel.add(endMinuteEntry, constraints);

        // lable for locaiont input
        JLabel location_label = new JLabel("Location: ");
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        panel.add(location_label, constraints);

        // input box for location
        locationEntry = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        panel.add(locationEntry, constraints);
    }

    /**
     * sets the input boxes required for a deadline event
     *
     */
    private void setDeadlinePanel() {
        // sets the shared characteristics for the grid bag contraint
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;

        // label for name entry
        JLabel name_label = new JLabel("Name: ");
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(name_label, constraints);

        // input box for name
        nameEntry = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(nameEntry, constraints);

        // label for start date and time entries
        JLabel start_label = new JLabel("Start Date and Time");
        start_label.setFont(new Font("", Font.PLAIN, 20));
        start_label.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(start_label, constraints);

        // label for day entry
        JLabel start_date_label = new JLabel("Day: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(start_date_label, constraints);

        // input box for day
        startDayEntry = new JTextField();
        startDayEntry.addActionListener(actionListener);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(startDayEntry, constraints);

        // lable for month entry
        JLabel start_month_label = new JLabel("Month: ");
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(start_month_label, constraints);

        // input box for month entry
        startMonthEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 3;
        panel.add(startMonthEntry, constraints);

        // label for year entry
        JLabel start_year_label = new JLabel("Year: ");
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(start_year_label, constraints);

        // input box for year entry
        startYearEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 4;
        panel.add(startYearEntry, constraints);

        // label for hour entry
        JLabel start_hour_label = new JLabel("Hour: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(start_hour_label, constraints);

        // input box for hour entry
        startHourEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(startHourEntry, constraints);

        // label for minute entry
        JLabel start_minute_label = new JLabel("Minute: ");
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(start_minute_label, constraints);

        // input box for minute entry
        startMinuteEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 5;
        panel.add(startMinuteEntry, constraints);

    }

    /**
     * Adds the input values to the event and closes out the jframe
     * Does not close the window if there is a bad value and shows an error message
     */
    private void addEvent() {
        boolean valid = true;
        String error = "";

        // parses the start date time and shows an error if there is a bad value
        int month = 0, day = 0, year = 0, hour = 0, minute = 0;
        try {
            month = Integer.parseInt(startMonthEntry.getText());
            day = Integer.parseInt(startDayEntry.getText());
            year = Integer.parseInt(startYearEntry.getText());
            hour = Integer.parseInt(startHourEntry.getText());
            minute = Integer.parseInt(startMinuteEntry.getText());
            event.setDateTime(LocalDateTime.of(year, month, day, hour, minute));
        } catch (Exception e) {
            error += " - Invalid Start Date";
            valid = false;
        }

        // if the event is a meeting, parses the end date time
        // shows an error if there is a bad value
        if (event instanceof Meeting meeting) {
            int end_month = 0, end_day = 0, end_year = 0, end_hour = 0, end_minute = 0;
            try {
                end_month = Integer.parseInt(endMonthEntry.getText());
                end_day = Integer.parseInt(endDayEntry.getText());
                end_year = Integer.parseInt(endYearEntry.getText());
                end_hour = Integer.parseInt(endHourEntry.getText());
                end_minute = Integer.parseInt(endMinuteEntry.getText());
                meeting.setEndDateTime(LocalDateTime.of(end_year, end_month, end_day, end_hour, end_minute));
            } catch (Exception e) {
                error += " - Invalid End Date";
                valid = false;
            }

            // sets the events location if it is not empty
            if (locationEntry.getText().compareTo("") != 0) {
                meeting.setLocation(locationEntry.getText());
            } else {
                error += " - Location must have a value";
                valid = false;
            }

        }

        // sets the events name if it is not empty
        if (nameEntry.getText().compareTo("") != 0) {
            event.setName(nameEntry.getText());
        } else {
            error += " - Name must have a value";
            valid = false;
        }

        // sets the error message text
        errorMessage.setText(error);

        // if the inputs were all valid, adds the event and closes the panel
        if (valid) {
            eventListPanel.addNewEvent(event);
            eventListPanel.revalidate();
            eventListPanel.repaint();
            this.dispose();
        }
    }

    /**
     * Action Listener for the buttons
     */
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // case for meeting button selection
            // sets the input fields to accept for a meeting
            if (e.getSource() == meeting_button) {
                isMeeting = true;
                event = new Meeting();
                panel.removeAll();
                setButtons();
                setMeetingPanel();
                setPadding();
                panel.revalidate();
                panel.repaint();
            }
            // case for the deadline button selection
            // sets the input fields to accept for a deadline
            else if (e.getSource() == deadline_button) {
                isMeeting = false;
                event = new Deadline();
                panel.removeAll();
                setButtons();
                setDeadlinePanel();
                setPadding();
                panel.revalidate();
                panel.repaint();
            }
            // case for the add new event button
            else if (e.getSource() == add_button) {
                addEvent();
            }
        }
    };
}
