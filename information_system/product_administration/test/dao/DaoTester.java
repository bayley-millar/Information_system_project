/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author milba845
 */
@RunWith(Parameterized.class)
public class DaoTester {

    StorageInterface dao;
    //first test product
    private Product prodOne;
    // second test product
    private Product prodTwo;

    public DaoTester(StorageInterface dao) {
        this.dao = dao;
    }

    @Before
    public void setUp() {
        // rst test product
        this.prodOne = new Product("1", "name1", "cat1", "desc1", 11.0, 22);
        // second test product
        this.prodTwo = new Product("2", "name2", "cat2", "desc2", 33.0, 44);
        // save the products
        dao.saveProduct(prodOne);
        dao.saveProduct(prodTwo);
    }

    @After
    public void tearDown() {
        dao.deleteProduct(prodOne);
        dao.deleteProduct(prodTwo);
    }

    @Parameterized.Parameters
    public static Collection<?> daoObjectsToTest() {
        return Arrays.asList(new Object[][]{
            {new StoreProducts()},
            {new DatabaseProductManager(
                "jdbc:h2:tcp://localhost/project-testing;IFEXISTS=TRUE")}
        });
    }

    @Test
    public void testDaoSaveAndDelete() {
        // create product for testing
        Product savedProd = new Product("3", "name", "desc", "cat", 1.0, 2);
        // save the product using DAO
        dao.saveProduct(savedProd);
        // retrieve the same product via DAO
        Product retrieved = dao.search("3");
        // ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same as the saved one", savedProd, retrieved);
        // delete the product via the DAO
        dao.deleteProduct(savedProd);
        // try to retrieve the deleted product
        retrieved = dao.search("3");
        // ensure that the student was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
    }

    @Test
    public void testDaoGetAll() {
        // call getAll
        Collection<Product> products = dao.returnAllProducts();
        // ensure the result includes the test products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
        // ensure the result ONLY includes the test products
        assertEquals("Only 2 products in result", 2, products.size());
        // nd prodOne − result is generic collection, so we have to sequentially search for it
        for (Product p : products) {
            if (p.equals(prodOne)) {
                // ensure that all of the details were correctly retrieved
                assertEquals(prodOne.getProductID(), p.getProductID());
                assertEquals(prodOne.getName(), p.getName());
                assertEquals(prodOne.getDescription(), p.getDescription());
                assertEquals(prodOne.getCategory(), p.getCategory());
                assertEquals(prodOne.getPrice(), p.getPrice(), 0);
                assertEquals(prodOne.getQuantityInStock(), p.getQuantityInStock());
            }
        }
    }

    @Test
    public void testDaoFindById() {
        // get prodOne using ndById method
        Product searchID = dao.search("1");
        // ensure that you got back prodOne, and not another product
        assertTrue("prodOne should exist", searchID.equals(prodOne));
        // ensure that prodOne's details were properly retrieved
        assertEquals("Retrieved product should be the same as the saved one", searchID, prodOne);
        // call ndById using a non−existent ID
        Product search2 = dao.search("9999999");
        // ensure that the result is null
        assertNull("Product should no longer exist", search2);
    }

    @Test
    public void testDaoGetCategories() {
        Collection<Product> categories = dao.returnAllProducts();
        assertTrue("prodOne should exist", categories.contains(prodOne));
        assertTrue("prodTwo should exist", categories.contains(prodTwo));
        assertEquals("Only 2 categories in result", 2, categories.size());
        for (Product c : categories) {
            if (c.equals(prodOne)) {
                assertEquals(prodOne.getCategory(), c.getCategory());
            }
        }
    }

    @Test
    public void testDaoFilterByCategory() {
        Collection filter = dao.filterByCategory(prodOne.getCategory());
        assertTrue("prodOne should exist", filter.contains(prodOne));
        assertFalse("prodtwo shouldnt exist", filter.contains(prodTwo));
        filter = dao.filterByCategory("9999999");
        System.out.println("filter2: " + filter);
        assertTrue("No products should be returned", filter.isEmpty());
    }
    @Test
    public void testDaoSave(){
        Product savedProd = new Product("3", "name", "desc", "cat", 1.0, 2);
        dao.saveProduct(savedProd);
        Product retrieved = dao.search("3");
        assertEquals("Retrieved product should be the same as the saved one", savedProd, retrieved);
        savedProd.setName("new name");
        dao.saveProduct(savedProd);
        retrieved = dao.search("3");
        assertEquals("Retrieved product should be the same as the saved one", savedProd, retrieved);
        assertEquals("name should be the same as the new one", savedProd.getName(), retrieved.getName());
        dao.deleteProduct(savedProd);
    }
}
