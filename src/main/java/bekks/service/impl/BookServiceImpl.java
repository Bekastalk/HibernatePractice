package bekks.service.impl;


import bekks.entity.Books;
import bekks.repasitory.BookDao;
import bekks.repasitory.impl.BookDaoImpl;
import bekks.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    BookDao bookDao=new BookDaoImpl();
    @Override
    public String saveBook(Books book) {
        bookDao.save(book);
        return "Successfully "+book.getName();
    }

    @Override
    public void saveAllBooks(List<Books> books) {
        bookDao.saveAllbooks(books);
    }

    @Override
    public List<Books> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Books findById(Long id) {

        Books books = bookDao.FindById1(id).orElseThrow(() -> new RuntimeException("Book with id " +id+ " not found!"));
        return books;
    }

    @Override
    public Books update(Long bookId, String name, String author, BigDecimal price) {

        return bookDao.update(bookId, name, author, price);
    }

    @Override
    public String remove(long bookId) {

        return  bookDao.remove(bookId);
    }

    @Override
    public void clearTable() {
        bookDao.clearTable();
    }

    @Override
    public void dropTable() {
        bookDao.dropTable();
    }

    @Override
    public List<Books> searchByName(String name) {

        return bookDao.searchByName(name);
    }

    @Override
    public List<String> uniqueName() {
        return bookDao.uniqueName();
    }

    @Override
    public void save2Book(String name, String author, BigDecimal price) {
        bookDao.save2Book(name,author,price);
    }

    @Override
    public Books findById2(Long bookId) {
        return bookDao.findById2(bookId);
    }

    @Override
    public List<Books> getAllBooks2() {
        return bookDao.getAllBooks2();
    }

    @Override
    public void update2(String name, String author, BigDecimal price) {
        bookDao.update2(name, author, price);
    }

//    @Override
//    public Books FindById(Long id) {
//
//        Books books = bookDao.FindById1(id).orElseThrow(() -> new RuntimeException("Book with id " +id+ " not found!"));
//        return books;
//    }

}
