package src;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.awt.Graphics;
import java.time.LocalDateTime;
import java.lang.Math;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventPanel extends JPanel implements ActionListener {
    private Event event;
    private JButton completeButton;
    private JLabel complete;

    public EventPanel() {
        // sets the Event Panel
        this.setPreferredSize(new Dimension(200, 200));
        // this.setLayout(new GridLayout(0, 1));
        this.setBackground(Color.lightGray);

        event = new Deadline("default", LocalDateTime.now());
        completeButton = new JButton("Complete");
        this.add(completeButton);

        // sets up panel
        this.setLayout(new GridLayout(0, 1));

        this.setSharedValues();

        // sets the values shared by meeting and deadline
        if (event instanceof Deadline deadline) {
            System.out.println("deadline");
            setDeadlineValues(deadline);
        } else if (event instanceof Meeting meeting) {
            System.out.println("meeting");
            setMeetingValues(meeting);
        } else
            System.out.println("bad");
        completeButton.addActionListener(this);
    }

    public EventPanel(Event event) {
        // sets the Event Panel
        this.setPreferredSize(new Dimension(200, 200));
        // this.setLayout(new GridLayout(0, 1));
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(0, 1));

        completeButton = new JButton("Complete");
        this.add(completeButton);

        this.event = event;

        this.setSharedValues();

        // sets the values shared by meeting and deadline
        if (event instanceof Deadline deadline) {
            System.out.println("deadline");
            setDeadlineValues(deadline);
        } else if (event instanceof Meeting meeting) {
            System.out.println("meeting");
            setMeetingValues(meeting);
        } else
            System.out.println("bad");
        completeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.event.complete();
        String complete_string = "";
        if (event.isComplete())
            complete_string = "Meeting is complete.";
        else
            complete_string = "Meeting is yet to come.";
        complete.setText(complete_string);
        this.remove(completeButton);
        this.repaint();

    }

    private void setDeadlineValues(Deadline event) {
        // sets the completion message
        String complete_string;
        if (event.isComplete())
            complete_string = "Meeting is complete.";
        else
            complete_string = "Meeting is yet to come.";
        complete = new JLabel(complete_string);
        this.add(complete);

    }

    private void setMeetingValues(Meeting event) {
        // sets the completion message
        String complete_string;
        if (event.isComplete())
            complete_string = "Meeting is complete.";
        else
            complete_string = "Meeting is yet to come.";

        complete = new JLabel(complete_string);
        this.add(complete);

        // sets the location
        JLabel location = new JLabel(event.getLocation());
        this.add(location);

        // sets the duration
        Duration duration = event.getDuration();
        int hours = Math.round(duration.getSeconds() / 3600);
        int minutes = Math.round((duration.getSeconds() - hours * 3600) / 60);
        String duration_string = "Event Duration = " + hours + " hours";
        if (minutes > 0)
            duration_string += " & " + minutes + " minutes";
        JLabel duration_label = new JLabel(duration_string);
        this.add(duration_label);
    }

    private void setSharedValues() {
        this.add(completeButton);
        // set the name
        JLabel name = new JLabel(event.getName());
        this.add(name);

        // set the date
        LocalDateTime dateTime = event.getDateTime();
        String dateString = "" + dateTime.getDayOfMonth()
                + " " + dateTime.getMonth()
                + ", " + dateTime.getYear();
        JLabel date = new JLabel(dateString);
        this.add(date);

        // sets the time
        String timeString = "" + dateTime.getHour()
                + ":";
        // adds the leading 0 for minutes less than 10
        if (dateTime.getMinute() < 10)
            timeString += "0" + dateTime.getMinute();
        else
            timeString += dateTime.getMinute();

        JLabel time = new JLabel(timeString);
        this.add(time);

    }
}
