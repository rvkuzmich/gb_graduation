package kuzmich.graduation_project.service;

import kuzmich.graduation_project.model.Employee;
import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.model.ValidationObject;
import kuzmich.graduation_project.repository.EmployeeRepository;
import kuzmich.graduation_project.repository.UserRepository;
import kuzmich.graduation_project.repository.ValidationObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final ValidationObjectRepository validationObjectRepository;

    public List<Employee> findAll() {
        return List.copyOf(employeeRepository.findAll());
    }

    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }

    public Employee setUser(UUID id, UUID userId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalEmployee.isEmpty() || optionalUser.isEmpty()) {
            throw new NoSuchElementException("No such employee or user");
        }
        Employee employee = optionalEmployee.get();
        User user = optionalUser.get();
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    public Employee setMaxWorkHours(UUID id, Integer maxWorkHours) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new NoSuchElementException("No such employee");
        }
        Employee employee = optionalEmployee.get();
        employee.setMaxWorkHours(maxWorkHours);
        return employeeRepository.save(employee);
    }

    public Employee addObjectToValidate(UUID id, UUID validationObjectId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Optional<ValidationObject> optionalValidationObject = validationObjectRepository.findById(validationObjectId);
        if (optionalEmployee.isEmpty() || optionalValidationObject.isEmpty()) {
            throw new NoSuchElementException("No such employee or validation object");
        }
        Employee employee = optionalEmployee.get();
        ValidationObject validationObject = optionalValidationObject.get();
        List<ValidationObject> objects = employee.getObjectsToValidate();
        objects.add(validationObject);
        employee.setObjectsToValidate(objects);
        Integer currentWorkHours = employee.getCurrentWorkHours();
        currentWorkHours += validationObject.getHoursToValidate();
        employee.setCurrentWorkHours(currentWorkHours);
        return employeeRepository.save(employee);
    }


}
