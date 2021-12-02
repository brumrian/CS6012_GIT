package assignment06;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{

    private LinkedList<String>[] storage;
    int size;
    int capacity_;
    HashFunctor hashFunctor;

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){

        storage = (LinkedList<String>[]) new LinkedList[capacity];
        size = 0;
        hashFunctor = functor;
        capacity_ = capacity;

        for(int i = 0; i < capacity_; i++){
         storage[i] = new LinkedList<String>();
        }
    }

    /**
     * Ensures that the Hash Table contains the specified item.
     *
     * @param item
     *          - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */

    @Override
    public boolean add(String item) {

        if(!contains(item)) {
            int position = hashFunctor.hash(item);
            storage[position].add(item);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Ensures that the Hash Table contains all items in the specified collection.
     *
     * @param items
     *          - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */

    @Override
    public boolean addAll(Collection<? extends String> items) {
        int beforeSize = 0;

        for(Object item: items){
            add((String) item);
        }

        int afterSize = size;

        if(beforeSize == afterSize) {
            return false;
        }
        return true;
    }

    /**
     * Removes all items from the Hash Table. The table will be empty after this method
     * call.
     */

    @Override
    public void clear() {

        for(LinkedList<String> list: storage){
            list.clear();
        }
        size = 0;
    }

    /**
     * Determines if there is an item in this table that is equal to the specified
     * item.
     *
     * @param item
     *          - the item sought in this table
     * @return true if there is an item in this table that is equal to the input item;
     *         otherwise, returns false
     */

    @Override
    public boolean contains(String item) {

        int expectedPosition = hashFunctor.hash(item);
        for(String string: storage[expectedPosition]){
            if(string.equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this table that is equal to it.
     *
     * @param items
     *          - the collection of items sought in this table
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */

    @Override
    public boolean containsAll(Collection<? extends String> items) {

        for(String item: items){
            if(!contains(item)){
                return false;
            }
        }
        return true;
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
     * Ensures that this table does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this table
     * @return true if this table changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */


    @Override
    public boolean remove(String item) {
        if(contains(item)){
            int position = hashFunctor.hash(item);
            storage[position].remove(item);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Ensures that this table does not contain any of the items in the specified
     * collection.
     *
     * @param items
     *          - the collection of items whose absence is ensured in this table
     * @return true if this table changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */

    @Override
    public boolean removeAll(Collection<? extends String> items) {

        boolean setChanged = false;
        for(Object item: items){
            if(remove((String)item)){
                setChanged = true;
            }
        }
        return setChanged;
    }

    /**
     * Returns the number of items in this set.
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the storage array.
     */

    public LinkedList<String>[] getStorage() {
        return storage;
    }

}
