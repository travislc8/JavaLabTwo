import java.time.LocalDateTime;
import java.time.Duration;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting() {
    }

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

    public Duration getDuration() {
        int hours = endDateTime.getHour() - getDateTime().getHour();
        int minutes = endDateTime.getMinute() - getDateTime().getMinute();
        int seconds = endDateTime.getSecond() - getDateTime().getSecond();

        int length = hours * 3600 + minutes * 60 + seconds;
        Duration duration = Duration.ofSeconds(length);
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
