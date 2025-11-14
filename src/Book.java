public class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int bookId, String title, String author, String category, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean getIsIssued() {
        return isIssued;
    }

    public void markAsIssued() {
        isIssued = true;
    }

    public void markAsReturned() {
        isIssued = false;
    }

    public void displayBookDetails() {
        System.out.println(bookId + " | " + title + " | " + author + " | " + category + " | " + (isIssued ? "Issued" : "Available"));
    }

    public int compareTo(Book b) {
        return this.title.compareToIgnoreCase(b.title);
    }
}
