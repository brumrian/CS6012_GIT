package assignment03;

import assignment03.Archive.LibraryBook;
import assignment03.Archive.LibraryBookGeneric;
import assignment03.Archive.LibraryGeneric;
import assignment03.Archive.PhoneNumber;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

class NaturalOrderBinarySearchSetTest {

    //Create Empty Set
    BinarySearchSet<Float> emptySet = new BinarySearchSet<>();
    float tofind = 5.5F;

    //Create String Set
    BinarySearchSet<String> stringSet = new BinarySearchSet<>();
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> stringsNotInSet = new ArrayList<>();

    //Create Integer Set
    BinarySearchSet<Integer> intSet = new BinarySearchSet<>();
    ArrayList<Integer> ints = new ArrayList<>();
    ArrayList<Integer> intsNotInSet = new ArrayList<>();





    @BeforeEach
    public void setup(){

        /* String Test Setup */

        strings.add("Banana");
        strings.add("Apple");
        strings.add("Teeth");
        strings.add("Running");
        strings.add("Zoo");
        strings.add("Friendly");
        strings.add("Quick");
        strings.add("Car");


        stringSet.addAll(strings);

        stringsNotInSet.add("Hello");
        stringsNotInSet.add("Not");
        stringsNotInSet.add("Joke");
        stringsNotInSet.add("Yes");
        stringsNotInSet.add("Funny");

        /* Integer Test Setup */


        ints.add(-1000);
        ints.add(-100);
        ints.add(50);
        ints.add(0);
        ints.add(37);
        ints.add(500);
        ints.add(3000);
        ints.add(-21);
        ints.add(77);
        ints.add(-300);


        intSet.addAll(ints);

        intsNotInSet.add(499);
        intsNotInSet.add(53);
        intsNotInSet.add(550);
        intsNotInSet.add(-77);
        intsNotInSet.add(-3000);


    }

    @AfterEach
    public void breakDown(){

        stringSet = null;
        strings = null;
        stringsNotInSet = null;

        intSet = null;
        ints = null;
        intsNotInSet = null;


    }





    @Test
    void driver() {

        Assert.assertEquals(emptySet.driver(0,0, tofind ), 0);

    }

    @Test
    void first() {

        Assert.assertEquals(stringSet.first(), "Apple");
        Assert.assertEquals(intSet.first(), ints.get(0));

    }

    @Test
    void last() {
        Assert.assertEquals(stringSet.last(), "Zoo");
        Assert.assertEquals(intSet.last(), ints.get(6));
    }

    @Test
    void add() {

        //String Tests
        Assert.assertFalse(stringSet.add("Banana") && stringSet.add("Apple") && stringSet.add("Teeth")
                && stringSet.add("Running") && stringSet.add("Zoo") && stringSet.add("Friendly")
                && stringSet.add("Quick") && stringSet.add("Car"));

        Assert.assertTrue((stringSet.add(stringsNotInSet.get(0)) && stringSet.add(stringsNotInSet.get(1)) && stringSet.add(stringsNotInSet.get(2))
                && stringSet.add(stringsNotInSet.get(3)) && stringSet.add(stringsNotInSet.get(4))));

        //Integer Tests
        Assert.assertFalse(intSet.add(-1000) && intSet.add(-100) && intSet.add(50) && intSet.add(0) && intSet.add(37)
                && intSet.add(500) && intSet.add(3000) && intSet.add(-21) && intSet.add(77) && intSet.add(-100));

        Assert.assertTrue(intSet.add(intsNotInSet.get(0)) && intSet.add(intsNotInSet.get(1)) && intSet.add(intsNotInSet.get(2))
                && intSet.add(intsNotInSet.get(3)) && intSet.add(intsNotInSet.get(4)));


    }

    @Test
    void addAll() {

        //String Tests
        Assert.assertFalse(stringSet.addAll(strings));
        Assert.assertTrue(stringSet.addAll(stringsNotInSet));

        //Integer tests
        Assert.assertFalse(intSet.addAll(ints));
        Assert.assertTrue(intSet.addAll(intsNotInSet));


    }

    @Test
    void clear() {

        //String Tests
        stringSet.clear();
        Assert.assertEquals(stringSet.size(), 0);

        //Integer Tests
        intSet.clear();
        Assert.assertEquals(intSet.size(), 0);

    }

