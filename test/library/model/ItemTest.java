/* 
 * ItemTest.java
 */

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
 * @authors Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class ItemTest extends TestCase {
    
    public ItemTest(String testName) {
        super(testName);
    }
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Before
    public static void setUpClass() {
    }
    
    @After
    public static void tearDownClass() {
    }

    /**
     * Test of makeCopy method, of class Item.
     */
    @Test
    public void testMakeCopy() {
        System.out.println("makeCopy");   
        Item instance = new Book("AZ123","Coding101","Bjork");
        Copy result = instance.makeCopy();
        Copy expResult = instance.getCopy(result.getCopyNumber());      
        assertEquals(expResult, result);
        Item instance2 = new DVD("PN", "Shrek", "Myers", "PG");
        Copy result2 = instance.makeCopy();
        Copy expResult2 = instance.getCopy(result2.getCopyNumber());      
        assertEquals(expResult2, result2);
      
    }

    /**
     * Test of getCopy method, of class Item.
     */
    @Test
    public void testGetCopy() {
        System.out.println("getCopy");
        int copyNumber = 1;
        Item instance = new Book("AZ123","Hi","Hello");
        Copy expResult = instance.makeCopy();
        Copy result = instance.getCopy(copyNumber);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCallNumber method, of class Item.
     */
    @Test
    public void testGetCallNumber() {
        System.out.println("getCallNumber");
        Item instance = new Book("AZ123","Coding101","Bjork");
        String expResult = "AZ123";
        String result = instance.getCallNumber();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTitle method, of class Item.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Item instance = new Book("AZ123","Coding101","Bjork");
        String expResult = "Coding101";
        String result = instance.getTitle();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getDescription method, of class Item.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Item instance = new Book("AZ123","Coding101","Bjork");
        String expResult = "Coding101 by Bjork";
        String result = instance.getDescription();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCheckoutPeriod method, of class Item.
     */
    @Test
    public void testGetCheckoutPeriod() {
        System.out.println("getCheckoutPeriod");
        Item instance = new Book("AZ123","Coding101","Bjork");
        int expResult = 28;
        int result = instance.getCheckoutPeriod();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAllowedNumberOfRenewals method, of class Item.
     */
    @Test
    public void testGetAllowedNumberOfRenewals() {
        System.out.println("getAllowedNumberOfRenewals");
        Item instance = new Book("AZ123","Coding101","Bjork");
        int expResult = 1;
        int result = instance.getAllowedNumberOfRenewals();
        assertEquals(expResult, result);
        
    }

    public class ItemImpl extends Item {

        public ItemImpl() {
            super("", "");
        }

        public String getDescription() {
            return "";
        }

        public int getCheckoutPeriod() {
            return 0;
        }

        public int getAllowedNumberOfRenewals() {
            return 0;
        }
    }
    
}
