package kuzmich.graduation_project.repository;

import kuzmich.graduation_project.model.ValidationObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ValidationObjectRepository extends JpaRepository<ValidationObject, UUID> {

//    @Query("select v from validation_object v where v.employeeId = :employeeId order by v.validationStatusExpiresAt asc")
//    List<ValidationObject> findByEmployeeId(UUID employeeId);

}
