package it.epicode.dao;

import it.epicode.entity.Libro;
import it.epicode.entity.Pubblicazione;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LibroDAO {

    private EntityManager em;

    public void insertLibro(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public Libro libroPerId(Long id) {
        return em.find(Libro.class, id);
    }

    public Libro libroPerAutore(String autore) {
        return (Libro) em.createNamedQuery("trovaPerAutore", Libro.class).getResultList();
    }

    public void updateLibro(Libro libro) {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    public void deleteLibro(Libro libro) {
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
    }

    public void insertAll(List<Libro> libri) {
        em.getTransaction().begin();
        for (Libro e : libri) {
            em.persist(e);
        }
        em.getTransaction().commit();
    }
}
