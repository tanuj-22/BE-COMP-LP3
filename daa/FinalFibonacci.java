import java.util.Scanner;

public class FinalFibonacci {
    public static int fibonacciRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public static int fibonacciIterative(int n){
        if(n==0) return 0;
        int prev = 0;
        int prev2 = 1;

        for(int i=2; i<=n; i++){
            int res = prev + prev2;
            prev = prev2;
            prev2 = res;
        }
        return prev2;
    }


    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter n : ");
        int N = scan.nextInt();

        System.out.println(fibonacciIterative(N));
        System.out.println(fibonacciRecursive(N));


    }
}
//tc - o(n)
//sc - o(1)

// x2 = x + 1

//x = (1 +- root5)/2

//f(n) = (a1)^n + (a2)^n

// O(1.618)^n
//1.6180 is also called the golden ratio
// modulo exponentiation

//0 0  fn+1  fn
//0 1    fn   fn+1  ---> logn
