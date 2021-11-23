/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class MatrixJUnitTester {

  Matrix threeByThree, threeByTwo, threeByTwo2, twoByThree, twoByTwoResult, twoByTwo1, twoByTwo2, twoByTwo3, twoByTwoResult2, twoByTwo4;
  /* Initialize some matrices we can play with for every test! */

  @Before
  public void setup(){

    //two by two matrices
    twoByTwo1 = new Matrix(new int[][]{{ 1, 2 }, { 5, 6 } });
    twoByTwo2 = new Matrix(new int[][]{{ -1, -2 }, { -5, -6 } });
    twoByTwo3 = new Matrix(new int[][]{{ 10, 3 }, { 5, 2 } });
    twoByTwo4 = new Matrix(new int[][]{{ 10, 3 }, { 5, 2 } });

    // this is the known correct result of multiplying twoByTwo1 by twoByTwo3
    twoByTwoResult2 = new Matrix(new int[][] { { 20, 7 }, { 80, 27 } });

    //three by two matrices
    threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
    threeByTwo2 = new Matrix(new int[][] { { 3, -6, 3 }, { 7, -5, 16 } });


    //two matrices with balanced dimensions for multiplication
    threeByThree = new Matrix(new int[][] { { 9, 5, -3 }, { 7, 2, 6 },  { 5, 15, -3 } });
    twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
    // this is the known correct result of multiplying M1 by M2
    twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

  }


  @Test
  public void equalsWithSelf(){

    Assert.assertTrue(twoByTwo1.equals(twoByTwo1));
    Assert.assertFalse(twoByTwo1.equals(twoByTwo2));
    Assert.assertTrue(twoByTwo3.equals(twoByTwo4));

  }


  @Test
  public void timesWithBalancedDimensions() {

    Matrix matrixProduct1 = threeByTwo.times(twoByThree);
    Assert.assertTrue(twoByTwoResult.equals(matrixProduct1));

    Matrix matrixProduct2 = twoByTwo1.times(twoByTwo3);
    Assert.assertTrue(twoByTwoResult2.equals(matrixProduct2));

  }

  @Test
  public void timesWithUnbalancedDimensions() {

   Assert.assertNull(threeByTwo.times(twoByTwo1));
   Assert.assertNull(threeByThree.times(twoByTwo2));
   Assert.assertNull(threeByThree.times(threeByTwo));

  }

  @Test
  public void twoByTwoToString() {

    String resultString = "13 12 \n29 26 \n";
    Assert.assertEquals(resultString, twoByTwoResult.toString());

  }

  @Test
  public void twoByTwoAddition(){

  String resultString = "0 0 \n0 0 \n";
  Assert.assertEquals(resultString, twoByTwo1.plus(twoByTwo2).toString());

  }

  @Test
  public void twoByThreeAddition(){

    String result = "4 -4 6 \n9 0 22 \n";
    Assert.assertEquals(result, threeByTwo.plus(threeByTwo2).toString());

  }

}
