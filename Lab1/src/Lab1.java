/**
 * Лабораторна робота №1
 * Варіант: C5=2, C7=3, C11=6
 */
public class Lab1 {
    public static void main(String[] args) {
        // Ініціалізація матриць A та B (типу int)
        int[][] A = {
            {1, 5, 9},
            {2, 6, 8},
            {3, 4, 7}
        };
        
        int[][] B = {
            {9, 2, 1},
            {8, 4, 3},
            {7, 5, 6}
        };
        
        // Перевірка розмірностей матриць
        if (A.length != B.length || A[0].length != B[0].length) {
            System.out.println("Помилка: Матриці різних розмірів!");
            return;
        }
        
        int rows = A.length;
        int cols = A[0].length;
        int[][] C = new int[rows][cols];
        
        // Дія 1: C = A + B
        System.out.println("Матриця C (A + B):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                C[i][j] = A[i][j] + B[i][j];
                System.out.print(C[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Дія 2: Обчислення суми за умовою C11=6
        // Парні номери стовпців (індекси 0, 2, 4...) -> найбільший елемент
        // Непарні номери стовпців (індекси 1, 3, 5...) -> найменший елемент
        int targetSum = 0;
        
        System.out.println("\nОбчислення екстремумів по стовпцях:");
        for (int j = 0; j < cols; j++) {
            int extremeVal = C[0][j];
            for (int i = 1; i < rows; i++) {
                if (j % 2 == 0) { 
                    // Парний стовпець - шукаємо максимум
                    if (C[i][j] > extremeVal) {
                        extremeVal = C[i][j];
                    }
                } else { 
                    // Непарний стовпець - шукаємо мінімум
                    if (C[i][j] < extremeVal) {
                        extremeVal = C[i][j];
                    }
                }
            }
            targetSum += extremeVal;
            System.out.println("Стовпець " + j + " (парність " + (j % 2 == 0 ? "парний" : "непарний") + "), екстремум: " + extremeVal);
        }
        
        System.out.println("\nСума екстремумів: " + targetSum);
    }
}
