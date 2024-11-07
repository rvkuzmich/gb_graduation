package kuzmich.graduation_project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "validation_object")
public class ValidationObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "objectName", nullable = false)
    private String objectName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "timeToValidate", nullable = false)
    private Integer hoursToValidate;

    @Column(name = "validationStatus", nullable = false)
    private Boolean validationStatus;

    @Column(name = "validationStatusAssignedAt", nullable = true)
    private LocalDate validationStatusAssignedAt;

    @Column(name = "validationStatusExpiresAt", nullable = true)
    private LocalDate validationStatusExpiresAt;

//    @Column(name = "employeeId", nullable = true)
//    private UUID employeeId;

}
