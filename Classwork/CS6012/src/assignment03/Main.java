package assignment03;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BinarySearchSet<String> stringSet = new BinarySearchSet<>();
        ArrayList<String> strings = new ArrayList<>();

        strings.add("Banana");
        strings.add("Apple");
        strings.add("Teeth");
        strings.add("Running");
        strings.add("Zoo");
        strings.add("Friendly");
        strings.add("Quick");
        strings.add("Car");


        stringSet.addAll(strings);

        if(stringSet.add("Banana") || stringSet.add("Apple") || stringSet.add("Teeth")
                || stringSet.add("Running") || stringSet.add("Zoo") || stringSet.add("Friendly")
                || stringSet.add("Quick") || stringSet.add("Car")){
            System.err.println("TEST FAILED -- stringSet add(duplicate");
        }

        if(stringSet.addAll(strings)){
            System.err.println("TEST FAILED -- stringSet addAll(duplicates");
        }

        if(!stringSet.contains("Banana") || !stringSet.contains("Apple") || !stringSet.contains("Teeth")
                || !stringSet.contains("Running") || !stringSet.contains("Zoo") || !stringSet.contains("Friendly")
                || !stringSet.contains("Quick") || !stringSet.contains("Car")){
            System.err.println("TEST FAILED -- stringSet !contains(element)");
        }

        if(!stringSet.containsAll(strings)){
            System.err.println("TEST FAILED -- stringSet !containsAll(elements)");

        }

        ArrayList<String> stringsNotInSet = new ArrayList<>();
        stringsNotInSet.add("Hello");
        stringsNotInSet.add("Not");
        stringsNotInSet.add("Joke");
        stringsNotInSet.add("Yes");
        stringsNotInSet.add("Funny");

        if(stringSet.contains(stringsNotInSet.get(0)) ||stringSet.contains(stringsNotInSet.get(1))
                || stringSet.contains(stringsNotInSet.get(2)) || stringSet.contains(stringsNotInSet.get(3))
                || stringSet.contains(stringsNotInSet.get(4))){
            System.err.println("TEST FAILED -- stringSet contains(element)");
        }

        if(stringSet.containsAll(stringsNotInSet)){
            System.err.println("TEST FAILED -- stringSet containsAll(elements)");

        }


        stringSet.remove("Car");

        if(stringSet.contains("Car")){
            System.err.println("TEST FAILED -- stringSet remove(element)");
        }
        if(!stringSet.removeAll(strings)){
            System.err.println("TEST FAILED -- stringSet removeAll(elements)");
        }




        BinarySearchSet<Integer> intSet = new BinarySearchSet<>();
        ArrayList<Integer> ints = new ArrayList<>();

        ints.add(-1000);
        ints.add(-100);
        ints.add(50);
        ints.add(0);
        ints.add(37);
        ints.add(500);
        ints.add(3000);
        ints.add(-21);
        ints.add(77);
        ints.add(-300);



        intSet.addAll(ints);

        if(intSet.add(-1000) || intSet.add(-100) || intSet.add(50) || intSet.add(0) || intSet.add(37)
                || intSet.add(500) || intSet.add(3000) || intSet.add(-21) || intSet.add(77) || intSet.add(-100)){
            System.err.println("TEST FAILED -- intSet add(duplicate)");

        }

        if(intSet.addAll(ints)){
            System.err.println("TEST FAILED -- intSet addAll(duplicates)");

        }

        if(!intSet.contains(-1000) || !intSet.contains(-100) || !intSet.contains(50) || !intSet.contains(0) || !intSet.contains(37)
                || !intSet.contains( 500) || !intSet.contains(3000) || !intSet.contains(-21) || !intSet.contains(77) || !intSet.contains(-100)){
            System.err.println("TEST FAILED -- intSet !contains(element)");

        }

        if(!intSet.containsAll(ints)){
            System.err.println("TEST FAILED -- intSet !containsAll(elements)");
        }

        ArrayList<Integer> intsNotInSet = new ArrayList<>();
        intsNotInSet.add(499);
        intsNotInSet.add(53);
        intsNotInSet.add(550);
        intsNotInSet.add(-77);
        intsNotInSet.add(-3000);

        if(ints.contains(intsNotInSet.get(0)) || intSet.contains(intsNotInSet.get(1)) || intSet.contains(intsNotInSet.get(2))
                || intSet.contains(intsNotInSet.get(3)) || intSet.contains(intsNotInSet.get(4))){
            System.err.println("TEST FAILED -- intSet contains(element)");
        }

        if(intSet.containsAll(intsNotInSet)){
            System.err.println("TEST FAILED -- intSet ContainsAll(elements)");
        }

        if(intSet.remove(77));

        if(intSet.contains(77)){
            System.err.println("TEST FAILED -- intSet remove(element)");
        }

        if(!intSet.removeAll(ints)){
            System.err.println("TEST FAILED -- intSet removeAll(elements)");
        }



        System.out.println("TESTING DONE.");


    }

}
