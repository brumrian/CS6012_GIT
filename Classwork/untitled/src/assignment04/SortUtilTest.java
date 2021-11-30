package assignment04;

import assignment02.LibraryBookGeneric;
import assignment02.LibraryGeneric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

    private ArrayList<Integer> sizeZeroArray;

    private ArrayList<Integer> intSizeOne;
    private ArrayList<Integer> intSizeOneNeg;
    private ArrayList<Integer> intMixedArrayEvenSize;
    private ArrayList<Integer> intMixedArrayOddSize;
    private ArrayList<Integer> intAllNegs;
    private ArrayList<Integer> intMultipleOfSame;
    private ArrayList<Integer> intWithMaxAndMin;

    private ArrayList<String> stringSizeOne;
    private ArrayList<String> stringMixedArrayEvenSize;
    private ArrayList<String> stringMixedArrayOddSize;
    private ArrayList<String> stringMultipleOfSame;

    private ArrayList<LibraryBookGeneric<String>> books;
    private ArrayList<LibraryBookGeneric<String>> booksNotInSet;

    private LibraryGeneric<String> library;
    private LibraryGeneric<String> libraryNotInSet;

    // Comparator to use for strings and ints
    Comparator myComparator = Comparator.naturalOrder();

    @BeforeEach
    public void setup(){

        // For testing array of size zero

        sizeZeroArray = new ArrayList<>(0);

        // For testing int arrays

        intSizeOne = new ArrayList<>(1);
        intSizeOne.add(7);

        intSizeOneNeg = new ArrayList<>(1);
        intSizeOneNeg.add(-100);

        intMixedArrayEvenSize = new ArrayList<>(10);
        intMixedArrayEvenSize.add(10);
        intMixedArrayEvenSize.add(-4);
        intMixedArrayEvenSize.add(0);
        intMixedArrayEvenSize.add(25);
        intMixedArrayEvenSize.add(-2);
        intMixedArrayEvenSize.add(120);
        intMixedArrayEvenSize.add(-346);
        intMixedArrayEvenSize.add(432);
        intMixedArrayEvenSize.add(1);
        intMixedArrayEvenSize.add(-128);

        intMixedArrayOddSize = new ArrayList<>(9);
        intMixedArrayOddSize.add(-4);
        intMixedArrayOddSize.add(0);
        intMixedArrayOddSize.add(25);
        intMixedArrayOddSize.add(-2);
        intMixedArrayOddSize.add(120);
        intMixedArrayOddSize.add(-346);
        intMixedArrayOddSize.add(432);
        intMixedArrayOddSize.add(1);
        intMixedArrayOddSize.add(-128);

        intAllNegs = new ArrayList<>(5);
        intAllNegs.add(-32);
        intAllNegs.add(-1);
        intAllNegs.add(-1000);
        intAllNegs.add(-743);
        intAllNegs.add(-57);

        intMultipleOfSame = new ArrayList<>(5);
        intMultipleOfSame.add(10);
        intMultipleOfSame.add(5);
        intMultipleOfSame.add(10);
        intMultipleOfSame.add(10);
        intMultipleOfSame.add(5);

        intWithMaxAndMin = new ArrayList<>(5);
        intWithMaxAndMin.add(Integer.MAX_VALUE);
        intWithMaxAndMin.add(0);
        intWithMaxAndMin.add(52);
        intWithMaxAndMin.add(Integer.MIN_VALUE);
        intWithMaxAndMin.add(-7);

        // For testing ArrayLists of strings

        stringSizeOne = new ArrayList<>(1);
        stringSizeOne.add("hello");

        stringMixedArrayEvenSize = new ArrayList<>(10);
        stringMixedArrayEvenSize.add("throw");
        stringMixedArrayEvenSize.add("Ball");
        stringMixedArrayEvenSize.add("zebra");
        stringMixedArrayEvenSize.add("super");
        stringMixedArrayEvenSize.add("Super");
        stringMixedArrayEvenSize.add("giraffe");
        stringMixedArrayEvenSize.add("crazy");
        stringMixedArrayEvenSize.add("for real");
        stringMixedArrayEvenSize.add("xylophone");
        stringMixedArrayEvenSize.add("Oblong");

        stringMixedArrayOddSize = new ArrayList<>(9);
        stringMixedArrayOddSize.add("throw");
        stringMixedArrayOddSize.add("Ball");
        stringMixedArrayOddSize.add("zebra");
        stringMixedArrayOddSize.add("super");
        stringMixedArrayOddSize.add("giraffe");
        stringMixedArrayOddSize.add("crazy");
        stringMixedArrayOddSize.add("for real");
        stringMixedArrayOddSize.add("xylophone");
        stringMixedArrayOddSize.add("Oblong");

        stringMultipleOfSame = new ArrayList<>(5);
        stringMultipleOfSame.add("wonder");
        stringMultipleOfSame.add("Plot");
        stringMultipleOfSame.add("wonder");
        stringMultipleOfSame.add("wonder");
        stringMultipleOfSame.add("Plot");

        // For testing a different type of object

        // So that we can get the comparator from the LibraryGeneric class
        library = new LibraryGeneric<String>();
        // Make another library with a smaller set of books
        libraryNotInSet = new LibraryGeneric<String>();

        // Make ArrayLists to use for testing with books
        books = new ArrayList<LibraryBookGeneric<String>>();
        booksNotInSet = new ArrayList<LibraryBookGeneric<String>>();

        // Add the long list of books from text file to the library
        library.addAll("src/assignment02/Mushroom_Publishing.txt");

        // Add 3 books (smaller set) to the libraryNotInSet library
        libraryNotInSet.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        libraryNotInSet.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        libraryNotInSet.add(9780446580342L, "David Baldacci", "Simple Genius");

        // set ArrayList equal to books in library
        books = library.getInventoryList();
        // set ArrayList equal to books in libraryNotInSet
        booksNotInSet = libraryNotInSet.getInventoryList();
    }

    @Test
    void sizeZeroMerge(){

        SortUtil.mergesort(sizeZeroArray, myComparator);

        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(1));

    }

    @Test
    void sizeZeroQuick(){

        SortUtil.quicksort(sizeZeroArray, myComparator);

        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sizeZeroArray.get(1));

    }

    @Test
    void intSizeOneMerge(){

        SortUtil.mergesort(intSizeOne, myComparator);
        SortUtil.mergesort(intSizeOneNeg, myComparator);

        int[] test = {7};
        int[] negTest = {-100};

        assertEquals(intSizeOne.get(0), test[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> intSizeOne.get(1));

        assertEquals(intSizeOneNeg.get(0), negTest[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> intSizeOneNeg.get(1));
    }

    @Test
    void intSizeOneQuick(){

        SortUtil.quicksort(intSizeOne, myComparator);
        SortUtil.quicksort(intSizeOneNeg, myComparator);

        int[] test = {7};
        int[] negTest = {-100};

        assertEquals(intSizeOne.get(0), test[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> intSizeOne.get(1));

        assertEquals(intSizeOneNeg.get(0), negTest[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> intSizeOneNeg.get(1));
    }

    @Test
    void intMixedArrayEvenMerge(){
        SortUtil.mergesort(intMixedArrayEvenSize, myComparator);

        int[] even = {-346, -128, -4, -2, 0, 1, 10, 25, 120, 432};

        for (int i = 0; i < intMixedArrayEvenSize.size(); i++){
            assertEquals(intMixedArrayEvenSize.get(i), even[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intMixedArrayEvenSize.get(10));

    }

    @Test
    void intMixedArrayEvenQuick(){
        SortUtil.quicksort(intMixedArrayEvenSize, myComparator);

        int[] even = {-346, -128, -4, -2, 0, 1, 10, 25, 120, 432};

        for (int i = 0; i < intMixedArrayEvenSize.size(); i++){
            assertEquals(intMixedArrayEvenSize.get(i), even[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intMixedArrayEvenSize.get(10));

    }

    @Test
    void intMixedArrayOddMerge(){
        SortUtil.mergesort(intMixedArrayOddSize, myComparator);
        int[] odd = {-346, -128, -4, -2, 0, 1, 25, 120, 432};

        for (int i = 0; i < intMixedArrayOddSize.size(); i++){
            assertEquals(intMixedArrayOddSize.get(i), odd[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intMixedArrayOddSize.get(9));
    }

    @Test
    void intMixedArrayOddQuick(){
        SortUtil.quicksort(intMixedArrayOddSize, myComparator);
        int[] odd = {-346, -128, -4, -2, 0, 1, 25, 120, 432};

        for (int i = 0; i < intMixedArrayOddSize.size(); i++){
            assertEquals(intMixedArrayOddSize.get(i), odd[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intMixedArrayOddSize.get(9));
    }

    @Test
    void intAllNegsMerge(){

        SortUtil.mergesort(intAllNegs, myComparator);

        int[] negs = {-1000, -743, -57, -32, -1};

        for (int i = 0; i < intAllNegs.size(); i++){
            assertEquals(intAllNegs.get(i), negs[i]);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> intAllNegs.get(5));
    }

    @Test
    void intAllNegsQuick(){

        SortUtil.quicksort(intAllNegs, myComparator);

        int[] negs = {-1000, -743, -57, -32, -1};

        for (int i = 0; i < intAllNegs.size(); i++){
            assertEquals(intAllNegs.get(i), negs[i]);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> intAllNegs.get(5));
    }

    @Test
    void intMultipleOfSameMerge(){

        SortUtil.mergesort(intMultipleOfSame, myComparator);

        int[] multiples = {5, 5, 10, 10, 10};

        for (int i = 0; i < intMultipleOfSame.size(); i++){
            assertEquals(intMultipleOfSame.get(i), multiples[i]);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> intMultipleOfSame.get(5));
    }

    @Test
    void intMultipleOfSameQuick(){

        SortUtil.quicksort(intMultipleOfSame, myComparator);

        int[] multiples = {5, 5, 10, 10, 10};

        for (int i = 0; i < intMultipleOfSame.size(); i++){
            assertEquals(intMultipleOfSame.get(i), multiples[i]);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> intMultipleOfSame.get(5));
    }

    @Test
    public void intWithMaxAndMinMerge(){

        SortUtil.mergesort(intWithMaxAndMin, myComparator);

        int[] maxAndMin = {Integer.MIN_VALUE, -7, 0, 52, Integer.MAX_VALUE};


        for (int i = 0; i < intWithMaxAndMin.size(); i++){
            assertEquals(intWithMaxAndMin.get(i), maxAndMin[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intWithMaxAndMin.get(5));
    }

    @Test
    public void intWithMaxAndMinQuick(){

        SortUtil.quicksort(intWithMaxAndMin, myComparator);

        int[] maxAndMin = {Integer.MIN_VALUE, -7, 0, 52, Integer.MAX_VALUE};


        for (int i = 0; i < intWithMaxAndMin.size(); i++){
            assertEquals(intWithMaxAndMin.get(i), maxAndMin[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> intWithMaxAndMin.get(5));
    }

    @Test
    void stringSizeOneMerge(){

        SortUtil.mergesort(stringSizeOne, myComparator);

        String[] test = {"hello"};

        assertEquals(stringSizeOne.get(0), test[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> stringSizeOne.get(1));

    }

    @Test
    void stringSizeOneQuick(){

        SortUtil.quicksort(stringSizeOne, myComparator);

        String[] test = {"hello"};

        assertEquals(stringSizeOne.get(0), test[0]);
        assertThrows(IndexOutOfBoundsException.class, () -> stringSizeOne.get(1));

    }

    @Test
    void stringMixedArrayEvenMerge(){

        SortUtil.mergesort(stringMixedArrayEvenSize, myComparator);

        String[] test = {"Ball", "Oblong", "Super", "crazy", "for real", "giraffe", "super", "throw", "xylophone", "zebra"};

        for (int i = 0; i < stringMixedArrayEvenSize.size(); i++){
            assertEquals(stringMixedArrayEvenSize.get(i), test[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMixedArrayEvenSize.get(10));
    }

    @Test
    void stringMixedArrayEvenQuick(){

        SortUtil.quicksort(stringMixedArrayEvenSize, myComparator);

        String[] test = {"Ball", "Oblong", "Super", "crazy", "for real", "giraffe", "super", "throw", "xylophone", "zebra"};

        for (int i = 0; i < stringMixedArrayEvenSize.size(); i++){
            assertEquals(stringMixedArrayEvenSize.get(i), test[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMixedArrayEvenSize.get(10));
    }

    @Test
    void stringMixedArrayOddMerge(){

        SortUtil.mergesort(stringMixedArrayOddSize, myComparator);

        String[] test = {"Ball", "Oblong", "crazy", "for real", "giraffe", "super", "throw", "xylophone", "zebra"};

        for (int i = 0; i < stringMixedArrayOddSize.size(); i++){
            assertEquals(stringMixedArrayOddSize.get(i), test[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMixedArrayOddSize.get(10));
    }

    @Test
    void stringMixedArrayOddQuick(){

        SortUtil.quicksort(stringMixedArrayOddSize, myComparator);

        String[] test = {"Ball", "Oblong", "crazy", "for real", "giraffe", "super", "throw", "xylophone", "zebra"};

        for (int i = 0; i < stringMixedArrayOddSize.size(); i++){
            assertEquals(stringMixedArrayOddSize.get(i), test[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMixedArrayOddSize.get(10));
    }

    @Test
    void stringMultipleOfSameMerge(){

        SortUtil.mergesort(stringMultipleOfSame, myComparator);

        String[] multiple = {"Plot", "Plot", "wonder", "wonder", "wonder"};

        for (int i = 0; i < stringMultipleOfSame.size(); i++){
            assertEquals(stringMultipleOfSame.get(i), multiple[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMultipleOfSame.get(5));
    }

    @Test
    void stringMultipleOfSameQuick(){

        SortUtil.quicksort(stringMultipleOfSame, myComparator);

        String[] multiple = {"Plot", "Plot", "wonder", "wonder", "wonder"};

        for (int i = 0; i < stringMultipleOfSame.size(); i++){
            assertEquals(stringMultipleOfSame.get(i), multiple[i]);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> stringMultipleOfSame.get(5));
    }

    @Test
    void bookObjectShortArrayMerge(){

        ArrayList<LibraryBookGeneric<String>> temp = new ArrayList<>(3);
        temp.add(booksNotInSet.get(2));
        temp.add(booksNotInSet.get(0));
        temp.add(booksNotInSet.get(1));

        // The comparator should sort each book by author
        SortUtil.mergesort(booksNotInSet, libraryNotInSet.getOrderByAuthorComparator());

        for (int i = 0; i < booksNotInSet.size(); i++){
            assertEquals(booksNotInSet.get(i), temp.get(i));
        }

        assertThrows(IndexOutOfBoundsException.class, () -> booksNotInSet.get(3));
    }

    @Test
    void bookObjectShortArrayQuick(){

        ArrayList<LibraryBookGeneric<String>> temp = new ArrayList<>(3);
        temp.add(booksNotInSet.get(2));
        temp.add(booksNotInSet.get(0));
        temp.add(booksNotInSet.get(1));

        // The comparator should sort each book by author
        SortUtil.quicksort(booksNotInSet, libraryNotInSet.getOrderByAuthorComparator());

        for (int i = 0; i < booksNotInSet.size(); i++){
            assertEquals(booksNotInSet.get(i), temp.get(i));
        }

        assertThrows(IndexOutOfBoundsException.class, () -> booksNotInSet.get(3));
    }

    @Test
    void bookObjectLongArrayMerge(){
        ArrayList<LibraryBookGeneric<String>> temp = new ArrayList<>(5);
        // This book should be first in sorted array
        temp.add(books.get(22));
        // This book should be last in sorted array
        temp.add(books.get(19));
        // This book should be 13th in the sorted array (~middle)
        temp.add(books.get(8));
        // This book should be 6th in the sorted array
        temp.add(books.get(5));
        // This book should be 18th in the sorted array
        temp.add(books.get(20));

        // The comparator should sort each book by author
        SortUtil.mergesort(books, library.getOrderByAuthorComparator());

        assertEquals(books.get(0), temp.get(0));
        assertEquals(books.get(books.size() - 1), temp.get(1));
        assertEquals(books.get(12), temp.get(2));
        assertEquals(books.get(5), temp.get(3));
        assertEquals(books.get(17), temp.get(4));

        assertThrows(IndexOutOfBoundsException.class, () -> books.get(23));

    }

    @Test
    void bookObjectLongArrayQuick(){
        ArrayList<LibraryBookGeneric<String>> temp = new ArrayList<>(3);
        // This book should be first in sorted array
        temp.add(books.get(22));
        // This book should be last in sorted array
        temp.add(books.get(19));
        // This book should be 13th in the sorted array (~middle)
        temp.add(books.get(8));
        // This book should be 6th in the sorted array
        temp.add(books.get(5));
        // This book should be 18th in the sorted array
        temp.add(books.get(20));

        // The comparator should sort each book by author
        SortUtil.quicksort(books, library.getOrderByAuthorComparator());

        assertEquals(books.get(0), temp.get(0));
        assertEquals(books.get(books.size() - 1), temp.get(1));
        assertEquals(books.get(12), temp.get(2));
        assertEquals(books.get(5), temp.get(3));
        assertEquals(books.get(17), temp.get(4));

        assertThrows(IndexOutOfBoundsException.class, () -> books.get(23));

    }

    @Test
    void createBestCase(){
        ArrayList<Integer> best = SortUtil.generateBestCase(20);

        assertEquals(best.size(), 20);

        for (int i = 0; i < best.size(); i++){
            assertEquals(best.get(i), i);
        }
    }

    @Test
    void createAverageCase(){
        ArrayList<Integer> average = SortUtil.generateAverageCase(20);

        assertEquals(average.size(), 20);

        for (int i = 0; i < average.size(); i++){
            System.out.println("average: " + average.get(i));
        }
    }

    @Test
    void createWorstCase(){

        ArrayList<Integer> worst = SortUtil.generateWorstCase(20);

        assertEquals(worst.size(), 20);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(19);
        expected.add(18);
        expected.add(17);
        expected.add(16);
        expected.add(15);
        expected.add(14);
        expected.add(13);
        expected.add(12);
        expected.add(11);
        expected.add(10);
        expected.add(9);
        expected.add(8);
        expected.add(7);
        expected.add(6);
        expected.add(5);
        expected.add(4);
        expected.add(3);
        expected.add(2);
        expected.add(1);
        expected.add(0);

        for (int i = 0; i < worst.size(); i++){
            assertEquals(worst.get(i), expected.get(i));
        }
    }

}