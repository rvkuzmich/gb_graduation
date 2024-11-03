package kuzmich.graduation_project.model;

import java.util.List;
import java.util.UUID;

public class Employee {

    UUID id;
    String firstName;
    String lastName;
    Long UID;
    String location;
    Integer maxWorkHours;
    Integer currentWorkHours;
    User user;
    List<ValidationObject> objectsToValidate;

}
