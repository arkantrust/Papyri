package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Company implements Randomizable {

    // attributes
    private String name;
    private String nit;
    private String address;

    /*
     * 
     * to a new item
     * so instead of looping through the array, the id attribute is the position,
     * going from complexity O(n) to O(1)
     */
    private ArrayList<User> users;
    private int position; // Stores the position of a user in the ArrayList
    private String userList;
    private String productsList;
    private HashMap<String, String> credentials; // Saves all login information for verification
    private HashMap<String, Integer> userIdToPositionMap; // Relates a user's ID to its position in the ArrayList

    // constants
    public static final double PREMIUM = 5; // USD

    // As this is a single-threaded program, Hashmap (Not syncronized) will perform
    // faster than HashTable(Syncronized)
    private HashMap<String, Product> products;

    // constructor
    public Company(String name, String nit, String address) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        users = new ArrayList<>();
        position = 1;
        userList = "";
        credentials = new HashMap<>();
        credentials = new HashMap<>();
        productsList = "";
        products = new HashMap<>();
        users.add(new Admin("admin", "admin@papyri.com", "devtest",
                "ADMIN", position - 1, Calendar.getInstance()));
        credentials.put(users.get(0).getName(), users.get(0).getPassword());
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int userIDs) {
        this.position = userIDs;
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

    public HashMap<String, Integer> getUserIdToPositionMap() {
        return userIdToPositionMap;
    }

    public void setUserIdToPositionMap(HashMap<String, Integer> userIdToPositionMap) {
        this.userIdToPositionMap = userIdToPositionMap;
    }

    public static double getPremium() {
        return PREMIUM;
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

    @Override
    public int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public void addCredentials(String id, String password) {
        credentials.put(id, password);
    }

    public User getUserByID(String id) {
        int position = userIdToPositionMap.get(id);
        return users.get(position);
    }

    public Boolean login(String id, String password) {
        boolean login = false;
        if (credentials.get(id).equals(password)) {
            login = true;
        }
        return login;
    }

    // User-related
    public boolean userExists(int id) {
        boolean exists = (id >= 0 || id < position) ? true : false;
        return exists;
    }


    public void addUserToList() {
        userList += users.get(position).toString() + '\n';
    }

    public boolean addUser(String name, String id, String email, String password) {
        boolean done = false;
        User newUser = new BaseUser(name, email, password, id, position, Calendar.getInstance(), true,
                new ArrayList<>(), "", 0, 0, 0);
        users.add(newUser);
        addUserToList();
        addCredentials(id, password);
        position++;
        done = true;
        return done;
    }

    public String displayUser(int userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        return users.get(userID).toString();
    }

    public String displayUserName(int userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        return users.get(userID).getName();
    }

    // Base to Premium
    public boolean upgradeUser(int userID, String nickname, String avatar, String card) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }
        var user = (BaseUser) users.get(userID);
        user = new PremiumUser(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInternalID(), user.getInitDate(), false, user.getProductsOwned(), user.getProductsOwnedList(),
                user.getProductsOwnedCount(), user.getBoughtBooks(), user.getSubscribedMagazines(), nickname, avatar,
                card,
                Calendar.getInstance().get(Calendar.MONTH), new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        users.set(userID, user);
        if (user instanceof PremiumUser) {
            PremiumUser newPremiumUser = (PremiumUser) users.get(userID);
            newPremiumUser.generatePayment(PREMIUM);
            done = true;
        }
        return done;
    }

    // Base to Reviewer
    public boolean upgradeUser(int userID, String nickname, String avatar, String card, String interest, String blog) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }
        var user = (BaseUser) users.get(userID);
        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInternalID(), user.getInitDate(), false, user.getProductsOwned(), user.getProductsOwnedList(),
                user.getProductsOwnedCount(), user.getBoughtBooks(), user.getSubscribedMagazines(),
                nickname, avatar, card, Calendar.getInstance().get(Calendar.MONTH),
                new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, interest, 0, blog);
        users.set(userID, user);
        if (user instanceof Reviewer) {
            Reviewer newReviewer = (Reviewer) users.get(userID);
            newReviewer.generatePayment(PREMIUM);
            done = true;
        }
        return done;
    }

    // Premium to Reviewer
    public boolean upgradeUser(int userID, String interest, String blog) {
        var done = false;
        if (!userExists(userID)) {
            return done;
        }

        PremiumUser user = (PremiumUser) users.get(userID);
        user = new Reviewer(user.getName(), user.getEmail(), user.getPassword(), user.getID(),
                user.getInternalID(), user.getInitDate(), user.hasAds(), user.getProductsOwned(),
                user.getProductsOwnedList(),
                user.getProductsOwnedCount(), user.getBoughtBooks(), user.getSubscribedMagazines(), user.getNickname(),
                user.getAvatar(), user.getCard(),
                user.getLastPaidMonth(), user.getPayments(), interest, 0, blog);
        users.set(userID, user);
        if (user instanceof Reviewer) {
            done = true;
        }
        return done;
    }

    public String generateSurprise(int userID) {
        int randMonth = randInt(1, 13);
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char randLetter = symbols.charAt(randInt(0, symbols.length()));
        var user = (BaseUser) users.get(userID);
        return user.surprise(randMonth, randLetter);
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
        productsList += products.get(id).toString() + '\n';
    }

    private BookGenre getBookGenre(int intGenre) {
        return switch (intGenre) {
            case 1 -> BookGenre.SCIENCE_FICTION;
            case 2 -> BookGenre.FANTASY;
            case 3 -> BookGenre.HISTORICAL_NOVEL;
            default -> null;
        };
    }

    public boolean registerBook(String name, Calendar publicationDate, int pages, String cover, double price,
            String review, int genre) {
        boolean done = false;
        String id = generateCode("ABCDEF1234567890");
        Product newBook = new Book(id, name, publicationDate, pages, cover, price, review, getBookGenre(genre),
                0, 0);
        products.put(id, newBook);
        if (products.get(id) instanceof Book) {
            done = true;
        }
        addProductToList(id);
        return done;
    }

    public IssuanceFrequency getIssuanceFrequency(int intFreq) {
        return switch (intFreq) {
            case 1 -> IssuanceFrequency.YEARLY;
            case 2 -> IssuanceFrequency.MONTHLY;
            case 3 -> IssuanceFrequency.WEEKLY;
            case 4 -> IssuanceFrequency.DAILY;
            default -> null;
        };
    }

    public static MagazineCategory getMagazineCategory(int intCategory) {
        return switch (intCategory) {
            case 1 -> MagazineCategory.MISCELLANY;
            case 2 -> MagazineCategory.DESIGN;
            case 3 -> MagazineCategory.SCIENTIFIC;
            default -> null;
        };
    }

    public boolean registerMagazine(String name, Calendar publicationDate, int pages, String cover, double price,
            int category, int freq) {
        boolean done = false;
        String id = generateCode("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        Product newMagazine = new Magazine(id, name, publicationDate, pages, cover, price,
                getMagazineCategory(category),
                getIssuanceFrequency(freq), 0);
        products.put(id, newMagazine);
        if (products.get(id) instanceof Magazine) {
            done = true;
        }
        addProductToList(id);
        return done;
    }

    // Business-related

    public boolean buyProduct(int userID, String productID) {
        boolean done = false;
        Product product = products.get(productID);
        var user = (BaseUser) users.get(userID);
        user.addProduct(product);

        if (user.productsOwned.get(user.getProductsOwnedCount() - 1).equals(product)) {
            done = true;
        } else {
            done = false;
        }
        return done;
    }
}
