package br.com.thiagopereira.nationalpetregistry.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column(length = 255, nullable = false)
  public String name;

  @Column(length = 255, nullable = false)
  public String breed;

  @Column(length = 1, nullable = false)
  public String gender;

  @Column(name = "date_of_birth", length = 10, nullable = false)
  public LocalDate dateOfBirth;

  @Column(name = "identification_document", length = 15, nullable = false, unique = true)
  public String identificationDocument;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  public User owner;

  // Validations
  @PrePersist
  @PreUpdate
  public void validate() {
    if (!gender.matches("[MF]")) {
      throw new IllegalArgumentException("Gender must be either 'M' or 'F'");
    }

    try {
      LocalDate.parse(dateOfBirth.toString());
    } catch (Exception e) {
      throw new IllegalArgumentException("dateOfBirth must be a valid LocalDate");
    }

    if (!identificationDocument.matches("^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}|\\d{9}$")) {
      throw new IllegalArgumentException("Identification document must match the format XX.XXX.XXX-X");
    }
  }

}
