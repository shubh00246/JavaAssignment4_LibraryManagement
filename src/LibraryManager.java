import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    public void loadFromFile() {
        try {
            File f1 = new File("books.txt");
            File f2 = new File("members.txt");
            f1.createNewFile();
            f2.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(f1));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                int id = Integer.parseInt(d[0]);
                books.put(id, new Book(id, d[1], d[2], d[3], Boolean.parseBoolean(d[4])));
            }
            br.close();

            br = new BufferedReader(new FileReader(f2));
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                int id = Integer.parseInt(d[0]);
                Member m = new Member(id, d[1], d[2]);
                if (d.length > 3) {
                    String[] issued = d[3].split("-");
                    for (String x : issued) {
                        if (!x.isEmpty()) m.addIssuedBook(Integer.parseInt(x));
                    }
                }
                members.put(id, m);
            }
            br.close();
        } catch (Exception e) {}
    }

    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
            for (Book b : books.values()) {
                bw.write(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getCategory() + "," + b.getIsIssued());
                bw.newLine();
            }
            bw.close();

            bw = new BufferedWriter(new FileWriter("members.txt"));
            for (Member m : members.values()) {
                StringBuilder sb = new StringBuilder();
                for (int x : m.getIssuedBooks()) sb.append(x).append("-");
                bw.write(m.getMemberId() + "," + m.getName() + "," + m.getEmail() + "," + sb.toString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {}
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        int id = books.size() + 101;
        System.out.print("Enter Book Title: ");
        String t = sc.nextLine();
        System.out.print("Enter Author: ");
        String a = sc.nextLine();
        System.out.print("Enter Category: ");
        String c = sc.nextLine();
        Book b = new Book(id, t, a, c, false);
        books.put(id, b);
        saveToFile();
        System.out.println("Book added with ID: " + id);
    }

    public void addMember() {
        Scanner sc = new Scanner(System.in);
        int id = members.size() + 201;
        System.out.print("Enter Member Name: ");
        String n = sc.nextLine();
        System.out.print("Enter Email: ");
        String e = sc.nextLine();
        Member m = new Member(id, n, e);
        members.put(id, m);
        saveToFile();
        System.out.println("Member added with ID: " + id);
    }

    public void issueBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int mId = sc.nextInt();

        if (!books.containsKey(bId) || !members.containsKey(mId)) {
            System.out.println("Invalid IDs");
            return;
        }

        Book b = books.get(bId);
        if (b.getIsIssued()) {
            System.out.println("Book already issued");
            return;
        }

        b.markAsIssued();
        members.get(mId).addIssuedBook(bId);
        saveToFile();
        System.out.println("Book issued");
    }

    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int mId = sc.nextInt();

        if (!books.containsKey(bId) || !members.containsKey(mId)) {
            System.out.println("Invalid IDs");
            return;
        }

        Book b = books.get(bId);
        b.markAsReturned();
        members.get(mId).returnIssuedBook(bId);
        saveToFile();
        System.out.println("Book returned");
    }

    public void searchBooks() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter search keyword: ");
        String s = sc.nextLine().toLowerCase();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(s) || b.getAuthor().toLowerCase().contains(s) || b.getCategory().toLowerCase().contains(s)) {
                b.displayBookDetails();
            }
        }
    }

    public void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());
        Collections.sort(list);
        for (Book b : list) b.displayBookDetails();
    }
}
