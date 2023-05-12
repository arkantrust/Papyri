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
        System.out.println("1. Register user");
        System.out.println("2. Search user");
        System.out.println("3. Show users");
        System.out.println("4. Upgrade user to premium");
        System.out.println("5. Generate payment");
        System.out.println("6. Generate surprise");
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

    public void showUsers() {
        System.out.println(readX.getUserList());
    }

    public void upgradeUser() {
        System.out.print("User's ID: ");
        String id = in.nextLine();
        System.out.print(readX.displayUserName(id) + " will be upgraded to premium. Which costs $5. Continue? Y/N");
        char confirmation = in.nextLine().charAt(0);
        if (!readX.continueOP(confirmation)) {
            System.out.println(readX.displayUserName(id) + " will remain as basic");
            System.out.println();
            return;
        }
        System.out.println("Avatar: ");
        String avatar = in.nextLine();
        System.out.println("Credit/Debit Card number: ");
        String card = in.nextLine();
        if (readX.user2Premium(id, avatar, card)) {
            System.out.println(readX.displayUserName(id) + " is now a premium user.");
        } else {
            System.out.println("Could not complete the operation. Try again later.");
        }
    }

    public static void main(String[] args) {
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||Papyri||||||||||||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
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
                    objPapyri.showUsers();
                    break;
                case 3:
                    objPapyri.searchUser();
                    break;
                case 4:
                    objPapyri.upgradeUser();
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }
}
