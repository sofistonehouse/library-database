/*
 *  Copy.java
 */

package library.model;

/** Representation for a single copy that can be checked out from the library
 * 
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY:  Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class Copy implements java.io.Serializable {
    Patron checkedOutToPatron = null;
    SimpleDate dueDate;
    int renewalCount = 0;
    SimpleDate checkOutDate = null;
    
    /** Constructor
     * 
     *  @param item the item this is a copy of
     *  @param copyNumber the copy number of this copy of the item
     */
    public Copy(Item item, int copyNumber)
    {
        this.item = item;
        this.copyNumber = copyNumber;
    }
    
    /** Accessor for the item of which this is a copy
     * 
     * @param the item
     */
    public Item getItem()
    {
        return item;
    }
    
    /** Accessor for the copy number of this copy
     * 
     *  @return the copy number
     */
    public int getCopyNumber()
    {
        return copyNumber;
    }
    
    /** Check this copy out to a patron
     *
     *  @param patron the patron to check this item out to
     *  @param dateDue the date on which the it will be due
     */
    public void checkOutTo(Patron patron, SimpleDate dateDue)
    {
        SimpleDate today = SimpleDate.getToday();
        this.checkedOutToPatron = patron;
        this.checkOutDate = today;
        this.dueDate = dateDue;
    }
    
    /** Get the patron to whom this copy is checked out
     * 
     *  @return the patron, or null if not checked out
     */
    public Patron getCheckedOutTo()
    {
        return checkedOutToPatron;
    }
    
    /** Get the date this copy will be due if it is checked out now
     * 
     *  @return the date it will be due
     */
    public SimpleDate getDateWillBeDue() 
    {
        SimpleDate possibleDate = SimpleDate.getToday().daysLater(this.getItem().getCheckoutPeriod());
        return possibleDate;
    }
    
    /** Get the date when this copy is due if it is already checked out
     * 
     *  @return the date due,
     */
    public SimpleDate getDateDue()
    {
       return dueDate;
    }
    
    /** Test to see whether this copy is checked out
     *
     *	@return true if this copy is checked out
     */
    public boolean isCheckedOut()
    {
        Patron patron = this.checkedOutToPatron;
        if (patron == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /** Calculate the number of days this copy is overdue
     * 
     * @return the number of days overdue - 0 if not overdue
     * 
     * Precondition: only applicable if copy is currently checked out
     */
    public int daysOverdue()
    {
        int daysOverdue = 0;
        if (this.getCheckedOutTo() != null)
        {
            SimpleDate today = SimpleDate.getToday();
            if(today.isAfter(dueDate))
            {
                daysOverdue = today.daysAfter(dueDate);
            }
        }
        return daysOverdue;
    }
    
    /** Renew this copy (to patron who currently has it out)
     *
     *	Precondition; this copy is checked out
     * 
     *  @return the new due date
     *  @exception IllegalArgumentException if this copy cannot be renewed
     *             because it is a copy of a non-renewable item, or has been
     *             renewed as many times as allowed, or is overdue.  (The
     *             message in the exception will specify the reason.)
     */
    public SimpleDate renew() throws IllegalArgumentException
    {
        if (this.getCheckedOutTo() == null)
        {
            throw new IllegalArgumentException(this.getItem().getTitle().toString() + " isn't checked out ");
        }
        else if (this.daysOverdue() != 0)
        {
            throw new IllegalArgumentException(this.getItem().getTitle().toString() + " is overdue.");
        }
        else if(renewalCount == this.getItem().getAllowedNumberOfRenewals())
        {
            throw new IllegalArgumentException(this.getItem().getTitle().toString() + " can't be renewed again");
        }
        else if(this.getItem().getAllowedNumberOfRenewals() == 0)
        {
            throw new IllegalArgumentException(this.getItem().getTitle().toString() + " is not allowed for renewal");
        }
        else
        {
            int checkOutTime = this.getItem().getCheckoutPeriod();
            SimpleDate newDueDate = this.dueDate.daysLater(checkOutTime);
            dueDate = newDueDate;
            renewalCount += 1;
            return dueDate;
        }
    }
    
    /** Record the return of this book
     * 
     *  @return if overdue, the number of days overdue; 0 if it is returned on time
     */
    public int recordReturn()
    {
        int daysOverdue = 0;
        renewalCount = 0;
        this.checkedOutToPatron = null;
        if (SimpleDate.getToday().isAfter(dueDate))
        {
            daysOverdue = SimpleDate.getToday().daysAfter(dueDate);
        }
    return daysOverdue;  
    }
    
    private Item item;
    private int copyNumber;
    static final long serialVersionUID = 1;
}