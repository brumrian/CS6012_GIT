package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {

    private String holder;
    private GregorianCalendar dueDate;


    public LibraryBook(long isbn, String author, String title){
        super(isbn, author, title);
    }

    /**
     * @return the holder
     */
    public String getHolder(){
        return holder;
    }

    /**
     * @return the due date
     */
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    /**
     * @param holderToSet
     *              -- the holder to assign to the book (set)
     */
    public void setHolder(String holderToSet){
        holder = holderToSet;
    }

    /**
     * @param dueDateToSet
     *              -- the due date to assign to the book (set)
     */
    public void setDueDate(GregorianCalendar dueDateToSet){
        dueDate = dueDateToSet;
    }

}
