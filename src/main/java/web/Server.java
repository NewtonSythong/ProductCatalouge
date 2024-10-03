package web;

import java.nio.file.Paths;
import java.util.Set;

import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {

    ProductDAO productDAO = JdbiDaoFactory.getProductDAO();
    CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();
    SaleDAO saleDAO = JdbiDaoFactory.getSaleDAO();

    public static void main(String[] args) {

        System.out.println("\nStarting Server.");
        new Server()
//                .setServerOptions(new ServerOptions().setPort(8087))
                .start();
    }

    public Server() {
        mount(new StaticAssetModule());
        install(new GsonModule());
        install(new BasicAccessAuth(customerDAO, Set.of("/api/.*"), Set.of("/api/register")));
        mount(new ProductModule((ProductDAO) productDAO));
        mount(new CustomerModule((CustomerDAO) customerDAO));
        mount(new SaleModule(saleDAO, productDAO));
        
        error(StatusCode.SERVER_ERROR, (ctx, cause, code) -> {
            ctx.getRouter().getLog().error(cause.getMessage(), cause);
            ctx.send(Paths.get("static/500.html"));
        });
    }

}
