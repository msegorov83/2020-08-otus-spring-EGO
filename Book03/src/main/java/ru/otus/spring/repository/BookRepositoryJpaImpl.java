package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book findByIdAllInfo(long id) {
        Query query = em.createQuery("SELECT b FROM Book b join fetch b.genre g join fetch b.author a WHERE b.id = :id");
        query.setParameter("id", id);
        return (Book) query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if(book.getId()==0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(b) from Book b");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Book> findAllBookByAuthor(String name){
        Query query = em.createQuery("SELECT b FROM Book b join fetch b.genre g join fetch b.author a WHERE a.fullName = :fullName");
        query.setParameter("fullName", name);
        return query.getResultList();
    }
}
