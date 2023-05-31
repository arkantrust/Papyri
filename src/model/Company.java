package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class Company implements Randomizable, Emboldenable, DateManipulator {

    // attributes
    private String name;

    private ArrayList<User> users;
    private String userList;

    private HashMap<String, String> credentials; // Saves all login information for verification
    private HashMap<String, Integer> userIdToIndexMap; // Relates a user's ID to its position in the ArrayList
    private ArrayList<Receipt> receipts;

    // constants
    public static final double MEMBERSHIP = 5; // premium membership price in USD
    public static final double MAX_BOOKS = 5; // Max books a base user can buy
    public static final double MAX_MAGAZINES = 2; // Max magazines a base user can be subscribed to

    private HashMap<String, Product> products;
    private String productsList;

    // constructor
    public Company(String name) {
        this.name = name;
        users = new ArrayList<>();
        userList = "";
        credentials = new HashMap<>();
        credentials = new HashMap<>();
        productsList = "";
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

    public String getProductsList() {
        return productsList;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
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

    // methods

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

    public User getUserByID(String id) {
        return users.get(userIdToIndexMap.get(id));
    }

    public boolean login(String id, String password) {
        if (credentials.containsKey(id) && credentials.get(id).equals(password)) {
            return true;
        }
        return false;
    }

    // User-related
    public boolean userExists(String userID) {
        var index = userIdToIndexMap.get(userID);
        var usersLen = users.size();
        boolean exists = (index >= usersLen || index < usersLen) ? true : false;
        return exists;
    }

    public boolean registerUser(String name, String id, String email, String password) {
        User newUser = new BaseUser(name, email, password, id, Calendar.getInstance(), true,
                new ArrayList<>(), 0, 0);
        users.add(newUser);
        credentials.put(id, password);
        userList += users.get(users.size() - 1).toString() + '\n';
        userIdToIndexMap.put(id, Integer.valueOf(users.size() - 1));
        return true;
    }

    // Base to Premium
    public boolean upgradeUser(String userID, String nickname, String avatar, String card) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }
        var user = (BaseUser) getUserByID(userID);
        user = new PremiumUser(user.getName(), user.getEmail(), user.getPassword(), user.getID(), user.getInitDate(),
                false, user.getLibrary(), user.getBoughtBooks(), user.getSubscribedMagazines(), nickname, avatar,
                card,
                Calendar.getInstance().get(Calendar.MONTH), new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
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

    // Base to Reviewer
    public boolean upgradeUser(String userID, String nickname, String avatar, String card, String interest,
            String blog) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }
        var user = (BaseUser) getUserByID(userID);
        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInitDate(), false, user.getLibrary(), user.getBoughtBooks(), user.getSubscribedMagazines(),
                nickname, avatar, card, Calendar.getInstance().get(Calendar.MONTH),
                new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, interest, 0, blog);
        users.set(userIdToIndexMap.get(userID), user);
        if (user instanceof Reviewer) {
            Reviewer newReviewer = (Reviewer) getUserByID(userID);
            newReviewer.generatePayment(MEMBERSHIP);
            done = true;
        }
        return done;
    }

    // Premium to Reviewer
    public boolean upgradeUser(String userID, String interest, String blog) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }

        PremiumUser user = (PremiumUser) getUserByID(userID);
        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInitDate(), user.hasAds(), user.getLibrary(), user.getBoughtBooks(),
                user.getSubscribedMagazines(), user.getNickname(),
                user.getAvatar(), user.getCard(), user.getLastMonthPaid().getValue(), user.getPayments(), interest, 0,
                blog);
        users.set(userIdToIndexMap.get(userID), user);
        if (user instanceof Reviewer) {
            done = true;
        }
        return done;
    }

    public String generateSurprise(String userID) {
        int randMonth = randInt(1, 13);
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char randLetter = symbols.charAt(randInt(0, symbols.length()));
        var user = (BaseUser) getUserByID(userID);
        return user.surprise(randMonth, randLetter);
    }

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
    public String generateCode(String symbols) {
        String code = "";
        for (int i = 0; i < 3; i++) {
            code += symbols.charAt(randInt(0, symbols.length()));
        }
        return code;
    }

    public void addProductToList(String id) {
        productsList += "- " + products.get(id).getName() + " | ";
        productsList += products.get(id).getId() + " | ";
        productsList += products.get(id).getPrice() + "\n";
    }

    public boolean registerBook(String name, Calendar publicationDate, int pages, String cover, double price,
            String review, int genre) {
        boolean done = false;
        String id = generateCode("ABCDEF1234567890");
        Product newBook = new Book(id, name, publicationDate, pages, cover, price, review, genre, 0, 0);
        products.put(id, newBook);
        if (products.get(id) instanceof Book) {
            done = true;
        }
        addProductToList(id);
        return done;
    }

    public boolean registerMagazine(String name, Calendar publicationDate, int pages, String cover, double price,
            int category, int freq) {
        boolean done = false;
        String id = generateCode("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        Product newMagazine = new Magazine(id, name, publicationDate, pages, cover, price, category, freq, 0);
        products.put(id, newMagazine);
        if (products.get(id) instanceof Magazine) {
            addProductToList(id);
            done = true;
        }
        return done;
    }

    // Business-related

    public Calendar randDate() {
        return stringToDate(String.valueOf(randInt(1, 29) + "-" + randInt(1, 12) + "-" + randInt(1950, 2023)));
    }

    public void deployTest() {
        registerUser("Arkantrust", "1", "arkantrust@papyri.com", "test");
        registerUser("John Doe", "2", "john.doe@papyri.com", "test");
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

    public void buyAllProducts(String userID) {
        for (String code : products.keySet()) {
            buyProduct(userID, code);
        }
    }

    public String getLastReceipt() {
        return receipts.get(receipts.size() - 1).toString();
    }

    public String getSubscribedMagazines(String userID) {
        String magazine = "";

        for (String productCode : getUserByID(userID).getLibrary()) {
            if (products.get(productCode) instanceof Magazine) {
                magazine += products.get(productCode).getName() + "\n";
            }
        }
        return magazine;
    }

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
}
