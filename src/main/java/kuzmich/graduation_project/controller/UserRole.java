package kuzmich.graduation_project.controller;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userRoleId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "role_name")
    private String roleName;

}
