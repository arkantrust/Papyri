package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import model.Company;

public class Papyri {
    private static Scanner in = new Scanner(System.in);;
    private static Company readX = new Company("ReadX");;

    public static Calendar readDate(String requiredDate) {
        boolean run = true;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while (run) {
            System.out.print("Enter " + requiredDate + " (dd-mm-yyyy): ");
            String dateString = in.nextLine();

            try {
                date.setTime(dateFormat.parse(dateString));
                run = false;
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
        return date;
    }

    public static void printBold(String text) {
        System.out.println("\033[1m" + text + "\033[0m");
    }

    // ------------------User Management----------------------
    public static void registerUser() {
        System.out.print("Enter full name: ");
        String name = in.nextLine();
        System.out.print("Enter ID: ");
        String id = in.nextLine();
        System.out.print("Enter email: ");
        String email = in.nextLine();
        System.out.print("Enter password: ");
        String password = in.nextLine();

        if (readX.registerUser(name, id, email, password)) {
            System.out.println("User registered succesfully!");
        } else {
            System.out.println("Something went wrong. Try again.");
        }
        System.out.println();
        System.out.println(readX.getUserByID(id).toString());
    }

    public static void searchUser() {
        System.out.print("User's ID: ");
        var id = in.nextLine();
        System.out.println(readX.getUserByID(id).toString());
    }

    public static void showUsers() {
        System.out.println(readX.getUserList());
    }

    public static void upgradeUserToPremium() {
        System.out.print("User's ID: ");
        var id = in.nextLine();
        // Verify if user exists
        if (!readX.userExists(id)) {
            System.out.println("User not found.");
            return;
        }
        // Confirming upgrade
        printBold(readX.getUserByID(id).getName() + " will be upgraded to premium. Which costs $" + Company.MEMBERSHIP
                + ". Continue? Y/N ");
        char confirmation = in.nextLine().charAt(0);
        if (!readX.confirmOperation(confirmation)) {
            System.out.println(readX.getUserByID(id).getName() + " will remain as basic");
            System.out.println();
            // early return exits (cancels) the method so the user is not upgraded
            return;
        }

        System.out.print("Nickname: ");
        String nickname = in.nextLine();
        System.out.print("Avatar: ");
        String avatar = in.nextLine();
        System.out.print("Credit/Debit Card number: ");
        String card = in.nextLine();
        if (readX.upgradeUser(id, nickname, avatar, card)) {
            System.out.println(readX.getUserByID(id).getName() + " is now a premium user.");
            System.out.println(readX.getLastReceipt());
        } else {
            System.out.println("Something went wrong. Try again.");
        }
    }

    public static void upgradeUserToReviewer() {
        System.out.print("User's ID: ");
        var id = in.nextLine();
        // Verify if user exists
        if (!readX.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Nickname: ");
        String nickname = in.nextLine();
        System.out.print("Avatar: ");
        String avatar = in.nextLine();
        System.out.print("Credit/Debit Card number: ");
        String card = in.nextLine();
        System.out.print("Interest: ");
        String interest = in.nextLine();
        System.out.print("blog: ");
        String blog = in.nextLine();
        if (readX.upgradeUser(id, nickname, avatar, card, interest, blog)) {
            System.out.println(readX.getUserByID(id).getName() + " is now a reviewer.");
        } else {
            System.out.println("Something went wrong. Try again.");
        }
    }

    public static void upgradePremiumUserToReviewer() {
        System.out.print("User's ID: ");
        var id = in.nextLine();
        // Verify if user exists
        if (!readX.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Interest: ");
        String interest = in.nextLine();
        System.out.print("blog: ");
        String blog = in.nextLine();
        if (readX.upgradeUser(id, interest, blog)) {
            System.out.println(readX.getUserByID(id).getName() + " is now a reviewer.");
        } else {
            System.out.println("Something went wrong. Try again.");
        }
    }

    public static void openUserManagement() {
        printBold("-----------------------User Management--------------------");
        boolean run = true;
        while (run) {
            System.out.println("1. Register user");
            System.out.println("2. Search user (ID)");
            System.out.println("3. Show users");
            System.out.println("4. Upgrade user to premium");
            System.out.println("5. Upgrade user to reviewer");
            System.out.println("6. Upgrade premium user to reviewer");
            System.out.println("0. Back");
            System.out.print("> ");
            int select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> registerUser();
                case 2 -> searchUser();
                case 3 -> showUsers();
                case 4 -> upgradeUserToPremium();
                case 5 -> upgradeUserToReviewer();
                case 6 -> upgradePremiumUserToReviewer();
            }
        }
    }

    // ----------------------Product Management-------------------------

    public static void registerBook() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        Calendar publicationDate = readDate("publication date");
        System.out.print("Enter number of pages: ");
        int pages = Integer.valueOf(in.nextLine());
        System.out.print("Enter URL to cover: ");
        String coverURL = in.nextLine();
        System.out.print("Enter price: ");
        double price = Double.valueOf(in.nextLine());
        System.out.print("Enter review: ");
        String review = in.nextLine();
        System.out.println("Enter genre:");
        System.out.println("1. Science fiction");
        System.out.println("2. Fantasy");
        System.out.println("3. Historical novel");
        System.out.print("> ");
        int genre = Integer.valueOf(in.nextLine());

        if (readX.registerBook(name, publicationDate, pages, coverURL, price, review, genre)) {
            System.out.println("Book registered succesfully!");
        } else {
            System.out.println("Something went wrong. Try again.");
        }
    }

    public static void registerMagazine() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        Calendar publicationDate = readDate("publication date");
        System.out.print("Enter number of pages: ");
        int pages = Integer.valueOf(in.nextLine());
        System.out.print("Enter URL to cover: ");
        String coverURL = in.nextLine();
        System.out.print("Enter price: ");
        double price = Double.valueOf(in.nextLine());
        System.out.println("Enter catgeory:");
        System.out.println("1. Miscellany");
        System.out.println("2. Design");
        System.out.println("3. Scientific");
        System.out.print("> ");
        int category = Integer.valueOf(in.nextLine());
        System.out.println("Enter issuance frequency:");
        System.out.println("1. Yearly");
        System.out.println("2. Monthly");
        System.out.println("3. Weekly");
        System.out.println("4. Daily");
        System.out.print("> ");
        int freq = Integer.valueOf(in.nextLine());
        if (readX.registerMagazine(name, publicationDate, pages, coverURL, price, category, freq)) {
            System.out.println("Magazine registered succesfully!");
        } else {
            System.out.println("Something went wrong. Try again.");
        }
    }

    public static void showProducts() {
        System.out.println(readX.getProductsList());
    }

    public static void openProductManagement() {
        printBold("-----------------------------Product Management-----------------------------");
        boolean run = true;
        while (run) {
            System.out.println("1. Register book");
            System.out.println("2. Register magazine");
            System.out.println("3. Show products");
            System.out.println("4. Edit product");
            System.out.println("5. Delete product");
            System.out.println("0. Back");
            System.out.print("> ");
            int select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> registerBook();
                case 2 -> registerMagazine();
                case 3 -> showProducts();
            }
        }
    }

