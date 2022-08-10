package librarySystem;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class LibraryRunner {
    private static Library library = new Library();
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

    public static void welcomePanel(){
        while(!end){
            System.out.println("Welcome to library system\n"
                    +"Please press: \n"
                    +"0 to shut down\n"
                    +"1 to Sign Up \n"
                    +"2 to Sign In \n"
                    +"3 for Admin panel");
            int choice = ScannerClass.nextInt();
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
            int choice = ScannerClass.nextInt();
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
                    checkBorrowedBooks();
                    break;
                case 4:
                    adminPanel();
                    break;
            }
        }
    }

    public static void adminPanel(){
        admin.checkPassword();
        while(!end){
            printOptionsAdmin();
            int choice = ScannerClass.nextInt();
            ScannerClass.nextLine();
            switch (choice){
                case 0:
                    welcomePanel();
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
                    checkCurrentlyBorrowedBooks();
                    break;
            }
        }
    }

    public static void printOptions(){
        System.out.println("Press \n" +
                "0 - to sign out\n" +
                "1 - to Check available books\n" +
                "2 - to borrow a book\n" +
                "3 - to check currently borrowed books\n");
    }

    public static void printOptionsAdmin(){
        System.out.println("Welcome to admin panel, choose an option \n"+
                "0 - to go welcome panel\n" +
                "1 - to add the book\n" +
                "2 - to check all the books\n" +
                "3 - to remove the book\n" +
                "4 - to update book details\n" +
                "5 - to check currently borrowed books");
    }

    public static void printRepeatedMsg(){

            System.out.println("Enter author's first name");
            firstName = ScannerClass.nextLine();
            System.out.println("Enter author's last name");
            lastName = ScannerClass.nextLine();
            System.out.println("Enter title");
            title = ScannerClass.nextLine();
            System.out.println("Enter year of publication");
            year = ScannerClass.nextInt();
            System.out.println("Enter month of publication");
            month = ScannerClass.nextInt();
            System.out.println("Enter day of publication");
            day = ScannerClass.nextInt();
            System.out.println("Enter isbn number");
            isbnNumber = ScannerClass.nextInt();
            while (!isbnNum.getIsbn().add(isbnNumber)){
                System.out.println("This isbn number arleady exists, try again");
                isbnNumber = ScannerClass.nextInt();
            }

    }

    public static void registerAccount(){
        System.out.println("Enter First Name");
        String firstName = ScannerClass.nextLine();
        scanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = ScannerClass.nextLine();
        System.out.println("Enter login");
        String userLogin = ScannerClass.nextLine();
        while (!login.getLogins().add(userLogin)){
            System.out.println("This login arleady exists, try again");
            userLogin = ScannerClass.nextLine();
        }
        System.out.println("Enter password");
        String password = ScannerClass.nextLine();
        System.out.println("Enter phoneNumber");
        int phoneNumber = ScannerClass.nextInt();
        while (!phone.getPhoneNumbers().add(phoneNumber)){
            System.out.println("This phone number arleady exists, try again");
            phoneNumber = ScannerClass.nextInt();
        }
        User user = User.createUser(firstName, lastName, userLogin,password, phoneNumber);
        if(library.getUsers().add(user)){
            System.out.println("Account has been created successfully");
        }
        welcomePanel();
    }

    public static void signIn(){
        ScannerClass.nextLine();
        System.out.println("Please enter your login");
        String userLogin = ScannerClass.nextLine();
        if(!library.findUser(userLogin)){
            System.out.println("Wrong login - Please register your account");
            welcomePanel();
        }
        System.out.println("Please enter password");
        String password = ScannerClass.nextLine();
        while(!library.findUserObject(userLogin).getPassword().equals(password)){
            System.out.println("Wrong password - Please try again");
            password = ScannerClass.nextLine();
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
        String title = ScannerClass.next();
        library.findBook(title);
        System.out.println("Please enter the ISBN number of the book you want to remove from database");
        int isbn = ScannerClass.nextInt();
        library.removeBook(isbn);
    }

    public static void updateBook(){
        System.out.println("Please enter the title to see all the copies available");
        String tit = ScannerClass.next();
        library.findBook(tit);
        System.out.println("Please enter the ISBN number of the book you want to remove from database");
        int isbn = ScannerClass.nextInt();
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
        String tit = ScannerClass.next();
        if(library.printBooksWithCopies(tit).isEmpty()){
            System.out.println("Book not available");
            userPanel(currentlyLoggedUser);
        }
        for(Map.Entry<String, Integer> entry : library.printBooksWithCopies(tit).entrySet()){
            System.out.println("Title: " + entry.getKey() + " number of copies available: " +entry.getValue());
        }
        System.out.println("Press 1 to reserve this book\n"
                +"Press 2 to cancel");
        int choice = ScannerClass.nextInt();
        if(choice == 1){
            System.out.println("For how many days would you like to borrow this book? - Maximum 14 days");
            int days = ScannerClass.nextInt();
            while(days > 14){
                System.out.println("Maximum time to borrow a book is 14 days. Please try again");
                days = ScannerClass.nextInt();
            }
            for(int i = 0; i<library.getBooks().size(); i++){
                if(library.getBooks().get(i).getTitle().equals(tit) && library.getBooks().get(i).isBorrowed() == false){
                    library.getBooks().get(i).setBorrowed(true);
                    library.getBooks().get(i).setBorrowedTo(currentlyLoggedUser);
                    library.getBooks().get(i).setBorrowedUntil(LocalDate.now().plusDays(days));
                    currentlyLoggedUser.getBooksBorrowed().add(library.getBooks().get(i));
                    System.out.println("Book has been reserved successfully");
                    break;
                }
            }
        }else if(choice == 2){
            userPanel(currentlyLoggedUser);
        }
    }

    public static void checkBorrowedBooks(){
        if(currentlyLoggedUser.getBooksBorrowed().isEmpty()){
            System.out.println("You haven't borrowed any books yet");
        }else{
            System.out.println(currentlyLoggedUser.getBooksBorrowed());
        }
    }

    public static void checkCurrentlyBorrowedBooks(){
        for(int i = 0; i<library.getBooks().size(); i++){
            if(library.getBooks().get(i).isBorrowed()){
                System.out.println(library.getBooks().get(i).getTitle() + " " + library.getBooks().get(i).getAuthor() + " " +library.getBooks().get(i).getIsbnNumber() + " " +library.getBooks().get(i).getBorrowedTo() + " " +library.getBooks().get(i).getBorrowedUntil());
            }else{
                System.out.println("None of the books has been borrowed yet");
            }
        }
    }
}