    @Test
    void contains() {


        //String Tests
        Assert.assertTrue(stringSet.contains("Banana") && stringSet.contains("Apple") && stringSet.contains("Teeth")
                && stringSet.contains("Running") && stringSet.contains("Zoo") && stringSet.contains("Friendly")
                && stringSet.contains("Quick") || stringSet.contains("Car"));

        Assert.assertFalse(stringSet.contains(stringsNotInSet.get(0)) && stringSet.contains(stringsNotInSet.get(1))
                && stringSet.contains(stringsNotInSet.get(2)) && stringSet.contains(stringsNotInSet.get(3))
                && stringSet.contains(stringsNotInSet.get(4)));


        //Integer Tests
        Assert.assertTrue(intSet.contains(-1000) && intSet.contains(-100) && intSet.contains(50) && intSet.contains(0) || !intSet.contains(37)
                && intSet.contains( 500) && intSet.contains(3000) && intSet.contains(-21) && intSet.contains(77) && intSet.contains(-100));

        Assert.assertFalse(ints.contains(intsNotInSet.get(0)) && intSet.contains(intsNotInSet.get(1)) && intSet.contains(intsNotInSet.get(2))
                && intSet.contains(intsNotInSet.get(3)) && intSet.contains(intsNotInSet.get(4)));

    }





    @Test
    void isEmpty() {

        Assert.assertFalse(stringSet.isEmpty());
        Assert.assertFalse(intSet.isEmpty());
//        Assert.assertFalse(libraryBookSet.isEmpty());

        Assert.assertTrue(emptySet.isEmpty());

    }

    @Test
    void remove() {

        //String Tests
        Assert.assertFalse(stringSet.remove("NotInSet"));
        Assert.assertTrue(stringSet.remove(strings.get(0)));
        Assert.assertFalse(stringSet.contains(strings.get(0)));

        //Integer Tests
        int sizeBefore = intSet.size();
        Assert.assertFalse(intSet.remove(75));
        Assert.assertTrue(intSet.remove(ints.get(0)));
        int sizeAfter = intSet.size();
        Assert.assertFalse(intSet.contains(ints.get(0)));
        Assert.assertTrue((sizeBefore - 1) == sizeAfter);

    }

    @Test
    void size() {

        //String Tests
        Assert.assertEquals(stringSet.size(), 8);

        //Integer Tests
        Assert.assertEquals(intSet.size(), 10);


    }

    @Test
    void toArray() {
        //String Tests
        Object[] expectedStringArray = new Object[]{ "Apple", "Banana", "Car", "Friendly", "Quick", "Running", "Teeth", "Zoo"};
        Assert.assertArrayEquals(expectedStringArray, stringSet.toArray());

        //Integer Tests
        Object[] expectedIntegerArray = new Object[]{-1000, -300, -100, -21, 0, 37, 50, 77, 500, 3000};
        Assert.assertArrayEquals(expectedIntegerArray, intSet.toArray());

    }

    @Test
    void removeAll() {

        //String Tests
        Assert.assertTrue(stringSet.removeAll(strings));
        Assert.assertFalse(stringSet.removeAll(stringsNotInSet));
        Assert.assertEquals(stringSet.size(), 0);

        //Integer Tests
        Assert.assertTrue(intSet.removeAll((ints)));
        Assert.assertFalse(stringSet.removeAll(intsNotInSet));
        Assert.assertEquals(intSet.size(), 0);

    }

    @Test
    void containsAll() {
        //String Tests
       Assert.assertTrue(stringSet.containsAll(strings));
       Assert.assertFalse(stringSet.containsAll(stringsNotInSet));

       //Integer Tests
       Assert.assertTrue(intSet.containsAll(ints));
       Assert.assertFalse(intSet.containsAll(intsNotInSet));

    }

    @Test
    void iterator(){
        Iterator<Integer> i = intSet.iterator();

        while(i.hasNext()){
            if(i.next() == 77){
                i.remove();
            }
        }

        Assert.assertFalse(intSet.contains(77));
        Assert.assertEquals(intSet.size(), 9);
    }

    @Test
    void testIterableThrowsException(){
        Iterator<Integer> i = intSet.iterator();

        Assert.assertThrows(IllegalStateException.class, () -> i.remove());

        i.next();
        i.remove();

        Assert.assertThrows(IllegalStateException.class, () -> i.remove());



    }


}