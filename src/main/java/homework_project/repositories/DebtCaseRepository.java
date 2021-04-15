package homework_project.repositories;

import homework_project.models.DebtCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DebtCaseRepository extends CrudRepository <DebtCase, Long>{
    Optional<DebtCase> findById(Long id);
    List<DebtCase> findAll();
    List<DebtCase> findByCustomerId(@Param("customerId")Long customerId);
}