    // Business-related

    public static void loginAsAdmin() {
        System.out.print("Password: ");
        String password = in.nextLine(); // TODO: validatePassword()
        System.out.println("Welcome, Beloved Admin!\n");
        boolean run = true;
        int select = 0;

        while (run) {
            printBold("------------------------------Admin------------------------------");
            System.out.println("1. Manage users");
            System.out.println("2. Manage Products");
            System.out.println("0. Log out");
            System.out.print("> ");
            select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> openUserManagement();
                case 2 -> openProductManagement();
            }
        }
    }

    public static void generateSurprise(String id) {
        System.out.println(readX.generateSurprise(id));
    }

    public static void goToSettings(String userID) {

    }

    public static void displayLibrary(String userID) {
        var run = true;
        while (run) {
            try {
                printBold(readX.getUserByID(userID).getName() + "'s library");
                System.out.println(readX.displayLibrary(userID));
                System.out.println("Next page: D");
                System.out.println("Previous page: A");
                System.out.println("Back: 0");
                System.out.print("> ");
                var move = in.nextLine();
                if (Integer.valueOf(move) == 0) {
                    run = false;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }
        }
    }

    public static void goToStore(String userID) {
        printBold("-----------------------------Store-----------------------------");
        boolean run = true;
        String productID = "";
        while (run) {
            System.out.print(readX.getProductsList());
            System.out.println("0. Exit");
            System.out.print("Enter product ID: ");
            try {
                productID = in.nextLine();

                if (Integer.valueOf(productID) == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }

            // Confirming purchase
            System.out.print("Are you sure you want to buy " + readX.getProducts().get(productID).getName()
                    + " for $" + readX.getProducts().get(productID).getPrice() + "? Y/N ");
            char confirmation = in.nextLine().charAt(0);
            if (!readX.confirmOperation(confirmation)) {
                System.out.println();
                // early return exits (cancels) the method so the user is not upgraded
                return;
            }

            if (readX.buyProduct(userID, productID)) {
                System.out.println(
                        "Congratulations on your purchase! You can view your bought products in your library.");
                System.out.println(readX.getLastReceipt());
            } else {
                System.out.println("Something went wrong. Try again later.");
            }
        }
    }

    public static void cancelMagazineSubscription(String userID) {
        System.out.println();
    }

    public static void loginAsUser() {
        System.out.print("Enter ID: ");
        var id = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine(); // TODO: validatePassword()
        boolean run = true;
        int select = 0;
        System.out.println("Welcome, " + readX.getUserByID(id).getName() + "!\n");

        while (run) {
            printBold("-----------------------------Home-----------------------------");
            System.out.println("1. Store");
            System.out.println("2. Library");
            System.out.println("3. Cancel Magazine subscription");
            System.out.println("4. Generate surprise");
            System.out.println("5. Settings");
            System.out.println("6. Buy All Products");
            System.out.println("0. Log out");
            System.out.print("> ");
            select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> goToStore(id);
                case 2 -> displayLibrary(id);
                case 3 -> cancelMagazineSubscription(id);
                case 4 -> generateSurprise(id);
                case 5 -> goToSettings(id);
                case 6 -> readX.buyAllProducts(id);
                // TODO: Settings
            }
        }
    }

    public static void main(String[] args) {
        printBold("-------------------------Papyri----------------------------");
        readX.deployTest();
        boolean run = true;
        int select = 0;

        while (run) {
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("0. Exit");
            System.out.print("> ");
            // try {
            select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> loginAsAdmin();
                case 2 -> loginAsUser();
            }
            // } catch (Exception e) {
            // System.out.println("Invalid input.");
            // }
        }
    }
}