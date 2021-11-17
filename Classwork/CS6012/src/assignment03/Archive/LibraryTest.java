package assignment03.Archive;
//Rian Brumfield

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
    //TESTS BELOW ADDED BY RIAN

    lib.addAll("src/assignment02/Mushroom_Publishing.txt");

    long firstBookISBN = 9781843190394L;
    if(lib.lookup(firstBookISBN) != null){
      System.err.println("TEST FAILED -- medium library: lookup(isbn)");
    }
    long lastBookISBN = 9781843193319L;
    if(lib.lookup(lastBookISBN) != null){
      System.err.println("TEST FAILED -- medium library: lookup(isbn)");
    }

    //books not yet checked out should return true
    if(lib.checkout(9781843190394L, "Rian", 12, 15, 2021) == false){
      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
    }
    if(lib.checkout(9781843190998L, "Rian", 12, 15, 2021) == false){
      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
    }
    if(lib.checkout(9781843190677L, "Rian", 12, 15, 2021) == false){
      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
    }
    if(lib.checkout(9781843190349L, "Rian", 12, 15, 2021) == false){
      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
    }

    //book already checked out should return false
    if(lib.checkout(9781843190998L, "Rian", 12, 15, 2021) != false){
      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
    }

    ArrayList<LibraryBook> riansBooks = lib.lookup("Rian");

    if(riansBooks.size() != 4){
      System.err.println("TEST FAILED -- medium library: lookup(holder)");
    }
    if(!riansBooks.get(0).equals(new LibraryBook(9781843190349L,	"Esme Ellis", "Pathway Into Sunrise"))){
      System.err.println("TEST FAILED -- medium library: lookup(holder)");
    }

    //check that the holder was properly set when checked in
    for(LibraryBook lb: riansBooks){
      if(!lb.getHolder().equals("Rian")){
        System.err.println("TEST FAILED -- medium library: checkout(isbn)");

      }
    }

    //this book is not checked out thus should not be able to be checked in
    if(lib.checkin(9781843190516L) != false){
      System.err.println("TEST FAILED -- medium library: checkin(isbn)");

    }

    //check in a book that has been checked in
    if(lib.checkin(9781843190998L) != true){
      System.err.println("TEST FAILED -- medium library: checkin(isbn)");

    }

    //Rian has books checked out so should be able to return books
    if(!lib.checkin("Rian")){
      System.err.println("TEST FAILED -- medium library: checkin(isbn)");

    }
    //check in all the books
    lib.checkin("Rian");

    //Rian should not be able to check in books because she has none checked out
    if(lib.checkin("Rian")){
      System.err.println("TEST FAILED -- medium library: checkin(isbn)");

    }









    // FILL IN

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
