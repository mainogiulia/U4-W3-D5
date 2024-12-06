package it.epicode.dao;

import it.epicode.entity.Pubblicazione;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PubblicazioneDAO {

    private EntityManager em;

    public void insertPubblicazione(Pubblicazione pubblicazione) {
        em.getTransaction().begin();
        em.persist(pubblicazione);
        em.getTransaction().commit();
    }

    public Pubblicazione pubblicazionePerId(Long id) {
        return em.find(Pubblicazione.class, id);
    }

    public Pubblicazione pubblicazionePerISBN(String isbn) {
        return (Pubblicazione) em.createNamedQuery("trovaPerISBN", Pubblicazione.class).getResultList();
    }

    public Pubblicazione pubblicazionePerAnno(Integer annoPubblicazione) {
        return (Pubblicazione) em.createNamedQuery("trovaPerAnno", Pubblicazione.class).getResultList();
    }

    public Pubblicazione pubblicazionePerTitolo(String titolo) {
        return (Pubblicazione) em.createNamedQuery("trovaPerTitolo", Pubblicazione.class).getResultList();
    }

    public void updatePubblicazione(Pubblicazione pubblicazione) {
        em.getTransaction().begin();
        em.merge(pubblicazione);
        em.getTransaction().commit();
    }

    public void deletePubblicazione(Pubblicazione pubblicazione) {
        em.getTransaction().begin();
        em.remove(pubblicazione);
        em.getTransaction().commit();
    }

    public void deletePubblicazionePerISBN(String isbn) {
        Pubblicazione pubblicazione = pubblicazionePerISBN(isbn);
        em.getTransaction().begin();
        em.remove(pubblicazione);
        em.getTransaction().commit();
    }

    public void insertAll(List<Pubblicazione> pubblicazioni) {
        em.getTransaction().begin();
        for (Pubblicazione e : pubblicazioni) {
            em.persist(e);
        }
        em.getTransaction().commit();
    }
}