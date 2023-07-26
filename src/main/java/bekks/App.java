package bekks;

import bekks.config.HibernateConfig;
import bekks.entity.Books;
import bekks.service.BookService;
import bekks.service.impl.BookServiceImpl;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        HibernateConfig.createEntityManagerFactory();
        BookService bookService = new BookServiceImpl();

        while (true) {
            System.out.println("""
                    Press to 1 book
                    Press to 2 save all
                    Press to 3 find all
                    Press to 4 find by id
                    Press to 5 update 
                    Press to 6 remove
                    Press to 7 clear table
                    Press ro 8 drop
                    Press to 9 search By Name
                    Press to 10 unique name
                    Press to 11 save2
                    Press to 12 findById2
                    Press to 13 getAllBooks2
                    Press to 14 update2 
                    """);
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> {
                    bookService.saveBook(
                            new Books("Syngan",
                                    "T.Kasymbekov",
                                    BigDecimal.valueOf(1000))
                    );
                }
                case "2" -> {
                    bookService.saveAllBooks(
                            List.of(
                                    new Books("Kitep1", "Author1", BigDecimal.valueOf(900)),
                                    new Books("Kitep2", "Author2", BigDecimal.valueOf(700)),
                                    new Books("Kitep3", "Author3", BigDecimal.valueOf(200)),
                                    new Books("Kitep4", "Author4", BigDecimal.valueOf(600)),
                                    new Books("Kitep4", "Author5", BigDecimal.valueOf(1200)),
                                    new Books("Kitep4", "Author6", BigDecimal.valueOf(1300))


                            )
                    );
                }
                case "3" -> {
                    bookService.getAllBooks().forEach(System.out::println);
                }
                case "4" -> {
                    System.out.println("Write id for find : ");
                    try {
                        System.out.println(bookService.findById(new Scanner(System.in).nextLong()));
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
                case "5" -> {
                    System.out.println("Write id, name, author, price ");
                    System.out.println(bookService.update((new Scanner(System.in).nextLong()),
                            (new Scanner(System.in).nextLine()),
                            (new Scanner(System.in).nextLine()),
                            (new Scanner(System.in).nextBigDecimal())
                    ));
                }
                case "6" -> {
                    System.out.println("Write id : ");
                    System.out.println(bookService.remove(new Scanner(System.in).nextLong()));
                }
                case "7" -> {
                    bookService.clearTable();
                }
                case "8" -> {
                    bookService.dropTable();
                }
                case "9" -> {
                    bookService.searchByName("Kitep4").forEach(System.out::println);
                }
                case "10"->{
                    bookService.uniqueName().forEach(System.out::println);
                }
                case "11"->{
                    bookService.save2Book("Kitep7","author5",BigDecimal.valueOf(1500));
                }
                case "12"->{
                    bookService.findById2(new Scanner(System.in).nextLong());
                }
                case "13"->{
                    bookService.getAllBooks2().forEach(System.out::println);
                }
                case "14"->{
                    bookService.update2("Hahahi","Sara",BigDecimal.valueOf(12000));
                }
            }
        }

    }
}
