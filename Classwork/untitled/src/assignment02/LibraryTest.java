package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * 
 */
public class LibraryTest {

  public static void main(String[] args) {
    // test an empty library
    Library lib = new Library();

    if (lib.lookup(978037429279L) != null)
      System.err.println("TEST FAILED -- empty library: lookup(isbn)");
    ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
    if (booksCheckedOut == null || booksCheckedOut.size() != 0)
      System.err.println("TEST FAILED -- empty library: lookup(holder)");
    if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
      System.err.println("TEST FAILED -- empty library: checkout");
    if (lib.checkin(978037429279L))
      System.err.println("TEST FAILED -- empty library: checkin(isbn)");
    if (lib.checkin("Jane Doe"))
      System.err.println("TEST FAILED -- empty library: checkin(holder)");

    // test a small library
    lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib.add(9780446580342L, "David Baldacci", "Simple Genius");

    if (lib.lookup(9780330351690L) != null)
      System.err.println("TEST FAILED -- small library: lookup(isbn)");
    if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
      System.err.println("TEST FAILED -- small library: checkout");
    booksCheckedOut = lib.lookup("Jane Doe");
    if (booksCheckedOut == null || booksCheckedOut.size() != 1
        || !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
        || !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
        || !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED -- small library: lookup(holder)");
    if (!lib.checkin(9780330351690L))
      System.err.println("TEST FAILED -- small library: checkin(isbn)");
    if (lib.checkin("Jane Doe"))
      System.err.println("TEST FAILED -- small library: checkin(holder)");

    // test a medium library
    lib.addAll("src/assignment02/Mushroom_Publishing.txt");
    // This IBSN does not exist, so the lookup should be null
    if (lib.lookup(9781843194444L) != null)
      System.err.println("TEST FAILED -- medium library: lookup(isbn)");

    // None of the following books have been checked out and they all exist in the library, so these statements should return true
    // Tested the first ISBN, the last ISBN, a middle ISBN, and a nonexistent ISBN in the .txt file to make sure all books are being accounted for
    if (!lib.checkout(9781843190677L, "Malila Freeman", 11, 15, 2021))
      System.err.println("TEST FAILED -- medium library: checkout (middle ISBN)");
    if (!lib.checkout(9781843190004L, "Taylor Swift", 11, 15, 2021))
      System.err.println("TEST FAILED -- medium library: checkout (first ISBN)");
    if (!lib.checkout(9781843193319L, "Malila Freeman", 1, 6, 2030))
      System.err.println("TEST FAILED -- medium library: checkout (last ISBN)");
    if (lib.checkout(9781843194444L, "Scooby Doo", 1, 1, 1999))
      System.err.println("TEST FAILED - medium library: checkout (nonexistent ISBN)");

    // Make an arraylist of all the books checked out for one holder - Malila Freeman (should have 2 books checkout out)
    ArrayList<LibraryBook> booksCheckedOutMed = lib.lookup("Malila Freeman");
    if (booksCheckedOutMed == null || booksCheckedOutMed.size() != 2
            // Correct books in the array at positions 0 and 1
        || !booksCheckedOutMed.get(0).equals(new Book(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"))
        || !booksCheckedOutMed.get(1).equals(new Book(9781843193319L, "Alan Burt Akers", "Transit to Scorpio"))
            // Correct holders for the books in the array at positions 0 and 1
        || !booksCheckedOutMed.get(0).getHolder().equals("Malila Freeman")
        || !booksCheckedOutMed.get(1).getHolder().equals("Malila Freeman")
            // Correct due dates for the books in the array at positions 0 and 1
        || !booksCheckedOutMed.get(0).getDueDate().equals(new GregorianCalendar(2021, 11, 15))
        || !booksCheckedOutMed.get(1).getDueDate().equals(new GregorianCalendar(2030, 1, 6)))
      System.err.println("TEST FAILED -- medium library: lookup(holder)");

    // Should be able to check in the first 3 ISBNs but not the last 2 (4th has already been checked in, 5th is nonexistent)
    if ( !lib.checkin(9781843190677L)
        || !lib.checkin(9781843190004L)
        || !lib.checkin(9781843193319L)
        || lib.checkin(9781843190677L)
        || lib.checkin(9781843194444L))
      System.err.println("TEST FAILED -- medium library: checkin(isbn)");

    // These books have already been checked in, so should not be able to check them in again
    if (lib.checkin("Malila Freeman")
        || lib.checkin("Taylor Swift"))
      System.err.println("TEST FAILED -- medium library: checkin(holder)");

    System.out.println("Testing done.");
  }

  /**
   * Returns a library of "dummy" books (random ISBN and placeholders for author
   * and title).
   * 
   * Useful for collecting running times for operations on libraries of varying
   * size.
   * 
   * @param size
   *          -- size of the library to be generated
   */
  public static ArrayList<LibraryBook> generateLibrary(int size) {
    ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

    for (int i = 0; i < size; i++) {
      // generate random ISBN
      Random randomNumGen = new Random();
      String isbn = "";
      for (int j = 0; j < 13; j++)
        isbn += randomNumGen.nextInt(10);

      result.add(new LibraryBook(Long.parseLong(isbn), "An author", "A title"));
    }

    return result;
  }

  /**
   * Returns a randomly-generated ISBN (a long with 13 digits).
   * 
   * Useful for collecting running times for operations on libraries of varying
   * size.
   */
  public static long generateIsbn() {
    Random randomNumGen = new Random();

    String isbn = "";
    for (int j = 0; j < 13; j++)
      isbn += randomNumGen.nextInt(10);

    return Long.parseLong(isbn);
  }
}
