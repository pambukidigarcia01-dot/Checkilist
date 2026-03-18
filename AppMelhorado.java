import java.util.*;
import java.util.stream.Collectors;

public class AppMelhorado {

    private List<String> items;

    public AppMelhorado() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        validateItem(item);
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    private void validateItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item cannot be null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppMelhorado)) return false;
        AppMelhorado that = (AppMelhorado) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    public void searchItem(String query) {
        List<String> results = items.stream()
                .filter(item -> item.contains(query))
                .collect(Collectors.toList());
        System.out.println("Search Results:");
        results.forEach(System.out::println);
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. List Items");
            System.out.println("4. Search Item");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter item to add: ");
                    addItem(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Enter item to remove: ");
                    removeItem(scanner.nextLine());
                    break;
                case "3":
                    System.out.println(getItems());
                    break;
                case "4":
                    System.out.print("Enter search query: ");
                    searchItem(scanner.nextLine());
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void main(String[] args) {
        AppMelhorado app = new AppMelhorado();
        app.menu();
    }
}