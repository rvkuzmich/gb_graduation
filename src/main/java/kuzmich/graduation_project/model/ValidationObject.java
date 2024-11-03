package kuzmich.graduation_project.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class ValidationObject {

    UUID id;
    String objectName;
    String location;
    Boolean validationStatus;
    LocalDate validationStatusAssignedAt;
    LocalDate validationStatusExpiresAt;
    Employee employee;

}
