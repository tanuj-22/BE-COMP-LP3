import java.util.*;

public class NQueensP {
    static int cntr;
    static void printSolution(int[][] mat, int n){
        for(int i=0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.print(mat[i][j]+" ");
            }System.out.println();
        }
    }

    static boolean isSafe(int[][] mat, int row, int col, int n){
        for(int i=row; i>=0; i--){
            if(mat[i][col] == 1) return false;
        }
        for(int i=row,j=col; i>=0 && j>=0; i--,j--){
            if(mat[i][j] == 1) return false;
        }
        for(int i=row,j=col; i>=0 && j<n; i--,j++){
            if(mat[i][j] == 1) return false;
        }
        return true;
    }

    static boolean nQueensSolverUtil(int[][]mat, int row, int n, int fixedI, int fixedJ){
        if(row == n){
            return true;
        }
        if(row == fixedI){
            return nQueensSolverUtil(mat, row+1, n, fixedI, fixedJ);
        }

        for(int i=0; i<n; i++){
            if(i != fixedJ && isSafe(mat, row, i, n)){
                mat[row][i] = 1;
                if(nQueensSolverUtil(mat, row+1, n, fixedI, fixedJ) == true){
                    cntr++;
                    System.out.println("Solution: " + cntr);
                    printSolution(mat, n);
                }
                mat[row][i] = 0;
            }
        }
        return false;
    }


    public static void nQueensSolver(int n, int i, int j){
        int[][] mat = new int[n][n];
        mat[i][j] = 1;

        nQueensSolverUtil(mat, 0, n, i, j);
        if(cntr == 0){
            System.out.println("No solution exists!");
        }
    }

    public static void main(String[] args) {
        cntr = 0;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int i = scan.nextInt();
        int j = scan.nextInt();
        nQueensSolver(n, i, j);
        scan.close();
    }
}
Backtracking is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time (by time, here, is referred to the time elapsed till reaching any level of the search tree).  Backtracking can also be said as an improvement to the brute force approach. So basically, the idea behind the backtracking technique is that it searches for a solution to a problem among all the available options.  Initially, we start the backtracking from one possible option and if the problem is solved with that selected option then we return the solution else we backtrack and select another option from the remaining available options. There also might be a case where none of the options will give you the solution and hence we understand that backtracking won’t give any solution to that particular problem. We can also say that backtracking is a form of recursion. This is because the process of finding the solution from the various option available is repeated recursively until we don’t find the solution or we reach the final state. So we can conclude that backtracking at every step eliminates those choices that cannot give us the solution and proceeds to those choices that have the potential of taking us to the solution.