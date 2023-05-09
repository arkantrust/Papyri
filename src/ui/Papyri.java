package ui;

import java.util.Scanner;
import model.Company;

public class Papyri {
    private Scanner in;
    private Company readX;

    public Papyri() {
        in = new Scanner(System.in);
        System.out.print("Company name: ");
        String name = in.nextLine();
        System.out.print("Company nit: ");
        String nit = in.nextLine();
        System.out.print("Company address: ");
        String address = in.nextLine();
        readX = new Company(name, nit, address);
    }

    public void displayMenu() {
        System.out.println("1. Create user");
        System.out.println("2. Show user");
        System.out.println("3. Upgrade user to premium");
        System.out.println("4. Generate payment");
        System.out.println("5. Generate surprise");
        System.out.println("0. Exit");
    }

    public void registerUser() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter email: ");
        String email = in.nextLine();

        if (readX.addUser(name, email)) {
            System.out.println("User registered succesfully!");
        } else {
            System.out.println("Could not register user. Try again.");
        }
        System.out.println();
        System.out.println(readX.displayUser(String.valueOf(readX.getUserIDs()-1)));
    }

    public void searchUser() {
        System.out.print("User's ID: ");
        String id = in.nextLine();
        System.out.println("\n" + readX.displayUser(id));
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Papyri, your go-to digital library.");
        Papyri objPapyri = new Papyri();
        boolean run = true;

        while (run) {
            objPapyri.displayMenu();
            System.out.print("> ");
            int select = objPapyri.in.nextInt();
            objPapyri.in.nextLine();
            switch (select) {
                case 0:
                    run = false;
                    break;
                case 1:
                    objPapyri.registerUser();
                    break;
                case 2:
                    objPapyri.searchUser();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    }
}
