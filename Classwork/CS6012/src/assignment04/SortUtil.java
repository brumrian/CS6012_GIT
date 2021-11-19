package assignment04;

import java.util.*;
import java.util.concurrent.Callable;

public class SortUtil {

    SortUtil(){

    }

    public static <T> void mergesort(ArrayList<T> arrayList, Comparator<? super T> comparator){

    }

    private static <T> int partition(ArrayList<T> arrayList, Comparator<? super T> comparator, int Left, int Right){

//        if(arrayList.size() <= 10) {
//            Collections.sort(arrayList, comparator);
//
//        }

        if(arrayList.size() == 0){
            return Left;
        }



        Random random = new Random();
        ArrayList<T> findPivot = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            int getRandom = random.nextInt(arrayList.size()-1);// This gets me a random int between 0 and size;
            findPivot.add(arrayList.get(getRandom));
        }

        Collections.sort(findPivot, comparator);

        T pivot = findPivot.get(1);


        while(Left<= Right){

            int compareLeft = comparator.compare(arrayList.get(Left), pivot);
            int compareRight = comparator.compare(arrayList.get(Right), pivot);
            if(compareLeft <= 0){
                Left++;
                continue;
            }
            if(compareRight >=0 ){
                Right++;
                continue;
            }
            swap(arrayList, Left, Right);
            Left++; Right--;
        }
        swap(arrayList, Left, Right);
        return Left;
    }



    public static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> comparator, int Left, int Right){

        if(Left >= Right){
            return;
        }

        int pivotIndex = partition(arrayList, comparator, Left, Right);
        quicksort(arrayList, comparator, Left, pivotIndex-1);
        quicksort(arrayList, comparator,pivotIndex+1, Right);

    }





    private static <T> void swap(ArrayList<T> arrayList, int left, int right) {
    }

    public static ArrayList<Integer> generateBestCase(int size){

        ArrayList<Integer> bestCase = new ArrayList<>();

        for(int i = 0; i < size; i++){
            bestCase.add(i);
        }

        return bestCase;
    }

    public static ArrayList<Integer> generateAverageCase(int size){
        return new ArrayList<>();
    }

    public static ArrayList<Integer> generateWorstCase(int size){

        ArrayList<Integer> worstCase = new ArrayList<>();

        for(int i = size - 1; i >= 0; i--){
            worstCase.add(i);
        }

        return worstCase;
    }
}
