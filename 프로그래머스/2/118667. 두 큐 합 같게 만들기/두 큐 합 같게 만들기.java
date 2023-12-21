import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long sum1 = 0, sum2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>(), q2 = new ArrayDeque<>();
        int maxSize = queue1.length * 3;
        
        for(int num : queue1) {
            sum1 += num;
            q1.offer(num);
        }
        for(int num : queue2) {
            sum2 += num;
            q2.offer(num);
        }
        
        answer = 0;
        while(sum1 != sum2 && answer < maxSize) {
            answer += 1;
            if (sum1 > sum2) {
                int num = q1.poll();
                sum1 -= num;
                sum2 += num;
                q2.offer(num);
            } else {
                int num = q2.poll();
                sum1 += num;
                sum2 -= num;
                q1.offer(num);
            }
        }
        
        if (answer >= maxSize)
            answer = -1;
        
        return answer;
    }
}