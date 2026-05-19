import java.util.Arrays;
import java.util.Comparator;

/**
 * Базовий клас для музичної композиції
 */
abstract class Track {
    private String title;
    private double duration; // Тривалість у хвилинах
    private String style;

    public Track(String title, double duration, String style) {
        this.title = title;
        this.duration = duration;
        this.style = style;
    }

    public String getTitle() { return title; }
    public double getDuration() { return duration; }
    public String getStyle() { return style; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %.2f хв", style, title, duration);
    }
}

// Класи-нащадки
class RockTrack extends Track {
    public RockTrack(String title, double duration) { super(title, duration, "Rock"); }
}

class PopTrack extends Track {
    public PopTrack(String title, double duration) { super(title, duration, "Pop"); }
}

class JazzTrack extends Track {
    public JazzTrack(String title, double duration) { super(title, duration, "Jazz"); }
}

/**
 * Клас Альбом (Диск)
 */
class Album {
    private Track[] tracks;

    public Album(Track[] tracks) {
        this.tracks = tracks;
    }

    // 1. Порахувати тривалість альбому
    public double calculateTotalDuration() {
        double total = 0;
        for (Track track : tracks) {
            total += track.getDuration();
        }
        return total;
    }

    // 2. Перестановка (сортування) композицій на основі стилю
    public void sortTracksByStyle() {
        Arrays.sort(tracks, Comparator.comparing(Track::getStyle));
    }

    // 3. Пошук композиції за діапазоном довжини
    public Track findTrackByDurationRange(double min, double max) {
        for (Track track : tracks) {
            if (track.getDuration() >= min && track.getDuration() <= max) {
                return track;
            }
        }
        return null;
    }

    public void printTracks() {
        for (Track track : tracks) {
            System.out.println(track);
        }
    }
}

public class Lab5 {
    public static void main(String[] args) {
        try {
            Track[] myTracks = {
                new RockTrack("Bohemian Rhapsody", 5.9),
                new PopTrack("Thriller", 5.95),
                new JazzTrack("Take Five", 5.4),
                new RockTrack("Stairway to Heaven", 8.0),
                new PopTrack("Billie Jean", 4.9)
            };

            Album myAlbum = new Album(myTracks);

            System.out.println("--- Список треків в альбомі ---");
            myAlbum.printTracks();

            System.out.printf("\nЗагальна тривалість альбому: %.2f хв\n", myAlbum.calculateTotalDuration());

            System.out.println("\n--- Треки після сортування за стилем ---");
            myAlbum.sortTracksByStyle();
            myAlbum.printTracks();

            double minRange = 4.5;
            double maxRange = 5.5;
            System.out.printf("\n--- Пошук треку в діапазоні %.2f - %.2f хв ---\n", minRange, maxRange);
            Track found = myAlbum.findTrackByDurationRange(minRange, maxRange);
            if (found != null) {
                System.out.println("Знайдено: " + found);
            } else {
                System.out.println("Треків у заданому діапазоні не знайдено.");
            }
        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }
}
