package repositories;

import models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface DebtCaseRepository extends CrudRepository <Long, Customer>{
}
