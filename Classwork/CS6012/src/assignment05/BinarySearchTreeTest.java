package assignment05;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BinarySearchTreeTest {

    BinarySearchTree<Integer> balancedBST;
    BinarySearchTree<Integer> unbalancedBST;

    ArrayList<Integer> balancedIntArray;
    ArrayList<Integer> unbalancedIntArray;

    @BeforeEach
    public void setup(){
        balancedBST = new BinarySearchTree<>();
        balancedIntArray = new ArrayList<>();
        balancedIntArray.add(50);
        balancedIntArray.add(40);
        balancedIntArray.add(60);
        balancedIntArray.add(37);
        balancedIntArray.add(63);
        balancedIntArray.add(52);
        balancedIntArray.add(45);

        unbalancedBST = new BinarySearchTree<>();
        unbalancedIntArray = new ArrayList<>();
        unbalancedIntArray.add(20);
        unbalancedIntArray.add(50);
        unbalancedIntArray.add(40);
        unbalancedIntArray.add(60);
        unbalancedIntArray.add(35);
        unbalancedIntArray.add(45);
        unbalancedIntArray.add(55);
        unbalancedIntArray.add(70);

        unbalancedBST.addAll(unbalancedIntArray);


    }

    @AfterEach
    public void breakdown(){

    }

    @Test
    void add() {

        balancedBST.add(10);
        balancedBST.add(7);
        balancedBST.add(12);
       BinarySearchTree.Node head = balancedBST.head;
       Assert.assertEquals(head.data, 10);
       Assert.assertEquals(head.left.data, 7);
       Assert.assertEquals(head.right.data, 12);
       Assert.assertEquals(balancedBST.size(), 3);


       BinarySearchTree.Node left = balancedBST.head.left;
       balancedBST.add(8);
       balancedBST.add(6);
       Assert.assertEquals(left.right.data, 8);
       Assert.assertEquals(left.left.data, 6);


       BinarySearchTree.Node right = balancedBST.head.right;
       balancedBST.add(11);
       balancedBST.add(15);
       Assert.assertEquals(right.right.data, 15);
       Assert.assertEquals(right.left.data, 11);

       Assert.assertEquals(balancedBST.size(), 7);



    }

    @Test
    void addAll() {

        Assert.assertTrue(balancedBST.addAll(balancedIntArray));
        Assert.assertEquals(balancedBST.size(), 7);

        BinarySearchTree.Node head = balancedBST.head;
        Assert.assertEquals(head.data, 50);

        BinarySearchTree.Node left = head.left;
        Assert.assertEquals(left.data, 40);

        BinarySearchTree.Node right = head.right;
        Assert.assertEquals(right.data, 60);

    }

    @Test
    void clear() {
    }

    @Test
    void contains() {

        balancedBST.addAll(balancedIntArray);
        Assert.assertTrue(balancedBST.contains(50));
        Assert.assertTrue(balancedBST.contains(60));
        Assert.assertTrue(balancedBST.contains(40));
        Assert.assertTrue(balancedBST.contains(37));
        Assert.assertTrue(balancedBST.contains(63));
        Assert.assertTrue(balancedBST.contains(52));
        Assert.assertTrue(balancedBST.contains(45));



    }

    @Test
    void containsAll() {

        balancedBST.addAll(balancedIntArray);
        Assert.assertTrue(balancedBST.containsAll(balancedIntArray));

        balancedIntArray.add(500);
        Assert.assertFalse(balancedBST.containsAll(balancedIntArray));

    }

    @Test
    void first() {
        balancedBST.addAll(balancedIntArray);
        Integer first = balancedBST.first();

    }

    @Test
    void isEmpty() {

        Assert.assertTrue(balancedBST.isEmpty());
        Assert.assertFalse(unbalancedBST.isEmpty());
        unbalancedBST.clear();
        Assert.assertTrue(unbalancedBST.isEmpty());
    }

    @Test
    void last() {
        balancedBST.addAll(balancedIntArray);
        Integer last = balancedBST.last();
        Integer expected = 63;
        Assert.assertEquals(last, expected);

        Integer last2 = unbalancedBST.last();
        Integer expected2 = 70;
        Assert.assertEquals(last2, expected2);

    }

    @Test
    void remove() {

        Assert.assertTrue(unbalancedBST.contains(20));
        Assert.assertTrue(unbalancedBST.remove(20));
        Assert.assertFalse(unbalancedBST.contains(20));

        Assert.assertTrue(unbalancedBST.contains(50));
        Assert.assertTrue(unbalancedBST.remove(50));
        Assert.assertFalse(unbalancedBST.contains(50));

        Integer first = unbalancedBST.first();
        Integer expected = 55;
        Assert.assertEquals(first, expected);

        Assert.assertTrue(unbalancedBST.contains(40));
        Assert.assertTrue(unbalancedBST.remove(40));
        Assert.assertFalse(unbalancedBST.contains(40));

        Assert.assertTrue(unbalancedBST.contains(70));
        Assert.assertTrue(unbalancedBST.remove(70));
        Assert.assertFalse(unbalancedBST.contains(70));

    }

    @Test
    void removeLeafNodeTest() {
        balancedBST.addAll(balancedIntArray);

        Assert.assertTrue(balancedBST.contains(37));
        Assert.assertEquals(balancedBST.size(), 7);

        Assert.assertTrue(balancedBST.remove(37));
        Assert.assertFalse(balancedBST.contains(37));
        Assert.assertEquals(balancedBST.size(), 6);

        Assert.assertTrue(balancedBST.remove(45));
        Assert.assertFalse(balancedBST.contains(45));
        Assert.assertEquals(balancedBST.size(), 5);

        Assert.assertTrue(balancedBST.contains(40));

        Assert.assertTrue(balancedBST.remove(40));
        Assert.assertFalse(balancedBST.contains(40));
        Assert.assertEquals(balancedBST.size(), 4);


        Assert.assertTrue(balancedBST.contains(63));

        Assert.assertTrue(balancedBST.remove(63));
        Assert.assertFalse(balancedBST.contains(40));
        Assert.assertEquals(balancedBST.size(), 3);


    }

    @Test
    void removeOneChildNodeTest() {
        balancedBST.addAll(balancedIntArray);

        balancedBST.add(90);
        balancedBST.add(95);
        balancedBST.add(92);
        balancedBST.add(39);
        balancedBST.add(49);
        balancedBST.add(55);

        Assert.assertTrue(balancedBST.contains(63));
        Assert.assertTrue(balancedBST.remove(63));
        Assert.assertFalse(balancedBST.contains(63));

        Assert.assertTrue(balancedBST.contains(52));
        Assert.assertTrue(balancedBST.remove(52));
        Assert.assertFalse(balancedBST.contains(52));

        Assert.assertTrue(balancedBST.contains(37));
        Assert.assertTrue(balancedBST.remove(37));
        Assert.assertFalse(balancedBST.contains(37));


        Assert.assertTrue(balancedBST.contains(45));
        Assert.assertTrue(balancedBST.remove(45));
        Assert.assertFalse(balancedBST.contains(45));


        Assert.assertTrue(balancedBST.contains(90));
        Assert.assertTrue(balancedBST.contains(95));
        Assert.assertTrue(balancedBST.contains(92));
        Assert.assertTrue(balancedBST.contains(39));
        Assert.assertTrue(balancedBST.contains(55));
        Assert.assertTrue(balancedBST.contains(49));

    }

    @Test
    void removeTwoChildrenTest() {
        balancedBST.addAll(balancedIntArray);
        balancedBST.add(90);


        Assert.assertTrue(balancedBST.contains(50));
//        bst.remove(60);
        Assert.assertTrue(balancedBST.remove(50));
        Assert.assertFalse(balancedBST.contains(50));

        Assert.assertTrue(balancedBST.contains(40));
        Assert.assertTrue(balancedBST.remove(40));
        Assert.assertFalse(balancedBST.contains(40));


        Assert.assertTrue(balancedBST.contains(60));
        Assert.assertTrue(balancedBST.remove(60));
        Assert.assertFalse(balancedBST.contains(60));



    }

    @Test
    void removeAll() {

        balancedBST.addAll(balancedIntArray);
        Assert.assertTrue(balancedBST.containsAll(balancedIntArray));
        Assert.assertTrue(balancedBST.removeAll(balancedIntArray));
        Assert.assertEquals(balancedBST.size(),0);
        Assert.assertTrue(balancedBST.isEmpty());

        Assert.assertTrue(unbalancedBST.containsAll(unbalancedIntArray));
        Assert.assertTrue(unbalancedBST.removeAll(unbalancedIntArray));
        Assert.assertEquals(unbalancedBST.size(), 0);
        Assert.assertTrue(unbalancedBST.isEmpty());
    }

    @Test
    void size() {

        Assert.assertEquals(balancedBST.size(), 0);
        Assert.assertEquals(unbalancedBST.size(), 8);
    }

    @Test
    void toArrayList() {


    }
}