/*
 * StatusUseCase.java
 */

package library.controller;

import library.gui.GUI;
import library.model.Copy;
import library.model.Patron;
import library.model.LibraryDatabase;

/** This is the controller for the use case that reports the status of copies.
 *	No objects of this class are ever created - the operation is done by
 *	a class method
 * 
 * @author Russell C. Bjork
 *
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */

public class StatusUseCase {
    
   /** Perform the use case 
     *
     *  @param copy the copy whose status is being requested
     */
    public static void perform(Copy copy) {
        Patron checkedOutTo = copy.getCheckedOutTo();
        if (checkedOutTo == null)
            GUI.getInstance().showMessage("Copy is on the shelf");
        else
        {
            GUI.getInstance().showMessage("Checked out to " +
                checkedOutTo.getFullName() + " due " + copy.getDateDue());
        }
        
    }
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor prevents instantiation
    private StatusUseCase()
    { }   
}
