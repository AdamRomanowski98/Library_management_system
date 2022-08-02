package librarySystem;

import java.time.LocalDate;

public class Book {

    private Author author;
    private String title;
    private LocalDate dateOfPublication;
    private int isbnNumber;
    private boolean isBorrowed;

    private User borrowedTo;

    private LocalDate borrowedUntil;

    public Book(Author author, String title, LocalDate dateOfPublication, int isbnNumber) {
        this.author = author;
        this.title = title;
        this.dateOfPublication = dateOfPublication;
        this.isbnNumber = isbnNumber;
        this.isBorrowed = false;
        this.borrowedTo = null;
        this.borrowedUntil = null;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public int getIsbnNumber() {
        return isbnNumber;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setIsbnNumber(int isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public User getBorrowedTo() {
        return borrowedTo;
    }

    public LocalDate getBorrowedUntil() {
        return borrowedUntil;
    }

    public void setBorrowedTo(User borrowedTo) {
        this.borrowedTo = borrowedTo;
    }

    public void setBorrowedUntil(LocalDate borrowedUntil) {
        this.borrowedUntil = borrowedUntil;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", isbnNumber=" + isbnNumber +
                ", isBorrowed=" + isBorrowed +
                '}';
    }

    public static Book addBook(Author author, String title, LocalDate dateOfPublication, int isbnNumber){
        return new Book(author, title, dateOfPublication, isbnNumber);
    }
}
