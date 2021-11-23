//package assignment02;
////Rian Brumfield
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//
///**
// * Testing class for LibraryGeneric.
// *
// */
//public class LibraryGenericTest {
//
//  public static void main(String[] args) {
//
//    // test a library that uses names (String) to id patrons
//    LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
//    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
//    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
//    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");
//
//    String patron1 = "Jane Doe";
//
//    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
//      System.err.println("TEST FAILED: first checkout");
//    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
//      System.err.println("TEST FAILED: second checkout");
//    ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);
//    if (booksCheckedOut1 == null || booksCheckedOut1.size() != 2
//        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
//        || !booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
//        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
//        || !booksCheckedOut1.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
//        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
//        || !booksCheckedOut1.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
//      System.err.println("TEST FAILED: lookup(holder)");
//    if (!lib1.checkin(patron1))
//      System.err.println("TEST FAILED: checkin(holder)");
//
//
//
//    // test a library that uses phone numbers (PhoneNumber) to id patrons
//    LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
//    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
//    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
//    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");
//
//    PhoneNumber patron2 = new PhoneNumber("801.555.1234");
//
//    if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
//      System.err.println("TEST FAILED: first checkout");
//    if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
//      System.err.println("TEST FAILED: second checkout");
//    ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);
//    if (booksCheckedOut2 == null || booksCheckedOut2.size() != 2
//        || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
//        || !booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
//        || !booksCheckedOut2.get(0).getHolder().equals(patron2)
//        || !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
//        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
//        || !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
//      System.err.println("TEST FAILED: lookup(holder)");
//    if (!lib2.checkin(patron2))
//      System.err.println("TEST FAILED: checkin(holder)");
//
//
//
//    /* Sorting tests added by Rian Brumfield */
//
//    //add books to library two for better testing
//    lib2.addAll("src/assignment02/Mushroom_Publishing.txt");
//
//
//    //test that the inventoryList is ordered from least to greatest based on ISBN
//    ArrayList<LibraryBookGeneric<PhoneNumber>> inventoryTest = lib2.getInventoryList();
//    boolean properlySorted1 = true;
//    for(int i = 0; i < inventoryTest.size() - 1; i++){
//      if(inventoryTest.get(i).getIsbn() > inventoryTest.get(i+1).getIsbn()){
//        properlySorted1 = false;
//      }
//    }
//    if(!properlySorted1){
//      System.err.println("TEST FAILED: getInventoryList not sorted");
//
//    }
//
//
//
//    //Check that the authors are listed from least to greatest
//    ArrayList<LibraryBookGeneric<PhoneNumber>> authorList = lib2.getOrderedByAuthor();
//    boolean properlySorted2 = true;
//    for(int i = 0; i < authorList.size() - 1; i++){
//      int compareAuthor = authorList.get(i).getAuthor().compareTo(authorList.get(i+1).getAuthor());
//      if( compareAuthor == 1){
//        properlySorted2 = false;
//      }
//    }
//    if(!properlySorted2){
//      System.err.println("TEST FAILED: getOrderedByAuthor not sorted");
//
//    }
//
//
//    //create a number to checkout books to test overdue list
//    PhoneNumber rian = new PhoneNumber("208.881.1671");
//
//
//    //Checking out books with diferent due dates to be sorted in
//    if(lib2.checkout(9781843190394L, rian, 11, 10, 2021) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843190998L, rian, 8, 11, 2021) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843190677L, rian, 6, 15, 2015) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843190349L, rian, 11, 15, 2021) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843191230L, rian, 11, 15, 2001) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843190042L, rian, 6, 15, 2015) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843190516L, rian, 3, 20, 1997) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//    if(lib2.checkout(9781843192701L, rian, 10, 15, 2010) == false){
//      System.err.println("TEST FAILED -- medium library: checkout(isbn)");
//    }
//
//
//
//
//
//  //test that the overdue list is ordered by date
//    ArrayList<LibraryBookGeneric<PhoneNumber>> overdueList = lib2.getOverdueList(12,16,2021);
//    boolean properlySorted3 = true;
//    for(int i = 0; i < overdueList.size() - 1; i++){
//
//      int compareDates= overdueList.get(i).getDueDate().compareTo(overdueList.get(i+1).getDueDate());
//      if( compareDates == 1){
//        properlySorted3 = false;
//      }
//    }
//    if(!properlySorted3){
//      System.err.println("TEST FAILED: getOverdueList not sorted");
//
//    }
//
//
//
//
//
//    System.out.println("Testing done.");
//  }
//}
