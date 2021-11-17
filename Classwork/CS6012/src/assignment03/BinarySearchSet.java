package assignment03;

import java.util.*;

public class BinarySearchSet <E> implements lab05.SortedSet<E>, Iterable<E> {

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


    }


    BinarySearchSet(Comparator<? super E> comparator){
        set = (E[]) new Object[capacity];
        myComparator = comparator;

    }


    public int driver(int min, int max, E toFind){

        if(size == 0 || set == null){
            return 0;
        }

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

        if(contains(element)){
            return false;
        }

        if (this.size == 0) {
            set[0] = element;
            size++;
            return true;
        }

        if(capacity <= this.size + 1){
            doubleCapacity();
        }

            int index = driver(0, size - 1, element);

            for (int i = size-1; i >= index; i--) {
                set[i+1] = set[i];
            }

            set[index] = element;

            size++;

            return true;

    }


    @Override
    public boolean addAll(Collection elements) {
        int before = size;

        for(Object e: elements){
                add((E) e);
            }


        int after = size;

        if(after > before){
            return true;
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

        if(size==0 || index >= size || index < 0 ){
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


    public class setIterator implements Iterator<E>{

        private int position = 0;
        private int previous = -1;
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
                throw new IllegalStateException("sup");
            }

            BinarySearchSet.this.remove(set[previous]);
            calledNext = false;

        }


    };

    @Override
    public Iterator<E> iterator() {
        return new setIterator();
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

            return true;


        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {

        Object[] toReturn = new Object[size];
        for(int i = 0; i < size; i++){
            toReturn[i] = set[i];
        }
        return toReturn;
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

