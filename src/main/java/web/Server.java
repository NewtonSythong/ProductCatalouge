package web;

import dao.JdbiDaoFactory;
import dao.CustomerDAO;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {

    ProductDAO productDAO = JdbiDaoFactory.getProductDAO();
    CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();

    public static void main(String[] args) {

        System.out.println("\nStarting Server.");
        new Server()
                .setServerOptions(new ServerOptions().setPort(8087))
                .start();
    }

    public Server() {
        install(new GsonModule());
        mount(new StaticAssetModule());
        mount(new ProductModule((ProductDAO) productDAO));
        mount(new CustomerModule((CustomerDAO) customerDAO));
    }

}
