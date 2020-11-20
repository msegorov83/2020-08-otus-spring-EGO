package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookAuthor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookAuthorRepositoryJpaImpl implements BookAuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public BookAuthor save (BookAuthor bookAuthor) {
            em.persist(bookAuthor);
            return bookAuthor;
    }

    @Override
    public List<BookAuthor> findByAuthorId (Author author)
    {
        Query query = em.createQuery("select ba from BookAuthor ba where ba.author = :author_id");
        query.setParameter("author_id", author);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteByBookId(long bookId) {
        Query query = em.createQuery("delete from BookAuthor ba where ba.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();

    }
}
