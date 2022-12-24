/*
 * LoginOut.java
 *
 */

package library.controller;

/** A singleton object of this class keeps track of whether or not a manager
 *  is logged in to the system
 *
 * @author Russell C. Bjork
 *
 * STUDENTS DO NOT NEED TO MODIFY THIS CLASS
 */
public class LoginOutUseCase extends java.util.Observable {
    
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static LoginOutUseCase getInstance()
    {
        if (theLoginOut == null)
            theLoginOut = new LoginOutUseCase();
        return theLoginOut;
    }
    
    /** Attempt to login as manager
     *
     *  @param password the password the user typed
     *  @exception IllegalArgumentException if the password is not valid
     */
    public void doLogin(String password) {
        if (password.equals(PASSWORD))
        {
            managerLoggedIn = true;
            setChanged();
            notifyObservers();
        }
        else
            throw new IllegalArgumentException("Invalid password");
    }
    
    /** Logout manager
     */
    public void doLogout() {
        managerLoggedIn = false;
        setChanged();
        notifyObservers();
    }

    /** Check to see if a manager is logged in
     *
     *  @return true if a manager is logged in, false if not
     */
    public boolean isManagerLoggedIn() {
        return managerLoggedIn;
    }

    // Private constructor - other classes should access through the singleton
    // pattern
    
    /**
     * Creates a new instance of LoginOut
     */
    private LoginOutUseCase() {
    }
    
    // True just when a manager is logged in
    
    private boolean managerLoggedIn;
    
    // Variable used for the singleton pattern
    private static LoginOutUseCase theLoginOut;
    
    private static String PASSWORD = "justice";
    
}
