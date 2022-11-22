import java.util.Arrays;

public class KnapSack {
    static int knapSack(int W, int wt[], int val[], int n)
    {
        int []dp = new int[W + 1];
    
    
        for (int i = 1; i < n + 1; i++) {
            for (int w = W; w >= 0; w--) {
        
                if (wt[i - 1] <= w)
                    dp[w] = Math.max(dp[w], dp[w - wt[i - 1]] + val[i - 1]);
            }
        }
        return dp[W];
    }
 
    static int knapSackRecursive(int W, int[] wt, int[] val, int n, int[][] dp){
        if(n==0){
            if(wt[n] <= W){
                return val[n];
            }return 0;
        }
        if(dp[W][n] != -1){
            return dp[W][n];
        }
        // pick
        int pick = Integer.MIN_VALUE;
        if(wt[n] <= W){
            pick = val[n] + knapSackRecursive(W-wt[n], wt, val, n-1, dp);
        }
        // not pick
        int notpick = knapSackRecursive(W, wt, val, n-1, dp);

        return dp[W][n] = Math.max(pick, notpick);
    }

    static int knapSackTabulation(int W, int[] val, int[] wt, int n){
        int[][] dp = new int[n][W+1];

        for(int i=wt[0]; i<=W; i++){
            dp[0][i] = val[0];
        }

        for(int i=1; i<n; i++){
            for(int j=0; j<=W; j++){
                int not = dp[i-1][j];
                int pick = Integer.MIN_VALUE;
                if(wt[i] <= j){
                    pick = val[i] + dp[i-1][j-wt[i]];
                }
                dp[i][j] = Math.max(not, pick);
            }
        }
        return dp[n-1][W];
    }

    public static void main(String args[])
    {
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;
        System.out.println(knapSackTabulation(W, val, wt, n));
        int[][] dp = new int[W+1][n];
        for(int[] item:dp){
            Arrays.fill(item, -1);
        }
        int res = knapSackRecursive(W, wt, val, n-1, dp);
        System.out.println(res);
    }
}