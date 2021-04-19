package homework_project.models.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "customer")
@Relation(collectionRelation = "customers")
public class CustomerDTO extends RepresentationModel<CustomerDTO> {

    private Long id;
    @NotBlank(message = "*Please provide your fist name")
    @Length(min = 2, max=200, message = "*Please provide your fist name")
    private String firstName;
    @NotBlank(message = "*Please provide your last name")
    @Length(min = 2, max=200, message = "*Please provide your last name")
    private String lastName;
    @NotBlank(message = "*Please provide country")
    @Length(min = 4, max=63,message = "*Please provide country")
    private String country;
    @NotEmpty(message = "*Please provide email address")
    @Email(message = "*Please provide email address")
    private String email;
    @NotBlank(message = "*Please provide password, it must be at least 5 characters long")
    @Length(min=5, max=180, message = "*Please provide password, it must be at least 5 characters long")
    private String password;
}
