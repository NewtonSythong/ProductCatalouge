package dao;

import java.util.Collection;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import domain.Product;

public interface ProductJdbiDAO extends ProductDAO {  
    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE CATEGORY = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);
    
    @Override
    @SqlQuery("SELECT DISTINCT CATEGORY FROM PRODUCT ORDER BY CATEGORY")
    public Collection<String> getCategories();
    
    @Override
    @SqlQuery("SELECT * FROM PRODUCT ORDER BY PRODUCTID0")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();
    
    @Override
    @SqlUpdate("DELETE FROM PRODUCT WHERE PRODUCTID = :productId")
    public void removeProduct(@BindBean Product aProduct);
    
    @Override
    @SqlUpdate("MERGE INTO PRODUCT(PRODUCTID, NAME, DESCRIPTION, CATEGORY, LISTPRICE, QUANTITYINSTOCK) VALUES (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product aProduct);
    
    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE PRODUCTID = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String productId);
}