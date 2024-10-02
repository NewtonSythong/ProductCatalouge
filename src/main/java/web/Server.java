package web;

import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {

    ProductDAO productDAO = JdbiDaoFactory.getProductDAO();
    CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();
    SaleDAO saleDAO = JdbiDaoFactory.getSaleDAO();

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
        mount(new SaleModule((SaleDAO) saleDAO));
    }

}
