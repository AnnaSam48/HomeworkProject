package homework_project.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEBT_CASE")
public class DebtCase implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "caseId")
    private long caseId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "currency")
    private String currency;
    @DateTimeFormat(pattern = "Yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "Yyyy-mm-dd HH:mm:ss")
    @Column(name = "due_date")
    private Date dueDate;

    @Override
    public String toString() {
        return "DebtCase{" +
                "caseId=" + caseId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
