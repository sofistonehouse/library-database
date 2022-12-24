
package library.model;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author myath
 */
public class BookTest extends TestCase {
    
    public BookTest(String testName) {
        super(testName);
        
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    * /

    /**
     * Test of getDescription method, of class Book.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Book instance = new Book ("AZ123","Coding101","Bjork");
        String expResult = "Coding101 by Bjork";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCheckoutPeriod method, of class Book.
     */
    @Test
    public void testGetCheckoutPeriod() {
        System.out.println("getCheckoutPeriod");
        Book instance = new Book ("AZ123","Coding101","Bjork");
        int expResult = 28;
        int result = instance.getCheckoutPeriod();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAllowedNumberOfRenewals method, of class Book.
     */
    @Test
    public void testGetAllowedNumberOfRenewals() {
        System.out.println("getAllowedNumberOfRenewals");
        Book instance = new Book ("AZ123","Coding101","Bjork");
        int expResult = 1;
        int result = instance.getAllowedNumberOfRenewals();
        assertEquals(expResult, result);
        
    }
    
}
