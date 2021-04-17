package homework_project.converters;

import homework_project.models.DebtCase;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;


public class ModelMapperForDebtCase implements DebtCaseConverter {

    private ModelMapper modelMapper;

    public ModelMapperForDebtCase() {
        modelMapper = new ModelMapper();
    }

    @Override
    public DebtCase convert(EntityModel<DebtCase> debtCaseEntityModel) {
        return modelMapper.map(debtCaseEntityModel, DebtCase.class);
    }
}
