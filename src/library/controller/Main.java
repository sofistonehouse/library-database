/*
 * Main.java
 */

package library.controller; 
import library.gui.GUI;
import library.model.LibraryDatabase;

/** Main controller for the program.  Responsible for starting and quitting
 *  the program, including saving the database.
 *
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY: 
 */
public class Main 
{
    /** Main method for the application
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
         GUI.getInstance().setVisible(true);
    }

    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static Main getInstance()
    {
        if (theMain == null)
            theMain = new Main();
        return theMain;
    }
    
    /** Save the library database
     *
     *  @exception Exception any exception thrown during the save will
     *             be propagated
     */
    public void saveLibraryDatabase() throws Exception
    {
        LibraryDatabase.getInstance().save();
    }
    
    /** Terminate the application after saving store data
     *              
     */
    public void quitProgram() 
    {
        try
        {
            saveLibraryDatabase();
        }
        catch(Exception e)
        {
            GUI.getInstance().showMessage("Exception while saving data " + e);
        }
        System.exit(0);
    }
        
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor.  Other classes should access through the singleton
    // pattern
    private Main() 
    {
    }

    // The one and only instance of this class
    private static Main theMain;
}
