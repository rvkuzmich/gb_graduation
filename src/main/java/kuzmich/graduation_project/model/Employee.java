package kuzmich.graduation_project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "fist_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "max_work_hours", nullable = true)
    private Integer maxWorkHours;

    @Column(name = "current_work_hours", nullable = true)
    private Integer currentWorkHours;

    @Column(name = "userId", nullable = true)
    private UUID userId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_validation_object",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "validation_object_id", referencedColumnName = "id"))
    private List<ValidationObject> objectsToValidate;

}
