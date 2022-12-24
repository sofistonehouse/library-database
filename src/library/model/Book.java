/*
 *  Book.java
 */

package library.model;

import java.util.Iterator;

/** Representation for a book
 * 
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class Book extends Item implements java.io.Serializable {
    
    /** Constructor
     * 
     *  @param callNumber the call number of this book
     *  @param title the title of this book
     *  @param author the principal author of this book
     */
    public Book(String callNumber, String title, String author)
    {
        super(callNumber, title);
        this.callNumber = callNumber;
        this.title = title;
        this.author = author;       
    }
    
    // Implementation of abstract methods of base class
    /**description of the book
     * 
     * @return name of the book and the authors name
     */
    public String getDescription()
    {
       return title + " by " + author;
    }
    /**Returns the number of days the book is checked out  for
     * 
     * @return checkout period
     */
    public int getCheckoutPeriod()
    {
       return 28;
    }
    /**Returns the number of renewed books 
     * 
     * @return allowed number of renewals 
     */
    public int getAllowedNumberOfRenewals()
    {
      return 1;
    }
    /**overrides the print report method in the Item class
     * 
     */
    public void printReport(){
        
        super.printReport();
        System.out.println("Author: " + author);
        System.out.println("\r\n");
    }
    
    private String callNumber;
    private String title;
    private String author;
    
    static final long serialVersionUID = 1;
}
