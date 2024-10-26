class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long l = 0;
        long r = 1_000_000_000L * 1_000_000_000L;
        long minTime = r;
        while(l < r) {
            long mid = (l + r) / 2;
            long maxPeople = 0;
            for(int t : times) {
                maxPeople += mid / t;
            }
            if (n <= maxPeople) {
                r = mid;
                if (minTime > mid)
                    minTime = mid;
            } else {
                l = mid + 1;
            }
        }
        answer = minTime;
        
        return answer;
    }
}