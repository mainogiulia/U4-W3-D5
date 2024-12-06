package it.epicode.dao;

import it.epicode.entity.Prestito;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {

    private EntityManager em;

    public void insertPrestito(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public Prestito prestitoPerId(Long id) {
        return em.find(Prestito.class, id);
    }

    public void updatePrestito(Prestito prestito) {
        em.getTransaction().begin();
        em.merge(prestito);
        em.getTransaction().commit();
    }

    public void deletePrestito(Prestito prestito) {
        em.getTransaction().begin();
        em.remove(prestito);
        em.getTransaction().commit();
    }

    public void insertAll(List<Prestito> prestiti) {
        em.getTransaction().begin();
        for (Prestito e : prestiti) {
            em.persist(e);
        }
        em.getTransaction().commit();
    }

    public List<Prestito> trovaPrestiti(LocalDate data){
        return this.em.createNamedQuery("trovaPrestitiScaduti", Prestito.class).getResultList();
    }
}
