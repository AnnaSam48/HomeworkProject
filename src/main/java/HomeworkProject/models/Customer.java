package HomeworkProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "*Please provide your fist name")
    @Length(min = 2, max=200, message = "*Please provide your fist name")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "*Please provide your last name")
    @Length(min = 2, max=200, message = "*Please provide your last name")
    @Column(name = "last_name")
    private String surname;
    @NotBlank(message = "*Please provide country")
    @Length(min = 4, max=63,message = "*Please provide country")
    @Column(name = "country")
    private String country;
    @NotEmpty(message = "*Please provide email address")
    @Email(message = "*Please provide email address")
    @Column(name = "customers_email")
    private String email;
    @NotBlank(message = "*Please provide password, it must be at least 5 characters long")
    @Length(min=5, max=180, message = "*Please provide password, it must be at least 5 characters long")
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "customer")
    private Set<DebtCase> customersDebtCases;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && firstName.equals(customer.firstName) && surname.equals(customer.surname) && country.equals(customer.country) && email.equals(customer.email) && password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, country, email, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
