/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.ProductDAO;
import dao.SaleDAO;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author User
 */
public class SaleModule extends Jooby {

    public SaleModule(SaleDAO saleDao, ProductDAO productDao) {
        post("/api/sales", ctx -> {
            Sale sale = ctx.body().to(Sale.class);

            // Update sale items with correct list price from the product details
            for (SaleItem item : sale.getItems()) {
                Product product = productDao.searchById(item.getProduct().getProductId());
                if (product != null) {
                    item.setSalePrice(product.getListPrice());
                } else {
                    return ctx.send(StatusCode.BAD_REQUEST)
                            .send("Product not found: " + item.getProduct().getProductId());
                }
            }

            // Save the sale
            saleDao.save(sale);
            return ctx.send(StatusCode.CREATED);
        });
    }
}
