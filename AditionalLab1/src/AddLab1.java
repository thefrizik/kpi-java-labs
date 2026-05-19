import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Додаткова лабораторна робота №1 (Stream API)
 * Завдання на базі Лабораторної роботи №3 (Спортивний інвентар).
 */

// Клас Спортивний Інвентар
class SportsEquipment {
    private String name;
    private String brand;
    private double price;
    private double weight;

    public SportsEquipment(String name, String brand, double price, double weight) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.weight = weight;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }

    @Override
    public String toString() {
        return String.format("%s %s (Ціна: %.2f, Вага: %.2f)", name, brand, price, weight);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SportsEquipment that = (SportsEquipment) obj;
        return Double.compare(that.price, price) == 0 &&
               Double.compare(that.weight, weight) == 0 &&
               name.equals(that.name) &&
               brand.equals(that.brand);
    }
}

public class AdditionalLab1 {
    public static void main(String[] args) {
        // Колекція об'єктів (замість простого масиву використовуємо List для Stream)
        List<SportsEquipment> equipmentList = Arrays.asList(
            new SportsEquipment("Футбольний м'яч", "Nike", 1500.0, 0.45),
            new SportsEquipment("Тенісна ракетка", "Wilson", 3200.0, 0.3),
            new SportsEquipment("Гантеля", "Bowflex", 1500.0, 10.0),
            new SportsEquipment("Скакалка", "Adidas", 500.0, 0.15),
            new SportsEquipment("Килимок", "Lululemon", 2500.0, 1.2)
        );

        System.out.println("--- Оригінальний список ---");
        equipmentList.forEach(System.out::println);

        SportsEquipment target = new SportsEquipment("Гантеля", "Bowflex", 1500.0, 10.0);
        System.out.println("\nШуканий об'єкт: " + target + "\n");

        System.out.println("--- Виконання Stream API Pipeline ---");
        
        // Використання Stream API:
        // 1. filter() - перший проміжний (відфільтровуємо некоректні ціни)
        // 2. sorted() - другий проміжний (сортування за ціною ↑, потім за вагою ↓)
        // 3. peek()   - третій проміжний (виводимо відсортовані елементи)
        // 4. filter() - четвертий проміжний (шукаємо заданий об'єкт)
        // 5. findFirst() - термінальний метод
        
        Optional<SportsEquipment> foundEquipment = equipmentList.stream()
            .filter(eq -> eq.getPrice() > 0) // Проміжний 1
            .sorted(Comparator.comparing(SportsEquipment::getPrice)
                              .thenComparing(Comparator.comparing(SportsEquipment::getWeight).reversed())) // Проміжний 2
            .peek(eq -> System.out.println("Відсортовано: " + eq)) // Проміжний 3
            .filter(eq -> eq.equals(target)) // Проміжний 4
            .findFirst(); // Термінальний

        System.out.println("\n--- Результат пошуку ---");
        if (foundEquipment.isPresent()) {
            System.out.println("Об'єкт знайдено успішно: " + foundEquipment.get());
        } else {
            System.out.println("Об'єкт не знайдено.");
        }
    }
}
