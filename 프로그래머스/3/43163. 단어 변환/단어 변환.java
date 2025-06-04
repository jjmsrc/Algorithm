class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] isVisited = new boolean[words.length];
        answer = findMin(begin, target, words, isVisited);
        answer = answer == 100 ? 0 : answer;
        
        return answer;
    }
    
    private int findMin(String source, String target, String[] words, 
                        boolean[] isVisited) {
        
        int dist = 100;
        
        for (int i = 0; i < words.length; i++) {
            if (isVisited[i]) continue;
            int diff = calcDiff(source, words[i]);
            if (diff != 1) continue;
            System.out.println(source + " : " +  words[i]);
            if (words[i].compareTo(target) == 0)
                return 1;
            isVisited[i] = true;
            int res = findMin(words[i], target, words, isVisited);
            isVisited[i] = false;
            if (dist > res + 1) {
                dist = res + 1;
            }
        }
        
        return dist;
    }
    
    private int calcDiff(String a, String b) {
        int cnt = 0;
        
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                cnt++;
        }
        
        return cnt;
    }
}