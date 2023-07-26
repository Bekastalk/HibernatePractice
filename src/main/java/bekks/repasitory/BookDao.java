package bekks.repasitory;

import bekks.entity.Books;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    void save(Books book);
    Optional<Books> FindById1(Long id);

    void saveAllbooks(List<Books> books);

    List<Books> getAllBooks();

    Books FindById(Long id);

    Books update(Long bookId, String name, String author, BigDecimal price);


    String remove(Long bookId);

    void clearTable();

    void dropTable();

    List<Books> searchByName(String name);

    List<String> uniqueName();

    void save2Book(String name, String author, BigDecimal price);

    Books findById2(Long bookId);

    List<Books> getAllBooks2();

    void update2(String name, String author, BigDecimal price);
}
