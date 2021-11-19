package assignment03;

import assignment03.Archive.LibraryBookGeneric;
import assignment03.Archive.LibraryGeneric;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class UserObjectBinarySearchSetTest {

    //Create Library Set

    BinarySearchSet<LibraryBookGeneric<String>> libraryBookSet;
    ArrayList<LibraryBookGeneric<String>> books;
    LibraryGeneric<String> library;

    LibraryGeneric<String> libNotInSet = new LibraryGeneric<String>();
    ArrayList<LibraryBookGeneric<String>> smallBookCollection;
    ArrayList<LibraryBookGeneric<String>> booksNotInSet;

    @BeforeEach
    private void setup(){

        /* LibraryBookGeneric Test Setup */

        library = new LibraryGeneric<>();
        libraryBookSet = new BinarySearchSet<>(library.getComparator());
//        libraryBookSet = new BinarySearchSet<>();
        library.addAll("src/assignment03/Archive/Mushroom_Publishing.txt");
        books = library.getInventoryList();

        libraryBookSet.addAll(books);

        libNotInSet.add(9780374292799L, "Thomas L. Friedman", "The World is Flat"); //3rd in inventory list
        libNotInSet.add(9780330351690L, "Jon Krakauer", "Into the Wild"); //2nd in inventory list
        libNotInSet.add(9780446580342L, "David Baldacci", "Simple Genius"); //1st in iventory list

        booksNotInSet = libNotInSet.getInventoryList();
        smallBookCollection = libNotInSet.getInventoryList();
    }

    @AfterEach
    private void breakDown(){

        library = null;
        libraryBookSet = null;
        books = null;
        libNotInSet = null;
        booksNotInSet = null;

    }

    @Test
    void add() {

        //Library Book Tests
        Assert.assertFalse(libraryBookSet.add(books.get(0)) && libraryBookSet.add(books.get(1)) && libraryBookSet.add(books.get(2)));
        Assert.assertTrue(libraryBookSet.add(booksNotInSet.get(0)) && libraryBookSet.add(booksNotInSet.get(1)) && libraryBookSet.add(booksNotInSet.get(2)));

    }

    @Test
    void addAll() {


        //Library Book Tests
        Assert.assertFalse(libraryBookSet.addAll(books));
        Assert.assertTrue(libraryBookSet.addAll(booksNotInSet));

    }

    @Test
    void clear() {
        //Library Book Tests
        libraryBookSet.clear();
        Assert.assertEquals(libraryBookSet.size(), 0);
    }

    @Test
    void contains() {
        //Library Book Tests
        Assert.assertTrue(libraryBookSet.contains(books.get(0)) && libraryBookSet.contains(books.get(2)) && libraryBookSet.contains(books.get(4))
                && libraryBookSet.contains(books.get(6)) && libraryBookSet.contains(books.get(8)));
        Assert.assertFalse(libraryBookSet.contains(booksNotInSet.get(0)) && libraryBookSet.contains(booksNotInSet.get(1))
                && libraryBookSet.contains(booksNotInSet.get(2)));
    }

    @Test
    void isEmpty() {

        Assert.assertFalse(libraryBookSet.isEmpty());

    }


    @Test
    void remove() {

        //Library Book Tests
        Assert.assertFalse(libraryBookSet.remove(booksNotInSet.get(0)));
        Assert.assertTrue(libraryBookSet.remove(books.get(10)));
        Assert.assertFalse(libraryBookSet.contains(books.get(10)));

    }

    @Test
    void size() {
        //Library Book Tests
        Assert.assertEquals(libraryBookSet.size(), 23);
    }

    @Test
    void toArray() {

        //Library Book Tests
        libraryBookSet.removeAll(books);
        libraryBookSet.addAll(smallBookCollection);
        Assert.assertFalse(libraryBookSet.containsAll(books));
        Assert.assertTrue(libraryBookSet.containsAll(smallBookCollection));

        Object[] expectedBookArray = new Object[] {smallBookCollection.get(2), smallBookCollection.get(0), smallBookCollection.get(1)};
        Assert.assertArrayEquals(expectedBookArray, libraryBookSet.toArray());

    }

    @Test
    void removeAll() {

        //Library Book Tests
        Assert.assertTrue(libraryBookSet.removeAll(books));
        Assert.assertFalse(libraryBookSet.removeAll(booksNotInSet));
        Assert.assertEquals(libraryBookSet.size() , 0);
    }

    @Test
    void containsAll() {

        //Library Book Tests
        Assert.assertTrue(libraryBookSet.containsAll(books));
        Assert.assertFalse(libraryBookSet.containsAll(booksNotInSet));

    }
}