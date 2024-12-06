package it.epicode;

import it.epicode.dao.*;
import it.epicode.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


//NOTA: ho provato a partire dal compito della settimana scorsa ma non riuscivo a trovarmi allora ho ricominciato da capo e tra i vari errori
// mi sono trovata senza molto tempo e quindi a fare tutto di fretta, col senno di poi ho sbagliato a dare priorità a finire tutti i punti
// piuttosto che fare bene quello che potevo


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
    static EntityManager em = emf.createEntityManager();

    static LibroDAO libroDAO = new LibroDAO(em);
    static RivistaDAO rivistaDAO = new RivistaDAO(em);
    static PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
    static PrestitoDAO prestitoDAO = new PrestitoDAO();

    //ESECUZIONE METODI ------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        boolean scelta = true;
        while (scelta) {
            System.out.println("----------------------------------- " +
                    "\nCosa desideri fare?" +
                    "\n1: Aggiungere un elemento al catalogo" +
                    "\n2: Rimuovere un elemento tramite codice ISBN" +
                    "\n3: Trovare un elemento tramite codice ISBN" +
                    "\n4: Trovare un elemento tramite anno di pubblicazione" +
                    "\n5: Trovare un libro tramite autore" +
                    "\n6: Trovare un elemento tramite titolo" +
                    "\n7: Trovare gli elementi non ancora restituiti, la cui data di restituzione è scaduta");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> aggiungiElemento();
                case 2 -> rimuoviElementoPerISBN();
                case 3 -> trovaElementoPerISBN();
                case 4 -> trovaElementoPerAnno();
                case 5 -> trovaElementoPerAutore();
                case 6 -> trovaElementoPerTitolo();
                case 7 -> trovaPrestitiScaduti();
                default -> {
                    scelta = false;
                    System.out.println("Error");
                }
            }
        }
    }

    //METODO PER AGGIUNGERE UN ELEMENTO --------------------------------------------------------------------------------
    public static void aggiungiElemento() {
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
        } else if (choice == 2) {
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
            scanner.nextLine();

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

    //METODO PER RIMUOVERE UN ELEMENTO TRAMITE ISBN --------------------------------------------------------------------
    public static void rimuoviElementoPerISBN() {
        System.out.println("Inserisci il codice ISBN dell'elemento da cancellare");
        String isbnDaCancellare = scanner.nextLine();

        Pubblicazione isbn = pubblicazioneDAO.pubblicazionePerISBN(isbnDaCancellare);
        pubblicazioneDAO.deletePubblicazionePerISBN(isbn.getISBN());
    }

    //RICERCA ELEMENTO PER ISBN ----------------------------------------------------------------------------------------
    public static void trovaElementoPerISBN() {
        System.out.println("Inserisci il codice ISBN dell'elemento da trovare");
        String isbnDaTrovare = scanner.nextLine();

        pubblicazioneDAO.pubblicazionePerISBN(isbnDaTrovare);
        System.out.println("Trovata corrispondenza: " + pubblicazioneDAO.pubblicazionePerISBN(isbnDaTrovare));
    }

    //RICERCA ELEMENTO PER ANNO DI PUBBLICAZIONE -----------------------------------------------------------------------
    public static void trovaElementoPerAnno() {
        System.out.println("Inserisci l'anno di pubblicazione dell'elemento da trovare");
        int annoPubblicazione = scanner.nextInt();
        scanner.nextLine();

        boolean trovato = false;
        if (annoPubblicazione != 0) {
            trovato = true;
            List<Pubblicazione> pubblicazioni = (List<Pubblicazione>) pubblicazioneDAO.pubblicazionePerAnno(annoPubblicazione);
            System.out.println("Trovata corrispondenza: " + pubblicazioni);
        } else if (!trovato) {
            System.out.println("Nessun elemento trovato");
        }
    }

    //RICERCA ELEMENTO PER AUTORE --------------------------------------------------------------------------------------
    public static void trovaElementoPerAutore() {
        System.out.println("Inserisci il nome dell'autore del libro da trovare");
        String autore = scanner.nextLine();

        List<Libro> libri = (List<Libro>) libroDAO.libroPerAutore(autore);
        if (libri.size() > 0) {
            System.out.println("Trovata corrispondenza: " + libri);
        } else {
            System.out.println("Nessuna corrispondenza trovata");
        }

    }

    //RICERCA ELEMENTO PER TITOLO O PARTE DI ESSO ----------------------------------------------------------------------
    public static void trovaElementoPerTitolo() {
        System.out.println("Inserisci il titolo dell' elemento da trovare");
        String titolo = scanner.nextLine();

        List<Pubblicazione> pubblicazioni = (List<Pubblicazione>) pubblicazioneDAO.pubblicazionePerTitolo(titolo);
        if (pubblicazioni.size() > 0) {
            System.out.println("Trovata corrispondenza: " + pubblicazioni);
        } else {
            System.out.println("Nessuna corrispondenza trovata");
        }
    }

    // RICERCA PRESTITI SCADUTI E NON ANCORA RESTITUITI ----------------------------------------------------------------
    public static void trovaPrestitiScaduti() {
        LocalDate data = LocalDate.now();
        List<Prestito> prestiti = (List<Prestito>) prestitoDAO.trovaPrestiti(data);
        if (prestiti.size() > 0) {
            System.out.println("Trovata corrispondenza: " + prestiti);
        } else {
            System.out.println("Nessuna corrispondenza trovata");
        }
    }
}
