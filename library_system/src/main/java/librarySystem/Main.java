package librarySystem;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Library library = Library.INSTANCE;
    private static Admin admin = new Admin("Adam", "Romanowski");
    private static Login login = new Login();
    private static User currentlyLoggedUser;
    private static PhoneNumber phone = new PhoneNumber();
    private static IsbnNumber isbnNum = new IsbnNumber();
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
        welcomePanel();
    }

    public static void welcomePanel(){
        while(!end){
            System.out.println("Welcome to library system\n"
            +"Please press: \n"
            +"0 to shut down\n"
            +"1 to Sign Up \n"
            +"2 to Sign In \n"
            +"3 for Admin panel");
            int choice = scanner.nextInt();
            switch (choice){
                case 0:
                    end = true;
                    break;
                case 1:
                    registerAccount();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    adminPanel();
                    break;
            }
        }
    }

    public static void userPanel(User user){
        while(!end){
            printOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 0:
                    welcomePanel();
                    break;
                case 1:
                    library.printBooksWithNumberOfCopies();
                    break;
                case 2:
                    borrowBook();
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
                        userPanel(currentlyLoggedUser);
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
                "2 - to borrow a book\n" +
                "3 - to....." +
                "5 - to...... ");
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
        while (!isbnNum.getIsbn().add(isbnNumber)){
            System.out.println("This isbn number arleady exists, try again");
            isbnNumber = scanner.nextInt();
        }

    }


    public static void registerAccount(){
        System.out.println("Enter First Name");
        String firstName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter login");
        String userLogin = scanner.nextLine();
        while (!login.getLogins().add(userLogin)){
            System.out.println("This login arleady exists, try again");
            userLogin = scanner.nextLine();
        }
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter phoneNumber");
        int phoneNumber = scanner.nextInt();
        while (!phone.getPhoneNumbers().add(phoneNumber)){
            System.out.println("This phone number arleady exists, try again");
            phoneNumber = scanner.nextInt();
        }
        User user = User.createUser(firstName, lastName, userLogin,password, phoneNumber);
        if(library.getUsers().add(user)){
            System.out.println("Account has been created successfully");
        }
        welcomePanel();
    }

    public User sign(){
        scanner.nextLine();
        System.out.println("Please enter your login");
        String userLogin = scanner.nextLine();
        if(!library.findUser(userLogin)){
            System.out.println("Wrong login - Please register your account");
            welcomePanel();
        }
        System.out.println("Please enter password");
        String password = scanner.nextLine();
        while(!library.findUserObject(userLogin).getPassword().equals(password)){
            System.out.println("Wrong password - Please try again");
            password = scanner.nextLine();
        }
        return library.findUserObject(userLogin);
    }
    public static void signIn(){
        scanner.nextLine();
        System.out.println("Please enter your login");
        String userLogin = scanner.nextLine();
        if(!library.findUser(userLogin)){
            System.out.println("Wrong login - Please register your account");
            welcomePanel();
        }
        System.out.println("Please enter password");
        String password = scanner.nextLine();
        while(!library.findUserObject(userLogin).getPassword().equals(password)){
            System.out.println("Wrong password - Please try again");
            password = scanner.nextLine();
        }
        currentlyLoggedUser = library.findUserObject(userLogin);
        userPanel(currentlyLoggedUser);
    }

    public static void addBook(){
        printRepeatedMsg();
        Author author = Author.createAuthor(firstName, lastName);
        Book book = Book.addBook(author, title, LocalDate.of(year, month, day), isbnNumber);
        library.getBooks().add(book);

    }

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

    public static void borrowBook(){
        System.out.println("Please enter the title to display available books");
        String tit = scanner.next();
        if(library.printBooksWithCopies(tit).isEmpty()){
            System.out.println("Book not available");
            userPanel(currentlyLoggedUser);
        }
        for(Map.Entry<String, Integer> entry : library.printBooksWithCopies(tit).entrySet()){
            System.out.println("Title: " + entry.getKey() + " number of copies available: " +entry.getValue());
        }
        System.out.println("Press 1 to reserve this book\n"
        +"Press 2 to cancel");
        int choice = scanner.nextInt();
        if(choice == 1){
            for(int i = 0; i<library.getBooks().size(); i++){
                if(library.getBooks().get(i).getTitle().equals(tit) && library.getBooks().get(i).isBorrowed() == false){
                    library.getBooks().get(i).setBorrowed(true);
                    System.out.println("Book has been reserved successfully");
                    break;
                }
            }
        }else if(choice == 2){
            userPanel(currentlyLoggedUser);
        }
    }



}
