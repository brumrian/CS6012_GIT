package lab05;

import assignment03.BinarySearchSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements lab05.List, Iterable<E> {

    Node<E> head;
    Node<E> tail;
    int size = 0;

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public class listIterator implements Iterator<E>{
        private Node<E> current = head;
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
            if (!hasNext()){
                throw new NoSuchElementException("End of List");
            }
            E temp = (E) current.next;
            current = current.next;  // if next is null, hasNext will return false.
            calledNext = true;
            return temp;
        }

        @Override
        public void remove(){

            if(!calledNext){
                throw new IllegalStateException("Cannot call .remove() before .next() is called");
            }
//
//           E temp = (E)current;
//            E previous = current.prev;



        }


    };


    private class Node<E>{

        // each node stores some data

        E data;
        Node next;
        Node prev;

        Node(){

            next = null;
            prev = null;
        }
    }

    public boolean contains(E item) {
//        Node temp = head;
//        while(temp != null) {
//            if(temp.data == item)
//                return true;
//            temp = temp.next;
//        }
        return false;
    }



//    LinkedNode f = new LinkedNode();
//    f.ID = 5;
//    f.name = “first node”;
//    f.next = new LinkedNode();
//    LinkedNode temp = f.next;
//    temp.ID = 12;
//    temp.name = “Billy”;

    @Override
    public void addFirst(Object element) {
        if(size == 0){

            head = new Node<E>();
            head.data = (E) element;
            head = tail;
        }


    }

    @Override
    public void addLast(Object o) {

    }

    @Override
    public void add(int index, Object element) throws IndexOutOfBoundsException {

    }

    @Override
    public Object getFirst() throws NoSuchElementException {
        return null;
    }

    @Override
    public Object getLast() throws NoSuchElementException {
        return null;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Object removeFirst() throws NoSuchElementException {
        return null;
    }

    @Override
    public Object removeLast() throws NoSuchElementException {
        return null;
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public int indexOf(Object element) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object element) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
