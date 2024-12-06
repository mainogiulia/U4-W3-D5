package it.epicode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "libri")
@NamedQuery(name = "trovaPerAutore", query = "SELECT a FROM Libro WHERE a.autore = :autore")

public class Libro extends Pubblicazione {
    private String autore;
    private String genere;
}
