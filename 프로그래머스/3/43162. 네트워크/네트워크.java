class Solution {
    
    private int n;
    private boolean[] isVisited;
    private int[][] computers;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        this.n = n;
        this.isVisited = new boolean[n];
        this.computers = computers;
        
        for (int i = 0; i < n; i++) {
            if (isVisited[i])
                continue;
            dfs(i);
            answer++;
        }
        
        return answer;
    }
    
    private void dfs(int v) {
        isVisited[v] = true;
        for (int i = 0; i < n; i++) {
            if (isVisited[i] || computers[v][i] == 0)
                continue;
            dfs(i);
        }
    }
}