/*
 * DVDTest.java
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
public class DVDTest extends TestCase {
    
    public DVDTest(String testName)  {
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /*
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    * /
    
    /**
     * Test of getDescription method, of class DVD.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        DVD instance = new DVD("PN", "Shrek", "Myers", "PG");
        String expResult = "Shrek (DVD) Myers, rated: PG";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCheckoutPeriod method, of class DVD.
     */
    @Test
    public void testGetCheckoutPeriod() {
        System.out.println("getCheckoutPeriod");
        DVD instance = new DVD("PN", "Shrek", "Myers", "PG");
        int expResult = 7;
        int result = instance.getCheckoutPeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllowedNumberOfRenewals method, of class DVD.
     */
    @Test
    public void testGetAllowedNumberOfRenewals() {
        System.out.println("getAllowedNumberOfRenewals");
        DVD instance = new DVD("PN", "Shrek", "Myers", "PG");
        int expResult = 0;
        int result = instance.getAllowedNumberOfRenewals();
        assertEquals(expResult, result);
        
   
    }
    
}
