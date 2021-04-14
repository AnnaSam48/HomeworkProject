package HomeworkProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "DEBT_CASE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DebtCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "caseId")
    private long caseId;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer debtor;
    @Positive
    @Column(name = "debt_amount")
    private double amount;
    @Column(name = "debt_currency")
    private String currency;
    @FutureOrPresent
    @Column(name = "debt_due_date")
    private Date dueDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebtCase)) return false;
        DebtCase debtCase = (DebtCase) o;
        return caseId == debtCase.caseId && Double.compare(debtCase.amount, amount) == 0 && debtor.equals(debtCase.debtor) && currency.equals(debtCase.currency) && dueDate.equals(debtCase.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseId, debtor, amount, currency, dueDate);
    }

    @Override
    public String toString() {
        return "DebtCase{" +
                "caseId=" + caseId +
                ", debtor=" + debtor +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
