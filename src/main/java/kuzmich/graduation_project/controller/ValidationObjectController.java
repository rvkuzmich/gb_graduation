package kuzmich.graduation_project.controller;

import kuzmich.graduation_project.model.ValidationObject;
import kuzmich.graduation_project.service.ValidationObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/validation-objects")
@RequiredArgsConstructor
public class ValidationObjectController {

    private final ValidationObjectService validationObjectService;

    @GetMapping
    public ResponseEntity<List<ValidationObject>> findAll(@CurrentSecurityContext(expression = "authentication")
                                                              Authentication authentication) {
        String userName = authentication.getName();
        return ResponseEntity.ok(validationObjectService.findAll(userName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidationObject> findById(@PathVariable("id") UUID id) {
        return validationObjectService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ValidationObject> create(@RequestBody ValidationObject validationObject) {
        validationObject = validationObjectService.createValidationObject(validationObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(validationObject);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        validationObjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/validation-status")
    public ResponseEntity<Void> setValidationStatus(@PathVariable("id") UUID id,
                                                    @RequestParam Boolean status) {
        validationObjectService.setValidationStatus(id, status);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}/validation-status-dates")
    public ResponseEntity<Void> setValidationStatusDates(@PathVariable("id") UUID id,
                                                         @RequestParam LocalDate assignDate,
                                                         @RequestParam LocalDate expireDate) {
        validationObjectService.setValidationStatusDates(id, assignDate, expireDate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
