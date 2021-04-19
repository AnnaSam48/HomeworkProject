package homework_project.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "debtCase")
public class DebtCaseDTO extends RepresentationModel<DebtCaseDTO> {

    private long caseId;
    private long customerId;
    @Digits(integer=15, fraction=2, message = "Please provide valid amount.")
    @Positive(message = "Debt amount can't be 0 or negative.")
    private BigDecimal amount;
    @NotBlank(message = "*Please provide currency.")
    @Length(min=2, max = 200, message = "Please provide valid currency")
    private String currency;
    @FutureOrPresent(message = "Date must me valid. Please provide date that's not in the past.")
    @DateTimeFormat(pattern = "Yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "Yyyy-mm-dd HH:mm:ss")
    private Date dueDate;
}
