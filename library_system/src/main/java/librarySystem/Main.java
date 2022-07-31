package librarySystem;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Library library = Library.INSTANCE;
    private static Admin admin = new Admin("Adam", "Romanowski");
    public static Scanner scanner = new Scanner(System.in);
    public static boolean end = false;
    public static String firstName;
    public static String lastName;
    public static String title;
    public static int year;
    public static int month;
    public static int day;
    public static int isbnNumber;

    public static void main(String[] args) {

//        while(!end){
//            System.out.println("Press 1 for user panel\n"+
//                    "Press 2 for admin panel\n"+
//                    "Press 3 for exit");
//            int choice = scanner.nextInt();
//            switch (choice){
//                case 1: userPanel();
//                break;
//                case 2: adminPanel();
//                break;
//                case 3: end = true;
//                break;
//                default:
//                    System.out.println("Wrong option chosen");
//            }
//        }


        adminPanel();
    }

    public static void userPanel(){
        while(!end){
            printOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 0:
                    end = true;
                    break;
                case 1:
                    library.printBooksWithNumberOfCopies();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    adminPanel();
                    break;
            }
        }
    }

    public static void adminPanel(){
            admin.checkPassword();
            while(!end){
                printOptionsAdmin();
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 0:
                        userPanel();
                        break;
                    case 1:
                        addBook();
                        break;
                    case 2:
                        library.printBooks();
                        break;
                    case 3:
                        removeBook();
                        break;
                    case 4:
                        updateBook();
                        break;
                    case 5:

                        break;
                }
            }
    }

    public static void printOptions(){
        System.out.println("Press \n" +
                "0 - to shutdown\n" +
                "1 - to Check available books\n" +
                "2 - to Check borrowed books\n" +
                "3 - to borrow a book\n" +
                "5 - to ");
    }

    public static void printOptionsAdmin(){
        System.out.println("Welcome to admin panel, choose an option \n"+
                "0 - to go back to user's panel\n" +
                "1 - to add the book\n" +
                "2 - to check all the books\n" +
                "3 - to remove the book\n" +
                "4 - to update book details\n" +
                "5 - to check currently borrowed books");
    }

    public static void printRepeatedMsg(){
        System.out.println("Enter author's first name");
        firstName = scanner.nextLine();
        System.out.println("Enter author's last name");
        lastName = scanner.nextLine();
        System.out.println("Enter title");
        title = scanner.nextLine();
        System.out.println("Enter year of publication");
        year = scanner.nextInt();
        System.out.println("Enter month of publication");
        month = scanner.nextInt();
        System.out.println("Enter day of publication");
        day = scanner.nextInt();
        System.out.println("Enter isbn number");
        isbnNumber = scanner.nextInt();
    }

    public static void addBook(){
        printRepeatedMsg();
        Author author = Author.createAuthor(firstName, lastName);
        Book book = Book.addBook(author, title, LocalDate.of(year, month, day), isbnNumber);
        library.getBooks().add(book);

    }

//    public static void printBooksWithCopies(){
//        for(Book book : library.getBooks()){
//            String value = book.getTitle();
//            Integer count = books1.get(value);
//            if(count == null){
//                books1.put(value, 1);
//            }else
//                books1.put(value, count+1);
//        }
//
//        for(Map.Entry<String, Integer> entry : books1.entrySet()){
//            System.out.println("Title: " + entry.getKey() + " number of copies available: " +entry.getValue());
//        }
//    }
    public static void removeBook(){
        System.out.println("Please enter the title to see all the copies available");
        String title = scanner.next();
        library.findBook(title);
        System.out.println("Please enter the ISBN number of the book you want to remove from database");
        int isbn = scanner.nextInt();
        library.removeBook(isbn);
    }

    public static void updateBook(){
        System.out.println("Please enter the title to see all the copies available");
        String tit = scanner.next();
        library.findBook(tit);
        System.out.println("Please enter the ISBN number of the book you want to remove from database");
        int isbn = scanner.nextInt();
        Book book = library.queryBook(isbn);
        if(book == null){
            System.out.println("Book not found");
            return;
        }
        scanner.nextLine();
        printRepeatedMsg();
        Author author = Author.createAuthor(firstName, lastName);
        Book newBook = Book.addBook(author, title, LocalDate.of(year, month, day), isbnNumber);
        library.updateBook(book, newBook);
    }

    public static void checkAvailableBooks(){
//        System.out.println("Please enter the title of the book");
//        String title = scanner.nextLine();
//        library.findOnlyAvailableBook(title);
        String title = "book";
        Book book = library.findBookObject(title);
        int count = 0;
        for(int i = 0; i< library.getBooks().size(); i++){
            if(book.getTitle().equals(title)){
                count++;
            }
        }
        System.out.println(count);
    }


}
