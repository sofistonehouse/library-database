/*
 * Patron.java
 */

package library.model;
import java.util.HashSet;
import java.util.Iterator;

/** Representation for a library patron
 * 
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class Patron implements java.io.Serializable {
    
    /** Constructor
     * 
     *  @param firstName the first name of the patron
     *  @param lastName the last name of the patron
     *  @param address the address of the patron
     *  @param phoneNumber the phone number of the patron
     */
    public Patron(String firstName, 
                  String lastName, 
                  String address,
                  String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;   
        copiesCheckedOut = new HashSet<Copy>();
    }
    
    /** Accessor for the full name of this patron
     *
     *	@return the full name (first and last)
     */
    public String getFullName()
    {

      return (firstName + " " + lastName);
    }
    
    /** Accessor for the phone number of this patron
     * 
     *  @return the phone number
     */
    public String getPhoneNumber()
    {
       return phoneNumber;
    }
    
    
    /** Accessor for the number of copies currently checked out to this patron
     * 
     *  @return the number of copies currently out
     */
    public int getCurrentCopiesOut()
    {
       return copiesCheckedOut.size();
    }

    /** Get the number of copies currently checked out that are overdue
     *  
     *  @return the number overdue
     */
    public int getNumberOverdue()
    {
        int count = 0;
        Iterator<Copy> iterator = this.getCopiesCheckedOut();
        while (iterator.hasNext())
        {
            Copy copy;
            copy = iterator.next();
            if (copy.daysOverdue() != 0)
            {
                count += 1;
            }
        }
      return count;       
    }
    
    /** Check out a copy to this patron
     * 
     *  @param copy the copy being checked out
     */
    public void checkOut(Copy copy)
    {
        copiesCheckedOut.add(copy);
        copy.checkOutTo(this, SimpleDate.getToday().daysLater(copy.getItem().getCheckoutPeriod()));
    }
    
    /** Return a copy that was checked out to this patron
     * 
     *  @param copy the copy to return
     */
    public void returnCopy(Copy copy)
    {       
        copiesCheckedOut.remove(copy);
    }
    
    /** Get an iterator over all the copies this patron has checked out
     * 
     *  @return an iterator
     */
    public Iterator<Copy> getCopiesCheckedOut()
    {
        return copiesCheckedOut.iterator();
    }
    

    public void printReport()
    {
        
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Copies out: " + copiesCheckedOut.size());
        for (Copy copy: copiesCheckedOut){
            if (copy.daysOverdue()> 0)
                System.out.println(copy.getItem().getCallNumber() + " " + 
                        copy.getCopyNumber() + ": " + copy.getItem().getDescription() +
                        " due: " + copy.getDateDue() + " overdue " + copy.daysOverdue() +
                                " days");
            else{
                continue;
            }
        }
        System.out.println("\r\n");
    }
    
    /** Create a string representation for this Patron to be used in a list
     * of patrons
     *
     * @return the string
     */
     public String toString() {
        return getPhoneNumber() + ": " + getFullName();
     }
     
        private String firstName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private HashSet<Copy> copiesCheckedOut;
        
	static final long serialVersionUID = 1;
     
}