package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.DateTimeException;

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

    AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        modal = this;
        // sets the event to a default of a meeting event
        event = new Meeting();

        this.setTitle("New Event");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLocation(200, 200);

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

    private void setPadding() {
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

    private void setMeetingPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        setDeadlinePanel();

        JLabel end_label = new JLabel("End Date and Time");
        end_label.setFont(new Font("", Font.PLAIN, 20));
        end_label.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(end_label, constraints);

        JLabel end_date_label = new JLabel("Day: ");
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(end_date_label, constraints);

        endDayEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(endDayEntry, constraints);

        JLabel end_month_label = new JLabel("Month: ");
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(end_month_label, constraints);

        endMonthEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 7;
        panel.add(endMonthEntry, constraints);

        JLabel end_year_label = new JLabel("Year: ");
        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        panel.add(end_year_label, constraints);

        endYearEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 8;
        panel.add(endYearEntry, constraints);

        JLabel end_hour_label = new JLabel("Hour: ");
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        panel.add(end_hour_label, constraints);

        endHourEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(endHourEntry, constraints);

        JLabel end_minute_label = new JLabel("Minute: ");
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        panel.add(end_minute_label, constraints);

        endMinuteEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 9;
        panel.add(endMinuteEntry, constraints);

        // sets location entry
        JLabel location_label = new JLabel("Location: ");
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        panel.add(location_label, constraints);

        locationEntry = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        panel.add(locationEntry, constraints);
    }

    private void setDeadlinePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;

        // sets name entry
        JLabel name_label = new JLabel("Name: ");
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(name_label, constraints);

        nameEntry = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(nameEntry, constraints);

        JLabel start_label = new JLabel("Start Date and Time");
        start_label.setFont(new Font("", Font.PLAIN, 20));
        start_label.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(start_label, constraints);

        JLabel start_date_label = new JLabel("Day: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(start_date_label, constraints);

        startDayEntry = new JTextField();
        startDayEntry.addActionListener(actionListener);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(startDayEntry, constraints);

        JLabel start_month_label = new JLabel("Month: ");
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(start_month_label, constraints);

        startMonthEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 3;
        panel.add(startMonthEntry, constraints);

        JLabel start_year_label = new JLabel("Year: ");
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(start_year_label, constraints);

        startYearEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 4;
        panel.add(startYearEntry, constraints);

        JLabel start_hour_label = new JLabel("Hour: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(start_hour_label, constraints);

        startHourEntry = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(startHourEntry, constraints);

        JLabel start_minute_label = new JLabel("Minute: ");
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(start_minute_label, constraints);

        startMinuteEntry = new JTextField();
        constraints.gridx = 3;
        constraints.gridy = 5;
        panel.add(startMinuteEntry, constraints);

    }

    private void addEvent() {
        boolean valid = true;
        String error = "";

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
            try {
                if (locationEntry.getText().compareTo("") == 0)
                    throw new Exception();
                meeting.setLocation(locationEntry.getText());
            } catch (Exception e) {
                error += " - Location must have a value";
                valid = false;
            }

        }
        try {
            if (nameEntry.getText().compareTo("") == 0) {
                throw new Exception();
            }
            event.setName(nameEntry.getText());
        } catch (Exception e) {
            error += " - Name must have a value";
            valid = false;
        }

        errorMessage.setText(error);

        // **********temp
        if (valid) {

            eventListPanel.addNewEvent(event);
            eventListPanel.revalidate();
            eventListPanel.repaint();
            this.dispose();
        }
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == meeting_button) {
                isMeeting = true;
                event = new Meeting();
                panel.removeAll();
                setButtons();
                setMeetingPanel();
                setPadding();
                panel.revalidate();
                panel.repaint();
            } else if (e.getSource() == deadline_button) {
                isMeeting = false;
                event = new Deadline();
                panel.removeAll();
                setButtons();
                setDeadlinePanel();
                setPadding();
                panel.revalidate();
                panel.repaint();
            } else if (e.getSource() == add_button) {
                addEvent();
            }

        }

    };
}
