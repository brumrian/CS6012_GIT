package assignment06;

public class GoodHashFunctor implements HashFunctor{

    int capacity_;

    public GoodHashFunctor(int capacity){
        capacity_ = capacity;

    }
    @Override
    public int hash(String item) {


        int hashVal = 0;

        for(int i = 0; i < item.length(); i++){
            hashVal = 47 * hashVal + item.charAt(i);
        }


        hashVal = hashVal % capacity_;

        if(hashVal < 0){
            return -hashVal;
        }


        return hashVal;
    }
}
