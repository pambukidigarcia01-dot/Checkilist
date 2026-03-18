import java.util.*;
import java.util.stream.*;

public class App_demo1_Melhorado {
    private String name;
    private int value;

    public App_demo1_Melhorado(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Validation method
    public boolean isValid() {
        return name != null && !name.isEmpty() && value >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof App_demo1_Melhorado)) return false;
        App_demo1_Melhorado that = (App_demo1_Melhorado) o;
        return value == that.value && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, App_demo1_Melhorado> items = new HashMap<>();
        boolean running = true;

        while (running) {
            System.out.println("1. Add Item\n2. List Items\n3. Quit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:  // Add Item
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter value: ");
                    int value = scanner.nextInt();
                    App_demo1_Melhorado item = new App_demo1_Melhorado(name, value);
                    if (item.isValid()) {
                        items.put(name, item);
                        System.out.println("Item added successfully!");
                    } else {
                        System.out.println("Invalid item.");
                    }
                    break;
                case 2:  // List Items
                    items.values().stream()
                         .forEach(i -> System.out.println(i.name + ": " + i.value));
                    break;
                case 3:  // Quit
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}