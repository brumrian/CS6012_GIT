package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
 *
 */
public class LibraryGenericTest {

  public static void main(String[] args) {

    // test a library that uses names (String) to id patrons
    LibraryGeneric<String> lib1 = new LibraryGeneric<String>();

    // library should be empty with no books
    if (lib1.lookup(9780374292799L) != null)
      System.err.println("TEST FAILED: empty library(lib1): lookup(isbn)");

    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

    String patron1 = "Jane Doe";

    // First two checkouts should return true because the books haven't been checked out yet
    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");

    // Already been checked out - should return false
    if (lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: incorrect checkout");

    ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);

    // both of these ISBN's are checked out under patron2's name
    if (lib1.lookup(9780330351690L) != patron1
            || lib1.lookup(9780374292799L) != patron1)
      System.err.println("TEST FAILED: lookup(IBSN)");

    if (booksCheckedOut1 == null || booksCheckedOut1.size() != 2
            // Only these 2 books have been checked out
        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
        || !booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
            // Both books that have been checked out should have patron 1 as the holder and 1/1/2008 as the due date
        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
        || !booksCheckedOut1.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
        || !booksCheckedOut1.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");

    // Should be able to check in the books since they have been checked out
    if (!lib1.checkin(patron1))
      System.err.println("TEST FAILED: checkin(holder)");

    // First IBSN was never checked out, so should fail to check in. 2nd and 3rd have already been checked in so should fail to check in.
    if(lib1.checkin(9780446580342L)
      || lib1.checkin(9780330351690L)
      || lib1.checkin(9780374292799L))
      System.err.println("TEST FAILED: checkin(IBSN)");

    //*******************************************************

    // test a library that uses phone numbers (PhoneNumber) to id patrons
    LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();

    // library should be empty with no books
    if (lib2.lookup(9780374292799L) != null)
      System.err.println("TEST FAILED: empty library (lib2): lookup(isbn)");

    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

    PhoneNumber patron2 = new PhoneNumber("801.555.1234");

    // First two checkouts should return true because the books haven't been checked out yet
    if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    // These two checkouts should return false because the 1st ISBN doesn't exist, the second has already been checked in
    if (lib2.checkout(9780330351640L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: third checkout");
    if (lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: incorrect checkout");

    ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);

    // both of these ISBN's are checked out under patron2's name
    if (lib2.lookup(9780330351690L) != patron2
        || lib2.lookup(9780374292799L) != patron2)
      System.err.println("TEST FAILED: lookup(IBSN)");

    if (booksCheckedOut2 == null || booksCheckedOut2.size() != 2
            // Only these 2 books have been checked out
        || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
        || !booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
            // Both books that have been checked out should have patron 2 as the holder and 1/1/2008 as the due date
        || !booksCheckedOut2.get(0).getHolder().equals(patron2)
        || !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
        || !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");

    // Book has not been checked in yet, so should be able to check it in
    if (!lib2.checkin(patron2))
      System.err.println("TEST FAILED: checkin(holder)");

    // First IBSN was never checked out, so should fail to check in. 2nd and 3rd have already been checked in so should fail to check in.
    if (lib2.checkin(9780446580342L)
        || lib2.checkin(9780374292799L)
        || lib2.checkin(9780330351690L))
      System.err.println("TEST FAILED: checkin(ISBN)");

    //*********************************************************************************

    // Test sorting

    // Make library 2 a larger library by adding Mushroom_Publishing.txt to it
    lib2.addAll("src/assignment02/Mushroom_Publishing.txt");

    // Sorting by IBSN

    ArrayList<LibraryBookGeneric<String>> ibsnTest1 = lib1.getInventoryList();
    boolean correctOrder = true;
    for (int i = 0; i < ibsnTest1.size() - 1; i++){
      if (ibsnTest1.get(i).getIsbn() > ibsnTest1.get(i + 1).getIsbn()) {
        correctOrder = false;
        break;
      }
    }
    if (!correctOrder){
      System.err.println("TEST FAILED: IBSN Sort Test 1");
    }

    ArrayList<LibraryBookGeneric<PhoneNumber>> ibsnTest2 = lib2.getInventoryList();
    correctOrder = true;
    for (int i = 0; i < ibsnTest2.size() - 1; i++){
      if (ibsnTest2.get(i).getIsbn() > ibsnTest2.get(i + 1).getIsbn()) {
        correctOrder = false;
        break;
      }
    }
    if (!correctOrder){
      System.err.println("TEST FAILED: IBSN Sort Test 2)");
    }

    // Sorting by Author

    ArrayList<LibraryBookGeneric<String>> authorTest1 = lib1.getOrderedByAuthor();
    correctOrder = true;
    for (int i = 0; i < authorTest1.size() - 1; i++){

      int authorComparison1 = authorTest1.get(i).getAuthor().compareTo(authorTest1.get(i+1).getAuthor());
      if (authorComparison1 > 0){
        correctOrder = false;
        break;
      }
    }
    if (!correctOrder){
      System.err.println("TEST FAILED: Author Sort Test 1");
    }

    ArrayList<LibraryBookGeneric<PhoneNumber>> authorTest2 = lib2.getOrderedByAuthor();
    correctOrder = true;
    for (int i = 0; i < authorTest2.size() - 1; i++){

      int authorComparison2 = authorTest2.get(i).getAuthor().compareTo(authorTest2.get(i+1).getAuthor());
      if (authorComparison2 > 0){
        correctOrder = false;
        break;
      }
    }
    if (!correctOrder){
      System.err.println("TEST FAILED: Author Sort Test 2");
    }

    // Sorting by Due Date

    LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
    lib3.addAll("src/assignment02/Mushroom_Publishing.txt");

    // SHOULD be in the overdue list (overdue year)
    lib3.checkout(9781843190004L, "Malila", 11, 15, 2020);
    // SHOULD NOT be in the overdue list (upcoming year)
    lib3.checkout(9781843190011L, "Malila", 11, 15, 2022);
    // SHOULD be in the overdue list (overdue day)
    lib3.checkout(9781843190028L, "Malila", 11, 14, 2021);
    // SHOULD NOT be in the overdue list (upcoming day)
    lib3.checkout(9781843190042L, "Malila", 11, 16, 2021);
    // SHOULD be in the overdue list (upcoming month)
    lib3.checkout(9781843190110L, "Malila", 10, 1, 2021);
    // SHOULD NOT be in the overdue list (overdue month)
    lib3.checkout(9781843190073L, "Malila", 12, 1, 2021);
    // SHOULD NOT be in the overdue list (same day)
    lib3.checkout(9781843190349L, "Malila", 11, 15, 2021);


    ArrayList<LibraryBookGeneric<String>> dueTest1 = lib3.getOverdueList(11, 15, 2021);
    correctOrder = true;

    for (int i = 0; i < dueTest1.size() - 1; i++){

      int dueComparison1 = dueTest1.get(i).getDueDate().compareTo(dueTest1.get(i+1).getDueDate());
      if (dueComparison1 > 0){
        correctOrder = false;
        break;
      }
    }

    if (!correctOrder){
      System.err.println("TEST FAILED: Due Date Sort Test");
    }

    System.out.println("Testing done.");
  }
}
