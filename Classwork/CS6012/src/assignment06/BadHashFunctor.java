package assignment06;

public class BadHashFunctor implements HashFunctor{
    int capacity;

    public BadHashFunctor(int cap){
        capacity = cap;
    }

    @Override
    public int hash(String item) {
        int hashVal = item.charAt(0);
//        System.out.println(hashVal);
        hashVal = hashVal % capacity;
//        System.out.println(hashVal);
//        System.out.println("_____________________________");
        return hashVal;
    }
}
