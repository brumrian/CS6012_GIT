package lab05;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    DoublyLinkedList<Integer> intList;
    DoublyLinkedList<String> stringList;

    @BeforeEach
    public void setup(){

        /* Integer List Setup*/
        intList = new DoublyLinkedList<>();

        intList.add(0, 0);
        intList.add(1, 1);
        intList.add(2, 2);
        intList.add(3, 3);


        stringList = new DoublyLinkedList<>();

        stringList.add(0, "Apple");
        stringList.add(1, "Banana");
        stringList.add(2, "Candy");
        stringList.add(3, "Daddy");


    }
    @AfterEach
    public void breakDown(){

        intList = null;
        stringList = null;

    }


    @Test
    void contains() {

        Assert.assertTrue(intList.contains(2));
        intList.add(3, 99);
        Assert.assertTrue(intList.contains(99));
        intList.remove(3);
        Assert.assertFalse(intList.contains(99));


        Assert.assertTrue(stringList.contains("Daddy"));
        stringList.add(1, "Yellow");
        Assert.assertTrue(stringList.contains("Yellow"));
        stringList.remove(1);
        Assert.assertFalse(stringList.contains("Yellow"));


    }

    @Test
    void addFirst() {

        intList.addFirst(-1);
        Assert.assertTrue(intList.contains(-1));
        Assert.assertEquals(intList.get(0), -1);

        intList.addFirst(-2);
        Assert.assertTrue(intList.contains(-2));
        Assert.assertEquals(intList.get(0), -2);
        Assert.assertEquals(intList.get(1), -1);



    }

    @Test
    void add() {



        Assert.assertTrue(intList.contains(0) && intList.contains(1) && intList.contains(2) && intList.contains(3));
        Assert.assertEquals(intList.get(3), 3);



        Assert.assertTrue(stringList.contains("Apple"));
        Assert.assertEquals(stringList.get(2), "Candy");



    }

    @Test
    void getFirst() {

        Assert.assertEquals(intList.getFirst(), 0);

        Assert.assertEquals(stringList.getFirst(), "Apple");
    }

    @Test
    void getLast() {

        Assert.assertEquals(intList.getLast(), 3);

        Assert.assertEquals(stringList.getLast(),"Daddy");
    }

    @Test
    void get() {

        Assert.assertEquals(intList.get(2), 2);

        Assert.assertEquals(stringList.get(1), "Banana");

    }

    @Test
    void removeFirst() {

        intList.removeFirst();
        Assert.assertEquals(intList.get(0), 1);
        Assert.assertFalse(intList.contains(0));

        stringList.removeFirst();
        Assert.assertEquals(stringList.getFirst(), "Banana");
        Assert.assertFalse(stringList.contains("Apple"));




    }

    @Test
    void removeLast() {

        intList.removeLast();
        Assert.assertEquals(intList.getLast(),2);
        Assert.assertFalse(intList.contains(3));

        stringList.removeLast();
        Assert.assertEquals(stringList.getLast(), "Candy");
        Assert.assertFalse(stringList.contains("Daddy"));



    }

    @Test
    void remove() {

        intList.remove(2);
        Assert.assertFalse(intList.contains(2));

        stringList.remove(3);
        Assert.assertFalse(stringList.contains("Daddy"));


    }

    @Test
    void indexOf() {

        Assert.assertEquals(intList.indexOf(0), 0);
        Assert.assertEquals(intList.indexOf(1), 1);
        Assert.assertEquals(intList.indexOf(2), 2);
        Assert.assertEquals(intList.indexOf(3), 3);

        Assert.assertEquals(stringList.indexOf("Apple"), 0);
        Assert.assertEquals(stringList.indexOf("Banana"), 1);
        Assert.assertEquals(stringList.indexOf("Candy"), 2);
        Assert.assertEquals(stringList.indexOf("Daddy"), 3);
    }

    @Test
    void lastIndexOf() {

        intList.addLast(0);
        Assert.assertEquals(intList.lastIndexOf(0), 4);

        stringList.addFirst("Hope");
        stringList.add(1, "Hope");
        Assert.assertEquals(stringList.lastIndexOf("Hope"), 1);

        stringList.addLast("Hope");
        Assert.assertEquals(stringList.size(), 7);
        Assert.assertEquals(stringList.lastIndexOf("Hope"), 6);




    }

    @Test
    void size() {

        Assert.assertEquals(intList.size() , intList.size);
        Assert.assertEquals(intList.size(), 4);

        intList.addFirst(-1);

        Assert.assertEquals(intList.size() , intList.size);
        Assert.assertEquals(intList.size(), 5);


        Assert.assertEquals(stringList.size() , stringList.size);
        Assert.assertEquals(stringList.size(), 4);

        stringList.addLast("Insert");

        Assert.assertEquals(stringList.size() , stringList.size);
        Assert.assertEquals(stringList.size(), 5);


    }

    @Test
    void isEmpty() {

        Assert.assertFalse(intList.isEmpty());
        intList.remove(0);
        intList.remove(0);
        intList.remove(0);
        intList.remove(0);
        Assert.assertTrue(intList.isEmpty());

        Assert.assertFalse(stringList.isEmpty());
        stringList.removeFirst();
        stringList.removeFirst();
        stringList.removeLast();
        stringList.removeLast();
        Assert.assertTrue(stringList.isEmpty());

    }

    @Test
    void clear() {

        intList.clear();
        Assert.assertEquals(intList.size(), 0);
        Assert.assertThrows(NoSuchElementException.class, () -> intList.getFirst());
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> intList.remove(0));



        stringList.clear();
        Assert.assertEquals(stringList.size(), 0);
        Assert.assertThrows(NoSuchElementException.class, () -> stringList.getLast());
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> stringList.remove(0));






    }

    @Test
    void toArray() {

        Integer[] expectedIntegerArray = new Integer[]{0,1,2,3};
        Assert.assertArrayEquals(expectedIntegerArray, intList.toArray());

        String[] expectedStringArray = new String[]{"Apple", "Banana", "Candy", "Daddy"};
        Assert.assertArrayEquals(expectedStringArray, stringList.toArray());

    }
}