import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Notebooks {
    Integer curQuantity = 0;// текущее количество ноутбуков в магазине
    // разные параметры ноутбуков хранятся в разных словорях, но ключи для
    // конкретного ноутбука в каждом словаре совпадают
    private Map<Integer, String> names = new HashMap<>();
    private Map<Integer, Integer> ozuMap = new HashMap<>();
    private Map<Integer, Integer> pzuMap = new HashMap<>();
    private Map<Integer, String> colors = new HashMap<>();
    private Map<Integer, String> osMap = new HashMap<>();
    Scanner iScanner = new Scanner(System.in);

    // метод для добавления информации о новом ноутбуке в магазин
    void addNotebook(String name, Integer ozu, Integer pzu, String color, String os) {
        curQuantity++;
        names.put(curQuantity, name);
        ozuMap.put(curQuantity, ozu);
        pzuMap.put(curQuantity, pzu);
        colors.put(curQuantity, color);
        osMap.put(curQuantity, os);
    }

    void printNotebook(Integer i) {
        System.out.println(i + ": " + names.get(i) + ", " + ozuMap.get(i) + " ГБ ОЗУ, " + pzuMap.get(i)
                + " ГБ на ЖД, цвет " + colors.get(i) + ", ОС - " + osMap.get(i));
    }

    void prinAllNotebooks() {
        for (int i = 0; i < curQuantity; i++) {
            printNotebook(i + 1);
        }
    }

    // метод для запроса и получения от пользователя параметров для фильтрации
    private Set<String> filterParams() {
        Set<String> setOfParametrs = new HashSet<>();
        System.out.println("Выберите параметры фильтрации:\n" +
                "1 - по минимальному объему оперативной памяти;\n" +
                "2 - по минимальному объему жесткого диска;\n" +
                "3 - по цвету;\n" +
                "4 - по операционной системе;\n" +
                "5 - хватит фильтров, пора искать.");
        String parametr = "";
        while (!parametr.equals("5")) {
            parametr = iScanner.next();
            setOfParametrs.add(parametr);
        }
        return setOfParametrs;
    }

    // метод для печати списка ноутбуков по номерам из общего списка после
    // фильтрации
    private void printFiltered(List<Integer> filterNumbers) {
        for (int i = 0; i < filterNumbers.size(); i++) {
            printNotebook(filterNumbers.get(i));
        }
    }

    // метод очистки консоли
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // метод фильтрации по объему оперативной памяти
    private void filterByOzu(List<Integer> filterList) {
        int minOzu = 0;
        // обработка ввода
        boolean isValidInput = false;
        do {
            System.out.println("Введите минимальный объем оперативной памяти в ГБ:");
            if (iScanner.hasNextInt()) {
                minOzu = iScanner.nextInt();
                isValidInput = true;
            } else {
                System.out.println("Ошибка! Минимальный объем оперативной памяти должен быть целым числом.");
                iScanner.next();
            }
        } while (!isValidInput);
        for (int i = 0; i < filterList.size(); i++) {
            if (ozuMap.get(filterList.get(i)) < minOzu) {
                filterList.remove(i--);
            }
        }
    }

    // метод фильтрации по объему жесткого диска
    private void filterByPzu(List<Integer> filterList) {
        // System.out.println("Введите минимальный объем жесткого диска в ГБ:");
        int minPzu = 0;
        // обработка ввода
        boolean isValidInput = false;
        do {
            System.out.println("Введите минимальный объем жесткого диска в ГБ:");
            if (iScanner.hasNextInt()) {
                minPzu = iScanner.nextInt();
                isValidInput = true;
            } else {
                System.out.println("Ошибка! Минимальный объем жесткого диска должен быть целым числом.");
                iScanner.next();
            }
        } while (!isValidInput);
        for (int i = 0; i < filterList.size(); i++) {
            if (pzuMap.get(filterList.get(i)) < minPzu) {
                filterList.remove(i--);
            }
        }
    }

    // метод фильтрации по цвету
    private void filterByColor(List<Integer> filterList) {
        System.out.println("Введите желаемый цвет:");
        String prefColor = iScanner.next();
        for (int i = 0; i < filterList.size(); i++) {
            if (!colors.get(filterList.get(i)).equals(prefColor)) {
                filterList.remove(i--);
            }
        }
    }

    // метод фильтрации по ОС
    private void filterByOs(List<Integer> filterList) {
        System.out.println("Введите предпочитаемую операционную систему:");
        String prefOs = iScanner.next();
        for (int i = 0; i < filterList.size(); i++) {
            if (!osMap.get(filterList.get(i)).equals(prefOs)) {
                filterList.remove(i--);
            }
        }
    }

    // основной метод для реализации фильтрации
    void getFiltered() {
        List<Integer> filterList = new ArrayList<>();
        for (int i = 1; i <= curQuantity; i++) {
            filterList.add(i);
        }
        Set<String> setOfParametrs = filterParams();
        if (setOfParametrs.contains("1")) {
            filterByOzu(filterList);
        }
        if (setOfParametrs.contains("2")) {
            filterByPzu(filterList);
        }
        if (setOfParametrs.contains("3")) {
            filterByColor(filterList);
        }
        if (setOfParametrs.contains("4")) {
            filterByOs(filterList);
        }
        clearConsole();
        System.out.println("Вот список подходящих ноутбуков: ");
        printFiltered(filterList);
    }

}
