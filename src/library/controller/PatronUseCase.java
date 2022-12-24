
package library.controller;

import library.gui.GUI;
import library.model.LibraryDatabase;
import library.model.Patron;
/** The controller for the use case for managing adding a patron to the database
 *
 * @author Sofi Stonehouse, Daniel Lovelace, Mya Randolph, Jason Asonye
 */


public class PatronUseCase {
     /** adds a patron to the database with all their corresponding information
     *
     *  @param firstName is the first name of the patron
     *  @param lastName is the last name of the patron
     *  @param address is the address of the patron
     *  @param phoneNumber is the phone number of the patron
     * 
     */
    public static void addPatron(String firstName, String lastName, String address, String phoneNumber)
    {
        Patron patron = new Patron(firstName, lastName, address, phoneNumber);
        LibraryDatabase.getInstance().add(patron);
        GUI.getInstance().showMessage("Patron " + firstName + " " + lastName 
                + " has successfully been added to the database.");
    }
    //creates a report for each patron
    public static void produceReport()
    {
        LibraryDatabase libraryDatabase = LibraryDatabase.getInstance();
        libraryDatabase.producePatronsReport();
    }
}
