/* Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите
 в лог-файл.
*/
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task2 {
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        int size = getNumberByUser("Задайте размер массиву чисел: ");
        int start = getNumberByUser("Введите минимальное значение элемента массива: ");
        int end = getNumberByUser("Введите минимальное значение элемента массива: ");
        bubbleSortByLogger(fillNumArrRandom(size, start, end));
    }
    static int getNumberByUser(String text){ // 1. Получение числа от пользолвателя.
        System.out.print(text);
        return input.nextInt();
    }
    static int[] fillNumArrRandom(int size, int min, int max) { // 2. Заполнение числового масиива рандомно на основе полученных чисел от пользователя
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.ints(min,(max+1)).findFirst().getAsInt();
        }
        return arr;
    }
    static void myLog(String res) {      // 3. Логирование
        Logger logger = Logger.getLogger(Task2.class.getName());
        try {
            FileHandler fh = new FileHandler("log.txt", true);
            logger.addHandler(fh);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat);
            logger.info(res);
            fh.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    static void bubbleSortByLogger(int[] numArr){ // 4. Сортировка массива "пузырьком" с добавлением логирования
        myLog("ИСХОДНЫЙ МАССИВ: " + Arrays.toString(numArr));
        //System.out.println("Исходный массив: ");
        int count = 1;
        for (int i = 1; i < numArr.length; i++) {
            for (int j = 0; j < numArr.length-i; j++) {
                if (numArr[j] > numArr [j+1]){
                    int temp = numArr [j];
                    numArr[j] = numArr[j+1];
                    numArr[j+1] = temp;
                    myLog("ИТЕРАЦИЯ № " + count + " ===> " + Arrays.toString(numArr));
                    count++;
                }
            }
        }
        myLog("ОТСОРТИРОВАННЫЙ МАССИВ: " + Arrays.toString(numArr));
    }
}
