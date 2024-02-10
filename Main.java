import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Notebooks notebooks = new Notebooks();
        Random random = new Random();
        // цвета прописаны на английском, поскольку введенные на русском строки мой vsc
        // не воспринимает корректно
        notebooks.addNotebook("Lenovo", 32, 500, "black", "Windows");
        notebooks.addNotebook("LG", 16, 320, "grey", "Windows");
        notebooks.addNotebook("MacBook", 8, 256, "motley", "MacOS");
        notebooks.addNotebook("LG", 4, 80, "red", "Linux");
        notebooks.addNotebook("HP", 32, 1000, "grey", "Windows");
        notebooks.addNotebook("Lenovo", 16, 200, "white", "Windows");
        System.out.println("Весь список ноутбуков: ");
        notebooks.prinAllNotebooks();
        System.out.println("Случайный ноутбук из имеющихся: ");
        notebooks.printNotebook(random.nextInt(notebooks.curQuantity) + 1);
        notebooks.getFiltered();
        notebooks.iScanner.close(); // не смог придумать, как корректно закрывать его в классе Notebooks
    }
}