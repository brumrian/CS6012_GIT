package assignment03;

import java.sql.SQLOutput;
import java.util.*;

/**
 * A set that provides a total ordering on its elements. The elements are
 * ordered using their natural ordering (i.e., using compareTo() from the
 * Comparable interface), or by a Comparator provided at sorted set creation
 * time. Thus, all elements inserted into a sorted set must implement the
 * Comparable interface or be accepted by the specified Comparator. The set's
 * iterator will traverse the set in ascending element order.
 *
 * @param <E>
 *          the type of elements maintained by this set
 */

public class BinarySearchSet <E> implements lab05.SortedSet<E>, Iterable<E> {

    private int size = 0; //size of set
    private int capacity = 100; //initial capacity
    private E[] set; //sorted set
    private Comparator<? super E> myComparator;
    boolean comparatorPassed; //did the user provide their own comparable in the constructor?


    /* *
     * The NaturalComparator Class sets default comparator to the Natural Comparator
    * */

    class NaturalComparator<T extends Comparable<T>>  implements Comparator<T> {
        @Override
        public int compare(T a, T b){
            return a.compareTo(b);
        }
    }


    /* *
     * The default constructor relies on the Natural Comparator
     * */

    public BinarySearchSet(){
        set = (E[]) new Object[capacity];
        myComparator = new NaturalComparator();
        comparatorPassed = false;



    }

    /* *
     * The User can use this constructor to pass in their own comparator
     * */


    BinarySearchSet(Comparator<? super E> comparator){
        set = (E[]) new Object[capacity];
        myComparator = comparator;
        comparatorPassed = true;


    }

    /* *
     * The driver checks that no null or size 0 arrays get passed into the recursive binary search function
     * @Param min is 0 to represent the beginning of the set array
     * @Param max is size-1 to represent the end of the set array
     * @Param toFind is the Element the user wishes to find within the set or add to the set
     * @return int is the desired index in the array
     *
     * */

    public int driver(int min, int max, E toFind){

        if(!comparatorPassed) {
            try {
                Comparator test = Comparator.naturalOrder();
                test.compare(toFind, toFind);

            } catch (ClassCastException e) {

//            System.err.println(e.getMessage());
                System.out.println("Object does not have natural order. Please pass comparator through constructor.");
                return -1;

            }
        }

        if(size == 0 || set == null){
            return 0;
        }

        return getBinarySearchIndex(min, max, toFind);
    }

    /* *
     * The BinarySearchIndex function returns the index of the desired element or the index where the element should be added
     * @Param toFind is the Element the user wishes to find within the set or add to the set
     * @Param left is the leftmost value for the part of the array to be checked for toFind
     * @Param right is the rightmost value for the part of the array to be checked for toFind
     * @return int is the desired index in the array
     *
     * */


    private int getBinarySearchIndex(int left, int right , E toFind){

        if(left >= right) {

            //base case: checks for three possible returns
            if(left >= size){
                return size;
            }
            else if(myComparator.compare(set[left], toFind) >= 0){
                return left;
            }
            else{
                return left + 1;
            }

        }


        int midIndex = (left + right) / 2;
        int compare = myComparator.compare(set[midIndex], toFind);

        if (compare == 0) {
            return midIndex;
        }
        else if (compare < 0) {
            //call binary search on left half
            return getBinarySearchIndex(midIndex + 1, right, toFind);

        }
        else {
            //call binary search on right half
            return  getBinarySearchIndex(left, midIndex -1 , toFind);


        }

    }


    /**
     * @return The comparator used to order the elements in this set, or null if
     *         this set uses the natural ordering of its elements (i.e., uses
     *         Comparable).
     */

    @Override
    public Comparator<? super E> comparator() {
        return myComparator;
    }

    /**
     * @return the first (lowest, smallest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */

