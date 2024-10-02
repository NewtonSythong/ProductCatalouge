/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sageb
 */
public class CustomerCollectionsDAO implements CustomerDAO {

    private Map<String, Customer> customers = new HashMap<>();

    @Override
    public void saveCustomer(Customer customer) {
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public boolean checkCustomer(String username, String password) {
        Customer customer = customers.get(username);
        return customer != null && customer.getPassword().equals(password);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customers.get(username);
    }

    @Override
    public void removeCustomer(String username) {
        customers.remove(username);
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customers.values();
    }

}
