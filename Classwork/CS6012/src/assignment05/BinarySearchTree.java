package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * An class to create a Binary Search tree for generically-typed items. By
 * definition, a set contains no duplicate items.
 *
 * @author Rian Brumfield
 */

public class BinarySearchTree <T extends Comparable<? super T>> implements SortedSet<T> {

    int size;
    Node head;

    public class Node {
        public T data;
        Node left;
        Node right;

        Node() {
            data = null;
            left = null;
            right = null;
        }

        Node(T item) {
            data = item;
            left = null;
            right = null;
        }
    }



    public <T> BinarySearchTree(){
        size = 0;
        head = null;


    }

    /**
     *
     * @param item
     *          - the item to be added to the tree
     * @return true if the item was added to the tree
     * @throws NullPointerException
     *           if the item is null
     */

    @Override
    public boolean add(T item) {

        if(item == null){
            throw new NullPointerException("Cannot add a null item to a Binary Search Tree.");
        }

        Node node = new Node();
        node.data = item;
        boolean toReturn = false;


        if(size == 0){
            head = node;
            size ++;
            return true;
        }
        else{
            toReturn = insert(head, item);
            size ++;
        }


        return toReturn;
    }

    /**
     * Adds all items in a collection to the Binary Search Tree in order they are listed
     *
     * @param items
     *          - the collection of items to be added to the tree
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */

