package src;

import java.lang.Comparable;
import java.time.LocalDateTime;

/**
 * Abstract class that represents and event with a name and date in
 * the form of a LocalDateTime object.
 */
public abstract class Event implements Comparable<Event> {
    private String name;
    private LocalDateTime dateTime;

    /**
     * Default constructor that sets name to "" and dateTime to now
     */
    public Event() {
        name = "";
        dateTime = LocalDateTime.now();
    }

    /**
     * Paramaterized constructor for Event class
     *
     * @param name     the name for the event
     * @param dateTime a LocalDateTime object to set as dateTime
     */
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * compares the dateTime of the event to the given event
     *
     * @param event the event to compare to
     */
    @Override
    public int compareTo(Event event) {
        return (this.dateTime.compareTo(event.getDateTime()));
    }

    /**
     * abstract class that should be overriden to complete the event
     */
    public abstract void complete();

    /**
     * abstract class that should be overriden to set
     */
    public abstract boolean isComplete();

}
