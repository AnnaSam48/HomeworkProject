package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "*Please provide your fist name")
    @Length(min = 2, max=200, message = "*Please provide your fist name")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "*Please provide your last name")
    @Length(min = 2, max=200, message = "*Please provide your last name")
    @Column(name = "last_name")
    private String surname;
    @NotEmpty(message = "*Please provide country")
    @Length(min = 4, max=63,message = "*Please provide country")
    @Column(name = "country")
    private String country;
    @NotEmpty(message = "*Please provide email address")
    @Email(message = "*Please provide email address")
    @Column(name = "customers_email")
    private String email;
    @NotEmpty(message = "*Please provide password, it must be at least 5 characters long")
    @Length(min=5, max=180, message = "*Please provide password, it must be at least 5 characters long")
    @Column(name = "password")
    private String password;

}
