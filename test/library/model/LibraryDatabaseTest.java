/* 
 * LibraryDatabaseTest.java
 */

package library.model;

import junit.framework.TestCase;

/** Tester for the library.model.LibraryDatabase class - full implementation for iteration 1
 *
 *	@author: Russell C. Bjork
 *
 *	MODIFIED BY:
 */
public class LibraryDatabaseTest extends TestCase {
    
    public LibraryDatabaseTest(String testName) {
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
     * Test of getInstance method, of class LibraryDatabase.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        LibraryDatabase db1 = LibraryDatabase.getInstance();
        LibraryDatabase db2 = LibraryDatabase.getInstance();
        // Singleton pattern should yield same value both times
        assertEquals(db1, db2);
   }

    /**
     * Test of save method, of class LibraryDatabase.
     */
    public void testSave() throws Exception {
        System.out.println("save is not tested");
    }

    /**
     * Test of add method, of class LibraryDatabase.
     */
    public void testAdd_Patron() {
        System.out.println("addPatron");
        LibraryDatabase instance = LibraryDatabase.getInstance();
        Patron duplicate = new Patron("duplicate", "patron", "xxx", "1234");
        instance.add(duplicate);
        assertEquals("duplicate patron", instance.getPatron("1234").getFullName());
        try
        {
            instance.add(duplicate);
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }

    /**
     * Test of add method, of class LibraryDatabase.
     */
    public void testAdd_Item() {
        System.out.println("addItem");
        LibraryDatabase instance = LibraryDatabase.getInstance();
        Book duplicate = new Book("ZZZZZ123", "duplicate", "book");
        instance.add(duplicate);
        assertEquals("duplicate", instance.getItem("ZZZZZ123").getTitle());
        try
        {
            instance.add(duplicate);
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }

    /**
     * Test of getPatron method, of class LibraryDatabase.
     */
    public void testGetPatron() {
        System.out.println("getPatron");
        LibraryDatabase instance = LibraryDatabase.getInstance();
        // A oatron with phone number "1" and name "Anthony Aardvark" and
        // one with phone number "2" and name "Boris Buffalo" 
        // are added by createDummyData.  To make the tests continue to work 
        // during iteration 2, the comment out for the following two 
        // lines must be removed
        // instance.add(new Patron("Anthony", "Aardark", "Winn Subbasement", "1"));
        // instance.add(new Patron("Boris", "Buffalo", "Hamilton Common", "2"));
        assertEquals("Anthony Aardvark", instance.getPatron("1").getFullName());
        assertEquals("Boris Buffalo", instance.getPatron("2").getFullName());
        try
        {
            instance.getPatron("99");
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }

    /**
     * Test of getItem method, of class LibraryDatabase.
     */
    public void testGetItem() {
        System.out.println("getItem");
        LibraryDatabase instance = LibraryDatabase.getInstance();
        // A book with call number "QA" and and a DVD with call number "PN"
        // are added by createDummyData.  To make the tests continue to work 
        // during iteration 2, the comment out for the following two 
        // lines must be removed
        // instance.add(new Book("QA", "Lisp", "Winston"));
        // instance.add(new DVD("PN", "Shrek", "Myers"));
        assertEquals("A Student Guide to OO Development", instance.getItem("QA").getDescription());
        assertEquals("Shrek (DVD)", instance.getItem("PN").getDescription());
        try
        {
            instance.getItem("ZZ");
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }

    /**
     * Test of getCopy method, of class LibraryDatabase.
     */
    public void testGetCopy() {
        System.out.println("getCopy");
        LibraryDatabase instance = LibraryDatabase.getInstance();
        // A book with call number "QA" and and a DVD with call number "PN"
        // are added by createDummyData, as are two copies of each.
        // To make the tests continue to work during iteration 2, the comment 
        // out for the following lines must be removed
        // instance.add(new Book("QA", "Lisp", "Winston"));
        // instance.add(new DVD("PN", "Shrek", "Myers"));
        // Item book = instance.getItem("QA");
        // instance.add(book.makeCopy());
        // instance.add(book.makeCopy());
        // Item dvd = instance.getItem("PN");
        // instance.add(dvd.makeCopy());
        // instance.add(dvd.makeCopy());
        assertEquals("QA", instance.getCopy("QA", 1).getItem().getCallNumber());
        assertEquals(1, instance.getCopy("QA", 1).getCopyNumber());
        assertEquals("QA", instance.getCopy("QA", 2).getItem().getCallNumber());
        assertEquals(2, instance.getCopy("QA", 2).getCopyNumber());
        assertEquals("PN", instance.getCopy("PN", 1).getItem().getCallNumber());
        assertEquals(1, instance.getCopy("PN", 1).getCopyNumber());
        assertEquals("PN", instance.getCopy("PN", 2).getItem().getCallNumber());
        assertEquals(2, instance.getCopy("PN", 2).getCopyNumber());
        try
        {
            instance.getCopy("QA", 3);
            fail("Should have thrown an IllegalArgumentException");
        }
        catch(IllegalArgumentException e)
        { /* This is OK */ }
    }
    
}
