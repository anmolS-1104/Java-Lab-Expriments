import java.util.ArrayList;

public class ArrayListBook {
    public static void main(String[] args) {
        ArrayList<Book> bList = new ArrayList<>();
        double totalPrice = 0;

        // 1. Book 1 (Default)
        Book b1 = new Book();
        bList.add(b1);

        // 2. Book 2 & 4 Block
        try {
            Book b2 = new Book("Eclipse", "Stephenie Mayer", "Action", 455.99);
            bList.add(b2);
            Book b4 = b2; // Reference copy
            b4.name = "Harry Potter and the Deathly hallows";
            b4.authorName = "J.K Rowling";
            bList.add(b4);
        } catch (InvalidPriceException | InvalidGenreException e) {
            System.out.println("Error in Book 2/4: " + e.getMessage());
        }

        // 3. Book 3 Block (This one has the negative price -899.00)
        try {
            Book b3 = new Book("Atomic Habits", "James Clear", 899.00, "HLP12345");
            bList.add(b3);
        } catch (InvalidPriceException ip) {
            System.out.println("Caught expected error for Book 3: " + ip.getMessage());
        }

        // 4. Book 5 Block (Now properly wrapped in try-catch to fix your error)
        try {
            // Using a new book creation here to ensure it compiles
            Book b5 = new Book("Lord of the circles", "J.R Tolkin", "fiction", 500.00);
            b5.name = "Lord of the circles";
            bList.add(b5);
        } catch (InvalidPriceException ip) {
            System.out.println("Price error in Book 5: " + ip.getMessage());
        } catch (InvalidGenreException ig) {
            System.out.println("Genre error in Book 5: " + ig.getMessage());
        }

        // 5. Final Calculation Block
        System.out.println("\n--- Final Book List & Prices ---");
        for (Book b : bList) {
            System.out.println("Title: " + b.name + " | Price: " + b.price);
            totalPrice += b.price; // Adding all prices together
        }

        System.out.println("--------------------------------");
        System.out.println("Total Price of all books: " + totalPrice);
    }
}











