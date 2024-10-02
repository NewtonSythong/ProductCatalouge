/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;

/**
 *
 * @author sageb
 */
public interface CustomerDAO {

    void saveCustomer(Customer customer);

    boolean checkCustomer(String username, String password);

    Customer getCustomerByUsername(String username);

    void removeCustomer(String username);
    
    Collection<Customer> getCustomers();
    
    
}
