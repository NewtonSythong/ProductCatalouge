/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sageb
 */
public class CustomerDAOTest {

//    private CustomerCollectionsDAO dao;
    private CustomerDAO dao;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        dao = JdbiDaoFactory.getCustomerDAO();

    }

    @AfterEach
    public void tearDown() {
        // Remove all customers in the DAO
        List<Customer> customersToDelete = new ArrayList<>(dao.getCustomers());
        for (Customer customer : customersToDelete) {
            dao.removeCustomer(customer.getUsername());
        }
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer(1, "user1", "John", "Doe", "password1", "123 Main St", "john.doe@example.com");

        dao.saveCustomer(customer);

        Customer retrievedCustomer = dao.getCustomerByUsername("user1");
        assertNotNull(retrievedCustomer, "Customer should be saved and retrievable");
        assertEquals("user1", retrievedCustomer.getUsername(), "Username should match");
        assertEquals("John", retrievedCustomer.getFirstName(), "First name should match");
        assertEquals("Doe", retrievedCustomer.getSurname(), "Surname should match");
        assertEquals("password1", retrievedCustomer.getPassword(), "Password should match");
        assertEquals("123 Main St", retrievedCustomer.getShippingAddress(), "Shipping address should match");
        assertEquals("john.doe@example.com", retrievedCustomer.getEmailAddress(), "Email address should match");
        assertNotNull(retrievedCustomer, "Customer should be saved and retrievable");
        assertEquals("password1", retrievedCustomer.getPassword(), "Password should match");
    }

    @Test
    public void testCheckCustomer() {
        Customer customer = new Customer(2, "user2", "Jane", "Smith", "password2", "456 Oak Ave", "jane.smith@example.com");
        dao.saveCustomer(customer);

        assertTrue(dao.checkCustomer("user2", "password2"), "Credentials should be valid");
        assertFalse(dao.checkCustomer("user2", "wrongpassword"), "Incorrect password should be invalid");
        assertFalse(dao.checkCustomer("nonexistentuser", "password2"), "Nonexistent user should be invalid");
    }

    @Test
    public void testGetCustomerByUsername() {
        Customer customer = new Customer(3, "user3", "Alice", "Johnson", "password3", "789 Pine Rd", "alice.johnson@example.com");
        dao.saveCustomer(customer);
        
        Customer retrievedCustomer = dao.getCustomerByUsername("user3");


        // Assert
        assertNotNull(retrievedCustomer, "Customer should be retrieved");
        assertEquals("user3", retrievedCustomer.getUsername(), "Username should match");
        assertEquals("Alice", retrievedCustomer.getFirstName(), "First name should match");
        assertEquals("Johnson", retrievedCustomer.getSurname(), "Surname should match");
        assertEquals("password3", retrievedCustomer.getPassword(), "Password should match");
        assertEquals("789 Pine Rd", retrievedCustomer.getShippingAddress(), "Shipping address should match");
        assertEquals("alice.johnson@example.com", retrievedCustomer.getEmailAddress(), "Email address should match");
    }

    @Test
    public void testRemoveCustomer() {
        Customer customer = new Customer(4, "user4", "Bob", "Brown", "password4", "101 Maple St", "bob.brown@example.com");
        dao.saveCustomer(customer);

        dao.removeCustomer("user4");

        Customer retrievedCustomer = dao.getCustomerByUsername("user4");
        assertNull(retrievedCustomer, "Customer should be removed and not retrievable");
    }

}
