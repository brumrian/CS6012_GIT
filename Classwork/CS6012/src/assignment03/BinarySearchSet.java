package assignment03;

import java.util.*;

public class BinarySearchSet <E> implements lab05.SortedSet<E> {

    int size = 0;
    int capacity = 100;

    public E[] set;

    class NaturalComparator<T extends Comparable<T>>  implements Comparator<T> {
        @Override
        public int compare(T a, T b){
            return a.compareTo(b);
        }
    }

    private Comparator<? super E> myComparator;


    BinarySearchSet(){
        set = (E[]) new Object[capacity];
        myComparator = new NaturalComparator();


        //implements comparable super E
        //if already implements comparable, use the natural ordering
        //binary search returns an index - the index where it is or the index it SHOULD be
    }


    BinarySearchSet(Comparator<? super E> comparator){
        set = (E[]) new Object[capacity];
        myComparator = comparator;

        //user wants to pass a comparator
        //assume E implements compareTo() (doesnt like this one)
        //we create a natural comparator that does the call to compare to
        //google natural order comparator
    }


    public int driver(int min, int max, E toFind){
        return getBinarySearchIndex(min, max, toFind);
    }

    private int getBinarySearchIndex(int left, int right , E toFind){

        if(left >= right) {
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
            return getBinarySearchIndex(midIndex + 1, right, toFind);

        }
        else {
            return  getBinarySearchIndex(left, midIndex -1 , toFind);


        }

    }

    @Override
    public Comparator<? super E> comparator() {
        return myComparator;
    }

    @Override
    public E first() throws NoSuchElementException {
        return set[0];
    }

    @Override
    public E last() throws NoSuchElementException {
        return set[size-1];
    }

    @Override
    public boolean add(E element) {

//        System.out.println("_______________________");

        if(contains(element)){
            return false;
        }

        if (this.size == 0) {
            set[0] = element;
            size++;
            return true;
        }

        if(capacity >= this.size + 1) {

            int index = driver(0, size - 1, element);

            for (int i = size-1; i >= index; i--) {
                set[i+1] = set[i];
            }

            set[index] = element;

            size++;

            for(int i = 0; i < size; i++){
                System.out.println("hello from add: " + set[i]);
            }
            return true;
        }


        return false;
    }


    @Override
    public boolean addAll(Collection elements) {
        for(Object e: elements){
            add((E) e);
        }
        return false;
    }

