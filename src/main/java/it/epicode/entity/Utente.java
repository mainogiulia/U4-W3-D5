package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "utenti")
@NamedQuery(name = "trovaUtenti", query = "SELECT a FROM Utente a")

public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private Date dataNascita;

    @Column(name = "numero_tessera")
    private String numeroTessera;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti = new ArrayList<>();
}
