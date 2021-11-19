package lab05;

import assignment03.BinarySearchSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Generic for DoubleLinkedList
 *
 * @author Rian Brumfield
 *
 * @param <E>
 *          -- the type of elements contained in the list
 */

public class DoublyLinkedList<E> implements lab05.List, Iterable<E> {

    public Node<E> head;
    public Node<E> tail;
    int size = 0;



    DoublyLinkedList(){

        head = null;
        tail = null;

    }

    private class Node<E>{

        E data;
        Node next;
        Node prev;

        Node(){

            next = null;
            prev = null;

        }
    }

    public boolean contains(E item) {
        Node<E> temp = head;
        while(temp != null) {
            if(temp.data == item) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Inserts the specified element at the beginning of the doubly linked list.
     * Time complexity: O(1)
     * @Param element is data user wants to store in first position of the list
     *
     */

    @Override
    public void addFirst(Object element) {

        if(size == 0){
            head = new Node<E>();
            head.data = (E) element;
            tail = head;
            size = 1;
            return;
        }

        Node<E> newFirst = new Node<E>();
        newFirst.next = head;
//        newFirst.prev = null;
        newFirst.data = (E) element;
        head.prev = newFirst;


        size++;

        head = newFirst;

    }

    /**
     * Inserts the specified element at the end of the doubly-linked list.
     * Time complexity: O(1)
     * @Param element is data user wants to store in last position of the list
     *
     */

    @Override
    public void addLast(Object element) {

        Node<E> oldTail = tail;
        Node<E> newLast = new Node<E>();

        newLast.prev = tail;
        newLast.data = (E) element;
        tail.next = newLast;

        size++;

        tail = newLast;


    }

    /**
     * Inserts the specified element at the specified position in the doubly-linked list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >= size)
     * Time complexity: O(N)
     * @Param index is where in the list the user wants to store the element
     * @Param element is data user wants to store in first position of the list
     */

    @Override
    public void add(int index, Object element) throws IndexOutOfBoundsException {

        if(index < 0){
            throw new IndexOutOfBoundsException("Index cannot be less than zero.");


        }
        if(index > size){
            throw new IndexOutOfBoundsException("Index outside bounds of doubly-linked list.");
        }

        if(index != 0 && index == size){
            addLast(element);
            return;
        }

        if(index == 0){
            addFirst(element);
            return;
        }

        Node<E> toAdd = new Node<>();
        toAdd.data = (E)element;

        Node<E> node = head;
        for(int i = 0; i < index-1; i++){
            node = node.next;
        }

        Node<E> storeOldNext = node.next;

        node.next = toAdd;
        toAdd.prev = node;
        toAdd.next = storeOldNext;
        storeOldNext.prev = toAdd;

        size++;





    }

    /**
     * @Return head.data returns the first element in the doubly-linked list. Throws NoSuchElementException if the
     * list is empty. Time complexity: O(1)
     *
     */

    @Override
    public Object getFirst() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("List is empty.");
        }
        return head.data;
    }

    /**
     * @Return tail.data returns the last element in the doubly-linked list. Throws NoSuchElementException if the
     * list is empty. Time complexity: O(1)
     */

    @Override
    public Object getLast() throws NoSuchElementException {

        if(size == 0){
            throw new NoSuchElementException("List is empty.");
        }
        return tail.data;
    }

    /**
     * Returns the element at the specified position in the doubly-linked list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
     * size()) Time complexity: O(N)
     *
     */

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if(index < 0){
            throw new IndexOutOfBoundsException("Index cannot be a negative number.");

        }

        if(index >= size){
            throw new IndexOutOfBoundsException("Index outside bounds of doubly-linked list.");
        }

        Node<E> node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }

        return node.data;
    }

    /**
     *  Throws NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
     *  @Return temp returns the first element from the list which is being removed
     */

    @Override
    public Object removeFirst() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("List is empty.");
        }
        E temp = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return temp;
    }

    /**
     * Removes and returns the last element from the doubly-linked list. Throws
     * NoSuchElementException if the list is empty. Time Complexity: O(1)
     *
     */

    @Override
    public Object removeLast() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("List is empty.");
        }
        E temp = tail.data;
        if(tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return temp;
    }

    /**
     * Removes and returns the element at the specified position in the doubly-linked list. Throws
     * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
     * size()) Time Complexity: O(N)
     */

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        if(index < 0){
            throw new IndexOutOfBoundsException("Index cannot be a negative number.");
        }
        if(index >= size){
            throw new IndexOutOfBoundsException("List not long enough to access index.");
        }

        if(index == size-1){
            E temp = tail.data;
            removeLast();
            return temp;
        }

        if(index == 0){
            E temp = head.data;
            removeFirst();
            return temp;
        }

        Node<E> nodeToRemove = head;
        for(int i = 0; i < index; i++){
            nodeToRemove = nodeToRemove.next;
        }


        Node<E> OldNext = nodeToRemove.next;
        Node<E> OldPrev = nodeToRemove.prev;

        OldPrev.next = OldNext;
        OldNext.prev = OldPrev;

        size--;
        return nodeToRemove.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the
     * doubly-linked list, or -1 if this list does not contain the element.
     * Time Complexity: O(N)
     */

    @Override
    public int indexOf(Object element) {

        Node<E> temp = head;
        int count = 0;
        while(temp != null) {
            if(temp.data.equals((E) element)) {
                return count;
            }

            temp = temp.next;
            count++;
        }
        return -1;

    }

    /**
     * Returns the index of the last occurrence of the specified element in this
     * list, or -1 if this list does not contain the element. O(N) for a
     * doubly-linked list.
     */

    @Override
    public int lastIndexOf(Object element) {

        Node<E> temp = head;
        int toReturn = -1;
        int count = 0;
        while(temp != null) {



            if(temp.data.equals((E) element)) {
               toReturn = count;
            }


            temp = temp.next;
            count++;



        }




        return toReturn;
    }

    /**
     * Returns the number of elements in the list.
     * Time Complexity: O(1)
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements. O(1) for a
     * doubly-linked list.
     */

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */

    @Override
    public void clear() {

        Node<E> temp = head;
        while(temp != null) {

            temp = temp.next;
            temp = null;
        }

        size = 0;


    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element). O(N) for a doubly-linked list.
     */


    @Override
    public Object[] toArray() {
        ArrayList<E> arrayList = new ArrayList<>();

        Node<E> temp = head;

        while(temp != null) {
            arrayList.add(temp.data);
            temp = temp.next;
        }

        E[] array = (E[]) new Object[arrayList.size()];

        for(int i = 0; i < arrayList.size(); i++){
            array[i] = arrayList.get(i);
        }

        return array;
    }

    public class listIterator implements Iterator<E>{
        private Node<E> current = head;
        private int position = 0;
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
            if (!hasNext()){
                throw new NoSuchElementException("End of List");
            }
            E temp = current.data;
            current = current.next;  // if next is null, hasNext will return false.
            calledNext = true;
            position++;
            return temp;
        }

        @Override
        public void remove(){

            if(!calledNext){
                throw new IllegalStateException("Cannot call .remove() before .next() is called");
            }

            DoublyLinkedList.this.remove(--position);
            calledNext = false;
        }


    };

    @Override
    public Iterator<E> iterator() {
        return new listIterator();
    }


}

