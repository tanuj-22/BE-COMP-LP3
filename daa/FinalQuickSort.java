import java.util.ArrayList;
import java.util.Arrays;

public class FinalQuickSort {

    public void swap(int arr[],int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public int partition(int[] arr,int pivot,int low,int high){


        int start = low - 1;
        for(int i=low;i<high;i++){

            if(arr[pivot] > arr[i]){
                start++;
                swap(arr,start,i);
            }
        }
        swap(arr,pivot,start+1);

        return start+1;

    }

    public void quicksort(int [] arr,int low,int high){
        if(low >= high) return;
        int pivot = high;
        int p = partition(arr,pivot,low,high);

        quicksort(arr,low,p-1);
        quicksort(arr,p+1,high);


    }
    public int rpartition(int[] arr,int low,int high){
        int pivot = high;
        int randint = (int)(Math.floor(Math.random() * (high - low) + low));
        swap(arr,randint,high);


        int start = low - 1;
        for(int i=low;i<high;i++){

            if(arr[pivot] > arr[i]){
                start++;
                swap(arr,start,i);
            }
        }
        swap(arr,pivot,start+1);

        return start+1;

    }


    public void randomquicksort(int [] arr,int low,int high){
        if(low >= high) return;

        int p = rpartition(arr,low,high);

        randomquicksort(arr,low,p-1);
        randomquicksort(arr,p+1,high);


    }


    public static void main(String[] args) {
        int arr[] ;
        arr = new int[10000];
        for(int i=0;i<10000;i++){
            arr[i] = i;
        }

        FinalQuickSort obj1 = new FinalQuickSort();

        long startTime = System.nanoTime();

        obj1.randomquicksort(arr,0,arr.length-1);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Random " + totalTime);


        System.out.println(Arrays.toString(arr));

        arr = new int[10000];
        for(int i=0;i<10000;i++){
            arr[i] = i;
        }
        startTime = System.nanoTime();

        obj1.quicksort(arr,0,arr.length-1);

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Quick "+totalTime);

        System.out.println(Arrays.toString(arr));



    }
}





//    Analysis of QuickSort
//        Time taken by QuickSort, in general, can be written as follows.
//
//        T(n) = T(k) + T(n-k-1) + \theta               (n)
//
//        The first two terms are for two recursive calls, the last term is for the partition process. k is the number of elements that are smaller than the pivot.
//        The time taken by QuickSort depends upon the input array and partition strategy. Following are three cases.
//
//        Worst Case:
//        The worst case occurs when the partition process always picks the greatest or smallest element as the pivot. If we consider the above partition strategy where the last element is always picked as a pivot, the worst case would occur when the array is already sorted in increasing or decreasing order. Following is recurrence for the worst case.
//
//        T(n) = T(0) + T(n-1) + \theta               (n)which is equivalent to  T(n) = T(n-1) + \theta               (n)
//
//        The solution to the above recurrence is                    (n2).
//
//        Best Case:
//        The best case occurs when the partition process always picks the middle element as the pivot. The following is recurrence for the best case.
//
//        T(n) = 2T(n/2) + \theta               (n)
//
//        The solution for the above recurrence is                   (nLogn).




// first pivot
//int partition(int arr[], int low, int high)
//{
//
//    // First element as pivot
//    int pivot = arr[low];
//    int st = low; // st points to the starting of array
//    int end = high; // end points to the ending of the array
//    int k = high;
//    for (int i = high; i > low; i--) {
//        if (arr[i] > pivot)
//            swap(arr[i], arr[k--]);
//    }
//    swap(arr[low], arr[k]);
//    // As we got pivot element index is end
//    // now pivot element is at its sorted position
//    // return pivot element index (end)
//    return k;
//}
//
//
//    Quick sort is based on the divide-and-conquer approach based on the idea of choosing one element as a pivot element and partitioning the array around it such that: Left side of pivot contains all the elements that are less than the pivot element Right side contains all elements greater than the pivot
//
//        It reduces the space complexity and removes the use of the auxiliary array that is used in merge sort. Selecting a random pivot in an array results in an improved time complexity in most of the cases.

//
//    int partition ( int A[],int start ,int end) {
//        int i = start + 1;
//        int piv = A[start] ;            //make the first element as pivot element.
//        for(int j =start + 1; j <= end ; j++ )  {
//    /*rearrange the array by putting elements which are less than pivot
//       on one side and which are greater that on other. */
//
//            if ( A[ j ] < piv) {
//                swap (A[ i ],A [ j ]);
//                i += 1;
//            }
//        }
//        swap ( A[ start ] ,A[ i-1 ] ) ;  //put the pivot element in its proper place.
//        return i-1;                      //return the position of the pivot
//    }