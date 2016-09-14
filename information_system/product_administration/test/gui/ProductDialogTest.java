/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StoreProducts;
import domain.Product;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class ProductDialogTest {

    private StoreProducts dao;
    private DialogFixture fest;

    @Before
    public void setUp() {
        // add some majors for testing with
        Collection<String> products = new TreeSet<>();
        products.add("cat");
        products.add("dog");

        // create a mock for the DAO
        dao = mock(StoreProducts.class);

        // stub the getMajors method to return the test majors
        when(dao.returnAllProducts()).thenReturn(products);
    }

    @After
    public void tearDown() {
        fest.cleanUp();
    }

    @Test
    public void testEdit() {
        // a student to edit
        Product cat = new Product("1234", "tim", "Furry", "cat", 200.0, 5);

        // create dialog passing in student and mocked DAO
        ProductEntry dialog = new ProductEntry(null, true, cat, dao);

        // use FEST to control the dialog
        fest = new DialogFixture(dialog);

        // show the dialog on the screen
        fest.show();

        // slow down the FEST robot a bit - default is 30 millis
        fest.robot.settings().delayBetweenEvents(75);

        // verify that the UI componenents contains the student's details
        fest.textBox("txtID").requireText("1234");
        fest.textBox("txtName").requireText("tim");
        fest.textBox("txtDescription").requireText("Furry");
        fest.comboBox("comboCat").requireSelection("cat");
        fest.textBox("txtQuantity").requireText("5");
        fest.textBox("txtPrice").requireText("200.00");

        // edit the name and major
        fest.textBox("txtName").selectAll().deleteText().enterText("todd");
        fest.comboBox("comboCat").replaceText("dog");
		//fest.comboBox("comboCat").enterText("dog");

        // click the save button
        fest.button("buttonSave").click();

        // create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed student
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed student from the captor
        Product editedStudent = argument.getValue();

        // check that the changes were saved
        assertEquals("Ensure the name was changed", "todd", editedStudent.getName());
        assertEquals("Ensure the major was changed", "dog", editedStudent.getCategory());
    }

    @Test
    public void testSave() {
        // create the dialog passing in the mocked DAO
        ProductEntry dialog = new ProductEntry(null, true, dao);

        // use FEST to control the dialog
        fest = new DialogFixture(dialog);
        fest.show();

        // slow down the FEST robot a bit - default is 30 millis
        fest.robot.settings().delayBetweenEvents(75);

        // enter some details into the UI components
        fest.textBox("txtID").enterText("1234");
        fest.textBox("txtName").enterText("tim");
        fest.comboBox("comboCat").enterText("cat");
        fest.textBox("txtDescription").enterText("Furry");
        fest.textBox("txtQuantity").enterText("5");
        fest.textBox("txtPrice").enterText("200.0");

        // click the save button
        fest.button("buttonSave").click();

        // create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed student
        verify(dao).saveProduct(argument.capture());

        // retrieve the passed student from the captor
        Product savedProduct = argument.getValue();

        // test that the student's details were properly saved
        assertEquals("Ensure the ID was saved", "1234", savedProduct.getProductID());
        assertEquals("Ensure the name was saved", "tim", savedProduct.getName());
        assertEquals("Ensure the category was saved", "cat", savedProduct.getCategory());
        assertEquals("Ensure the description was saved", "Furry", savedProduct.getDescription());
        assertEquals("Ensure the quantity was saved", 5, savedProduct.getQuantityInStock());
        assertEquals("Ensure the price was saved", 200.0, savedProduct.getPrice(), 0);
    }

}
