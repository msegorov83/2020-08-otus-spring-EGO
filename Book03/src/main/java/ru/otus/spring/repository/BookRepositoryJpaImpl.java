package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> finbByAuthor(Author author){
        Query query = em.createQuery("SELECT b FROM Book b join fetch b.author a WHERE a.id = :id");
        query.setParameter ("id", author.getId());
        return query.getResultList();
    }

    @Override
    public Book findByIdAllInfo(long id) {
        Query query = em.createQuery("SELECT b FROM Book b join fetch b.genre g join fetch b.author a WHERE b.id = :id");
        query.setParameter("id", id);
        return (Book) query.getSingleResult();
    }


    @Transactional
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


}
