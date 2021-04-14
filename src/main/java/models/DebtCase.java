package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NegativeOrZero;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DebtCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private long caseId;
    @NegativeOrZero
    @Column(name = "debt_amount")
    private double amount;
    @Column(name = "debt_currency")
    private String currency;
    @FutureOrPresent
    @Column(name = "debt_due_date")
    private Date dueDate;

}
