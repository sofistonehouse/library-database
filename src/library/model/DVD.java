/*
 *  DVD.java
 */

package library.model;
import java.util.Iterator;
/** Representation for a DVD
 * 
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class DVD extends Item implements java.io.Serializable {
    
    /** Constructor
     * 
     *  @param callNumber the call number of this DVD
     *  @param title the title of this DVD
     *  @param leadActor the lead actor of this DVD
     *	@param rating the rating of this DVD
     */
    public DVD(String callNumber, String title, String leadActor, String rating)
    {
        super(callNumber, title);
        this.title = title;
        this.leadActor = leadActor;
        this.rating = rating;        
    }
    
    // Implementation of abstract methods of base class
    /**returns the description of the book
     * 
     * @return the title, lead actor and rating of the DVD
     */
    public String getDescription()
    {
        return title + " (DVD) " + leadActor + ", rated: " + rating;
    }
     /**Returns the number of days the DVD is checked out  for
     * 
     * @return checkout period
     */
    public int getCheckoutPeriod()
    {
       return 7;
    }
      /**Returns the number of renewed DVDs 
     * 
     * @return allowed number of renewals 
     */
    public int getAllowedNumberOfRenewals()
    {
       return 0;
    }
     /**overrides the print report method in the Item class
     * 
     */
    public void printReport()
    {
        super.printReport();
        System.out.println("Lead Actor: " + leadActor);
        System.out.println("Rating: " + rating);
        System.out.println("\r\n");
    }
    
        private String callNumber;
        private String title;
        private String leadActor;
        private String rating;
        
        static final long serialVersionUID = 1;    
}
