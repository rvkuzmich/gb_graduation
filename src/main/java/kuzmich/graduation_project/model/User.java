package kuzmich.graduation_project.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class User {

    UUID id;
    String userName;
    String password;
    Employee employee;
    List<Role> roles;

}
