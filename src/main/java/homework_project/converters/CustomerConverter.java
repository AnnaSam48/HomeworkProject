package homework_project.converters;

import homework_project.models.Customer;
import org.springframework.hateoas.EntityModel;

public interface CustomerConverter {

    Customer convert(EntityModel<Customer> customerEntityModel);
}
