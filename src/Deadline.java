package src;

import java.time.LocalDateTime;

/**
 * Deadline class that holds information about an evetn
 * Extends Event
 * Implements Completable
 */
public class Deadline extends Event implements Completable {
    private boolean complete = false;

    /**
     * Default constructor for Deadline class
     */
    public Deadline() {
        setName("default name");
        setDateTime(LocalDateTime.now());
    }

    /**
     * Parameterized constructor that takes a name and dateTime
     *
     * @param name     string for deadline name
     * @param dateTime LocalDateTime object to set the dateTime
     */
    public Deadline(String name, LocalDateTime dateTime) {
        setName(name);
        setDateTime(dateTime);
    }

    public boolean isComplete() {
        return complete;
    }

    /**
     * Sets complete to true
     */
    public void complete() {
        complete = true;
    }

}
