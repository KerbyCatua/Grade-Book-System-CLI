import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Services services = new Services();

        services.landingPage(scanner);

        scanner.close();
    }
}
