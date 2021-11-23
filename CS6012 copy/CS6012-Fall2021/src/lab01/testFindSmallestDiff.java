package lab01;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class testFindSmallestDiff {


    private int[] arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9;




    @BeforeAll
    public static void setUpBeforeClass(){


    }
    @BeforeEach
    public void setup(){
        arr1 = new int[0];
        arr2 = new int[] {10, 10, 10};
        arr3 = new int[] {52, 4, -8, 0, -17};
        arr4 = new int[] {-10, -8, -6, -4, -2, 0, 2, 4, 56, 8, 10, 12};
        arr5 = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        arr6 = new int[] {Integer.MAX_VALUE,0};
        arr7 = new int[] {-1000, -100, -80, -97, -57, -55, -17, -6};
        arr8 = new int[] {8};
        arr9 = new int[] {1, 10, 199, 8, 199};
    }
    @AfterEach
    public void tearDown(){
        arr1 = null;
        arr2 = null;
        arr3 = null;

    }
    @AfterAll
    public static void tearDownAfterClass(){

    }

    @Test
    public void emptyArray(){
        assertEquals(-1, DiffUtil.smallestDiff(arr1));

    }

    @Test
    public void allArrayElementsEqual(){
        assertEquals(0, DiffUtil.smallestDiff(arr2));


    }

    @Test
    public void smallRandomArrayElements(){
        assertEquals(4, DiffUtil.smallestDiff(arr3));


    }

    @Test
    public void evenNumberArrayElements(){
        assertEquals(2, DiffUtil.smallestDiff(arr4));


    }

    @Test
    public void maxIntMinIntArrayElements(){
        assertEquals(1, DiffUtil.smallestDiff(arr5));


    }

    @Test
    public void maxIntArrayElements(){
        assertEquals(2147483647, DiffUtil.smallestDiff(arr6));


    }

    @Test
    public void negativeArrayElements(){
        assertEquals(2, DiffUtil.smallestDiff(arr7));


    }


    @Test
    public void sizeOneArrayElement(){
        assertEquals(-1, DiffUtil.smallestDiff(arr8));


    }

    @Test
    public void oneRepeatElementArrayElement(){
        assertEquals(0, DiffUtil.smallestDiff(arr9));


    }






}