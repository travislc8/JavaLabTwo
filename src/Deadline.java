package src;

import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete = false;

    public Deadline() {
        setName("default name");
        setDateTime(LocalDateTime.now());
    }

    public Deadline(String name, LocalDateTime dateTime) {
        setName(name);
        setDateTime(dateTime);
    }

    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }
}
