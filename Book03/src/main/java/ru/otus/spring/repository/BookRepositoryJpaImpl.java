package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Book> findByIds (List<Long> ids) {
        Query query = em.createQuery("select b from Book b where b.id in (:ids)");
        query.setParameter("ids", ids);

        return query.getResultList();
    }

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
    public Book findByIdAllInfo(long id) {
        Query query = em.createQuery("SELECT b FROM Book b join fetch b.genre g  WHERE b.id = :id");
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
