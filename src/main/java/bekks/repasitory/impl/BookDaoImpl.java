package bekks.repasitory.impl;

import bekks.config.HibernateConfig;
import bekks.entity.Books;
import bekks.repasitory.BookDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BookDaoImpl implements BookDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.createEntityManagerFactory();
    private final SessionFactory sessionFactory = HibernateConfig.createSessionFactory();

    @Override
    public void save(Books book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveAllbooks(List<Books> books) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        for (int i = 0; i < books.size(); i++) {
//            entityManager.persist(books.get(i));
//        }
        books.forEach(entityManager::persist);


        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Books> getAllBooks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Books> resultList = entityManager.createQuery("""
                select b from Books b
                """, Books.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Books FindById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.find(Books.class, id);
        Books book = entityManager.createQuery("""
                        select b from Books b where b.id=:newid
                        """, Books.class)
                .setParameter("newid", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return book;
    }

    @Override
    public Books update(Long bookId, String name, String author, BigDecimal price) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Books bookToUpdate = entityManager.find(Books.class, bookId);
        if (bookToUpdate != null) {
            bookToUpdate.setName(name);
            bookToUpdate.setAuthor(author);
            bookToUpdate.setPrice(price);

            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Book with id " + bookId + " not found.");
        }

        entityManager.close();
        return bookToUpdate;
    }

    @Override
    public String remove(Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Books books = FindById(bookId);
        entityManager.remove(books);
        entityManager.getTransaction().commit();
        entityManager.close();
        return books.getName() + " is remove";
    }

    @Override
    public void clearTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("""
                delete from Books 
                """).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void dropTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String sql = ("drop table Books");
        entityManager.createNativeQuery(sql).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Books> searchByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Books> booksList = entityManager.createQuery("""
                        select b from Books b where name=:nameS
                        """, Books.class)
                .setParameter("nameS", name)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return booksList;
    }

    @Override
    public List<String> uniqueName() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> resultList = entityManager.createQuery("""
                select distinct b.name from Books b
                """).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public void save2Book(String name, String author, BigDecimal price) {
        EntityManager entityManager = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        entityManager.getTransaction().begin();

        Books book = new Books();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);

        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(book.getName() + " success saved");
    }

    @Override
    public Books findById2(Long bookId) {
        EntityManager entityManage = entityManagerFactory.createEntityManager();
        entityManage.getTransaction().begin();

        Books books = entityManage.find(Books.class, bookId);

        entityManage.getTransaction().commit();
        entityManage.close();
        return books;
    }

    @Override
    public List<Books> getAllBooks2() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String hql = "select Books";
        List<Books> booksList = entityManager.createQuery(hql, Books.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return booksList;
    }

    @Override
    public void update2(String name, String author, BigDecimal price) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query updateQuery = entityManager.createNativeQuery("""
            update Books set name=:name, author=:author, price=:price
            """);
        updateQuery.setParameter("name", name);
        updateQuery.setParameter("author", author);
        updateQuery.setParameter("price", price);
        int rowsUpdated = updateQuery.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }



    public Optional<Books> FindById1(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        Books book =entityManager.find(Books.class, id);
        Books book = entityManager.createQuery("""
                        select b from Books b where b.id=:newid
                        """, Books.class)
                .setParameter("newid", id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(book);
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        sessionFactory.close();
    }
}
