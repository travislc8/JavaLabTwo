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

    public EventPanel(Event event) {
        // sets the Event Panel
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(0, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.event = event;

        this.setSharedValues();

        // sets the values not shared by meeting and deadline
        if (event instanceof Deadline deadline) {
            setDeadlineValues(deadline);
        } else if (event instanceof Meeting meeting) {
            setMeetingValues(meeting);
        } else
            System.out.println("bad");

        // add complete button if it is not complete
        if (!event.isComplete()) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(this);
            this.add(completeButton);
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.event.complete();
        String complete_string = "";
        if (event.isComplete())
            complete_string = "Event is complete";
        else
            complete_string = "Event is not complete";
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
            complete_string = "Event is yet to come.";
        complete = new JLabel(complete_string);
        this.add(complete);

    }

    private void setMeetingValues(Meeting event) {

        // sets the location
        String location_string = "Location: " + event.getLocation();
        JLabel location = new JLabel(location_string);
        this.add(location);

        // sets the duration
        Duration duration = event.getDuration();
        int hours = Math.round(duration.getSeconds() / 3600);
        int minutes = Math.round((duration.getSeconds() - hours * 3600) / 60);
        String duration_string = "Duration = " + hours + " hours";
        if (minutes > 0)
            duration_string += " & " + minutes + " minutes";
        JLabel duration_label = new JLabel(duration_string);
        this.add(duration_label);
        //
        // sets the completion message
        String complete_string;
        if (event.isComplete())
            complete_string = "Meeting is complete.";
        else
            complete_string = "Meeting is yet to come.";

        complete = new JLabel(complete_string);
        this.add(complete);
    }

    private void setSharedValues() {
        // set the name
        String name_string = "Event Name: " + event.getName();
        JLabel name = new JLabel(name_string);
        this.add(name);

        // set the date
        LocalDateTime dateTime = event.getDateTime();
        String dateString = "Event Date: " + dateTime.getDayOfMonth()
                + " " + dateTime.getMonth()
                + ", " + dateTime.getYear();
        JLabel date = new JLabel(dateString);
        this.add(date);

        // sets the time
        String timeString = "Event Time: " + dateTime.getHour()
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
