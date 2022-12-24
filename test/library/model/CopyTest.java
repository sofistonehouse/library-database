/*
 * CopyTest.java
 */

package library.model;

import junit.framework.TestCase;

/** Tester for Copy class
 *
 * @author bjork
 */
public class CopyTest extends TestCase {
    
    public CopyTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        book = new Book("QA", "Lisp", "Winston");
        dvd = new DVD("PN", "Shrek", "Myers", "PG");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getCItem method, of class Copy.
     */
    public void testGetItem() {
        System.out.println("getItem");
        Copy instance = book.makeCopy();
        assertEquals(book, instance.getItem());
    }
    /**
     * Test of checkOut method, of class Copy.
     */
    public void testCheckOutTo() {
        System.out.println("checkOutTo will be tested with getCheckedOutTo, getDateDue methods below");
    }

    /**
     * Test of getCheckedOutTo method, of class Copy.
     */
    public void testGetCheckedOutTo() {
        System.out.println("getCheckedOutTo");
        Copy instance = book.makeCopy(); 
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instance.checkOutTo(patron, SimpleDate.getToday());
        Patron expResult = patron;
        Patron result = instance.getCheckedOutTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateWillBeDue method, of class Copy.
     */
    public void testGetDateWillBeDue() {
        System.out.println("getDateWillBeDue");
        Copy bookCopy = book.makeCopy();
        Copy dvdCopy = dvd.makeCopy();
        Book specialBook = new Book("special", "book", "AAAAA") {
           public int getCheckoutPeriod() { return 2; }
        };
        Copy specialCopy = specialBook.makeCopy();
        SimpleDate expResult = SimpleDate.getToday().daysLater(28);
        assertEquals(expResult, bookCopy.getDateWillBeDue());
        expResult = SimpleDate.getToday().daysLater(7);
        assertEquals(expResult, dvdCopy.getDateWillBeDue());
        expResult = SimpleDate.getToday().daysLater(2);
        assertEquals(expResult, specialCopy.getDateWillBeDue());
    }
    
    /**
     * Test of getDateDue method, of class Copy.
     */
    public void testGetDateDue() {
        System.out.println("getDateDue");
        Copy instance = book.makeCopy();
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        SimpleDate.changeTodayBy(2);
        SimpleDate expResult = SimpleDate.getToday().daysLater(book.getCheckoutPeriod() - 2);
        SimpleDate result = instance.getDateDue();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isCheckedOut method, of class Copy.
     */
    public void testIsCheckedOut() {
        System.out.println("isCheckedOut");
        Copy instanceIn = book.makeCopy();  
        Copy instanceOut = book.makeCopy();  
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instanceOut.checkOutTo(patron, SimpleDate.getToday());
        assertFalse(instanceIn.isCheckedOut());
        assertTrue(instanceOut.isCheckedOut());
    }

    /**
     * Test of daysOverdue(( method, of class Copy.
     */
    public void testDaysOverdue() {
        System.out.println("isOverdue");
        Copy instance = book.makeCopy();  
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        assertEquals(0, instance.daysOverdue());
        SimpleDate.changeTodayBy(book.getCheckoutPeriod());
        assertEquals(0, instance.daysOverdue());
        SimpleDate.changeTodayBy(1);
        assertEquals(1, instance.daysOverdue());
    }

    /**
     * Test of renew method, of class Copy.
     */
    public void testRenew() {
        System.out.println("renew");
        Copy instance = book.makeCopy(); 
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        SimpleDate expResult = SimpleDate.getToday().daysLater(2 * book.getCheckoutPeriod());
        SimpleDate result = instance.renew();
        SimpleDate.changeTodayBy(1);
        assertEquals(expResult, result);
        result = instance.getDateDue();
        assertEquals(expResult, result);
        try
        {
            // Has already been renewed
            instance.renew();
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        SimpleDate.changeTodayBy(book.getCheckoutPeriod() + 1);
        try
        {
            // overdue
            instance.renew();
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
        instance = dvd.makeCopy();  
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        SimpleDate.changeTodayBy(1);
        try
        {
            // non-renewable
            instance.renew();
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }
 

    /**
     * Test of recordReturn method, of class Copy.
     */
    public void testRecordReturn() {
        System.out.println("recordReturn");
        Copy instance = book.makeCopy(); 
        Patron patron = new Patron("Anthony", "Aardvark", "Winn Subbasement", "1");
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        int expResult = 0;
        assertTrue(instance.isCheckedOut());
        int result = instance.recordReturn();
        assertEquals(expResult, result);
        assertFalse(instance.isCheckedOut());
        instance.checkOutTo(patron, SimpleDate.getToday().daysLater(book.getCheckoutPeriod()));
        SimpleDate.changeTodayBy(book.getCheckoutPeriod() + 2);
        expResult = 2;
        result = instance.recordReturn();
        assertEquals(expResult, result);
    }
    
    Book book;
    DVD dvd;

}
