/**
 * The Papyri class is a user interface for managing users, products, and generating reports in the
 * ReadX company's digital library system.
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import model.Company;

public class Papyri {
    private static Scanner in = new Scanner(System.in);;
    private static Company readX = new Company("ReadX");;
    private static String error = "Something went wrong. Try again.";

    /**
     * This function reads a date in the format of "dd-mm-yyyy" from user input and
     * returns it as a
     * Calendar object.
     * 
     * @param requiredDate a string that specifies what date is being requested
     *                     (e.g. "start date",
     *                     "end date", etc.).
     * @return A Calendar object is being returned.
     */
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

    /**
     * The function prints the given text in bold format in the console using escape
     * sequences.
     * 
     * @param text The text that will be printed in bold format.
     */
    public static void printBold(String text) {
        System.out.println("\033[1m" + text + "\033[0m");
    }

    // ------------------User Management----------------------
    /**
     * This function registers a user by taking their name, ID, email, and password
     * as input and then
     * checks if the registration was successful or not.
     */
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
            System.out.println(error);
        }
        System.out.println();
        System.out.println(readX.getUserByID(id).toString());
    }

    /**
     * This Java function prompts the user for an ID, retrieves the corresponding
     * user object using a
     * readX method, and prints out the user's information.
     */
    public static void searchUser() {
        System.out.print("User's ID: ");
        var id = in.nextLine();
        System.out.println(readX.getUserByID(id).toString());
    }

    /**
     * The function prints out the list of users obtained from the readX object.
     */
    public static void showUsers() {
        System.out.println(readX.getUserList());
    }

    /**
     * The function upgrades a user to premium by prompting for their information
     * and confirming the
     * upgrade.
     */
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
            System.out.println(error);
        }
    }

    /**
     * The function upgrades a user to a reviewer by taking input for their
     * nickname, avatar,
     * credit/debit card number, interest, and blog.
     */
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
            System.out.println(error);
        }
    }

    /**
     * This function upgrades a premium user to a reviewer by taking their ID,
     * interest, and blog as
     * input and verifying their existence before upgrading.
     */
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
            System.out.println(error);
        }
    }

    /**
     * The function opens a user management menu with options to register, search,
     * show, and upgrade
     * users.
     */
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

    /**
     * This function registers a book by taking input from the user for various book
     * details and then
     * calls a method to register the book.
     */
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
            System.out.println(error);
        }
    }

    /**
     * This function registers a magazine by taking input from the user for various
     * attributes such as
     * name, publication date, number of pages, cover URL, price, category, and
     * issuance frequency.
     */
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
            System.out.println(error);
        }
    }

    /**
     * This function prints out the products using the readX object.
     */
    public static void showProducts() {
        System.out.println(readX.showProducts());
    }

    /**
     * This function allows the user to edit a product's name, publication date,
     * price, or pages by
     * selecting the corresponding option and entering the new value.
     */
    private static void editProduct() {
        showProducts();
        System.out.print("Product ID: ");
        String code = in.nextLine();

        System.out.println("What do you want to change?");
        System.out.println("1. Name");
        System.out.println("2. Publication date");
        System.out.println("3. price");
        System.out.println("4. pages");
        System.out.print("> ");
        int selection = Integer.valueOf(in.nextLine());
        System.out.print("Change: ");
        String change = in.nextLine();

        System.out.println(readX.editProduct(code, selection, change));
    }

    /**
     * This function deletes a product by prompting the user for the product ID and
     * calling the
     * deleteProduct method, and outputs a success message or an error message.
     */
    private static void deleteProduct() {
        showProducts();
        System.out.print("Product ID: ");
        String code = in.nextLine();
        if (readX.deleteProduct(code)) {
            System.out.println("Product deleted succesfully.");
        } else {
            System.out.println(error);
        }
    }

    /**
     * The function opens a product management menu with options to register, show,
     * edit, and delete
     * products.
     */
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
                case 4 -> editProduct();
                case 5 -> deleteProduct();
            }
        }
    }

    // ----------------------Reports-------------------------

    /**
     * This Java function prints the total number of read pages.
     */
    public static void showTotalReadPages() {
        System.out.println("Read pages:\n" + readX.showTotalReadPages());
    }

    // Business-related

    /**
     * The function allows an admin to manage users, products, and view read pages
     * while logged in.
     */
    public static void loginAsAdmin() {
        System.out.println("Welcome, Beloved Admin!\n");
        boolean run = true;
        int select = 0;

        while (run) {
            printBold("------------------------------Admin------------------------------");
            System.out.println("1. Manage users");
            System.out.println("2. Manage Products");
            System.out.println("3. Show read pages");
            System.out.println("0. Log out");
            System.out.print("> ");
            select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> openUserManagement();
                case 2 -> openProductManagement();
                case 3 -> showTotalReadPages();
            }
        }
    }

    /**
     * This Java function generates a surprise based on an input ID using a method
     * called
     * "readX.generateSurprise".
     * 
     * @param id The parameter "id" is a String variable that represents the
     *           identifier of a surprise
     *           that needs to be generated.
     */
    public static void generateSurprise(String id) {
        System.out.println(readX.generateSurprise(id));
    }

    /**
     * The function "goToSettings" takes a userID parameter and does not return
     * anything.
     * 
     * @param userID The userID parameter is a string that represents the unique
     *               identifier of a user.
     *               It is used as input to the goToSettings method, which
     *               presumably navigates the user to their
     *               settings page or allows them to modify their account settings
     *               in some way.
     */
    public static void goToSettings(String userID) {
    }

    /**
     * The function displays a user's library and allows them to start reading a
     * book by selecting its
     * code or go back to the library.
     * 
     * @param userID The userID parameter is a String that represents the ID of the
     *               user whose library
     *               is being displayed.
     */
    public static void displayLibrary(String userID) {
        var run = true;
        while (run) {
            printBold(readX.getUserByID(userID).getName() + "'s library");
            System.out.println(readX.displayLibrary(userID));
            System.out.print("Type a code to start reading or 0 to go back: ");
            var selection = in.nextLine();

            try {
                if (Integer.valueOf(selection) == 0) {
                    run = false;
                }
            } catch (Exception e) {
                String productCode = selection;
                int currentPage = 0;
                boolean reading = true;
                while (reading) {
                    printBold("\nReading Session\n");

                    // Show ad
                    if (currentPage > 0 && currentPage % 5 == 0) {
                        System.out.println(readX.showAd());
                    }

                    System.out.println("Now reading: " + readX.getProducts().get(productCode).getName());

                    System.out.println(
                            "Reading page " + currentPage + " of " + readX.getProducts().get(productCode).getPages()
                                    + "\n");
                    System.out.println("Type A to go to the previous page");
                    System.out.println("Type S to go to the next page");
                    System.out.println("Type B to go back to the library");
                    System.out.print("> ");
                    var select = in.nextLine().charAt(0);

                    switch (Character.toUpperCase(select)) {
                        case 'A': {
                            if (currentPage > 0) {
                                currentPage--;
                            }
                            break;
                        }
                        case 'S': {
                            currentPage++;
                            readX.getProducts().get(productCode).incrementReadPages();
                            break;
                        }
                        case 'B':
                            reading = false;
                            break;
                    }
                }
            }
        }
    }

    /**
     * This function allows a user to buy products from a store and adds them to
     * their library.
     * 
     * @param userID The ID of the user who is going to the store to buy products.
     */
    public static void goToStore(String userID) {
        printBold("-----------------------------Store-----------------------------");
        boolean run = true;
        String productID = "";
        while (run) {
            showProducts();
            System.out.println("0. Exit");
            System.out.print("Enter product ID: ");
            try {
                productID = in.nextLine();

                if (Integer.valueOf(productID) == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(error);
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
                System.out.println(error);
            }
        }
    }

    /**
     * This function cancels a magazine subscription for a given user ID and product
     * ID.
     * 
     * @param userID The ID of the user whose magazine subscription is being
     *               canceled.
     */
    public static void cancelMagazineSubscription(String userID) {
        showProducts();

        System.out.print("product ID: ");
        var productID = in.nextLine();

        if (readX.cancelMagazineSubscription(userID, productID)) {
            System.out.println("Subscription canceled. :(");
        } else {
            System.out.println(error);
        }
    }

    /**
     * This function allows a user to log in and select various options such as
     * accessing a store or
     * library, cancelling a magazine subscription, generating a surprise, and
     * logging out.
     */
    public static void loginAsUser() {
        System.out.print("Enter ID: ");
        var id = in.nextLine();
        boolean run = true;
        int select = 0;
        System.out.println("Welcome, " + readX.getUserByID(id).getName() + "!\n");

        while (run) {
            printBold("-----------------------------Home-----------------------------");
            System.out.println("1. Store");
            System.out.println("2. Library");
            System.out.println("3. Cancel Magazine subscription");
            System.out.println("4. Generate surprise");
            System.out.println("6. Buy All Products - FOR TESTING ONLY");
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