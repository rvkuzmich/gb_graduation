package kuzmich.graduation_project.service;

import kuzmich.graduation_project.model.Employee;
import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.model.ValidationObject;
import kuzmich.graduation_project.repository.EmployeeRepository;
import kuzmich.graduation_project.repository.UserRepository;
import kuzmich.graduation_project.repository.ValidationObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidationObjectService {

    private final ValidationObjectRepository validationObjectRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public List<ValidationObject> findAll(String userName) {
        Optional<User> optionalUser = userRepository.findByLogin(userName);
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        Optional<Employee> optionalEmployee = employeeRepository.findByUserId(user.getId());
        Employee employee = new Employee();
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        }
        if (employee.getLocation().equals("Moscow")) {
            return validationObjectRepository.findAll();
        }
        Employee finalEmployee = employee;
        return List.copyOf(validationObjectRepository
                .findAll().stream().filter(
                        validationObject -> validationObject
                                .getLocation()
                                .equals(finalEmployee
                                        .getLocation()))
                .toList());
    }

    public Optional<ValidationObject> findById(UUID id) {
        return validationObjectRepository.findById(id);
    }

    public ValidationObject createValidationObject(ValidationObject validationObject) {
        return validationObjectRepository.save(validationObject);
    }

    public void deleteById(UUID id) {
        validationObjectRepository.deleteById(id);
    }

    public void setValidationStatus(UUID id, Boolean status) {
        Optional<ValidationObject> optionalValidationObject = validationObjectRepository.findById(id);
        if (optionalValidationObject.isEmpty()) {
            throw new NoSuchElementException("No such validation object");
        }
        ValidationObject validationObject = optionalValidationObject.get();
        validationObject.setValidationStatus(status);
        validationObjectRepository.save(validationObject);
    }

    public void setValidationStatusDates(UUID id, LocalDate assignDate, LocalDate expireDate) {
        Optional<ValidationObject> optionalValidationObject = validationObjectRepository.findById(id);
        if (optionalValidationObject.isEmpty()) {
            throw new NoSuchElementException("No such validation object");
        }
        ValidationObject validationObject = optionalValidationObject.get();
        validationObject.setValidationStatusAssignedAt(assignDate);
        validationObject.setValidationStatusExpiresAt(expireDate);
        validationObjectRepository.save(validationObject);
    }
}
