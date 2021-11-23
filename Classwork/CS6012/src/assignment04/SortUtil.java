package assignment04;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.Callable;

public class SortUtil {

    /* insertion sort is used as the base case for MergeSort and QuickSort
    * used for arrayLists of size 15 or less.
    * @Param arr is arrayList to be sorted
    * @Param comparator is the comparator for the objects in the arrayList
    * */
   private static <T> void insertionSort(ArrayList<T> arr, Comparator<? super T> comparator, int Right, int Left){

           int n = arr.size();
           for (int i = 1; i < n; ++i) {
               T key = arr.get(i);
               int j = i - 1;

               while (j >= 0 && (comparator.compare(arr.get(j), key) > 0)) {
                   arr.set(j + 1, arr.get(j));
                   j = j - 1;
               }
               arr.set(j + 1, key);
           }

   }

    public static <T> void mergesort(ArrayList<T> arrayList, Comparator<? super T> comparator) {

    }
    /* quickSortDriver is used as the driver function for the recursive quicksort function
     * @Param arr is arrayList to be sorted
     * @Param c is the comparator for the objects in the arrayList
     * */

    public static <T> void quickSortDriver(ArrayList<T> arrayList, Comparator<? super T> c) {
        quicksort(arrayList, c, 0, arrayList.size() - 1);
    }

    /* quicksort is the recursive quicksort function
     * @Param arr is arrayList to be sorted
     * @Param c is the comparator for the objects in the arrayList
     * @Param Left is the first position in the array
     * @Param Right is the last position in the array
     * */
    private static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> comparator, int Left, int Right) {

        int cutoff = 30; // cutoff determines the size of array which will be sent to insertion sort
        if (Left + cutoff >= Right) {
            insertionSort(arrayList, comparator, Left, Right);
            return;
        }

        else {
            // takes 0 for a random pivot, 1 for the rightmost value as pivot, any other number for median pivot
            T pivot = getPivot(3, arrayList, comparator, Left, Right);

            //moves right and left pointers until they cross
            int i, j;
            for (i = Left, j = Right - 1; ; ) {

                while (comparator.compare(arrayList.get(++i), pivot) < 0)
                    ;
                while (j > 0 && comparator.compare(pivot, arrayList.get(--j)) < 0)
                    ;
                if (i >= j) {
                    break;
                }


                swap(arrayList, i, j);


            }
            swap(arrayList, i, Right - 1); //restore pivot


            quicksort(arrayList, comparator, Left, i - 1); // call quicksort on left side

            quicksort(arrayList, comparator, i + 1, Right); // call quicksort on right side
        }
    }

    /* swap is called within the recursive quicksort function
     * @Param arr is arrayList which needs items swapped
     * @Param left if the left option to swap
     * @Param right is the right object to swap
     * */


    private static <T> void swap(ArrayList<T> arrayList, int left, int right) {

        T temp = arrayList.get(left );
        arrayList.set(left, arrayList.get(right));
        arrayList.set(right, temp);

;

    }

    /* generateBestCase returns an ascending order Integer arrayList of size @Param size
     * @Param size is desired size of arrayList
     * @Param c is the comparator for the objects in the arrayList
     * @Return sorted ArrayList
     * */

    public static ArrayList<Integer> generateBestCase(int size){

        ArrayList<Integer> bestCase = new ArrayList<>();

        for(int i = 0; i < size; i++){
            bestCase.add(i);
        }

        return bestCase;
    }

    /* generateAverageCase returns an Integer Arraylist with all numbers 0 - (size-1) in random order
     * @Param size is desired size of arrayList
     * @Return shuffled ArrayList
     * */

    public static ArrayList<Integer> generateAverageCase(int size){
       Random random = new Random();
       ArrayList<Integer> averageCase = generateBestCase(size);
       Collections.shuffle(averageCase);


       return averageCase;


    }

    /* generateWorstCase returns an Integer Arraylist with all numbers 0 - (size-1) in descending order
     * @Param size is desired size of arrayList
     * @Return descending order Integer ArrayList
     * */

    public static ArrayList<Integer> generateWorstCase(int size){

        ArrayList<Integer> worstCase = new ArrayList<>();

        for(int i = size - 1; i >= 0; i--){
            worstCase.add(i);
        }

        return worstCase;
    }

    private static <T> T getPivot(int a, ArrayList<T> arrayList, Comparator comparator, int Left, int Right){
       T pivot;
       if( a == 0){
           Random random = new Random();
           int randomPos = random.nextInt(arrayList.size() - 2) + 1;

           swap(arrayList, randomPos, Right - 1);
           pivot = arrayList.get(Right - 1);
//           System.out.println("CHOSE OPTION 0 -- Random");


       }
       else if(a == 1){
           int first = Right;


           swap(arrayList, first, Right - 1);
           pivot = arrayList.get(Right - 1);
//           System.out.println("CHOSE OPTION 1 -- FIRST");



       }
       else{

           int middle = (Left + Right) / 2;

           if (comparator.compare(arrayList.get(middle), arrayList.get(Left)) < 0)
               swap(arrayList, Left, middle);
           if (comparator.compare(arrayList.get(Left), arrayList.get(Right)) < 0)
               swap(arrayList, Left, Right);
           if (comparator.compare(arrayList.get(Left), arrayList.get(Right)) < 0)
               swap(arrayList, middle, Left);


           swap(arrayList, middle, Right - 1);
           pivot = arrayList.get(Right - 1);
//           System.out.println("CHOSE MEDIAN");




       }

       return pivot;
    }
}
