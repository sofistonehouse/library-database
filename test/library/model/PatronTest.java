/*
 *  PatronTest.java
 */

package library.model;

import junit.framework.TestCase;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @authors Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class PatronTest extends TestCase {
    
    public PatronTest(String testName) {
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
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    /*
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
    
    */
    
    /**
     * Test of getFullName method, of class Patron.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        String expResult = "Jimmy Johnson";
        String result = instance.getFullName();
        assertEquals(expResult, result);      
    }

    /**
     * Test of getPhoneNumber method, of class Patron.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        String expResult = "6079908054";
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);      
    }

    /**
     * Test of getCurrentCopiesOut method, of class Patron.
     */
    @Test
    public void testGetCurrentCopiesOut() {
        System.out.println("getCurrentCopiesOut");
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        Book book = new Book("AZ123","Coding101","Bjork");
        Copy copy = book.makeCopy();
        int expResult = 0;
        int result = instance.getCurrentCopiesOut();
        assertEquals(expResult, result);  
        instance.checkOut(copy);
        int expResult2 = 1;
        int result2 = instance.getCurrentCopiesOut();
        assertEquals(expResult2, result2); 
    }

    /**
     * Test of getNumberOverdue method, of class Patron.
     */
    @Test
    public void testGetNumberOverdue() {
        System.out.println("getNumberOverdue");
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        Book book = new Book("AZ123","Coding101","Bjork");
        Copy copy = book.makeCopy();
        instance.checkOut(copy);
        int expResult = 0;
        int result = instance.getNumberOverdue();
        assertEquals(expResult, result);  
        copy.checkOutTo(instance, copy.getDateDue());
        SimpleDate.changeTodayBy(30);
        int expResult2 = 1;
        int result2 = instance.getNumberOverdue();
        assertEquals(expResult2, result2);
        
    }

    /**
     * Test of checkOut method, of class Patron.
     */
    @Test
    public void testCheckOut() {
        System.out.println("checkOut");
        Book book = new Book("AZ123","Coding101","Bjork");
        Copy copy = book.makeCopy();
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        instance.checkOut(copy);   
        int expResult = 1;
        int result = instance.getCurrentCopiesOut();
        assertEquals(expResult, result);
    }

    /**
     * Test of returnCopy method, of class Patron.
     */
    @Test
    public void testReturnCopy() {
        System.out.println("returnCopy");
        Book book = new Book("AZ123","Coding101","Bjork");
        Copy copy = book.makeCopy();
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        instance.checkOut(copy);
        int result2 = instance.getCurrentCopiesOut();
        int expResult2 = 1;
        assertEquals(expResult2, result2);
        instance.returnCopy(copy); 
        int expResult = 0;
        int result = instance.getCurrentCopiesOut();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCopiesCheckedOut method, of class Patron.
     */
    @Test
    public void testGetCopiesCheckedOut() {
        System.out.println("getCopiesCheckedOut");
        Patron instance = new Patron("Jimmy","Johnson","378 Forest Rd","6079908054");
        Book book1 = new Book("AZ123","Coding101","Bjork");
        Book book2 = new Book("PJ111", "Percy Jackson", "Riordan");
        Copy copy = book1.makeCopy();
        Copy copy2 = book2.makeCopy();
        int result2 = instance.getCurrentCopiesOut();
        int expResult2 = 0;
        assertEquals(result2, expResult2);
        instance.checkOut(copy);
        instance.checkOut(copy2);
        Iterator <Copy> it = instance.getCopiesCheckedOut();
        int expResult = 2;
        int result = 0;
        while(it.hasNext()){
            Copy test = it.next();
            if(test==copy)
            {
                result +=1;
            }
            else if(test == copy2)
            {
                result +=1;
            }
        }
        assertEquals(expResult, result);           
    }
    
}