    @Override
    public boolean addAll(Collection items) {

        int sizeBefore = size;
        for(Object item: items){
            add((T) item);
        }
        int sizeAfter = size;

        if(sizeBefore != sizeAfter){
            return true;
        }

        return false;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */

    @Override
    public void clear() {
        head.data = null;
        head = null;
        size = 0;
    }

    /**
     * Determines if there is an item in the Binary Search Tree that is equal to the specified
     * item.
     *
     * @param item
     *          - the item sought in this set
     * @return true if there is an item in this tree that is equal to the input item;
     *         otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */

    @Override
    public boolean contains(T item) {
        if(item == null){
            throw new NullPointerException("Binary Search Tree cannot contain a null item.");
        }

        return search(head, item);
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this Binary Search Tree that is equal to it.
     *
     * @param items
     *          - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this tree that is equal to it; otherwise, returns false
     * @throws NullPointerException
     *           if any of the items is null
     */

    @Override
    public boolean containsAll(Collection items) {

        for(Object item: items){
            if(!contains((T)item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */

    @Override
    public T first() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("The tree is empty.");
        }

        Node node = head;
        while(node.left != null){
            node = node.left;
        }

        return node.data;
    }

    /**
     * Returns true if this set contains no items.
     */

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */

    @Override
    public T last() throws NoSuchElementException {

        if(size == 0){
        throw new NoSuchElementException("The tree is empty");
        }

        Node node = head;
        while(node.right != null){
            node = node.right;
        }

        return node.data;
    }
    /**
     * Ensures that this tree does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this tree changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */

    @Override
    public boolean remove(T item) {

        if(item == null){
            throw new NullPointerException("Cannot remove a null item from the tree.");
        }

       Node toDelete = findNode(head, item); //find the node which needs to be deleted to send to correct case

       if(toDelete.right == null && toDelete.left == null){ //leaf node case 1
          return removeLeafNode(item);
       }
       else if(toDelete.right == null || toDelete.left == null){ // one child node case 2
           boolean leftNull = false;
           if(toDelete.left == null){leftNull = true;}
           return removeOneChildNode(item, leftNull);
       }
       else { //2 children case 3
           return removeWithTwoChildren(item);
       }

       }

    /* this method is called by remove if the node to be
    * deleted has two children
    */

    private boolean removeWithTwoChildren(T item) {

        Node node = head;
        if(!(node.data.compareTo(item) == 0)){

            while(node.left != null || node.right != null){

                if(item.compareTo(node.data) < 0) {
                    if (node.left.data.compareTo(item) == 0) {
                        node = node.left;
                        size--;
                        break;
                    }
                    node = node.left;
                }
                else{
                    if(node.right.data.compareTo(item) == 0){
                        node = node.right;
                        size--;
                        break;
                    }

                    node = node.right;

                }
            }

        }

        Node replacement = node.right;

        while(replacement.left != null){
            replacement = replacement.left;
        }

        T data = replacement.data;
        boolean toReturn = remove(data);
        node.data = data;

        return toReturn;


    }

    /* method is called by remove if the node to be deleted
       has no children
     */

    private boolean removeLeafNode(T item){

        Node node = head;
        if(node.data.compareTo(item) == 0){
            head = null;
            size = 0;
            return true;
        }

        while(node.left != null || node.right != null){

        if(item.compareTo(node.data) < 0) {
            if (node.left.data.compareTo(item) == 0) {
                node.left = null;
                size--;
                return true;
            }
            node = node.left;
        }
        else{
            if(node.right.data.compareTo(item) == 0){
                    node.right = null;
                    size--;
                    return true;
            }

            node = node.right;

            }
        }

        return false;

    }

       /* method is called by remove if the node to be deleted
       has one child
     */


    public boolean removeOneChildNode(T item, boolean leftNull){

        Node node = head;
        if(node.data.compareTo(item) == 0){
           if(leftNull){
               head = null;
               head = node.right;
               size = 0;
               return true;
           }
           else{
               head = null;
               head = node.left;
               size = 0;
               return true;
           }
        }

        while(node.left != null || node.right != null){

            if(item.compareTo(node.data) < 0) {
                if (node.left.data.compareTo(item) == 0) {
                    if(leftNull){
                        node.left = node.left.right;
                    }
                    else{
                        node.left = node.left.left;
                    }
                    size--;
                    return true;
                }
                node = node.left;
            }
            else{
                if(node.right.data.compareTo(item) == 0){
                    if(leftNull){
                        node.right = node.right.right;
                    }
                    else{
                        node.right = node.right.left;
                    }
                    size--;
                    return true;
                }

                node = node.right;

            }
        }

        return false;
    }
    /**
     * Deletes contained items in the tree.
     *
     * @param items
     *          - the collection of items to be removed from the tree.
     * @return true if this tree changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */

    @Override
    public boolean removeAll(Collection items) {
        int beforeSize = size;

        for(Object item: items){
            if(item != null){
                remove((T) item);
            }
        }

        int afterSize = size;

        if(beforeSize != afterSize){
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in this set.
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */

    @Override
    public ArrayList toArrayList() {

      ArrayList<T> toReturn = new ArrayList<>();

      inOrderTraversalForArray(head, toReturn);

      return toReturn;
    }

    /**
     * The recursive function called by the add function
     * to add a node to the tree
     */

    private boolean insert(Node n, T item) {
        if (item.compareTo(n.data) < 0) {
            if (n.left == null) {
                n.left = new Node(item);
                return true;
            }
            else {
                insert(n.left, item);
            }
        }

        else {
            if (n.right == null){
                n.right = new Node(item);
                return true;
            }
            else {
                insert(n.right, item);
            }
        }

        return false;


    }

    /**
     * The recursive function called by the contains function
     * to see if data is contained in tree
     */

    boolean search(Node n, T item)
    {
        if(n == null){
            return false;
        }
        if(item.equals(n.data)){
            return true;
        }

        if(item.compareTo(n.data) < 0){
            return search(n.left, item);
        }
        else{
            return search(n.right, item);
        }
    }

    /**
     * The recursive function called by the remove function
     * to find the node which needs to be removed
     */

    public Node findNode(Node n, T item)
    {
        if(n == null){
            return null;
        }
        if(item.equals(n.data)){
            return n;
        }

        if(item.compareTo(n.data) < 0){
            return findNode(n.left, item);
        }
        else{
            return findNode(n.right, item);
        }
    }

    /**
     * The recursive function called by the toArrayList function
     * to add the tree data from least to greatest
     */
    private void inOrderTraversalForArray(Node node, ArrayList<T> array){

        if (node == null){
            return;
        }

        inOrderTraversalForArray(node.left, array);
        array.add((T)node.data);
        inOrderTraversalForArray(node.right, array);
    }


}



