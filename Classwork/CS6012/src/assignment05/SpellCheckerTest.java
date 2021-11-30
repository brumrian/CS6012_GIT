package assignment05;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class SpellCheckerTest {

    SpellChecker SpellCheckDictionaryTxt;
    SpellChecker SpellCheckArrayList;

    @BeforeEach
    public void setup(){
        SpellCheckDictionaryTxt = new SpellChecker(new File("src/assignment05/dictionary.txt"));

        String[] words = new String[]{"apple", "banana", "car", "goat", "swing", "laugh", "love", "play", "outside",
                                      "inside", "clay", "orange", "sweet", "bitter", "rude"};
        List<String> listOfWords = new ArrayList<>();

        for(String word: words){
            listOfWords.add(word);
        }

        SpellCheckArrayList = new SpellChecker(listOfWords);


    }

    @Test
    void addToDictionary() {

        Assert.assertFalse(SpellCheckDictionaryTxt.getDictionary().contains("want"));
        SpellCheckDictionaryTxt.addToDictionary("want");
        Assert.assertTrue(SpellCheckDictionaryTxt.getDictionary().contains("want"));

        Assert.assertFalse(SpellCheckArrayList.getDictionary().contains("vigorous"));
        SpellCheckArrayList.addToDictionary("vigorous");
        Assert.assertTrue(SpellCheckArrayList.getDictionary().contains("vigorous"));

        System.out.println("AddToDictionary Tests Complete.");

    }

    @Test
    void removeFromDictionary() {

        Assert.assertTrue(SpellCheckDictionaryTxt.getDictionary().contains("orgasms"));
        SpellCheckDictionaryTxt.removeFromDictionary("orgasms");
        Assert.assertFalse(SpellCheckDictionaryTxt.getDictionary().contains("orgasms"));

        Assert.assertTrue(SpellCheckArrayList.getDictionary().contains("bitter"));
        SpellCheckArrayList.removeFromDictionary("bitter");
        Assert.assertFalse(SpellCheckArrayList.getDictionary().contains("bitter"));

        System.out.println("RemoveFromDictionary Tests Complete.");



    }

    @Test
    void spellCheck() {


        ArrayList<String> expectedMisspelled = new ArrayList<>();
        expectedMisspelled.add("aple");
        expectedMisspelled.add("banna");
        expectedMisspelled.add("cr");
        expectedMisspelled.add("got");
        expectedMisspelled.add("swig");
        expectedMisspelled.add("laug");
        expectedMisspelled.add("lve");
        expectedMisspelled.add("sweat");
        expectedMisspelled.add("bittr");

        ArrayList<String> actualMisspelled = SpellCheckArrayList.spellCheck(new File("src/assignment05/test_words.txt"));

        Assert.assertEquals(expectedMisspelled,actualMisspelled );

        ArrayList<String> expectedMisspelled2 = new ArrayList<>();
        expectedMisspelled2.add("mediesvalists");
        expectedMisspelled2.add("salesslady");
        expectedMisspelled2.add("sacrsed");
        expectedMisspelled2.add("gamesx");


        ArrayList<String> actualMisspelled2 = SpellCheckDictionaryTxt.spellCheck(new File("src/assignment05/test_words2.txt"));

        Assert.assertEquals(expectedMisspelled2, actualMisspelled2 );

        System.out.println("SpellCheck Tests Complete.");

    }
}