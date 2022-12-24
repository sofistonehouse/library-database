/*
 * CheckoutUseCase.java
 */

package library.controller;

import library.model.Copy;
import library.model.Patron;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/** This is the controller for the use case that manages checking out copies to
 *  a patron.
 *  • It maintains a list of copies to be checked out, with methods provided to
 *    add or remove an individual copy and to clear all copies.
 *  • It provides a method to check out all the items currently listed.
 *
 * @author Russell C. Bjork
 *
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class CheckoutUseCase {
    
    /** Constructor
     *
     *  @param patron the patron to whom copies will be checked out
     */
    public CheckoutUseCase(Patron patron) {
        patronToCheckoutTo = patron;
        copiesToBeCheckedOut = new ArrayList<Copy> ();
    }
    
    /** Accessor for the patron being checked out to
     *
     *  @return the patron being checked out to
     */
    public Patron getPatron() 
    {
        return patronToCheckoutTo;
    }
    
    /** Add a copy to the list of copies to check out
     *
     *  @param copy the copy to add
     *  
     *  @exception IllegalArgumentException if the copy is already checked out 
     *             or already on the list
     */
    public void addCopy(Copy copy) throws IllegalArgumentException
    {
        if (copy.isCheckedOut()){
            throw new IllegalArgumentException("Copy is already checked out");
        }
        else if (copiesToBeCheckedOut.contains(copy)){
            throw new IllegalArgumentException("Copy is already on the list");
        }
        else
            copiesToBeCheckedOut.add(copy);
        
    }
    
    /** Remove a copy from the list of copies to check out
     *
     *  @param position the position of the copy in the list - 0 = first, etc
     */
    public void removeCopy(int position)
    {
        copiesToBeCheckedOut.remove(position);
    }
    
    /** Clear all copies from the list of copies to check out
     */
    public void clearCopies()
    {
        copiesToBeCheckedOut.clear();
    }
    
    /** Check out all copies currently in the list to the patron
     */
    public void checkoutCopies()
    {
        // For each copy in copiesToBeCheckedOut
        //   Check out the copy
        //   Add the copy to the list of copies the patron has
        Iterator<Copy> iterator = copiesToBeCheckedOut.iterator();
        while (iterator.hasNext()){
            Copy copy = iterator.next();
            patronToCheckoutTo.checkOut(copy);
            copy.checkOutTo(patronToCheckoutTo, copy.getDateWillBeDue());
        }
    }
        
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // The patron we are checking out to
    private Patron patronToCheckoutTo;
    // The list of copies to be rented
    private List<Copy> copiesToBeCheckedOut;
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private CheckoutUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static CheckoutUseCase theCheckoutUseCase;
}
