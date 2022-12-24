/*
 * SimpleDate.java
 *
 * A simple representation for a calendar date - without regard to time.
 * To faciliate using this class while developing programs, when the
 * class is loaded it creates a static variable containing today's
 * date, and provides static methods getToday() to access it and changeTodayBy()
 * to modify it to simulate running the program on a different day.
 * Programs that need to store a date should use getToday() rather than
 * a constructor unless they are starting with a Date stored in a database.
 */

package library.model;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;


/** A simple representation for a calendar date - without regard to time.
 *
 * @author Russell C. Bjork
 */
public class SimpleDate implements java.io.Serializable 
{    
    /** Get "today"'s date
     *
     *  @return the program's notion of "today" - possibly modified by 
     *  changeTodayBy() to allow testing.  The object returned is a clone
     *  of today, so that subsequent calls to changeTodayBy() won't affect
     *  it.
     */
    public static SimpleDate getToday() 
    {
        return new SimpleDate(today);
    }
    
    /** Change "today"'s date to facilitate testing
     *
     *  @param after the number of days to add to the program's notion
     *         of "today" - can be negative or positive (negative amounts to
     *         go back in time)
     */
    public static void changeTodayBy(int after) 
    {
        today.value.add(Calendar.DATE, after);
    }
    
    /** Convert this object to a nicely printable string
     *
     *  @return formatted string representing this object in MM/DD/YY format
     */
    public String toString() 
    {
        return DateFormat.getDateInstance(DateFormat.SHORT,
        								  java.util.Locale.US).format(value.getTime());
    }
            
    /** Convert this object to a nicely printable string in YYMMDD format
     *
     *  @return formatted string representing this object in YY/MM/DD format
     */
    public String toStringYYMMDD() 
    {
        // Convert to YY/MM/DD format
        // Since the default short representation for dates does not pad
        // one-digit months or days with a leading 0, do so for this case 
        String s = DateFormat.getDateInstance(DateFormat.SHORT).format(value.getTime());
        String month = s.substring(0, s.indexOf('/'));
        if (month.length() < 2) month = '0' + month;
        String day = s.substring(s.indexOf('/') + 1,
                                 s.lastIndexOf('/'));
        if (day.length() < 2) day = '0' + day;
        String year = s.substring(s.lastIndexOf('/') + 1,
                      s.length());
        if (year.length() < 2) year = '0' + year;
        return year + '/' + month + '/' + day;
    }

    /** Test to see if the date represented by this object is the same as that
     *  represented by some other object
     *
     *  @param other the date to be compared to
     *  @return true if other object is a SimpleDate and date represented by
     *          this object is the same as that represented by other object
     *
     */
    public boolean equals(Object other) 
    {
        return (other instanceof SimpleDate) &&
            this.value.equals(((SimpleDate) other).value);
    }
    
    /** Test to see if the date represented by this object is after that
     *  represented by some other SimpleDate object
     *
     *  @param other the date to be compared to
     *  @return true if this object represents a date after other
     *
     */
    public boolean isAfter(SimpleDate other) 
    {
        return this.value.after(other.value);
    }
    
    /** Return the number of days the date represented by this object is
     *  after or before that represented by other.
     *
     *  @param other the date to be subtracted from this
     *  @return the number of days this is after other; a negative value will
     *   be returned if this is before other
     *
     */
    public int daysAfter(SimpleDate other) 
    {
        return (int) ((this.value.getTime().getTime() - 
                other.value.getTime().getTime()) / MILLISECONDS_PER_DAY);
    }   

    /** Create a new date that represents a date some number of days later or
     *  earlier than this object.
     *
     *  @param after the number of days after this (negative for before)
     *  @return a SimpleDate that is some number of days after (before) this
     */
    public SimpleDate daysLater(int after) 
    {
        SimpleDate result = new SimpleDate(this);
        result.value.add(Calendar.DATE, after);
        return result;
    }
    
    /** Create a new date that represents a date one month after the date to which
     *  it is applied.  (If the day of the month of this SimpleDate is impossible
     *  for the next month [ e.g. it's 31 and the next month is a 30 day month ],
     *  the day of the month is set to the largest possible value in the new
     *  month.)
     *
     *	@return a SimpleDate that is one month later than this
     */
    public SimpleDate monthLater()
    {
        SimpleDate result = new SimpleDate(this);
        result.value.add(Calendar.MONTH, 1);
        result.value.getTime();     // Forces any needed cleanup
        return result;
    }
    
    /** Convert this object to a java.util.Date object
     *
     *  @return an object corresponding to the represented date
     */
    public Date toDate()
    {
        return value.getTime();
    }
    
    /** Construct as SimpleDate object from a java.util.Date object
     *
     *  @param date the date to represent
     */
    public SimpleDate(Date date)
    {
        value = Calendar.getInstance();
        value.setTime(date);
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);
    }
    
    // INSTANCE VARIABLE
    
    private Calendar value;
    
    // THE CURRENT DATE
    
    private static SimpleDate today = new SimpleDate();

    // Number of milliseconds in a day
    private static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

    // Private methods, used only for implementing the above
    
    /** Constructor - the object constructed represents today. 
     */
    private SimpleDate() 
    {    
        // A date is represented internally be a calendar object set to 
        // 0:00:00 on the specified date
        
        value = Calendar.getInstance();
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);
    }
    
    /** Constructor - the object constructed is a clone of copyFrom 
     *
     *  @param copyFrom - the date to clone
     */
    private SimpleDate(SimpleDate copyFrom) 
    {
        this.value = (Calendar) copyFrom.value.clone();
    }
        
    // The following method is provided only to facilitate testing
    
    /** Set the value of today to a known value
     *
     *  @param date the date to be set
     */
    public static void setToday (Date date)
    {
        today.value.setTime(date);
    }
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
