package assignment03.Archive;
//Rian Brumfield

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{


        private Type holder;
        private GregorianCalendar dueDate;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
    }


        public Type getHolder() {
            return holder;
        }

        public GregorianCalendar getDueDate() {
            return dueDate;
        }

        public void setHolder(Type holder) {
            this.holder = holder;
        }

        public void setDueDate(GregorianCalendar dueDate) {
            this.dueDate = dueDate;
        }


}
