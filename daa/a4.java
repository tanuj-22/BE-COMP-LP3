import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FinalNQueens {

    public boolean isSafe(int row,int col,char[][] board){

        int rowVal = row, colVal = col;

        while(row >=0 && col>=0){
            if(board[row--][col--] == 'Q') return false;

        }

        row = rowVal;
        col = colVal;

        while(row < board.length && col < board.length){
            if(board[row++][col++] == 'Q') return false;

        }

        row = rowVal;
        col = colVal;




        while(col>=0 && row < board.length){
            if(board[row++][col--] == 'Q') return false;
        }

        row = rowVal;
        col = colVal;

        while(col < board.length && row >=0){
            if(board[row--][col++] == 'Q') return false;
        }

        row = rowVal;
        col = colVal;

        while(col >=0){
            if(board[row][col--] == 'Q') return false;
        }
        col = colVal;
        while(col <board.length){
            if(board[row][col++] == 'Q') return false;
        }


        return true;

    }

    public void addBoard(List<List<String>> result, char[][] board){
        List<String> temp = new ArrayList<>();

        for(int i=0;i<board.length;i++){
            temp.add(String.valueOf(board[i]));
        }

        result.add(temp);



    }
    public void solve(int col,char[][] board,int[] res,List<List<String>> result,int alreadyPlacedCol){

        if(col == board.length){
            addBoard(result,board);
            res[0]++;
            return;
        }
        if(col == alreadyPlacedCol){
            solve(col+1,board,res,result,alreadyPlacedCol);
        }

        for(int row = 0;row < board.length;row++){

            if(isSafe(row,col,board)){

                board[row][col] = 'Q';
                solve(col+1,board,res,result,alreadyPlacedCol);
                board[row][col] = '.';



            }


        }


    }

    public void printBoards(List<List<String>> res){
        int count = 1;

        for(List<String> x : res){

            System.out.println("Solution "+ count++ + " : ");

            for(String y : x){
                System.out.println(y);
            }


            System.out.println();
        }
    }

    public int totalNQueens(int n,int i1,int j1) {

        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        board[i1][j1] = 'Q';
        int res[] = new int[]{0};

        solve(0,board,res,result,j1);
        printBoards(result);
        return res[0];

    }



    public static void main(String[] args) throws IOException {
        System.out.println("Enter The Value of N : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FinalNQueens obj1 = new FinalNQueens();
        int n = Integer.parseInt(br.readLine());
        System.out.println("Enter coordinates of 1st Queen : ");
        int i = Integer.parseInt(br.readLine()) - 1;
        int j = Integer.parseInt(br.readLine()) - 1;

        if(i < 0 || j <0 || i >= n || j>= n ){
            System.out.println("Wrong Coordinates");
            System.exit(0);
        }
        System.out.println("Total Solution : "+obj1.totalNQueens(n,i,j));
        br.close();
    }
}



//    We will discuss in this section the time complexity of the code above of N Queens Backtracking Algorithm.
//
//        Let N be the number of total queens, n the number of left queens and N-n thus, the number of already placed queens.
//
//        First, Let’s find the number of all possible arrangements of n Queens on n*n chessboard without satisfying the special conditions. This means we will go through every position n for every queen n, which results in N^Narrangements. This gives the brute force solution without using backtracking.
//
//        However, the backtracking approach reduces the time complexity to: O(n!) because it eliminates dead ends. In the given code, the main program calls the function queen(n) which calls the function place(k). The function place(k) checks the already placed queens. So it has O(N-n) worst case running time. Since O(N-n)<O(N), we can write O(N-n)=O(N)
//        Since the function queen iterates n times and for each iteration it calls the function place(row) function the total time complexity would be O(N^2).
//
//        Besides, queen(k,n) is a recursive function. Thus, the recursive call of T(n-1) will run n times because it will run only for the safe cells. Since we have started by filling up the rows, there won't be more than n (number of queens left) safe cells in the row in any case. Which in turn results in nT(n-1) times. By adding the results above we get T(n)= nT(n-1) + O(N^2) By recursion Tree:
//

//    Replacing T(n−1) with O(N^2)+(n−1)T(n−2):
//
//        T(n) = O(N^2)+n∗(O(N^2)+(n−1)T(n−2))T(n) = O(N^2)+n∗(O(N^2)+(n−1)T(n−2)) =O(N^2)+nO(N^2)+n(n−1)T(n−2)
//
//        Replacing T(n−2) with O(N^2)+(n−2)T(n−3):
//
//        T(n)=O(N^2)+nO(N^2)+n(n−1)(O(N^2)+(n−2)T(n−3))T(n) =O(N^2)+nO(N^2)+n(n−1)(O(N^2)+(n−2)T(n−3)) =O(N^2)+nO(N^2)+n(n−1)O(N^2)+n(n−1)(n−2)T(n−3)
//
//        Similarly:
//
//        T(n)=O(N^2)(1+n+n(n−1)+n(n−2)+...)+n∗(n−1)∗(n−2)∗(n−3)∗(n−4)∗....∗T(0)T(n) =O(N^2)(1+n+n(n−1)+n(n−2)+...)+n∗(n−1)∗(n−2)∗(n−3)∗(n−4)∗....∗T(0)T(n) =O(N^2)(O((n−2)!))+n∗(n−1)∗(n−2)∗(n−3)∗....∗T(0)T(n) =O(N^2)(O((n−2)!))+n∗(n−1)∗(n−2)∗(n−3)∗....∗T(0) =O(N^2)(O((n−2)!))+O(n!)= O(n!) According to the definition of Big-Oh notation
//
//        So we can conclude that the time complexity of N Queens Algorithm is O(n!)
//    Time Complexity: Exponential in nature, since we are trying out all ways. To be precise it goes as O
//
//        (N! * N) nearly.
//
//        Space Complexity: O(N^2)


//    Time Complexity: Exponential in nature since we are trying out all ways, to be precise it is O(N! * N).
//
//        Space Complexity: O(N)


//import java.util.*;
//class TUF {
//    public static List < List < String >> solveNQueens(int n) {
//        char[][] board = new char[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                board[i][j] = '.';
//        List < List < String >> res = new ArrayList < List < String >> ();
//        dfs(0, board, res);
//        return res;
//    }
//
//    static boolean validate(char[][] board, int row, int col) {
//        int duprow = row;
//        int dupcol = col;
//        while (row >= 0 && col >= 0) {
//            if (board[row][col] == 'Q') return false;
//            row--;
//            col--;
//        }
//
//        row = duprow;
//        col = dupcol;
//        while (col >= 0) {
//            if (board[row][col] == 'Q') return false;
//            col--;
//        }
//
//        row = duprow;
//        col = dupcol;
//        while (col >= 0 && row < board.length) {
//            if (board[row][col] == 'Q') return false;
//            col--;
//            row++;
//        }
//        return true;
//    }
//
//    static void dfs(int col, char[][] board, List < List < String >> res) {
//        if (col == board.length) {
//            res.add(construct(board));
//            return;
//        }
//
//        for (int row = 0; row < board.length; row++) {
//            if (validate(board, row, col)) {
//                board[row][col] = 'Q';
//                dfs(col + 1, board, res);
//                board[row][col] = '.';
//            }
//        }
//    }
//
//
//
//    static List < String > construct(char[][] board) {
//        List < String > res = new LinkedList < String > ();
//        for (int i = 0; i < board.length; i++) {
//            String s = new String(board[i]);
//            res.add(s);
//        }
//        return res;
//    }
//    public static void main(String args[]) {
//        int N = 4;
//        List < List < String >> queen = solveNQueens(N);
//        int i = 1;
//        for (List < String > it: queen) {
//            System.out.println("Arrangement " + i);
//            for (String s: it) {
//                System.out.println(s);
//            }
//            System.out.println();
//            i += 1;
//        }
//
//    }
//}



//branch and bound
//class Solution {
//
//    public List<String> addBoard(char[][] board){
//        List<String> res = new ArrayList<>();
//        for(int i=0 ; i< board.length;i++){
//            String temp = new String(board[i]);
//            res.add(temp);
//        }
//
//        return res;
//
//    }
//
//
//    public void solve(int col,int n,List<List<String>> res,char[][] board,int[] rowFilled,int[] leftDiag,int[] rightDiag){
//        if(col==n){
//            res.add(addBoard(board));
//            return;
//        }
//
//        for(int row=0;row<n;row++){
//            if(rowFilled[row]==0 && leftDiag[row+col]==0 && rightDiag[n-1+row-col]==0){
//
//                rowFilled[row]=1;
//                leftDiag[row+col]=1;
//                rightDiag[n-1+row-col]=1;
//                board[row][col]='Q';
//
//                solve(col+1,n,res,board,rowFilled,leftDiag,rightDiag);
//
//                rowFilled[row]=0;
//                leftDiag[row+col]=0;
//                rightDiag[n-1+row-col]=0;
//                board[row][col]='.';
//
//
//
//            }
//        }
//
//    }
//
//    public List<List<String>> solveNQueens(int n) {
//
//        char[][] board = new char[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                board[i][j] = '.';
//
//        List<List<String>> res = new ArrayList<>();
//
//        int[] leftRow = new int[n];
//        int[] upperDiagonal = new int[2*n -1];
//        int[] lowerDiagonal = new int[2*n -1];
//        solve(0,n,res,board,leftRow,upperDiagonal,lowerDiagonal);
//
//        return res;
//
//    }
//}




//    Backtracking is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time (by time, here, is referred to the time elapsed till reaching any level of the search tree).  Backtracking can also be said as an improvement to the brute force approach. So basically, the idea behind the backtracking technique is that it searches for a solution to a problem among all the available options.  Initially, we start the backtracking from one possible option and if the problem is solved with that selected option then we return the solution else we backtrack and select another option from the remaining available options. There also might be a case where none of the options will give you the solution and hence we understand that backtracking won’t give any solution to that particular problem. We can also say that backtracking is a form of recursion. This is because the process of finding the solution from the various option available is repeated recursively until we don’t find the solution or we reach the final state. So we can conclude that backtracking at every step eliminates those choices that cannot give us the solution and proceeds to those choices that have the potential of taking us to the solution.