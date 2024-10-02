package domain;

import java.util.Objects;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author Mark George
 */
public class Customer {

    private Integer customerId;

    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username cannot be blank.")
    @Length(max = 30, message = "Username must be at most 30 characters long.")
    private String username;

    @NotBlank(message = "First name cannot be blank.")
    @Length(max = 30, message = "First name must be at most 30 characters long.")
    private String firstName;

    @NotBlank(message = "Surname cannot be blank.")
    @Length(max = 30, message = "Surname must be at most 30 characters long.")
    private String surname;

    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password cannot be blank.")
    @Length(min = 7, max = 20, message = "Password must be between 7 and 20 characters long.")
    private String password;

    @Email(message = "Invalid email address.")
    @Length(max = 30, message = "Email address must be at most 30 characters long.")
    private String emailAddress;

    @Length(max = 50, message = "Shipping address must be at most 50 characters long.")
    private String shippingAddress;

    public Customer() {
    }

    public Customer(Integer customerId, String username, String firstName, String surname, String password, String shippingAddress, String emailAddress) {
        this.customerId = customerId;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.shippingAddress = shippingAddress;
        this.emailAddress = emailAddress;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer personId) {
        this.customerId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.customerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return Objects.equals(this.customerId, other.customerId);
    }

}
