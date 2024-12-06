package br.com.thiagopereira.nationalpetregistry.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(name = "gender", length = 1)
  private String gender;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(length = 20)
  private String phone;

  @Column(columnDefinition = "TEXT")
  private String address;

  @Column(name = "identification_document", nullable = false, unique = true)
  private String identificationDocument;

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

    if (!phone.matches("^\\(\\d{2,3}\\)\\s9\\d{4}-\\d{4}$")) {
      throw new IllegalArgumentException("Phone must match the format (XX) 9XXXX-XXXX");
    }

    if (!identificationDocument.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
      throw new IllegalArgumentException("Identification document must match the format XXX.XXX.XXX-XX");
    }
  }
}
