import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int n = friends.length;
        
        HashMap<String, Integer> nameMap = new HashMap<>();
        int[][] giftMat = new int[n][n];
        int[][] giftPoint = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            String f = friends[i];
            nameMap.put(f, i);
        }
        
        for (int i = 0; i < gifts.length; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            int from = nameMap.get(st.nextToken());
            int to = nameMap.get(st.nextToken());
            giftMat[from][to]++;
            giftPoint[from][0]++;
            giftPoint[to][1]++;
        }
        
        for (int i = 0; i < n; i++) {
            giftPoint[i][2] = giftPoint[i][0] - giftPoint[i][1];
        }
        
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                int diff = giftMat[i][j] - giftMat[j][i];
                if (diff > 0 || (diff == 0 && giftPoint[i][2] > giftPoint[j][2])) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
}