/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author sageb
 */
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlQuery("SELECT * FROM customer ORDER BY customerid")
    @RegisterBeanMapper(Customer.class)
    public Collection<Customer> getCustomers();

    @Override
    @SqlUpdate("DELETE FROM customer WHERE username = :username")
    public void removeCustomer(@Bind("username") String username);

    @Override
    @SqlQuery("SELECT * FROM customer WHERE username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getCustomerByUsername(@Bind("username") String username);

    @Override
    @SqlQuery("SELECT COUNT(*) > 0 FROM customer WHERE username = :username AND password = :password")
    public boolean checkCustomer(@Bind("username") String username, @Bind("password") String password);

    @Override
    @SqlUpdate("INSERT INTO customer (username, firstname, surname, password, emailaddress, shippingaddress) "
            + "VALUES (:username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
    public void saveCustomer(@BindBean Customer customer);

}
