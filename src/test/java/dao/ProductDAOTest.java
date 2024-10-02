/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Product;

/**
 *
 * @author User
 */
public class ProductDAOTest {

    private ProductDAO dao;
    private Product product;
    private Product product2;
    private Product product3;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        dao = JdbiDaoFactory.getProductDAO();

        product = new Product();
        product.setProductId("1111");
        product.setName("HuntingForYourDream");
        product.setDescription("One of the greatest endings");
        product.setCategory("Song");
        product.setListPrice(new BigDecimal("19.99"));
        product.setQuantityInStock(new BigDecimal("200"));

        product2 = new Product("2222", "AReallyGoodMeme", "A picture of Richard Dawkins", "Meme", new BigDecimal("2.00"), new BigDecimal("10.00"));
        product3 = new Product("3333", "Phone", "Hand-held mobile device", "Technology", new BigDecimal("2099.50"), new BigDecimal("1000"));

    }

    @AfterEach
    public void tearDown() {
        List<Product> productsToDelete = new ArrayList<>(dao.getProducts());
        for (Product product : productsToDelete) {
            dao.removeProduct(product);
        }
    }

    @Test
    public void testSaveProduct() {
        dao.saveProduct(product);
        Product retrievedProduct = dao.searchById("1111");
        assertThat(retrievedProduct.getName(), is("HuntingForYourDream"));
    }

    @Test
    public void testRemoveProduct() {
        dao.saveProduct(product);
        dao.saveProduct(product2);
        assertThat(dao.searchById(product.getProductId()), is(notNullValue()));
        assertThat(dao.searchById(product2.getProductId()), is(notNullValue()));

        dao.removeProduct(product);

        assertThat(dao.searchById(product.getProductId()), is(nullValue()));
        assertThat(dao.searchById(product2.getProductId()), is(notNullValue()));

        assertThat(dao.getProducts(), hasSize(1));
    }

    @Test
public void testGetProducts() {
    // Save products
    dao.saveProduct(product);
    dao.saveProduct(product3);

    // Assert that products were saved and can be retrieved by ID
    assertThat(dao.searchById(product.getProductId()), is(notNullValue()));
    assertThat(dao.searchById(product3.getProductId()), is(notNullValue()));

    // Assert that all products are retrieved
    Collection<Product> allProducts = dao.getProducts();
    assertThat(allProducts, hasSize(2));

    // Check that the name and description fields are retrieved correctly for each product
    Product retrievedProduct1 = dao.searchById(product.getProductId());
    assertThat(retrievedProduct1.getName(), is(product.getName()));
    assertThat(retrievedProduct1.getDescription(), is(product.getDescription()));

    Product retrievedProduct3 = dao.searchById(product3.getProductId());
    assertThat(retrievedProduct3.getName(), is(product3.getName()));
    assertThat(retrievedProduct3.getDescription(), is(product3.getDescription()));

    // Negative Test: Remove products and check that the collection is empty
    dao.removeProduct(product);
    dao.removeProduct(product3);
    assertThat(dao.getProducts(), hasSize(0));
}

    @Test
    public void testGetCategories() {
        dao.saveProduct(product);
        dao.saveProduct(product2);
        dao.saveProduct(product3);
        Collection<String> categories = dao.getCategories();
        assertThat(categories, hasSize(3));
        assertThat(categories, containsInAnyOrder("Song", "Meme", "Technology"));

        // Negative test
        dao.removeProduct(product);
        dao.removeProduct(product2);
        dao.removeProduct(product3);
        assertThat(dao.getCategories(), hasSize(0));
    }

    @Test
    public void testSearchById() {
        ProductCollectionsDAO dao = new ProductCollectionsDAO();
        Product product = new Product("1", "HuntingForYourDream", "Description", "Category", new BigDecimal("10.00"), new BigDecimal("5"));
        dao.saveProduct(product);

        Product retrievedProduct = dao.searchById("1");
        assertThat("Product should not be null", retrievedProduct, is(notNullValue()));
        assertThat("Product name should match", retrievedProduct.getName(), is("HuntingForYourDream"));
    }

    @Test
    public void testFilterByCategory() {
        dao.saveProduct(product2);
        dao.saveProduct(product3);
        Collection<Product> memeProducts = dao.filterByCategory("Meme");
        assertThat(memeProducts, hasSize(1));
        assertThat(memeProducts, contains(product2));

        Collection<Product> techProducts = dao.filterByCategory("Technology");
        assertThat(techProducts, hasSize(1));
        assertThat(techProducts, contains(product3));
        assertThat(techProducts, not(hasItem(product2)));

        // Negative Test
        Collection<Product> nonExistentCategoryProducts = dao.filterByCategory("NonExistentCategory");
        assertThat(nonExistentCategoryProducts, hasSize(0));
    }
}
