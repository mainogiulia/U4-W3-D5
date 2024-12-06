package it.epicode.dao;

import it.epicode.entity.Rivista;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RivistaDAO {

    private EntityManager em;

    public void insertRivista(Rivista rivista) {
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
    }

    public Rivista rivistaPerId(Long id) {
        return em.find(Rivista.class, id);
    }

    public void updateRivista(Rivista rivista) {
        em.getTransaction().begin();
        em.merge(rivista);
        em.getTransaction().commit();
    }

    public void deleteRivista(Rivista rivista) {
        em.getTransaction().begin();
        em.remove(rivista);
        em.getTransaction().commit();
    }

    public void insertAll(List<Rivista> riviste) {
        em.getTransaction().begin();
        for (Rivista e : riviste) {
            em.persist(e);
        }
        em.getTransaction().commit();
    }
}
