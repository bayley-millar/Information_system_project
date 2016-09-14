/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author milba845
 */
public class Customer {
    @NotNull(message = "user name must be provided.")
    @NotBlank(message = "user name must be provided.")
    @Length(min = 2, message = "user name must contain at least two characters.")
    private String userName;
    
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min = 2, message = "Name must contain at least two characters.")
    private String name;
    
    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be provided.")
    @Length(min = 2, message = "Email must contain at least two characters.")
    private String email;
    
    @NotNull(message = "Address must be provided.")
    @NotBlank(message = "Address must be provided.")
    @Length(min = 2, message = "Address must contain at least two characters.")
    private String address;
    
    @NotNull(message = "Credit card number must be provided.")
    @NotBlank(message = "Credit card number must be provided.")
    @Length(min = 2, message = "Credit card number must contain at least two characters.")
    private String creditCardNumber;
    
    @NotNull(message = "password must be provided.")
    @NotBlank(message = "password must be provided.")
    @Length(min = 2, message = "password must contain at least four characters.")
    private String password;

    public Customer(String userName, String name, String email, String address, String creditCardNumber, String password) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.password = password;
    }

    
    
    public Customer() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" + "userName=" + userName + ", name=" + name + ", creditCardNumber=" + creditCardNumber + ", password=" + password + ", email=" + email + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    
    
}
