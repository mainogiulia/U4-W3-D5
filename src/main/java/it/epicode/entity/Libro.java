package it.epicode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "libri")

public class Libro extends Pubblicazione {
    private String autore;
    private String genere;
}
