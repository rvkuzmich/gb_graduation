package kuzmich.graduation_project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "roleName", nullable = false)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
