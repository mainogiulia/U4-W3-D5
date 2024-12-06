package it.epicode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "riviste")

public class Rivista extends Pubblicazione {
    private Periodicita periodicita;
}
