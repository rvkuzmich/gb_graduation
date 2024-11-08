package kuzmich.graduation_project.controller;

import kuzmich.graduation_project.model.Employee;
import kuzmich.graduation_project.model.ValidationObject;
import kuzmich.graduation_project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/rest/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") UUID id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/validation-objects")
    public ResponseEntity<List<ValidationObject>> findEmployeeValidationObjects(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(employeeService.findValidationObjects(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/validation-objects/set")
    public ResponseEntity<Void> setValidationObject(@PathVariable("id") UUID id,
                                                    @RequestParam UUID validationObjectId) {
        employeeService.addObjectToValidate(id, validationObjectId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}/validation-objects/release")
    public ResponseEntity<Void> releaseValidationObject(@PathVariable("id") UUID id,
                                                        @RequestParam UUID validationObjectId) {
        employeeService.releaseObject(id, validationObjectId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> setMaxWorkHours(@PathVariable("id") UUID id,
                                                @RequestParam Integer maxWorkHours) {
        employeeService.setMaxWorkHours(id, maxWorkHours);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<Void> setUser(@PathVariable("id") UUID id,
                                        @RequestParam UUID userId) {
        employeeService.setUser(id, userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
