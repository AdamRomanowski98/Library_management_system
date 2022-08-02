package librarySystem;

import java.util.Scanner;

public class Admin {

    Scanner scanner = new Scanner(System.in);
    private String adminPassword;

    private String firstName;
    private String lastName;

    public Admin(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adminPassword = "Library123";
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public boolean checkPassword(){
        String password = "";
        while(!password.equals(getAdminPassword())){
            System.out.println("Please provide admin password");
            password = scanner.next();
        }
        return true;
    }

}
