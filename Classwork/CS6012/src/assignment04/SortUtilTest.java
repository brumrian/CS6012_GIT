package assignment04;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static assignment04.SortUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

    Integer size = 100;
    Comparator comparator;

    @BeforeEach
    public void setup(){
    comparator = Comparator.naturalOrder();
    }


    @Test
    void mergesort() {
    }

    @Test
    void quicksort() {
        ArrayList<Integer> worstCase = generateWorstCase(500);
        ArrayList<Integer> bestCase = generateBestCase(500);
        SortUtil.quickSortDriver(worstCase, comparator);
        Assert.assertEquals(worstCase, bestCase);


        ArrayList<Integer> averageCase = generateAverageCase(500);

        for(Integer i: averageCase){
            System.out.println(i);
        }
        System.out.println("_________________");

        SortUtil.quickSortDriver( averageCase, comparator);
        Assert.assertEquals(averageCase, bestCase);



        SortUtil.quickSortDriver(averageCase, comparator);

        for(Integer i: averageCase){
            System.out.println(i);
        }
        System.out.println("_________________");




    }

    @Test
    void generateBestCaseTest() {

    ArrayList<Integer> bestCase = generateBestCase(size);
    Assert.assertEquals(bestCase.size(), 100);
//    Assert.assertEquals(bestCase.get(0)), 0);
//    Assert.assertEquals(java.util.Optional.ofNullable(bestCase.get(size - 1)), 99);




    }

    @Test
    void generateAverageCaseTest() {
        ArrayList<Integer> arrayList = generateAverageCase(size);
        Assert.assertEquals(arrayList.size(), 100);
    }

    @Test
    void generateWorstCaseTest() {

        ArrayList<Integer> arrayList = generateWorstCase(size);
        Assert.assertEquals(arrayList.size(), 100);


    }
}