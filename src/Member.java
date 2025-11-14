import java.util.*;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public List<Integer> getIssuedBooks() {
        return issuedBooks;
    }

    public void displayMemberDetails() {
        System.out.println(memberId + " | " + name + " | " + email + " | Issued Books: " + issuedBooks);
    }
}
