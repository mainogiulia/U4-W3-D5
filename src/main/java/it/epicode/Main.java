package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.dao.*;
import it.epicode.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main {
     public static void main(String[] args) {
          Faker faker = new Faker(new Locale("it"));
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
          EntityManager em = emf.createEntityManager();

          LibroDAO libroDAO = new LibroDAO(em);
          RivistaDAO rivistaDAO = new RivistaDAO(em);
          PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
          UtenteDAO utenteDAO = new UtenteDAO();
          PrestitoDAO prestitoDAO = new PrestitoDAO();

          List<Libro> libri = new ArrayList<>();
          List<Rivista> riviste = new ArrayList<>();
          List<Pubblicazione> pubblicazioni = new ArrayList<>();
          List<Utente> utenti = new ArrayList<>();
          List<Prestito> prestiti = new ArrayList<>();

          //AGGIUNGO I LIBRI
          for (int i = 0; i < 30; i++) {
               Libro libro = new Libro();
               libro.setTitolo(faker.book().title());
               libro.setAutore(faker.book().author());
               libro.setGenere(faker.book().genre());
               libro.setAnnoPubblicazione(faker.number().numberBetween(1930, 2024));
               libro.setNumeroPagine(faker.number().numberBetween(200, 1200));
               libri.add(libro);
          }

          //AGGIUNGO LE RIVISTE
          for (int i = 0; i < 30; i++) {
               Rivista rivista = new Rivista();
               rivista.setTitolo(faker.book().title());
               rivista.setPeriodicita(Periodicita.values()[faker.random().nextInt(Periodicita.values().length)]);
               rivista.setAnnoPubblicazione(faker.number().numberBetween(1930, 2024));
               rivista.setNumeroPagine(faker.number().numberBetween(200, 1200));
               riviste.add(rivista);
          }

          //AGGIUNGO GLI UTENTI
          for (int i = 0; i < 80; i++) {
               Utente utente = new Utente();
               utente.setNome(faker.name().firstName());
               utente.setCognome(faker.name().lastName());
               utente.setDataNascita(faker.date().birthday());
               utenti.add(utente);
          }

          //AGGIUNGO I PRESTITI
          for (int i = 0; i < 100; i++) {
               Prestito prestito = new Prestito();
               Utente utente = utenteDAO.utentePerId(faker.number().randomNumber());
               prestito.setUtente(utente);
               prestito.setDataInizioPrestito(faker.date().past(1095, TimeUnit.DAYS));
               prestito.setDataRestituzionePrevista(faker.date().future(30, TimeUnit.DAYS, prestito.getDataInizioPrestito()));
               prestito.setDataRestituzioneEffettiva(faker.date().between(prestito.getDataInizioPrestito(), faker.date().future(500, TimeUnit.DAYS)));
               prestiti.add(prestito);
          }

          libroDAO.insertAll(libri);
          rivistaDAO.insertAll(riviste);
          utenteDAO.insertAll(utenti);
     }
}
