package HomeworkProject.repositories;

import HomeworkProject.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findById(Long id);
    Iterable<Customer> findAll();
    Customer findByEmail(String email);
    Customer findByFirstNameAndSurname(String firstName, String surname);

}
