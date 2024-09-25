package src;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Class that holds the detail of a meeting
 * Extends the event class
 * Implements the Completable interface
 */
public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete = false;

    /**
     * Default constructor
     * Sets the date to now and the end to 1 hour from now
     */
    public Meeting() {
        setName("default name");
        location = "default location";
        setDateTime(LocalDateTime.now());
        LocalDateTime temp = getDateTime().plusHours(1);
        setEndDateTime(temp);
    }

    /**
     * Parameterized constructor
     *
     * @param name     the name of the event
     * @param start    the start date and time of the meeting
     * @param end      the end data and time of the meeting
     * @param location the locaiton of the meeting
     */
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        setName(name);
        setDateTime(start);
        endDateTime = end;
        this.location = location;
    }

    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Gets a Duration object containing the duration of the meeting
     *
     * @return duration of the meeting
     */
    public Duration getDuration() {
        // gets the duration between the start and end time
        Duration duration = Duration.between(getDateTime(), endDateTime);

        return duration;
    }

    public String getLocation() {
        return location;
    }

    public void setEndDateTime(LocalDateTime end) {
        endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
