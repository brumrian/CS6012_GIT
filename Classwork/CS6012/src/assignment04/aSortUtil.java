package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class aSortUtil <T> {

    private static int insertionThreshold = 5;

    /**
     * Allocates space for the temp array (which is a basic array) that is used in the merge routine.
     * Calls on the recursive mergesort algorithm (mergeSortRecursive).
     *
     * The purpose of this method is to be a driver for the mergesort algorithm. It allows the user to call
     * on mergesort by only passing in an ArrayList to sort and a comparator.
     *
     * @param inputArray The ArrayList that the user would like to sort. Type T, so is a generic type
     *
     * @param comparator How the user would like to compare the elements in the inputArray to sort them.
     *
     */
    public static <T> void mergesort(ArrayList<T> inputArray, Comparator<? super T> comparator){

        // Allocate space for the temp array once here to avoid during so during every recursive call
        T[] temp = (T[]) new Object[inputArray.size()];

        // Want to sort entire array, so we want to start at index 0 (left) and end at the last index (right)
        int left = 0;
        int right = inputArray.size() - 1;

        // Call on the recursive mergeSort method
        mergeSortRecursive(inputArray, temp, comparator, left, right);

    }

    /**
     * quicksort is used as the driver function for the recursive quicksort function
     *
     * @param arrayList is arrayList to be sorted
     *
     * @param c is the comparator for the objects in the arrayList
     *
     */

    public static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> c) {
        quicksortRecursive(arrayList, c, 0, arrayList.size() - 1);
    }


    /**
     * Merges two subarrays into one sorted array, stores this sorted array in an temporary basic array, and then copies it
     * back over into the initially passed in inputArray.
     *
     * @param arr passed in from mergeSortRecursive. arr ends up being a subsection of the initial array passed in that we
     *            need to merge with another subsection as per the recursive nature of mergeSortRecursive.
     *
     * @param temp temporary basic array. Initialized in mergesort and passed in (so we only allocate the memory once).
     *
     * @param comparator How the user would like to compare the elements in the inputArray to sort them. Initially passed into
     *                   mergesort.
     *
     * @param left leftmost index of the left subarray to merge.
     *
     * @param mid leftmost index of the right subarray to merge.
     *
     * @param right rightmost index of the right subarray to merge.
     *
     */

    private static <T> void merge (ArrayList<T> arr, T[] temp, Comparator<? super T> comparator, int left, int mid, int right){

        // Marks the rightmost position of the left half of the array
        int leftArrayPos = mid - 1;
        // Need to keep track of the temp array's index
        int tempIndex = left;
        // How many elements we're looking at in the current array
        int numElements = right - left + 1;

        // left is the pointer for the left half of the array, and mid is the pointer for the right half of the array
        while (left <= leftArrayPos && mid <= right){

            // Use the comparator passed in to compare the first elements of both the left half and the right half
            int comparison = comparator.compare(arr.get(left), arr.get(mid));

            // If the element at the left half pointer is less than the value at the right half pointer, insert this
            // element into the temp array and increment both the temp array's index and the left pointer
            if (comparison <= 0){
                temp[tempIndex] = arr.get(left);
                tempIndex++;
                left++;
            }
            // If the element at the right half pointer is less than the value at the left half pointer, insert this
            // element into the temp array and increment both the temp array's index and the right pointer
            else {
                temp[tempIndex] = arr.get(mid);
                tempIndex++;
                mid++;
            }
        }

        // Insert any remaining elements from the left half of the array into the temp array
        while (left <= leftArrayPos){
            temp[tempIndex] = arr.get(left);
            tempIndex++;
            left++;
        }
        // Insert any remaining elements from the right half of the array into the temp array
        while (mid <= right){
            temp[tempIndex] = arr.get(mid);
            tempIndex++;
            mid++;
        }

        // Copy the elements from the temp array back into inputArray
        for (int i = 0; i < numElements; i++, right--){
            arr.set(right, temp[right]);
        }

        System.out.println("In merge routine");
    }

    /**
     * Makes recursive calls to divide the ArrayList passed in into smaller and smaller subarrays. Once one branch of the recursive
     * calls has hit the insertionsort threshold, calls on insertionsort to sort the subarray. Once both a left and a right branch of
     * the recursive calls has completed, calls on merge routine to combine the subarrays into one merged array.
     *
     * @param inputArray passed in from mergeSortRecursive. arr ends up being a subsection of the initial array passed in that we
     *            need recursively divide into smaller subarrays.
     *
     * @param temp temporary basic array. Initialized in mergesort and passed in (so we only allocate the memory once). Used to pass
     *             into merge routine.
     *
     * @param comparator How the user would like to compare the elements in the inputArray to sort them. Initially passed into
     *                   mergesort.
     *
     * @param left leftmost index of the array.
     *
     * @param right rightmost index of array.
     *
     */

    private static <T> void mergeSortRecursive(ArrayList<T> inputArray, T[] temp, Comparator<? super T> comparator, int left, int right){

        // Call insertion sort when the array size reaches the specified insertion sort threshold
        if (left + insertionThreshold >= right){
            insertionSort(inputArray, comparator, left, right);
            System.out.println("In insertion");
            return;
        }

        int mid = (left + right) / 2;

        mergeSortRecursive(inputArray, temp, comparator, left, mid);
        System.out.println("hit left");
        mergeSortRecursive(inputArray, temp, comparator, mid + 1, right);
        System.out.println("hit right");
        merge(inputArray, temp, comparator, left, mid + 1, right);
        System.out.println("hit merge");
//        System.out.println("In merge sort recursive");
    }

    /**
     * quicksort is the recursive quicksort function
     *
     * @param arrayList is arrayList to be sorted
     *
     * @param comparator is the comparator for the objects in the arrayList
     *
     * @param comparator How the user would like to compare the elements in the inputArray to sort them. Initially passed into
     *                   mergesort.
     *
     * @param Left is the first position in the array
     *
     * @param Right is the last position in the array
     *
     */

    private static <T> void quicksortRecursive(ArrayList<T> arrayList, Comparator<? super T> comparator, int Left, int Right) {

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


            quicksortRecursive(arrayList, comparator, Left, i - 1); // call quicksort on left side

            quicksortRecursive(arrayList, comparator, i + 1, Right); // call quicksort on right side
        }
    }

    /**
     * swap is called within the recursive quicksort function
     *
     * @param arrayList is arrayList which needs items swapped
     *
     * @param left if the left option to swap
     *
     * @param right is the right object to swap
     *
     */

    private static <T> void swap(ArrayList<T> arrayList, int left, int right) {

        T temp = arrayList.get(left );
        arrayList.set(left, arrayList.get(right));
        arrayList.set(right, temp);
    }

    /**
     * getPivot is used to change between pivot selection methods.
     *
     * @Param a is the code for pivot selection
     * If 0 is entered for a, the pivot will be chosen at random.
     * If 1 is entered for a, the first element will be chosen for the pivot.
     * If any other number is entered, the Median between the first, last, and middle element
     * will be chosen as the pivot
     *
     * @param arrayList is arrayList to be sorted
     *
     * @param comparator compares objects in arrayList
     *
     * @param Left is the first element of the arrayList
     *
     * @param Right is the last element of the arrayList
     *
     * @Return T is the pivot used for the recursive quicksort algorithm
     *
     */

    private static <T> T getPivot(int a, ArrayList<T> arrayList, Comparator comparator, int Left, int Right){
        T pivot;
        if( a == 0){
            Random random = new Random();
            int randomPos = random.nextInt(arrayList.size() - 2) + 1;

            swap(arrayList, randomPos, Right - 1);
            pivot = arrayList.get(Right - 1);

        }

        else if(a == 1){
            int first = Right;


            swap(arrayList, first, Right - 1);
            pivot = arrayList.get(Right - 1);
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
        }

        return pivot;
    }


    /**
     * insertion sort is used as the base case for MergeSort and QuickSort
     * used for arrayLists of size 15 or less.
     *
     * @param arr is arrayList to be sorted
     *
     * @param comparator is the comparator for the objects in the arrayList
     *
     */

    private static <T> void insertionSort(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right){
//        Comparator comparator = Comparator.naturalOrder();

        int j = 0;
        T key = null;

//        int n = right - left + 1;
        for (int i = left + 1; i <= right; ++i) {
            key = arr.get(i);
            j = i;

         /* Move elements of arr[0..i-1], that are
            greater than key, to one position ahead
            of their current position */
            while (j > left && (comparator.compare(arr.get(j - 1), key) > 0)) {
                arr.set(j, arr.get(j - 1));
                j--;
            }
            arr.set(j, key);
        }

    }

    /**
     * generateBestCase returns an ascending order Integer arrayList of size @Param size
     *
     * @param size is desired size of arrayList
     *
     * @return sorted ArrayList
     *
     */

    public static ArrayList<Integer> generateBestCase(int size){

        ArrayList<Integer> bestCase = new ArrayList<>();

        for(int i = 0; i < size; i++){
            bestCase.add(i);
        }

        return bestCase;
    }

    /**
     * generateAverageCase returns an Integer Arraylist with all numbers 0 - (size-1) in random order
     *
     * @param size is desired size of arrayList
     *
     * @return shuffled ArrayList
     *
     */

    public static ArrayList<Integer> generateAverageCase(int size){

        ArrayList<Integer> averageCase = generateBestCase(size);
        Collections.shuffle(averageCase);

        return averageCase;
    }

    /**
     * generateWorstCase returns an Integer Arraylist with all numbers 0 - (size-1) in descending order
     *
     * @param size is desired size of arrayList
     *
     * @return descending order Integer ArrayList
     *
     */

    public static ArrayList<Integer> generateWorstCase(int size){

        ArrayList<Integer> worstCase = new ArrayList<>();

        for(int i = size - 1; i >= 0; i--){
            worstCase.add(i);
        }

        return worstCase;
    }

}
