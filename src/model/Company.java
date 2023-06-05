package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class Company implements Randomizable, Emboldenable, DateManipulator {

    // attributes
    private String name;

    private ArrayList<User> users;
    private String userList;

    private String[] ads;

    private HashMap<String, String> credentials; // Saves all login information for verification
    private HashMap<String, Integer> userIdToIndexMap; // Relates a user's ID to its position in the ArrayList
    private ArrayList<Receipt> receipts;

    // constants
    public static final double MEMBERSHIP = 5; // premium membership price in USD
    public static final double MAX_BOOKS = 5; // Max books a base user can buy
    public static final double MAX_MAGAZINES = 2; // Max magazines a base user can be subscribed to

    private HashMap<String, Product> products;

    // constructor
    public Company(String name) {
        this.name = name;
        users = new ArrayList<>();
        userList = "";
        ads = new String[] {
                "¡Suscríbete al Combo Plus y llévate Disney+ y Star+ a un precio increíble!",
                "Ahora tus mascotas tienen una app favorita: Laika. Los mejores productos para tu peludito.",
                "¡Estamos de aniversario! Visita tu Éxito más cercano y sorpréndete con las mejores ofertas."
        };
        credentials = new HashMap<>();
        credentials = new HashMap<>();
        products = new HashMap<>();
        userIdToIndexMap = new HashMap<>();
        receipts = new ArrayList<>();
        users.add(new Admin("admin", "admin@papyri.com", "devtest",
                "ADMIN", Calendar.getInstance()));
        credentials.put(users.get(0).getName(), users.get(0).getPassword());
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public HashMap<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(HashMap<String, String> userInfo) {
        this.credentials = userInfo;
    }

    public HashMap<String, Integer> getUserIdToIndexMap() {
        return userIdToIndexMap;
    }

    public void setUserIdToIndexMap(HashMap<String, Integer> userIdToPositionMap) {
        this.userIdToIndexMap = userIdToPositionMap;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(ArrayList<Receipt> receipts) {
        this.receipts = receipts;
    }

    public String[] getAds() {
        return ads;
    }

    public void setAds(String[] ads) {
        this.ads = ads;
    }

    // methods

    /**
     * This function takes a character response and returns a boolean value based on
     * whether the
     * response is 'Y' or 'N'.
     * 
     * @param response a character input representing the user's response to a
     *                 confirmation prompt
     *                 (either 'Y' for yes or 'N' for no).
     * @return A boolean value representing the confirmation of an operation, based
     *         on the user's
     *         response.
     */
    public boolean confirmOperation(char response) {
        boolean confirmation = false;
        try {
            response = Character.toUpperCase(response);
            confirmation = switch (response) {
                case 'Y' -> true;
                case 'N' -> false;
                default -> false;
            };
        } catch (Exception e) {
            confirmation = false;
        }
        return confirmation;
    }

    /**
     * This Java function returns a User object based on the ID provided, using a
     * HashMap to map the ID
     * to an index in a list of users.
     * 
     * @param id The parameter "id" is a String representing the unique identifier
     *           of a user. The
     *           method "getUserByID" takes this identifier as input and returns the
     *           corresponding User object
     *           from a collection of users.
     * @return The method `getUserByID` returns a `User` object. The specific `User`
     *         object that is
     *         returned is determined by the `id` parameter passed to the method.
     *         The method uses a `Map`
     *         called `userIdToIndexMap` to look up the index of the `User` object
     *         in the `List` called
     *         `users`, and then returns that `User` object.
     */
    public User getUserByID(String id) {
        return users.get(userIdToIndexMap.get(id));
    }

    /**
     * The function checks if the given ID and password match the stored credentials
     * and returns true
     * if they do.
     * 
     * @param id       The user ID or username of the person trying to log in.
     * @param password The password parameter is a String that represents the
     *                 password entered by the
     *                 user during the login process.
     * @return The method is returning a boolean value. It returns true if the
     *         provided id and password
     *         match the values stored in the credentials map, and false otherwise.
     */
    public boolean login(String id, String password) {
        if (credentials.containsKey(id) && credentials.get(id).equals(password)) {
            return true;
        }
        return false;
    }

    // User-related
    /**
     * The function checks if a user with a given userID exists in a list of users.
     * 
     * @param userID The userID parameter is a String representing the unique
     *               identifier of a user.
     * @return The method is returning a boolean value indicating whether a user
     *         with the given userID
     *         exists in the system or not.
     */
    public boolean userExists(String userID) {
        var index = userIdToIndexMap.get(userID);
        var usersLen = users.size();
        boolean exists = (index >= usersLen || index < usersLen) ? true : false;
        return exists;
    }

    /**
     * This function registers a new user by creating a new BaseUser object with the
     * given parameters,
     * adding it to a list of users, and updating various data structures.
     * 
     * @param name     The name of the user being registered.
     * @param id       The id parameter is a unique identifier for the user being
     *                 registered. It could be a
     *                 username, a student ID, or any other identifier that can be
     *                 used to distinguish one user from
     *                 another.
     * @param email    The email parameter is a String that represents the email
     *                 address of the user being
     *                 registered.
     * @param password The password parameter is a String that represents the
     *                 password chosen by the
     *                 user during registration.
     * @return A boolean value of true is being returned.
     */
    public boolean registerUser(String name, String id, String email, String password) {
        User newUser = new BaseUser(name, email, password, id, Calendar.getInstance(), true,
                new ArrayList<>(), 0, 0);
        users.add(newUser);
        credentials.put(id, password);
        userList += users.get(users.size() - 1).toString() + '\n';
        userIdToIndexMap.put(id, Integer.valueOf(users.size() - 1));
        return true;
    }

    /**
     * This function upgrades a user to a premium membership and generates a payment
     * and receipt.
     * 
     * @param userID   The unique identifier of the user being upgraded.
     * @param nickname The new nickname for the user being upgraded.
     * @param avatar   The avatar parameter is a string that represents the image or
     *                 icon that will be used to represent the user.
     * 
     * @param card     The card parameter is a String that represents the payment
     *                 method or credit card
     *                 information of the user who is upgrading their account.
     * @return The method returns a boolean value indicating whether the user
     *         upgrade was successful or not.
     */
    public boolean upgradeUser(String userID, String nickname, String avatar, String card) {
        // Base to Premium
        var done = false;

        if (!userExists(userID))
            return done;
        else if (!(getUserByID(userID) instanceof BaseUser))
            return done;

        var user = getUserByID(userID);

        var monthPaid = Calendar.getInstance().get(Calendar.MONTH);
        var payments = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        user = new PremiumUser(user.getName(), user.getEmail(), user.getPassword(), userID, user.getInitDate(),
                user.getLibrary(), nickname, avatar, card, monthPaid, payments);
        users.set(userIdToIndexMap.get(userID), user);
        if (user instanceof PremiumUser) {
            PremiumUser newPremiumUser = (PremiumUser) getUserByID(userID);
            newPremiumUser.generatePayment(MEMBERSHIP);
            // Generate receipt
            receipts.add(new Receipt("Premium Membership", user, Calendar.getInstance(), MEMBERSHIP));
            done = true;
        }
        return done;
    }

    /**
     * This function upgrades a user from a base user to a reviewer, generates a
     * payment for the new membership, and adds a receipt to the receipts list.
     * 
     * @param userID   The unique identifier of the user being upgraded.
     * @param nickname The new nickname for the user being upgraded.
     * @param avatar   The avatar parameter is a string that represents the image or
     *                 icon that a user chooses to represent themselves on their
     *                 profile.
     * @param card     The parameter "card" likely refers to the credit card
     *                 information of the user, which
     *                 may be used for payment or verification purposes.
     * @param interest The "interest" parameter in the "upgradeUser" method is a
     *                 String that represents
     *                 the interests of the user. It could be any topic or activity
     *                 that the user is interested in, such as sports, music,
     *                 cooking, etc.
     * @param blog     A string representing the blog URL of the user being
     *                 upgraded.
     * @return The method returns a boolean value indicating whether the user was
     *         successfully upgraded
     *         or not.
     */
    public boolean upgradeUser(String userID, String nickname, String avatar, String card, String interest,
            String blog) {
        // Base to Reviewer
        var done = false;

        if (!userExists(userID))
            return done;
        else if (!(getUserByID(userID) instanceof BaseUser))
            return done;

        var user = getUserByID(userID);

        var monthPaid = Calendar.getInstance().get(Calendar.MONTH);
        var payments = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), userID, user.getInitDate(),
                user.getLibrary(), nickname, avatar, card, monthPaid, payments, interest, 0, blog);
        users.set(userIdToIndexMap.get(userID), user);
        if (user instanceof Reviewer) {
            Reviewer newReviewer = (Reviewer) getUserByID(userID);
            newReviewer.generatePayment(MEMBERSHIP);
            receipts.add(new Receipt("Premium Membership", user, Calendar.getInstance(), MEMBERSHIP));
            done = true;
        }
        return done;
    }

    public boolean upgradeUser(String userID, String interest, String blog) {
        // Premium to Reviewer
        var done = false;
        if (!userExists(userID)) {
            return done;
        }

        PremiumUser user = (PremiumUser) getUserByID(userID);
        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInitDate(), user.getLibrary(), user.getNickname(),
                user.getAvatar(), user.getCard(), user.getLastMonthPaid().getValue(), user.getPayments(), interest, 0,
                blog);
        users.set(userIdToIndexMap.get(userID), user);
        if (user instanceof Reviewer) {
            done = true;
        }
        return done;
    }

    /**
     * The function generates a surprise for a user based on their ID, using a
     * random letter or month depending on the type of user.
     * 
     * @param userID The ID of the user for whom the surprise is being generated.
     * @return The method is returning a String, which is the surprise generated for
     *         the user with the given userID.
     */
    public String generateSurprise(String userID) {
        String surprise = "";

        if (getUserByID(userID) instanceof BaseUser) {
            BaseUser user = (BaseUser) getUserByID(userID);
            String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char randLetter = symbols.charAt(randInt(0, symbols.length()));
            surprise = user.surprise(randLetter);
        }

        else {
            var user = getUserByID(userID);
            int randMonth = randInt(1, 13);
            surprise = user.surprise(randMonth);
        }

        return surprise;
    }

    /**
     * This Java function displays a user's library of codes in a 5x5 matrix format
     * with padded
     * strings.
     * 
     * @param userID The userID is a String parameter that represents the unique
     *               identifier of a user
     *               in the system. It is used to retrieve the user's library, which
     *               is a list of codes that the user
     *               has access to.
     * @return The method is returning a formatted string that displays the user's
     *         library in a 5x5
     *         matrix format, with each code padded to the length of the longest
     *         code in the library. If the
     *         library has less than 25 codes, the remaining cells in the matrix
     *         will be filled with hyphens.
     */
    public String displayLibrary(String userID) {
        var userLib = getUserByID(userID).getLibrary();

        String library = "";

        int index = 0;
        int maxLength = userLib.stream() // Lambda to get the length of the largest String in the userLib
                .mapToInt(String::length)
                .max()
                .orElse(0); // If user has no products, then length will be 0

        // Iterate like a 5x5 matrix
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                // Only try to add codes to the matrix if there are still codes in the library
                if (index < userLib.size()) {
                    String code = userLib.get(index);
                    String paddedCode = String.format("| %-" + maxLength + "s ", code);
                    library += paddedCode;
                } else { // if the library has no more codes, then fill with hyphens
                    library += "| ---- ";
                }
                index++;
            }
            // Append a new line after each row
            library += "|\n";
        }
        return library;
    }

    // Product-related
    /**
     * This function generates a random 3-character code using a given set of
     * symbols.
     * 
     * @param symbols The "symbols" parameter is a String that contains the
     *                characters that can be used
     *                to generate the code.
     * @return The method is returning a String that consists of three randomly
     *         selected characters
     *         from the input String "symbols".
     */
    public String generateCode(String symbols) {
        String code = "";
        for (int i = 0; i < 3; i++) {
            code += symbols.charAt(randInt(0, symbols.length() - 1));
        }
        return code;
    }

    /**
     * This Java function returns a string containing the details of all products in
     * a given map.
     * 
     * @return The method `showProducts` is returning a string that contains the
     *         details of all the
     *         products in the `products` map. The details are obtained by calling
     *         the `toString` method on
     *         each product object and concatenating them together.
     */
    public String showProducts() {
        var productsList = "";

        for (String code : products.keySet()) {
            productsList += products.get(code).toString();
        }

        return productsList;
    }

    /**
     * This function registers a new book product with a generated code and adds it
     * to a map of
     * products.
     * 
     * @param name            The name of the book being registered.
     * @param publicationDate A Calendar object representing the date of publication
     *                        of the book.
     * @param pages           The number of pages in the book.
     * @param cover           The parameter "cover" is a String that represents the
     *                        type of cover of the book,
     *                        such as hardcover or paperback.
     * @param price           The price of the book.
     * @param review          The parameter "review" is a String that represents the
     *                        review or description of
     *                        the book.
     * @param genre           The genre parameter is an integer that represents the
     *                        category or type of the book
     *                        being registered. The specific values for each genre
     *                        may vary depending on the implementation,
     *                        but typically they are assigned numerical codes or IDs
     *                        that correspond to a specific genre such
     *                        as romance, mystery, science fiction, etc.
     * @return A boolean value indicating whether the book registration was
     *         successful or not.
     */
    public boolean registerBook(String name, Calendar publicationDate, int pages, String cover, double price,
            String review, int genre) {
        boolean done = false;
        String id = generateCode("ABCDEF1234567890");
        Product newBook = new Book(id, name, publicationDate, pages, cover, price, review, genre, 0, 0);
        products.put(id, newBook);
        if (products.get(id) instanceof Book) {
            done = true;
        }
        return done;
    }

    /**
     * This function registers a new magazine product with a unique ID, given its
     * name, publication
     * date, number of pages, cover type, price, category, and frequency.
     * 
     * @param name            The name of the magazine being registered.
     * @param publicationDate A Calendar object representing the date of publication
     *                        of the magazine.
     * @param pages           The number of pages in the magazine.
     * @param cover           The parameter "cover" is a String that represents the
     *                        type of cover of the
     *                        magazine, such as "hardcover" or "paperback".
     * @param price           The price of the magazine.
     * @param category        The category parameter is an integer that represents
     *                        the category of the
     *                        magazine. The specific categories and their
     *                        corresponding integer values would depend on the
     *                        implementation of the system.
     * @param freq            The "freq" parameter in the "registerMagazine" method
     *                        is an integer that represents
     *                        the frequency of the magazine's publication. It
     *                        indicates how often the magazine is published,
     *                        such as weekly, monthly, or quarterly.
     * @return The method is returning a boolean value indicating whether the
     *         registration of a new
     *         magazine was successful or not. If the registration was successful,
     *         the method returns true,
     *         otherwise it returns false.
     */
    public boolean registerMagazine(String name, Calendar publicationDate, int pages, String cover, double price,
            int category, int freq) {
        boolean done = false;
        String id = generateCode("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        Product newMagazine = new Magazine(id, name, publicationDate, pages, cover, price, category, freq, 0);
        products.put(id, newMagazine);
        if (products.get(id) instanceof Magazine) {
            done = true;
        }
        return done;
    }

    // Business-related
    /**
     * The function checks if a user can buy a product and generates a receipt if
     * the purchase is
     * successful.
     * 
     * @param userID    A string representing the ID of the user who wants to buy a
     *                  product.
     * @param productID The ID of the product being bought.
     * @return A boolean value is being returned.
     */
    public boolean buyProduct(String userID, String productID) {
        User user = getUserByID(userID);
        Product product = products.get(productID);

        // Check if the user and product exist
        if (user == null || product == null) {
            throw new IllegalArgumentException("User or product not found.");
        }

        for (String code : user.getLibrary()) {
            if (product.getId().equals(code)) {
                return false;
            }
        }

        // Check if the user has already bought the maximum number of
        // books or magazines (if user is a BaseUser)
        if (user instanceof BaseUser) {
            BaseUser baseUser = (BaseUser) user;
            if (product instanceof Book && baseUser.getBoughtBooks() >= MAX_BOOKS) {
                return false;
            } else if (product instanceof Magazine && baseUser.getSubscribedMagazines() >= MAX_MAGAZINES) {
                return false;
            }
        }

        // Generate receipt
        receipts.add(new Receipt(product.getName(), user, Calendar.getInstance(), product.getPrice()));

        user.getLibrary().add(productID);
        product.incrementCount();
        return true;
    }

    /**
     * The function returns the string representation of the last element in a list
     * of receipts.
     * 
     * @return The method `getLastReceipt()` is returning a string representation of
     *         the last receipt
     *         in the `receipts` list.
     */
    public String getLastReceipt() {
        return receipts.get(receipts.size() - 1).toString();
    }

    /**
     * The function returns a string of magazine names that a user with a given ID
     * is subscribed to.
     * 
     * @param userID The userID parameter is a String representing the unique
     *               identifier of a user in
     *               the system.
     * @return The method `getSubscribedMagazines` returns a string containing the
     *         names of all the
     *         magazines that the user with the given `userID` is subscribed to.
     */
    public String getSubscribedMagazines(String userID) {
        String magazine = "";

        for (String productCode : getUserByID(userID).getLibrary()) {
            if (products.get(productCode) instanceof Magazine) {
                magazine += products.get(productCode).getName() + "\n";
            }
        }
        return magazine;
    }

    /**
     * The function cancels a magazine subscription for a user and updates their
     * subscription count if
     * they are a base user.
     * 
     * @param userID    A String representing the ID of the user whose magazine
     *                  subscription is being
     *                  cancelled.
     * @param productID The ID of the magazine subscription that the user wants to
     *                  cancel.
     * @return A boolean value is being returned.
     */
    public boolean cancelMagazineSubscription(String userID, String productID) {
        var user = getUserByID(userID);
        var product = products.get(productID);

        if (user == null || product == null) {
            return false;
        }

        user.getLibrary().remove(productID);

        if (user instanceof BaseUser) {
            ((BaseUser) user).setSubscribedMagazines(((BaseUser) user).getSubscribedMagazines() - 1);
        }
        return true;
    }

    /**
     * This Java function edits a product's name, publication date, price, or number
     * of pages based on
     * user input.
     * 
     * @param code      A String representing the code of the product to be edited.
     * @param selection An integer representing the attribute of the product that
     *                  needs to be edited.
     *                  The possible values are:
     * @param change    The new value that will replace the existing value of the
     *                  selected attribute of
     *                  the product.
     * @return The method is returning a String. If the product with the given code
     *         does not exist, it
     *         returns a message saying so. Otherwise, it updates the selected
     *         attribute of the product with
     *         the given change and returns the updated product as a String.
     */
    public String editProduct(String code, int selection, String change) {
        if (products.get(code) == null) {
            return "There is not a product with this code";
        }

        var product = products.get(code);

        switch (selection) {
            case 1 -> product.setName(change);
            case 2 -> product.setPublicationDate(stringToDate(change));
            case 3 -> product.setPrice(Double.valueOf(change));
            case 4 -> product.setPages(Integer.valueOf(change));
        }

        return product.toString();
    }

    /**
     * This function deletes a product from a map of products based on its code and
     * returns a boolean
     * indicating whether the deletion was successful.
     * 
     * @param code a String representing the code of the product that needs to be
     *             deleted from a
     *             collection of products.
     * @return The method is returning a boolean value indicating whether or not the
     *         product with the
     *         given code was successfully deleted from the products map. If the
     *         product was found and deleted,
     *         the method returns true. If the product was not found, the method
     *         returns false.
     */
    public boolean deleteProduct(String code) {
        boolean check = products.get(code) != null;
        if (check) {
            products.remove(code);
            return check;
        }
        return check;
    }

    /**
     * The function returns the number of pages in a product if it exists, otherwise
     * it returns an
     * empty string.
     * 
     * @param userID    A string representing the user ID of the person starting the
     *                  reading session.
     * @param productID The unique identifier for the product being read.
     * @return The method `startReadingSession` returns a `String` value which
     *         represents the total
     *         number of pages in the product with the given `productID`. If the
     *         product is not found, an empty
     *         string is returned.
     */
    public String startReadingSession(String userID, String productID) {
        if (products.get(productID) == null) {
            return "";
        }
        String page = String.valueOf((products.get(productID)).getPages());
        return page;
    }

    /**
     * The function returns a randomly selected ad from an array of ads.
     * 
     * @return A randomly selected ad from the array `ads`.
     */
    public String showAd() {
        return ads[randInt(0, 2)];
    }

    // Reports

    public String showTotalReadPages() {

        String message = "";

        // magazines
        int magCount = 0;
        int bookCount = 0;
        for (String productCode : products.keySet()) {
            if (products.get(productCode) instanceof Magazine) {
                magCount += products.get(productCode).getReadPages();
            } else if (products.get(productCode) instanceof Book) {
                bookCount += products.get(productCode).getReadPages();
            }
        }
        message += "Magazines: " + magCount + "\n";
        message += "Books: " + bookCount + "\n";
        message += "Total: " + (magCount + bookCount) + "\n";
        return message;
    }

    // Testing

    public Calendar randDate() {
        return stringToDate(String.valueOf(randInt(1, 30) + "-" + randInt(1, 12) + "-" + randInt(1950, 2023)));
    }

    /**
     * The function deploys a test scenario by registering users, upgrading their
     * accounts, and
     * registering books and magazines.
     */
    public void deployTest() {
        registerUser("David", "1", "david.dulce@papyri.com", "test");
        registerUser("Liliana", "2", "liliana.franco@papyri.com", "test");
        registerUser("Jane Doe", "3", "jane.doe@papyri.com", "test");
        // premium
        upgradeUser("1", "Arkan", "avatar1", "1234 5678 9101 1121");
        // reviewer
        upgradeUser("1", "Doeman", "avatar2", "1234 5678 9876", "Engineering",
                "https://www.social-engineer.org/blog/");

        String lorem = "Lorem ipsum dolor sit amet.";
        registerBook("Unmasking the Social Engineer: The Human Element of Security",
                stringToDate("17-2-2014"), 256, lorem, 25.99, lorem, 4);
        registerBook("The great barbarian", randDate(), randInt(1, 1000), lorem, randInt(1, 100), lorem, 2);
        registerBook("Python: coding vibes", randDate(), randInt(1, 1000), lorem, randInt(1, 100), lorem, 4);
        registerBook("The Telematic Advantage: Unveiling Elite Programmers", randDate(), randInt(1, 1000), lorem,
                randInt(1, 100),
                "The best book you'll find in the market", 4);
        registerBook("Syntax Wars: Language's Last Stand", randDate(), randInt(1, 1000), lorem, randInt(1, 100), lorem,
                1);
        registerBook("Byte-sized Banter: The Compiler Capers", randDate(), randInt(1, 1000), lorem, randInt(1, 100),
                lorem, 3);
        registerBook("Binary Bonds: Love Among Microchips", randDate(), randInt(1, 1000), lorem, randInt(1, 100), lorem,
                2);
        registerMagazine("Curious Chronicles", randDate(), randInt(1, 50), lorem, randInt(1, 40), 1, 4);
        registerMagazine("Artistic Visions", randDate(), randInt(1, 50), lorem, randInt(1, 40), 2, 3);
        registerMagazine("The Science Spectrum", randDate(), randInt(1, 50), lorem, randInt(1, 40), 3, 3);
        registerMagazine("Urban Chic", randDate(), randInt(1, 50), lorem, randInt(1, 40), 4, 3);
        registerMagazine("IEEE Communications Magazine", stringToDate("2014-2-1979"), 50, lorem, 38.99, 5, 2);
        registerMagazine("Profit Pulse", randDate(), randInt(1, 50), lorem, randInt(1, 40), 6, 3);
    }

    /**
     * The function buys all products for a given user ID.
     * 
     * @param userID The userID parameter is a String that represents the unique
     *               identifier of the user
     *               who is buying all the products.
     */
    public void buyAllProducts(String userID) {
        for (String code : products.keySet()) {
            buyProduct(userID, code);
        }
    }
}
