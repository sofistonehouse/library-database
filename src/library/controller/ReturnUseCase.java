/*
 * ReturnUseCase.java
 */


package library.controller;


import library.model.Copy;
import library.model.Patron;
import library.model.LibraryDatabase;

/**
 *
 * @author Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */

public class ReturnUseCase {
       /** Perform the use case 
     *
     *  @param copy the copy whose status is being requested
     */
    public static void perform(Copy copy) {
        
        Patron checkedOutTo = copy.getCheckedOutTo();
        if (checkedOutTo == null)
            throw new IllegalArgumentException("Copy is not checked out");
        else
        {
            checkedOutTo.returnCopy(copy);
            copy.recordReturn();
        }
        
    }
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    // Private constructor prevents instantiation
    private ReturnUseCase()
    { }   
}
