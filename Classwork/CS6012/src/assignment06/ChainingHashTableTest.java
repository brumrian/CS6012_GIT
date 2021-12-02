package assignment06;

import org.jfree.util.ArrayUtilities;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    int capacity_ = 30;

    ChainingHashTable badCHT;
    LinkedList<String>[] badStorage;

    ChainingHashTable mediocreCHT;
    LinkedList<String>[] mediocreStorage;

    ChainingHashTable goodCHT;
    LinkedList<String>[] goodStorage;

    String[] words = new String[]{"apple", "amazing", "always", "believe", "baby", "bananas", "car", "candy",
            "dad", "dead", "dance", "evermore", "family", "fun", "girl", "great", "hello", "handy",
            "happy", "hilarious", "igloo", "ingenious", "just", "job", "killer", "kangaroo", "keep",
            "look", "little", "lad", "maybe", "money", "mad", "nope", "never", "nice", "oh",
            "unordinary", "valentine", "vespa", "vigorous", "want", "wish", "whale", "whammy"};

    String[] moreWords = new String[]{"operation", "old", "oligarchy", "please", "probably", "panther", "quiet",
            "quite", "quick", "rusty", "rage", "ranger", "slut", "slippery", "song", "saw", "telepathy", "tall",
            "titillating", "ugly", "unknown", "unbelievable"};

    ArrayList<String> moreWordsList = new ArrayList<>();




    @BeforeEach
    public void setup(){


        badCHT = new ChainingHashTable(capacity_, new BadHashFunctor(capacity_));
        badStorage = badCHT.getStorage();

        mediocreCHT = new ChainingHashTable(capacity_, new MediocreHashFunctor(capacity_));
        mediocreStorage = mediocreCHT.getStorage();

        goodCHT = new ChainingHashTable(capacity_, new GoodHashFunctor(capacity_));
        goodStorage = goodCHT.getStorage();



        for(int i = 0; i < words.length; i++){
            badCHT.add(words[i]);
            mediocreCHT.add(words[i]);
            goodCHT.add(words[i]);
        }

        for(String word: moreWords){
            moreWordsList.add(word);
        }

    }

    @Test
    void add() {




        Assert.assertEquals(badCHT.size(), words.length);
        Assert.assertTrue(badCHT.contains("family"));

        Assert.assertEquals(mediocreCHT.size(), words.length);
        Assert.assertTrue(mediocreCHT.contains("whammy"));

        Assert.assertEquals(goodCHT.size(), words.length);
        Assert.assertTrue(goodCHT.contains("apple"));




    }

    @Test
    void addAll() {

        Assert.assertEquals(mediocreCHT.size(), words.length);
        Assert.assertFalse(mediocreCHT.contains("rage"));
        mediocreCHT.addAll(moreWordsList);
        Assert.assertEquals(mediocreCHT.size(), words.length + moreWords.length);
        Assert.assertTrue(mediocreCHT.contains("rage"));

    }

    @Test
    void clear() {

        badCHT.clear();
        Assert.assertTrue(badStorage[0].isEmpty());
        Assert.assertTrue(badStorage[10].isEmpty());
        Assert.assertTrue(badStorage[20].isEmpty());
        Assert.assertEquals(badCHT.size(), 0);


    }

    @Test
    void contains() {

        Assert.assertTrue(badCHT.contains("mad"));
        Assert.assertFalse(badCHT.contains("made"));


        Assert.assertTrue(goodCHT.contains("evermore"));
        Assert.assertFalse(goodCHT.contains("hope"));

    }

    @Test
    void containsAll() {

        goodCHT.addAll(moreWordsList);
        Assert.assertTrue(goodCHT.containsAll(moreWordsList));

        moreWordsList.add("new");
        Assert.assertFalse(goodCHT.containsAll(moreWordsList));

    }

    @Test
    void isEmpty() {

        Assert.assertFalse(mediocreCHT.isEmpty());
        mediocreCHT.clear();
        Assert.assertTrue(mediocreCHT.isEmpty());

    }

    @Test
    void remove() {

        Assert.assertTrue(badCHT.contains("dance"));
        badCHT.remove("dance");
        Assert.assertFalse(badCHT.contains("dance"));

    }

    @Test
    void removeAll() {

        Assert.assertFalse(goodCHT.removeAll(moreWordsList));
        goodCHT.addAll(moreWordsList);
        Assert.assertTrue(goodCHT.containsAll(moreWordsList));
        Assert.assertTrue(goodCHT.removeAll(moreWordsList));

    }

    @Test
    void size() {

        Assert.assertEquals(badCHT.size(), words.length);
        Assert.assertTrue(badCHT.addAll(moreWordsList));
        Assert.assertEquals(badCHT.size(), words.length + moreWords.length);


    }

}