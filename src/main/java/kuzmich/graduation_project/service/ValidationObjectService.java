package kuzmich.graduation_project.service;

import kuzmich.graduation_project.model.ValidationObject;
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

    public List<ValidationObject> findAll() {
        return validationObjectRepository.findAll();
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

    public ValidationObject setValidationStatus(UUID id, Boolean status) {
        Optional<ValidationObject> optionalValidationObject = validationObjectRepository.findById(id);
        if (optionalValidationObject.isEmpty()) {
            throw new NoSuchElementException("No such validation object");
        }
        ValidationObject validationObject = optionalValidationObject.get();
        validationObject.setValidationStatus(status);
        return validationObjectRepository.save(validationObject);
    }

    public ValidationObject setValidationStatusDates(UUID id, LocalDate assignDate, LocalDate expireDate) {
        Optional<ValidationObject> optionalValidationObject = validationObjectRepository.findById(id);
        if (optionalValidationObject.isEmpty()) {
            throw new NoSuchElementException("No such validation object");
        }
        ValidationObject validationObject = optionalValidationObject.get();
        validationObject.setValidationStatusAssignedAt(assignDate);
        validationObject.setValidationStatusExpiresAt(expireDate);
        return validationObjectRepository.save(validationObject);
    }
}
