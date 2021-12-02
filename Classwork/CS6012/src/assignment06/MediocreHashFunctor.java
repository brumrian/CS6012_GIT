package assignment06;

public class MediocreHashFunctor implements HashFunctor{
    int capacity_;

    public MediocreHashFunctor(int capacity){
        capacity_ = capacity;

    }

    @Override
    public int hash(String item) {
        int hashVal = 0;

        for(int i = 0; i < item.length(); i++){
            hashVal += item.charAt(i);
        }
        return hashVal % capacity_;
    }
}
