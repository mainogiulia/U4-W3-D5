package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pubblicazioni")
@Inheritance(strategy = InheritanceType.JOINED)

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
