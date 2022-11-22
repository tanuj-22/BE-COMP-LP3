import java.io.*;
import java.util.*;

public class RandomisedQuickSort {

    public static int random(int l, int h){
        Random r = new Random();
        int i = r.nextInt(h-l) + l;
        return i;
    }

    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    static int partition(int[] arr, int l, int h){
        int pi = arr[h];

        int i = l-1;
        for(int j=l; j<h; j++){
            if(arr[j] < pi){
                i++;
                swap(arr, i, j);
            }
        }
        return i+1;
    }

    static void randomisedQuickSort(int[] arr, int l, int h){
        int r = random(l, h);
        swap(arr, r, h);

        if(l < h){
            int p = partition(arr, l, h);
            randomisedQuickSort(arr, l, p-1);
            randomisedQuickSort(arr, p+1, h);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.println();
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }


        scan.close();
    }
}
