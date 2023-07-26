package bekks.service;


import bekks.entity.Books;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {

    String saveBook(Books book);

    void saveAllBooks(List<Books> books);

    List<Books> getAllBooks();

    Books findById(Long id);

    Books update(Long bookId, String name, String author, BigDecimal price);

    String remove(long bookId);

    void clearTable();

    void dropTable();

    List<Books> searchByName(String name);

    List<String> uniqueName();

    void save2Book(String name, String author, BigDecimal price);

    Books findById2(Long bookId);

    List<Books> getAllBooks2();

    void update2(String name, String author, BigDecimal price);
}
