import java.lang.Comparable;
import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    private String name;
    private LocalDateTime dateTime;

    public Event() {
        name = "";
        dateTime = LocalDateTime.of(2024, 12, 7, 17, 0);
    }

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

    public int compareTo(Event e) {
        return (this.dateTime.compareTo(e.getDateTime()));
    }

}
