class Solution {
    
    private static int[] calcPos(int num) {
        int[] ans = new int[2];
        if (num == 10){
            ans[0] = 3;
            ans[1] = 0;
        } else if (num == 11){
            ans[0] = 3;
            ans[1] = 2;
        } else if (num == 0){
            ans[0] = 3;
            ans[1] = 1;
        } else {
            ans[0] = (num - 1) / 3;
            ans[1] = (num - 1) % 3 ;
        }
        
        return ans;
    }
    
    private static int calcDist(int src, int dst) {
        
        int[] srcPos = calcPos(src);
        int[] dstPos = calcPos(dst);
        
        int dist = Math.abs(srcPos[0] - dstPos[0]) + 
            Math.abs(srcPos[1] - dstPos[1]);
        
        return dist;
    }
    
    private static char whichHand(char hand, int left, int right, int num){
        
        int leftDist = calcDist(left, num);
        int rightDist = calcDist(right, num);
        
        if (leftDist < rightDist) {
            return 'L';
        } else if(leftDist == rightDist) {
            return hand;
        } else {
            return 'R';
        }
    }

    public String solution(int[] numbers, String hand) {
        
        int idxAnswer = 0;
        char[] answer = new char[numbers.length];
        int left = 10, right = 11;
        
        for(int num: numbers){
            if (num % 3 == 1){
                answer[idxAnswer++] = 'L';
                left = num;
            } else if (num % 3 == 0 && num != 0) {
                answer[idxAnswer++] = 'R';
                right = num;
            } else {
                char h = hand.equals("left") ? 'L' : 'R';
                char ans = whichHand(h, left, right, num);
                answer[idxAnswer++] = ans;
                if (ans == 'L')
                    left = num;
                else
                    right = num;
            }
        }
        
        return new String(answer);
    }
}