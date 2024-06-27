import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int iCmd = 0; iCmd < commands.length; iCmd++) {
            int[] cmd = commands[iCmd];
            int i = cmd[0] - 1, j = cmd[1] - 1, k = cmd[2] - 1;
            int[] tmpArray = new int[j - i + 1];
            for (int x = i, y = 0; x <= j; x++, y++) {
                tmpArray[y] = array[x];
            }
            Arrays.sort(tmpArray);
            answer[iCmd] = tmpArray[k];
        }
        
        return answer;
    }
}