    @Override
    public void clear() {
        capacity = 100;
        set = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean contains(Object element) {

        int index = driver(0, size, (E) element);

        if(size==0 || index >= size){
            return false;
        }

        if(myComparator.compare(set[index], (E) element) == 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean remove(Object element) {

        if(contains(element)){
            //check where it is remove it and move the items back reduce size by one...
            int index = driver(0, size -1, (E) element);
            set[index] = null;

            for (int i = index; i < size - 1; i++) {
                set[i] = set[i+1];
            }


            size --;
//
//            for(int i = 0; i < size; i++){
//                System.out.println("hello from remove" + set[i]);
//            }
            return true;


        }

        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return set;
    }

    @Override
    public boolean removeAll(Collection elements) {

        int before = size;

        for(Object e: elements){
            if(contains(e)) {
                remove((E) e);
                size--;
            }
        }

        int after = size;
        System.out.println("after size expect 2 is " + size);

        if(before != after){

            for(int i = 0; i < size; i++){
                System.out.println("hello from removeAll" + set[i]);
            }

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

    public static void main(String[] args) {

        BinarySearchSet<String> stringSet = new BinarySearchSet<>();
        ArrayList<String> strings = new ArrayList<>();

        strings.add("Banana");
        strings.add("Apple");
        strings.add("Teeth");
        strings.add("Running");
        strings.add("Zoo");
        strings.add("Friendly");
        strings.add("Quick");
        strings.add("Car");


        stringSet.addAll(strings);

        if(stringSet.add("Banana") || stringSet.add("Apple") || stringSet.add("Teeth")
                || stringSet.add("Running") || stringSet.add("Zoo") || stringSet.add("Friendly")
                || stringSet.add("Quick") || stringSet.add("Car")){
            System.err.println("TEST FAILED -- stringSet add(duplicate");
        }

        if(stringSet.addAll(strings)){
            System.err.println("TEST FAILED -- stringSet addAll(duplicates");
        }

        if(!stringSet.contains("Banana") || !stringSet.contains("Apple") || !stringSet.contains("Teeth")
                || !stringSet.contains("Running") || !stringSet.contains("Zoo") || !stringSet.contains("Friendly")
                || !stringSet.contains("Quick") || !stringSet.contains("Car")){
            System.err.println("TEST FAILED -- stringSet !contains(element)");
        }

        if(!stringSet.containsAll(strings)){
            System.err.println("TEST FAILED -- stringSet !containsAll(elements)");

        }

        ArrayList<String> stringsNotInSet = new ArrayList<>();
        stringsNotInSet.add("Hello");
        stringsNotInSet.add("Not");
        stringsNotInSet.add("Joke");
        stringsNotInSet.add("Yes");
        stringsNotInSet.add("Funny");

        if(stringSet.contains(stringsNotInSet.get(0)) ||stringSet.contains(stringsNotInSet.get(1))
                || stringSet.contains(stringsNotInSet.get(2)) || stringSet.contains(stringsNotInSet.get(3))
                || stringSet.contains(stringsNotInSet.get(4))){
            System.err.println("TEST FAILED -- stringSet contains(element)");
        }

        if(stringSet.containsAll(stringsNotInSet)){
            System.err.println("TEST FAILED -- stringSet containsAll(elements)");

        }


        stringSet.remove("Car");

        if(stringSet.contains("Car")){
            System.err.println("TEST FAILED -- stringSet remove(element)");
        }
        if(!stringSet.removeAll(strings)){
            System.err.println("TEST FAILED -- stringSet removeAll(elements)");
        }




        BinarySearchSet<Integer> intSet = new BinarySearchSet<>();
        ArrayList<Integer> ints = new ArrayList<>();

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

        if(intSet.add(-1000) || intSet.add(-100) || intSet.add(50) || intSet.add(0) || intSet.add(37)
                || intSet.add(500) || intSet.add(3000) || intSet.add(-21) || intSet.add(77) || intSet.add(-100)){
            System.err.println("TEST FAILED -- intSet add(duplicate)");

        }

        if(intSet.addAll(ints)){
            System.err.println("TEST FAILED -- intSet addAll(duplicates)");

        }

        if(!intSet.contains(-1000) || !intSet.contains(-100) || !intSet.contains(50) || !intSet.contains(0) || !intSet.contains(37)
                || !intSet.contains( 500) || !intSet.contains(3000) || !intSet.contains(-21) || !intSet.contains(77) || !intSet.contains(-100)){
            System.err.println("TEST FAILED -- intSet !contains(element)");

        }

        if(!intSet.containsAll(ints)){
            System.err.println("TEST FAILED -- intSet !containsAll(elements)");
        }

        ArrayList<Integer> intsNotInSet = new ArrayList<>();
        intsNotInSet.add(499);
        intsNotInSet.add(53);
        intsNotInSet.add(550);
        intsNotInSet.add(-77);
        intsNotInSet.add(-3000);

        if(ints.contains(intsNotInSet.get(0)) || intSet.contains(intsNotInSet.get(1)) || intSet.contains(intsNotInSet.get(2))
                || intSet.contains(intsNotInSet.get(3)) || intSet.contains(intsNotInSet.get(4))){
            System.err.println("TEST FAILED -- intSet contains(element)");
        }

        if(intSet.containsAll(intsNotInSet)){
            System.err.println("TEST FAILED -- intSet ContainsAll(elements)");
        }

        if(intSet.remove(77));

        if(intSet.contains(77)){
            System.err.println("TEST FAILED -- intSet remove(element)");
        }

        if(!intSet.removeAll(ints)){
            System.err.println("TEST FAILED -- intSet removeAll(elements)");
        }



        System.out.println("TESTING DONE.");

    }




}

