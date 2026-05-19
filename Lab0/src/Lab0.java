/**
 * Лабораторна робота №0
 * Варіант: C2=1, C3=2, C5=2, C7=3
 */
public class Lab0 {
    public static void main(String[] args) {
        // Оголошення меж сум
        int a = 1; // Нижня межа для i
        int n = 5; // Верхня межа для i
        int b = 1; // Нижня межа для j
        int m = 5; // Верхня межа для j
        
        final int C = 2; // Константа
        double S = 0.0;  // Результат
        
        // Подвійний цикл для обчислення суми
        for (int i = a; i <= n; i++) {
            // Перевірка ділення на нуль (i - C)
            if (i == C) {
                System.out.println("Пропуск ітерації: знаменник дорівнює нулю при i = " + i);
                continue;
            }
            for (int j = b; j <= m; j++) {
                // Перевірка ділення на нуль при взятті остачі (i % j)
                if (j == 0) {
                    System.out.println("Пропуск ітерації: ділення на нуль при j = 0");
                    continue;
                }
                S += (double) (i % j) / (i - C);
            }
        }
        
        System.out.println("Результат обчислення S = " + S);
    }
}
