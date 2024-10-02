/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author newton
 */
public class CustomerModule extends Jooby {

    public CustomerModule(CustomerDAO dao) {
        post("/api/register", ctx -> {
            Customer customer = ctx.body().to(Customer.class);
            dao.saveCustomer(customer);
            return ctx.send(StatusCode.CREATED);
        });

        get("/api/customers/{username}", ctx -> {
            String username = ctx.path("username").value();
            Customer customer = dao.getCustomerByUsername(username);

            if (customer == null) {
                // no customer with that username found, so return a 404/Not Found error
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                // If the customer is found, proceed to hide sensitive data
                customer.setPassword(null);
                customer.setShippingAddress(null);
                customer.setEmailAddress(null);
                return customer;
            }
        });
    }
}
