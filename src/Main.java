import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        lm.loadFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) lm.addBook();
            else if (c == 2) lm.addMember();
            else if (c == 3) lm.issueBook();
            else if (c == 4) lm.returnBook();
            else if (c == 5) lm.searchBooks();
            else if (c == 6) lm.sortBooks();
            else if (c == 7) {
                lm.saveToFile();
                break;
            }
        }
    }
}
