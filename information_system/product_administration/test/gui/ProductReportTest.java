/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StoreProducts;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.util.Collection;
import java.util.TreeSet;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author milba845
 */
public class ProductReportTest {

    private StoreProducts dao;
    private DialogFixture fest;
    private Product prodOne;
    private Product prodTwo;

    @Before
    public void setUp() {
        // add some majors for testing with
        Collection<Product> products = new TreeSet<>();
        this.prodOne = new Product("1234", "tim", "furry", "cat", 200.0, 5);
        this.prodTwo = new Product("5678", "todd", "fluffy", "dog", 500.0, 5);
        products.add(prodOne);
        products.add(prodTwo);
        
        Collection<String>categories = new TreeSet<>();
        categories.add(prodOne.getCategory());
        categories.add(prodTwo.getCategory());
        
        dao = mock(StoreProducts.class);

        when(dao.returnAllProducts()).thenReturn(products);
        when(dao.returnAllCategories()).thenReturn(categories);
        when(dao.filterByCategory("cat")).thenReturn(products);

    }

    @After
    public void tearDown() {
        fest.cleanUp();
    }

    @Test
    public void viewProducts() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fest = new DialogFixture(dialog);
        fest.show();
        fest.robot.settings().delayBetweenEvents(75);
        SimpleListModel model = (SimpleListModel) fest.list("lstProducts").component().getModel();
        // check the contents
        assertTrue("list contains the correct product1", model.contains(prodOne));
        assertTrue("list contains the correct product2", model.contains(prodTwo));

        assertEquals("list ONLY contains the correct product", 2, model.getSize());
    }

    @Test
    public void delete() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fest = new DialogFixture(dialog);
        fest.show();
        fest.robot.settings().delayBetweenEvents(75);
        SimpleListModel model = (SimpleListModel) fest.list("lstProducts").component().getModel();
        fest.list("lstProducts").selectItem(prodOne.toString());
        fest.button("buttonDelete").click();
        DialogFixture confirmDialog = fest.dialog(withTitle("Confirm Deletion").andShowing()).requireVisible();
        confirmDialog.button(withText("Yes")).click();
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        verify(dao).deleteProduct(argument.capture());
        Product deletedProduct = argument.getValue();
        assertFalse("list contains the correct product1", !model.contains(deletedProduct));
    }

    @Test
    public void searchByID() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fest = new DialogFixture(dialog);
        fest.show();
        fest.robot.settings().delayBetweenEvents(75);
        fest.textBox("txtSearch").enterText("1234");
        fest.button("buttonSearch");
        SimpleListModel model = (SimpleListModel) fest.list("lstProducts").component().getModel();
        assertTrue("list contains the correct product1", model.contains(prodOne));
    }

    @Test
    public void filterByCategory() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fest = new DialogFixture(dialog);
        fest.show();
        fest.robot.settings().delayBetweenEvents(75);

        //getting the combo box and selecting item
        SimpleListModel model = (SimpleListModel) fest.comboBox("comboCategoryFilter").component().getModel();
        fest.comboBox("comboCategoryFilter").selectItem(prodOne.getCategory());
        //getting the list
        SimpleListModel model2 = (SimpleListModel) fest.list("lstProducts").component().getModel();
        //seeing if prodOne is in the Jlist
        assertTrue("list contains product", model2.contains(prodOne));
    }

    @Test
    public void edit() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fest = new DialogFixture(dialog);
        fest.show();
        fest.robot.settings().delayBetweenEvents(75);

        // select item to edit
        fest.list("lstProducts").selectItem(prodOne.toString());
        // click the edit button
        fest.button("buttonEdit").click();
        // find the data entry dialog that appears
        DialogFixture editDialog = fest.dialog("ProductEntry");
        // check that the data entry dialog is displaying selected item
        editDialog.textBox("txtID").requireText(prodOne.getProductID());
    }

}
