package HomeworkProject.repositories;

import HomeworkProject.models.Customer;
import HomeworkProject.models.DebtCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DebtCaseRepository extends CrudRepository <DebtCase, Long>{

    Optional<DebtCase> findById(Long id);
    List<DebtCase> findAll();
    String searchDebtCasesByCustomerId = "SELECT dc FROM DEBT_CASE dc JOIN dc.customer c WHERE c.id = customerId";
    @Query(searchDebtCasesByCustomerId)
    List<DebtCase> findByCustomer(@Param(value = "customerId") Long customerId);


}
