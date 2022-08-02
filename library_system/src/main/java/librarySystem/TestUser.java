package librarySystem;

import static librarySystem.Main.*;

public class TestUser {
    public static void main(String [] args){
        Login login = new Login();

          System.out.println("Provide First Name");
          String firstName = scanner.nextLine();
          System.out.println("Provide Last Name");
          String lastName = scanner.nextLine();
          System.out.println("Provide login");
          String userLogin = scanner.nextLine();
          while (!login.getLogins().add(userLogin)){
              System.out.println("This login arleady exists, try again");
              userLogin = scanner.nextLine();
          }
          System.out.println("Provide password");
          String password = scanner.nextLine();
          System.out.println("Provide phoneNumber");
          int phoneNumber = scanner.nextInt();
          User user = User.createUser(firstName, lastName, userLogin,password, phoneNumber);
          System.out.println(user);



    }
}
