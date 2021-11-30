package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

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

    @Override
    public boolean add(T item) {


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

    @Override
    public void clear() {
        head.data = null;
        head = null;
        size = 0;


    }

    @Override
    public boolean contains(T item) {

        return search(head, item);
    }

    @Override
    public boolean containsAll(Collection items) {

        for(Object item: items){
            if(!contains((T)item)){
                return false;
            }
        }
        return true;
    }

    @Override
    public T first() throws NoSuchElementException {
        T data = head.data;
        return data;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public T last() throws NoSuchElementException {

        Node node = head;
        while(node.right != null){
            node = node.right;
        }

        return node.data;
    }

    @Override
    public boolean remove(T item) {



       Node toDelete = findNode(head, item);

       if(toDelete.right == null && toDelete.left == null){
          return removeLeafNode(item);
       }
       else if(toDelete.right == null || toDelete.left == null){
           boolean leftNull = false;
           if(toDelete.left == null){leftNull = true;}
           return removeOneChildNode(item, leftNull);
       }
       else {
           return removeWithTwoChildren(item);
       }

       }



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

    @Override
    public int size() {
        return size;
    }

    @Override
    public ArrayList toArrayList() {

        ArrayList<T> toReturn;

        for(int i = 0; i < size; i++){
        }
        return null;
    }

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


}



