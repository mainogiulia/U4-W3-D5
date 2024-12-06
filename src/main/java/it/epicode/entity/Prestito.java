package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "prestiti")
@NamedQuery(name = "trovaPrestiti", query = "SELECT a FROM Prestito a")

public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "pubblicazione_id")
    private Pubblicazione pubblicazione;

    @Column(name = "data_inizio_prestito")
    private Date dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private Date dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private Date dataRestituzioneEffettiva;
}
