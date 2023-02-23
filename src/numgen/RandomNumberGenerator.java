/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package numgen;

/**
 *
 * @author HP
 */
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGenerator {

    private static final int MIN_RANGE = -10000000;
    private static final int MAX_RANGE = 10000000;
    private static final int ARRAY_SIZE = 1000000;
    private static final String FILE_NAME = "Numeros_generados.txt";

    public static void crear() {
        int[] numbers = generateRandomNumbers(ARRAY_SIZE, MIN_RANGE, MAX_RANGE);
        writeToFile(numbers, FILE_NAME);
        System.out.println("Random numbers generated and saved to file: " + FILE_NAME);
        
        int[] sortedNumbers = readFromFileAndSort(FILE_NAME);
        System.out.println("Random numbers sorted and saved to file: sorted_" + FILE_NAME);
    }

    private static int[] generateRandomNumbers(int arraySize, int minRange, int maxRange) {
        int[] numbers = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = random.nextInt(maxRange - minRange + 1) + minRange;
        }
        return numbers;
    }

    private static void writeToFile(int[] numbers, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int number : numbers) {
                writer.write(String.valueOf(number));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readFromFileAndSort(String fileName) {
        int[] numbers = new int[ARRAY_SIZE];
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int i = 0;
            while (scanner.hasNextInt()) {
                numbers[i++] = scanner.nextInt();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selectionSort(numbers);
        writeToFile(numbers, "sorted_" + fileName);
        return numbers;
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

