package homework_project.converters;

import homework_project.models.DebtCase;
import org.springframework.hateoas.EntityModel;

public interface DebtCaseConverter{

    DebtCase convert(EntityModel<DebtCase> debtCaseEntityModel);
}
