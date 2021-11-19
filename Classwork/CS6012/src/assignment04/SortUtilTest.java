package assignment04;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

import static assignment04.SortUtil.generateBestCase;
import static assignment04.SortUtil.generateWorstCase;
import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

    Integer size = 100;


    @Test
    void mergesort() {
    }

    @Test
    void quicksort() {
        ArrayList<Integer> worstCase = generateWorstCase(5);
        Comparator comparator = Comparator.naturalOrder();

        for(Integer i: worstCase){
            System.out.println(i);
        }

        System.out.println("_________________");
        SortUtil.quicksort(worstCase, comparator, 0, 4);

        for(Integer i: worstCase){
            System.out.println(i);
        }
        System.out.println("_________________");


        ArrayList<Integer> bestCase = generateWorstCase(5);

        for(Integer i: bestCase){
            System.out.println(i);
        }
        System.out.println("_________________");


        SortUtil.quicksort(bestCase, comparator, 0, 4);

        for(Integer i: bestCase){
            System.out.println(i);
        }
        System.out.println("_________________");




    }

    @Test
    void generateBestCaseTest() {

    ArrayList<Integer> arrayList = generateBestCase(size);
    Assert.assertEquals(arrayList.size(), 100);


    }

    @Test
    void generateAverageCaseTest() {
    }

    @Test
    void generateWorstCaseTest() {

        ArrayList<Integer> arrayList = generateWorstCase(size);
        Assert.assertEquals(arrayList.size(), 100);


    }
}