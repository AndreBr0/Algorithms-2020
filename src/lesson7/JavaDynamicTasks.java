package lesson7;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.Math.min;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        if (first == null || second == null || first.length() == 0 || second.length() == 0) return "";
        if (first.equals(second)) return first;

        var matrix = new char[first.length() + 1][second.length() + 1][];

        for (int i = 0; i <= first.length(); i++)
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0 || j == 0) matrix[i][j] = new char[0];
                else if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    matrix[i][j] = Arrays.copyOf(matrix[i - 1][j - 1], matrix[i - 1][j - 1].length + 1);
                    matrix[i][j][matrix[i - 1][j - 1].length] = first.charAt(i - 1);
                } else
                    matrix[i][j] = matrix[i - 1][j].length > matrix[i][j - 1].length ? matrix[i - 1][j] : matrix[i][j - 1];
            }
        return new String(matrix[first.length()][second.length()]);
    }

    //    time: O(length(first) * length(second))
    //    memory: O(length(first) * length(second))

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        List<String> list = new ArrayList<>();
        File fileRead = new File(inputName);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRead))) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                list.add(string);
            }
        }
        int column = list.get(0).split(" ").length;
        int listSize = list.size();
        var input = new Integer[listSize][column];
        var size = new Integer[listSize][column];
        for (int i = 0; i < listSize; i++) {
            String[] string = list.get(i).split(" ");
            for (int j = 0; j < column; j++) input[i][j] = Integer.parseInt(string[j]);
        }
        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    size[i][j] = 0;
                }
                else if (i == 0) {
                    size[i][j] = size[i][j - 1] + input[i][j];
                }
                else if (j == 0) {
                    size[i][j] = size[i - 1][j] + input[i][j];
                }
                else {
                    size[i][j] = min(size[i - 1][j], min(size[i][j - 1], size[i - 1][j - 1])) + input[i][j];
                }
            }
        }
        return size[listSize - 1][column - 1];
    }

    //    time: O(n*m)
    //    memory: O(n*m)

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}

