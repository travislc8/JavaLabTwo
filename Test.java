import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.of(2024, 12, 7, 17, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 12, 7, 17, 0);

        var event = new Event("test", date);
        var event2 = new Event("test", date2);

        System.out.println(event2.compareTo(event));
    }
}
