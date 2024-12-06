package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pubblicazioni")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "trovaPerISBN", query = "SELECT a FROM Pubblicazione WHERE a.isbn = :isbn")
@NamedQuery(name = "trovaPerAnno", query = "SELECT a FROM Pubblicazione WHERE a.annoPubblicazione = :annoPubblicazione")
@NamedQuery(name = "trovaPerTitolo", query = "SELECT a FROM Pubblicazione WHERE a.titolo LIKE :titolo OR a.title LIKE ('%', :titolo, '%')")

public class Pubblicazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ISBN;
    private String titolo;
    private Integer annoPubblicazione;
    private Integer numeroPagine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestito_id")
    private Prestito prestito;
}
