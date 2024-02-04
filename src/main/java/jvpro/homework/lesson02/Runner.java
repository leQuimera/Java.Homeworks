package jvpro.homework.lesson02;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Runner {

    public static final String POSITION_ENGINEER = "Инженер";
    private static final List<Integer> TEST_INT_LIST = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
    private static final List<Employee> EMPLOYEES = List.of(
            new Employee("Иван", 41, "Бухгалтер"),
            new Employee("Мустафа", 24, "Инженер"),
            new Employee("Анна", 70, "Бухгалтер"), //
            new Employee("Ждан", 22, "Инженер"),
            new Employee("Кассиопея", 19, "Инженер"),
            new Employee("Илья", 30, "Инженер"), //
            new Employee("Екатерина", 33, "Бухгалтер"),
            new Employee("Мустафа", 52, "Инженер"), //
            new Employee("Абрам", 36, "Инженер"),
            new Employee("Инокентий", 48, "Инженер")
    );
    private static final List<String> DICTIONARY = List.of(
            "hello",
            "demenager",
            "coworker",
            "ami",
            "good",
            "obstacle"
    );
    private static final String STRING_OF_WORDS = "строка регистре набором слов в нижнем регистре регистре строка регистререгистре разделенных регистре строка пробелом";


//    Реализуйте удаление из листа всех дубликатов
    private static List<Integer> removeDoublesInList(List<Integer> list) {
    return list.stream()
            .distinct()
            .collect(Collectors.toList());
}

//    Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
    private static Integer findThirdMaxElement(List<Integer> list) {
    return list.stream()
            .sorted(Comparator.reverseOrder())
            .skip(2)
            .findFirst()
            .get();
}

//    Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
private static Integer findThirdMaxUniqueElement(List<Integer> list) {
    return list.stream()
            .sorted(Comparator.reverseOrder())
            .distinct()
            .skip(2)
            .findFirst()
            .get();
}

//    Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
    private static String findThirdEmployeesEngineers(List<Employee> list){
        return list.stream()
                .filter(employee -> Objects.equals(employee.getJob(), POSITION_ENGINEER))
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .map(Employee::getName)
                .collect(Collectors.toList())
                .subList(0, 3)
                .toString();
    }

//    Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
private static Double findAverageAgeForEngineers(List<Employee> list){
        return list.stream()
                .filter(employee -> Objects.equals(employee.getJob(), POSITION_ENGINEER))
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble();
}

//    Найдите в списке слов самое длинное
    private static String findLongestWord(List<String> list) {
        return list.stream()
                .max(Comparator.comparing(String::length))
                .get();
    }

//    Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
private static Map<String, Long> findWordsCount(String words) {
        return Arrays
                .stream(words.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
}

//    Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
public static void printWordsShortToLong(List<String> list) {
    list.stream()
            .sorted(Comparator.comparingInt(String::length)
                    .thenComparing(Comparator.naturalOrder()))
            .forEachOrdered(System.out::println);
}

//    Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них


    public static void runAllTasks() {
        System.out.printf("1.Удаляем дубликаты: %s\n", removeDoublesInList(TEST_INT_LIST));
        System.out.printf("2.Находим 3-е наибольшее число: %s\n", findThirdMaxElement(TEST_INT_LIST));
        System.out.printf("3.Находим 3-е наибольшее уникальное число: %s\n", findThirdMaxUniqueElement(TEST_INT_LIST));
        System.out.printf("4.Находим 3-е самых старых инженеров: %s\n", findThirdEmployeesEngineers(EMPLOYEES));
        System.out.printf("5.Находим средний возраст 3-е самых старых инженеров: %s\n", findAverageAgeForEngineers(EMPLOYEES));
        System.out.printf("6.Находим самое длинное слово в списке слов: %s\n", findLongestWord(DICTIONARY));
        System.out.printf("7.Находим, сколько раз слова встречаются в строке: %s\n", findWordsCount(STRING_OF_WORDS));
        System.out.printf("8.Печатаем слова от короткого к длинному: ");
        printWordsShortToLong(DICTIONARY);
    }

}
