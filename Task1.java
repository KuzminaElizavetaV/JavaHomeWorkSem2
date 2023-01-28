/* В файле содержится строка с исходными данными в такой форме:
   {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
   Требуется на её основе построить и вывести на экран новую строку, в форме SQL запроса:
   SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city = "Moscow";
   Для разбора строки используйте String.split. Сформируйте новую строку, используя StringBuilder.
   Значения null не включаются в запрос.

   Структура программы (создать методы):
    1. Получить строку на основе данных из файла
    3. Разбор строки (удаление лишних символов)
    4. Создание новой строки в форме SQL-запроса
*/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        createStringSqlRequest(getSplitString(getStringFromFile("Task1.txt")));
    }
    static String getStringFromFile(String fName) {
        File file = new File(fName);
        try (Scanner sc = new Scanner(file)) {
            String line = sc.nextLine();
            System.out.println("Строка из файла: " + line);
            return line;
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
        return fName;
    }
    static String[] getSplitString(String str) {
        String str1 = str.substring(1, str.length() - 1); //"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"
        String[] arrStr = str1.split(", ");//["name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"]
        int size = arrStr.length;
        ArrayList<String> parametres = new ArrayList<String>(); //ArrayList использую чтобы не заморачиваться с размером массива
        for (int i = 0; i < size; i++) {                        // также удобно добавлять нужные элементы в массив
            String[] param = arrStr[i].split(":");
            if (!param[1].contains("null")) { //если "значение" не равно "null"
                parametres.add(param[0]); //добавляю уловный "ключ" в ArrayList
                parametres.add(param[1]); //добавляю условное "значение" в ArrayList
            }
        }
        String[] paramArray = parametres.toArray(new String[0]); //на выходе мне нужен просто массив для дальнейших манипуляций
        System.out.println("Массив из разбитой строки: " + Arrays.toString(paramArray));
        return paramArray;
    }
    static String createStringSqlRequest(String[] values) {
        int size = values.length;
        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("SELECT * FROM students WHERE ");
        int count = 0;
        for (int i = 0; i < size - 2; i+=2) {
            sbSQL.append(values[i],1,values[i].length()-1).append(" = ").append(values[i+1]).append(" AND ");
            count+=2;
        }
        sbSQL.append(values[count],1,values[count].length()-1).append(" = ").append(values[count+1]).append(";");
        System.out.println("SQL-запрос: " + sbSQL);
        return sbSQL.toString();
    }
}

