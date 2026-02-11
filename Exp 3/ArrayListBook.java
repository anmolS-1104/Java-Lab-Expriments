import java.util.ArrayList;

public class ArrayListBook {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("Eclipse","Stephenie Mayer","Fiction",455.99);
        Book b3 = new Book("Atomic Habits","James Clear","HUB",899.00);
        Book b4 = b2;
        b4.name ="Harry Potter and the Deathly hallows";
        b4.authorName = "J.K Rowling";
        Book b5 = new Book(b3);
        b5.name = "Lord of the circles";
        ArrayList<Book> bList= new ArrayList();
        bList.add(b1);
        bList.add(b2);
        bList.add(b3);
        bList.add(b4);
        bList.add(b5);
        bList.forEach(b->{
            System.out.println(b.name);
            System.out.println(b.authorName);
            System.out.println(b.price);
            System.out.println(b.ISBN);
            System.out.println(b.genre);
            System.out.println(b.publisherName);
            System.out.println(b.dateOfPublishing);
        });


    }}

