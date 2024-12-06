package it.epicode;

import it.epicode.dao.*;
import it.epicode.entity.Libro;
import it.epicode.entity.Periodicita;
import it.epicode.entity.Rivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
    EntityManager em = emf.createEntityManager();

    LibroDAO libroDAO = new LibroDAO(em);
    RivistaDAO rivistaDAO = new RivistaDAO(em);
    PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
    UtenteDAO utenteDAO = new UtenteDAO();
    PrestitoDAO prestitoDAO = new PrestitoDAO();

    //METODO PER AGGIUNGERE UN ELEMENTO

    public void aggiungiElemento(){
        System.out.println("Cosa desideri aggiungere? Un libro(1) o una rivista(2) ? Altro per uscire");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Libro libro = new Libro();

            System.out.println("Inserisci il codice ISBN del libro");
            String isbn = scanner.nextLine();
            libro.setISBN(isbn);

            System.out.println("Inserisci il titolo del libro");
            String titolo = scanner.nextLine();
            libro.setTitolo(titolo);

            System.out.println("Inserisci l'anno di pubblicazione del libro");
            int annoPubblicazione = scanner.nextInt();
            scanner.nextLine();
            libro.setAnnoPubblicazione(annoPubblicazione);

            System.out.println("Inserisci il numero di pagine del libro");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();
            libro.setNumeroPagine(numeroPagine);

            System.out.println("Inserisci l'autore del libro");
            String autore = scanner.nextLine();
            libro.setGenere(autore);

            System.out.println("Inserisci il genere del libro");
            String genere = scanner.nextLine();
            libro.setGenere(genere);

            libroDAO.insertLibro(libro);
        } else if (choice == 2){
            Rivista rivista = new Rivista();

            System.out.println("Inserisci il codice ISBN della rivista");
            String isbn = scanner.nextLine();
            rivista.setISBN(isbn);

            System.out.println("Inserisci il titolo della rivista");
            String titolo = scanner.nextLine();
            rivista.setTitolo(titolo);

            System.out.println("Inserisci l'anno di pubblicazione della rivista");
            int annoPubblicazione = scanner.nextInt();
            scanner.nextLine();
            rivista.setAnnoPubblicazione(annoPubblicazione);

            System.out.println("Inserisci il numero di pagine della rivista");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();
            rivista.setNumeroPagine(numeroPagine);

            System.out.println("Che tipo di periodicità ha la rivista? Settimanale(1), Mensile(2) o Semestrale(3)?");
            int periodicita = scanner.nextInt();
            scanner.nextLine(); // Consuma il carattere di newline

            Periodicita scelta = switch (periodicita) {
                case 1 -> Periodicita.SETTIMANALE;
                case 2 -> Periodicita.MENSILE;
                case 3 -> Periodicita.SEMESTRALE;
                default -> null;
            };
            rivista.setPeriodicita(scelta);

            rivistaDAO.insertRivista(rivista);
        } else {
            System.out.println("Chiusura programma");
        }
    }

    //METODO PER RIMUOVERE UN ELEMENTO TRAMITE ISBN
    public static void rimuoviElemento(){

    }
}
