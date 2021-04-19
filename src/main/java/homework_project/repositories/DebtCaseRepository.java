package homework_project.repositories;

import homework_project.models.entities.DebtCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DebtCaseRepository extends CrudRepository <DebtCase, Long>{

    String serchForAllCasesForCustomer = "SELECT d FROM DebtCase d JOIN Customer c ON d.customerId = c.id WHERE c.id =:customerId";
    Optional<DebtCase> findById(Long id);
    List<DebtCase> findAll();
    @Query(serchForAllCasesForCustomer)
    List<DebtCase> findByCustomerId(@Param("customerId")Long customerId);
}
