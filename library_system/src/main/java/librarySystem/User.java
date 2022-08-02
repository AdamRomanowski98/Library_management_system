package librarySystem;

import java.util.ArrayList;
import java.util.Set;

public class User {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private ArrayList<Book> booksBorrowed;
    private int phoneNumber;

    public User(String firstName, String lastName, String login, String password, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.booksBorrowed = new ArrayList<>();
        this.phoneNumber = phoneNumber;
    }

    public static User createUser(String firstName, String lastName, String login, String password, int phoneNumber){
        return new User(firstName, lastName, login, password, phoneNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", booksBorrowed=" + booksBorrowed +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
