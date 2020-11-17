package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre findById(long id) {
        return em.find(Genre.class,id);
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g",Genre.class);
        return query.getResultList();
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return (Long) query.getSingleResult();
     }

    @Transactional
    @Override
    public void deleteById(long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        if(genre.getId()==0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }
}
