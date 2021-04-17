package homework_project.converters;

import homework_project.models.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;

public class ModelMapperForCustomer implements CustomerConverter {

    private ModelMapper modelMapper;

    public ModelMapperForCustomer() {
        modelMapper = new ModelMapper();
    }

    @Override
    public Customer convert(EntityModel<Customer> customerEntityModel) {
        return modelMapper.map(customerEntityModel, Customer.class);
    }
}
