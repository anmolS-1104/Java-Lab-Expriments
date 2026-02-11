
import java.time.*;
public class Book {
    public String name;
    public String authorName;
    public double price;
    public String publisherName;
    public String genre;
    public String ISBN;
    public LocalDate dateOfPublishing;
    public Book()
    {
        name = "unknown";
        authorName = "unknown";
        price = 0.0;
        publisherName = "unspecified";
        genre = "uncategorized";
        ISBN = "000000";
        dateOfPublishing = LocalDate.parse("2020-01-01");
    }
    public Book(String n,String a,double p,String isbn)
    {
        name = n;
        price = p;
        ISBN = isbn;
        authorName = a;
    }
    public Book(String n,String a,String g, double p)
    {
        name = n;
        authorName = a;
        genre = g;
        price = p;
    }
    public Book(Book b){
        price = b.price;
        publisherName = b.publisherName;
        genre = b.genre;
        authorName = b.authorName;
        ISBN = b.ISBN;
        name = b.name;
    }

}

