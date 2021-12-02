package assignment06;

import java.util.ArrayList;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) {

        int capacity = 100;

        ChainingHashTable testTable_Bad = new ChainingHashTable(capacity, new BadHashFunctor(capacity));
        LinkedList<String>[] badStorage = testTable_Bad.getStorage();

        ChainingHashTable testTable_Mediocre = new ChainingHashTable(capacity, new MediocreHashFunctor(capacity));
        LinkedList<String>[] mediocreStorage = testTable_Mediocre.getStorage();

        ChainingHashTable testTable_Good = new ChainingHashTable(capacity, new GoodHashFunctor(capacity));
        LinkedList<String>[] goodStorage = testTable_Good.getStorage();


        String[] words = new String[]{"apple", "amazing", "always", "believe", "baby", "bananas", "car", "candy",
                "dad", "dead", "dance", "evermore", "family", "fun", "girl", "great", "hello", "handy",
                "happy", "hilarious", "igloo", "ingenious", "just", "job", "killer", "kangaroo", "keep",
                "look", "little", "lad", "maybe", "money", "mad", "nope", "never", "nice", "oh",
                "unordinary", "valentine", "vespa", "vigorous", "want", "wish", "whale", "whammy","operation", "old", "oligarchy", "please", "probably", "panther", "quiet",
                "quite", "quick", "rusty", "rage", "ranger", "slut", "slippery", "song", "saw", "telepathy", "tall",
                "titillating", "ugly", "unknown", "unbelievable", "long", "dark", "correct", "indonesia", "world", "play", "carry", "poop",
                "kill", "jeans", "jack", "clown", "funny", "babysit", "joyful", "plea", "guilty", "lawyer", "cheating", "freaky",
                "unimpressed", "organs", "heaps", "hallow", "leisure", "pivot", "interest", "loan", "keen"};


        ArrayList<String> wordsList = new ArrayList<>();
        for(String word: words){
            wordsList.add(word);
        }

        testTable_Bad.addAll(wordsList);
        testTable_Mediocre.addAll(wordsList);
        testTable_Good.addAll(wordsList);


        ArrayList<Integer> badCollisions = new ArrayList<>();
        ArrayList<Integer> mediocreCollisions = new ArrayList<>();
        ArrayList<Integer> goodCollisions = new ArrayList<>();

        for(int i = 0; i < capacity; i++){
//            System.out.println("FOR THE INDEX " + i);
            badCollisions.add(badStorage[i].size());
//            System.out.println("The bad size is: " + badStorage[i].size());
            mediocreCollisions.add(mediocreStorage[i].size());
//            System.out.println("The mediocre size is: " + mediocreStorage[i].size());
            goodCollisions.add(goodStorage[i].size());
//            System.out.println("The good size is: " + goodStorage[i].size());
//            System.out.println("_____________________________________________");

        }
        int badZeroCount = 0;
        for(Integer i: badCollisions){
            if(i == 0){badZeroCount++;}
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("______^^BAD^^________"+ "Count: " + badZeroCount + "________");

        int mediocreZeroCount = 0;
        for(Integer i: mediocreCollisions){
            if(i == 0){mediocreZeroCount++;}
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("_______^^MEDIOCRE^^_____"+ "Count: " + mediocreZeroCount +" __________");

        int goodZeroCount = 0;
        for(Integer i: goodCollisions){
            if(i == 0){goodZeroCount++;}

            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("_______^^GOOD^^_______" + "Count: " + goodZeroCount +" __________");

        System.out.println(words.length);







    }
}