    @Override
    public E first() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("The Array is Null");
        }
        return set[0];
    }

    /**
     * @return the last (highest, largest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */

    @Override
    public E last() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("Array is Null");
        }
        return set[size-1];
    }

    /**
     * Adds the specified element to this set if it is not already present and
     * not set to null.
     *
     * @param  element to be added to this set
     * @return true if this set did not already contain the specified element and it was added
     * @return false if the element was already contained in the set
     */

    @Override
    public boolean add(E element) {

        if(!comparatorPassed) { //if comparator not passed, check Object has natural
            try {
                Comparator test = Comparator.naturalOrder();
                test.compare(element, element);

            } catch (ClassCastException e) {

                //if object does not have natural order, request comparable
                System.out.println(e.getMessage());
                System.err.println("Object does not have a natural order. Please pass comparable through BinarySearchSet constructor.");

                System.exit(-1);

            }
        }


        if(contains(element)){
            return false; //do not add a duplicate value
        }

        if (this.size == 0) { //if the size is zero, add the element to the 0th index of array
            set[0] = element;
            size++;
            return true;
        }

        if(capacity <= this.size + 1){ //grow array size if add exceeds capacity
            doubleCapacity();
        }

            int index = driver(0, size - 1, element); //run binary search to get index

            for (int i = size-1; i >= index; i--) {
                //move every element one to the left
                set[i+1] = set[i];
            }

            //add element to desired index
            set[index] = element;

            size++;

            return true;

    }

    /**
     * Adds all of the elements in the specified collection to this set if they
     * are not already present and not set to null.
     *
     * @param elements Collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */

    @Override
    public boolean addAll(Collection elements) {



        int before = size; //to check if size changes


            for (Object e : elements) {
                add((E) e); //add all elements, duplicate check inside add method
            }



        int after = size;

        if(after > before){ //if the size changed return true
            return true;
        }

        return false;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after
     * this call returns.
     */

    @Override
    public void clear() { //reset set array
        capacity = 100;
        set = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * @param element Object whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */

    @Override
    public boolean contains(Object element) {

        int index = driver(0, size, (E) element); //run binary search for potential position

        if(size==0 || index >= size || index < 0 ){ //return false if array is size 0 or index is out of bounds
            return false;
        }

        if(myComparator.compare(set[index], (E) element) == 0){ //if the element at the index is the desired element, return true
            return true;
        }

        return false;
    }

    /**
     * @return true if this set contains no elements
     */

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }


    public class setIterator implements Iterator<E>{

        private int position = 0;
        private int previous = -1; //for remove() method
        boolean calledNext = false;


        @Override
        public boolean hasNext() {
            if(position < size){
                return true;
            }
            return false;
        }

        @Override
        public E next() {

            if(!hasNext()){
                throw new NoSuchElementException();
            }
            calledNext = true;
            previous++;
            return set[position++];
        }
        @Override
        public void remove(){

            if(!calledNext){
                throw new IllegalStateException("Cannot call remove() before next() is called");
            }

            BinarySearchSet.this.remove(set[previous]);
            calledNext = false;

        }


    };

    /**
     * @return an iterator over the elements in this set, where the elements are
     *         returned in sorted (ascending) order
     */

    @Override
    public Iterator<E> iterator() {
        return new setIterator();
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param element Object to be removed from this set, if present
     * @return true if this set contained the specified element
     */

    @Override
    public boolean remove(Object element) {

        if(contains(element)){
            int index = driver(0, size -1, (E) element); //call binary search to find index
            set[index] = null; // remove value at that index

            for (int i = index; i < size - 1; i++) { //move every index to the space before it
                set[i] = set[i+1];
            }


            size --;

            return true;


        }

        return false;
    }

    /**
     * @return the number of elements in this set
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * @return an array containing all of the elements in this set, in sorted
     *         (ascending) order.
     */

    @Override
    public Object[] toArray() {

        Object[] toReturn = new Object[size];
        for(int i = 0; i < size; i++){
            toReturn[i] = set[i];
        }
        return toReturn;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     *
     * @param elements Collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */

    @Override
    public boolean removeAll(Collection elements) {

        int before = size;

        for(Object e: elements){
            if(contains(e)) {
                remove((E) e);
                size--;
            }
        }
        if(size < 0){size = 0;}
        int after = size;

        if(before != after){

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection elements) {

        boolean containsAll = true;
        for(Object e : elements){
            if(!contains(e)){
                containsAll = false;
            }

        }
        return containsAll;
    }

    private void doubleCapacity() {
        capacity = capacity*2;
        E[] larger = (E[]) new Object[capacity];
        for(int i = 0; i < size(); i++){
            larger[i] = set[i];
        }
       set = larger;
    }


}

