/*
 *  LibraryDatabase.java
 */

package library.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import library.model.Patron;

import displayablecollections.DisplayableTreeMap;
import javax.swing.ListModel;
import java.util.*;


/** A singleton object of this class represents the library's database
 *
 * @author  Russell C. Bjork
 *
 * MODIFIED BY: Sofi Stonehouse, Jason Asonye, Daniel Lovelace, and Mya Randolph
 */
public class LibraryDatabase implements java.io.Serializable {
    DisplayableTreeMap<String,Item> items = new DisplayableTreeMap<String, Item>();
    DisplayableTreeMap<String, Patron> patrons = new DisplayableTreeMap<String, Patron>();
    
  /** Accessor for the one and only object of this class (singleton
   *  pattern)
   *
   *  @return the one and only object of this class
   */
  public static LibraryDatabase getInstance()
    {
        if (theLibraryDatabase == null)
        {
            try {
                ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(SAVED_DATABASE_NAME));
                theLibraryDatabase = (LibraryDatabase) stream.readObject();
                stream.close();
            }
            catch(FileNotFoundException e) {
                theLibraryDatabase = new LibraryDatabase();
            }
            catch(Throwable e) {
                System.err.println("Unexpected exception " + e);
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        return theLibraryDatabase;
    }
    
    /** Save the library database
     *
     *  @exception Exception any Exception thrown during the save is propagated
     */
    public void save() throws Exception
    /*        
    {
        library.gui.GUI.getInstance().showMessage("Saving of data not yet enabled");
    }
    */
    
    {
        ObjectOutputStream stream = new ObjectOutputStream(
                    new FileOutputStream(SAVED_DATABASE_NAME));
        stream.writeObject(theLibraryDatabase);
        stream.close();
 
    }
    

  /** Add a patron to the list of patrons
   *
   *  @param patron the patron to add
   *  @exception IllegalArgumentException if a patron with this phone number is
   *			   already in the database
   */
  public void add(Patron patron) throws IllegalArgumentException {
    if(patrons.containsKey(patron.getPhoneNumber())){
        throw new IllegalArgumentException ("Patron is already in the database");
    }
    else {
        patrons.put(patron.getPhoneNumber(), patron);      
    }
  }

  /** Add an item to the list of items
   *
   *  @param item the item to add
   *	@exception IllegalArgumentException if an item with this call number is
   *			   already in the database
   */
  public void add(Item item) throws IllegalArgumentException {
      if(items.containsKey(item.getCallNumber())){
        throw new IllegalArgumentException ("Item is already in the database");
    }
    else {
        items.put(item.getCallNumber(), item);
    }
  }

  /** Get the patron having a given phone number
   *
   *  @param phoneNumber the phone number of the patron
   *  @return the patron having this phone number
   *  @exception IllegalArgumentException if this patron does not exist
   */
  public Patron getPatron(String phoneNumber) throws IllegalArgumentException {
      if(!patrons.containsKey(phoneNumber)){
          throw new IllegalArgumentException ("Patron does not exist");
      }
      else{
        return patrons.get(phoneNumber);
      }
  }
  
  
  /** Get the item having a given call number
   *
   *  @param callNumber the call number of the item
   *  @return the patron having this phone number
   *  @exception IllegalArgumentException if this item does not exist
   */
  public Item getItem(String callNumber) throws IllegalArgumentException {
      if(!items.containsKey(callNumber)){
          throw new IllegalArgumentException ("Item does not exist");
      }else{
        return items.get(callNumber);
      }
    
  }

  /** Get the copy having a given call number and copy number
   *
   *  @param callNumber the call number of the copy
   *  @param copyNumber the copy number of the copy
   *  @return the copy having this call number and copy number
   *  @exception IllegalArgumentException if this copy does not exist
   */
  public Copy getCopy(String callNumber, int copyNumber) 
  {
      Item item = getItem(callNumber);
      Copy copy = item.getCopy(copyNumber);
      return copy;
  }

 /** Get a list model for the patrons
 *
 * @return a list model
 */
 public ListModel<Patron> getPatronsModel()
 {
    return patrons.keyOrderedListModel();
 }
 /** Get a list model for the items
 *
 * @return a list model
 */
 public ListModel<Item> getItemsModel()
 {
    return items.keyOrderedListModel();
 }
  
  // Private constructor.  Other classes should access through the singleton
  // pattern
  private LibraryDatabase() 
  {  
    createDummyData();
  }
  // produces a patron report
  public void producePatronsReport()
  {
      Iterator iterator = patrons.values().iterator();
      while(iterator.hasNext()){
          Patron patron = (Patron)iterator.next();
          patron.printReport();
      }
  }
  //prodcues a item report
  public void produceItemsReport()
  {
      Iterator iterator = items.values().iterator();
      while(iterator.hasNext()){
          Item item = (Item)iterator.next();
          item.printReport();
      }
  }
  
   // Populate the database with dummy data.  Needed only until code for
   // adding patrons, etc. has been written
    private void createDummyData() 
    {
        Patron aardvark = new Patron("Anthony", 
                                     "Aardvark", 
                                     "Jenks Subbasement",
                                     "1");
        add(aardvark);
        
        Patron buffalo = new Patron("Boris", 
                                    "Buffalo", 
                                    "Hamilton Common",
                                    "2");
        add(buffalo);
        
        Patron cat = new Patron("Charlene",
                                "Cat",
                                "20 Litterbox Lane",
                                "3");
    	add(cat);
        
        Patron dog = new Patron("Donna",
                                "Dog",
                                "Fire Hydrant",
                                "4");
		add(dog);
        
		Book oo = new Book("QA","A Student Guide to OO Development",
						"Britton and Doake"); 
        add(oo);
        Copy op1 = oo.makeCopy();
        Copy oo2 = oo.makeCopy();
        
        DVD shrek = new DVD("PN", "Shrek", "Myers, Mike", "PG");
        add(shrek);
        Copy shrek1 = shrek.makeCopy();
        Copy shrek2 = shrek.makeCopy();
        
        DVD hobbit = new DVD("YA", "The Hobbit", "Freeman, Martin", "PG" );
        add(hobbit);
        Copy hobbit1 = hobbit.makeCopy();
        Copy hobbit2 = hobbit.makeCopy();
        
    }
  
  
  // The one and only instance of this class
  private static LibraryDatabase theLibraryDatabase;

  // Name to use for saved database
  private static final String SAVED_DATABASE_NAME = "library.database";

  // Symbolic constant needed to prevent unnecessary ClassCastExceptions
  // when reading a serialized object created by an earlier version of this
  // class
  static final long serialVersionUID = 1;
    
}
