/* В файле содержится строка с данными:
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
 {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
 {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Напишите метод, который разберёт её на составные части и, используя StringBuilder создаст массив строк такого вида:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        String fileName = "Task3.txt";
        createStudentProgressReport(getSplitString(getStringFromFile(fileName)));
    }
    static String getStringFromFile(String fileName) { //получение строки из файла
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            String line = sc.nextLine();
            System.out.println("СТРОКА ИЗ ФАЙЛА: " + line);
            return line;
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
        return fileName;
    }
    static String[] getSplitString(String str) { //разбор строки на составные части
        String str1 = str.substring(2, str.length() - 2); //убираем по 2 символа с обеих сторон исходной строки т.е. [{ и }]
        String[] arrStr = str1.split("[}{]");   //разбиение строки сразу по 2 символам с помощью регулярных выражений
        ArrayList<String> studentGrades = new ArrayList<>(); //ArrayList использую чтобы не заморачиваться с размером массива
        for (String strElem : arrStr){
            if (strElem.length() > 2){
                String[] allValues = strElem.split(",");
                for (String value : allValues){
                    String[] keyValue = value.split(":");
                    studentGrades.add(keyValue[1]);
                }
            }
        }
        String[] gradesArray = studentGrades.toArray(new String[0]); //на выходе мне нужен просто массив значений типа ["Иванов", "5", "Математика",
        System.out.println("МАССИВ НА ВЫХОДЕ: " + Arrays.toString(gradesArray)); // "Петрова", "4", "Информатика", "Краснов", "5", "Физика"]
        System.out.println();                                                    // для дальнейших манипуляций
        return gradesArray;
    }
    static void createStudentProgressReport(String[] values) { // создание отчета об успеваемости студентов в требуемом формате
        int size = values.length;
        StringBuilder sbReport = new StringBuilder();
        for (int i = 0; i < size; i+=3) {
            sbReport.append("Студент ").append(values[i],1,values[i].length()-1).append(" получил ").
                    append(values[i+1], 1, values[i+1].length()-1).append(" по предмету ").
                    append(values[i+2], 1, values[i+2].length()-1).append(".\n");
            }
        System.out.println(sbReport);
    }
}
