/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Customer;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized;
import dao.StoreCustomer;

/**
 *
 * @author milba845
 */
public class log_in_test {
    StoreCustomer dao = new StoreCustomer("jdbc:h2:tcp://localhost/project-testing;IFEXISTS=TRUE");
    private Customer custOne;
    
    @Before
    public void setUp() {

        this.custOne = new Customer("milba", "jim", "fsd@gmail.com","123 fake st", "12345", "qwerty" );
        dao.saveCustomer(custOne);
    
    }
    
    @After
    public void tearDown() {
        dao.deleteCustomer(custOne);
     
    }
    @Test
    public void test_log_in() {
       Customer login = dao.login("milba", "qwerty");
       assertEquals("Retrieved customer should be the same as the saved one", login, custOne);
       Customer fake = dao.login("fake", "alsofake");
       assertNull("checking whether fake is false",fake);
    }
    
}
