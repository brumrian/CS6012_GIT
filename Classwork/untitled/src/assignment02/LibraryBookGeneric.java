package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric <Type> extends Book {

    private Type holder;
    private GregorianCalendar dueDate;


    public LibraryBookGeneric(long isbn, String author, String title){
        super(isbn, author, title);
    }

    /**
     * @return the generic type holder
     */
    public Type getHolder(){
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
     *              -- the generic type holder to assign to the book (set)
     */
    public void setHolder(Type holderToSet){
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
