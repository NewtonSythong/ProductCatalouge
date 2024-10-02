/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.JListFixture;
import static org.assertj.swing.timing.Pause.pause;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author sageb
 */
public class ProductViewerTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private JFrame frame;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
        robot.settings().delayBetweenEvents(75);

        // create a mock for the DAO
        dao = mock(ProductDAO.class);

        Collection<Product> products = new ArrayList<>();
        products.add(new Product("2222", "AReallyGoodMeme", "A picture of Richard Dawkins", "Meme", new BigDecimal("2.00"), new BigDecimal("10.00")));
        products.add(new Product("3333", "Phone", "Hand-held mobile device", "Technology", new BigDecimal("2099.50"), new BigDecimal("1000")));

        when(dao.getProducts()).thenReturn(products);

        frame = new JFrame();
        ProductViewer dialog = new ProductViewer(frame, true, dao);

        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testView() {
        // Verify that getProducts method was called
        verify(dao).getProducts();

        // Use JListFixture to verify the contents of the JList
        JListFixture listFixture = fixture.list(); // Adjust if the JList has a specific name

        // Ensure the JList contains the expected number of products
        listFixture.requireItemCount(2);

        // Get the JList component and its model
        JList<?> list = listFixture.target();
        ListModel<?> listModel = list.getModel();

        ArrayList<Product> actualProducts = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            actualProducts.add((Product) listModel.getElementAt(i));
        }

        assertThat("Ensure the JList contains the correct products", actualProducts, containsInAnyOrder(
                new Product("2222", "AReallyGoodMeme", "A picture of Richard Dawkins", "Meme", new BigDecimal("2.00"), new BigDecimal("10.00")),
                new Product("3333", "Phone", "Hand-held mobile device", "Technology", new BigDecimal("2099.50"), new BigDecimal("1000"))
        ));
        pause(3, TimeUnit.SECONDS);
    }

}
