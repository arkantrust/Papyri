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
        String library = "";

        // Fill the library
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                library += " | " + getUserByID(userID).getLibrary().get(j);
            }
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
            done = true;
        }
        addProductToList(id);
        return done;
    }

    // Business-related

    public void deployTest() {
        registerUser("Arkantrust", "1", "arkantrust@papyri.com", "test");
        registerUser("John Doe", "2", "john.doe@papyri.com", "test");
        registerUser("Jane Doe", "3", "jane.doe@papyri.com", "test");
        // premium
        upgradeUser("1", "Arkan", "avatar1", "1234 5678 9101 1121");
        // reviewer
        upgradeUser("1", "Doeman", "avatar2", "1234 5678 9876", "Engineering",
                "https://www.social-engineer.org/blog/");

        var bookReview = """
                Focuses on combining the science of understanding non-verbal communications
                with the knowledge of how social engineers, scam artists and con men use
                these skills to build feelings of trust and rapport in their targets.
                """;
        registerBook("Unmasking the Social Engineer: The Human Element of Security",
                stringToDate("17-2-2014"), 256,
                "https://m.media-amazon.com/images/I/51qbyOU3cEL._SX331_BO1,204,203,200_.jpg", 25.99,
                bookReview, 4);

        registerMagazine("IEEE Communications Magazine", stringToDate("2014-2-1979"),
                50, "https://www.ieee.org/ibp/product/images/MEMCOM019_ftrd.gif?ver=1", 0.50, 3, 2);
    }

    public boolean buyProduct(String userID, String productID) {
        User user = getUserByID(userID);
        Product product = products.get(productID);

        // Check if the user and product exist
        if (user == null || product == null) {
            throw new IllegalArgumentException("User or product not found.");
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

    public String getLastReceipt() {
        return receipts.get(receipts.size() - 1).toString();
    }

    public String getSubscribedMagazines(String userID) {
        String magazine = "";
        return magazine;
    }

    public boolean cancelMagazineSubscription(String userID, String productID) {
        User user = getUserByID(userID);
        Product product = products.get(productID);

        if (user == null || product == null) {
            throw new IllegalArgumentException("User or product not found.");
        }

        return true;
    }
}
