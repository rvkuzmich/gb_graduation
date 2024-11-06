package kuzmich.graduation_project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "fist_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "uid", nullable = false)
    private Long UID;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "max_work_hours", nullable = true)
    private Integer maxWorkHours;

    @Column(name = "current_work_hours", nullable = true)
    private Integer currentWorkHours;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany()
    private List<ValidationObject> objectsToValidate;

}
