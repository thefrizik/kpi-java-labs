import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас, що описує спортивний інвентар.
 */
class SportsEquipment {
    private String name;
    private String brand;
    private double price;
    private double weight;
    private int yearOfProduction;

    public SportsEquipment(String name, String brand, double price, double weight, int yearOfProduction) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.yearOfProduction = yearOfProduction;
    }

    // Getters
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }
    public int getYearOfProduction() { return yearOfProduction; }

    @Override
    public String toString() {
        return String.format("Спортивний інвентар [назва=%s, бренд=%s, ціна=%.2f, вага=%.2f, рік=%d]", 
                             name, brand, price, weight, yearOfProduction);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SportsEquipment that = (SportsEquipment) obj;
        return Double.compare(that.price, price) == 0 &&
               Double.compare(that.weight, weight) == 0 &&
               yearOfProduction == that.yearOfProduction &&
               name.equals(that.name) &&
               brand.equals(that.brand);
    }
}

public class Lab3 {
    public static void main(String[] args) {
        // Створення масиву об'єктів
        SportsEquipment[] equipmentList = {
            new SportsEquipment("Футбольний м'яч", "Nike", 1500.0, 0.45, 2023),
            new SportsEquipment("Тенісна ракетка", "Wilson", 3200.0, 0.3, 2022),
            new SportsEquipment("Гантеля", "Bowflex", 1500.0, 10.0, 2021),
            new SportsEquipment("Скакалка", "Adidas", 500.0, 0.15, 2023),
            new SportsEquipment("Килимок для йоги", "Lululemon", 2500.0, 1.2, 2022)
        };

        System.out.println("Оригінальний масив:");
        for (SportsEquipment eq : equipmentList) {
            System.out.println(eq);
        }

        // Сортування: за зростанням ціни, а при рівності - за спаданням ваги
        Arrays.sort(equipmentList, Comparator.comparing(SportsEquipment::getPrice)
                                             .thenComparing(Comparator.comparing(SportsEquipment::getWeight).reversed()));

        System.out.println("\nВідсортований масив (за ціною ↑, потім за вагою ↓):");
        for (SportsEquipment eq : equipmentList) {
            System.out.println(eq);
        }

        // Пошук ідентичного об'єкта
        SportsEquipment target = new SportsEquipment("Гантеля", "Bowflex", 1500.0, 10.0, 2021);
        boolean found = false;
        
        System.out.println("\nПошук об'єкта: " + target);
        for (SportsEquipment eq : equipmentList) {
            if (eq.equals(target)) {
                System.out.println("Об'єкт успішно знайдено у масиві!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Об'єкт не знайдено.");
        }
    }
}
