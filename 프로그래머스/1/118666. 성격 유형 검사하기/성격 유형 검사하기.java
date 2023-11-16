import java.util.*;
import java.io.*;

class Solution {
    private static class Type {
        char typeA;
        char typeB;
        int score;
        Type(char a, char b) {
            this.typeA = a;
            this.typeB = b;
            score = 0;
        }
        void choose(String s, int c) {
            if (s.charAt(0) == typeA) {
                score += c - 4;
            } else {
                score += -(c - 4);
            }
        }
        char getType() {
            if (score < 0) {
                return typeA;
            } else if (score > 0) {
                return typeB;
            } else {
                return typeA < typeB ? typeA : typeB;
            }
        }
    }
    
    private static class Survey {
        Type[] types;
        Survey () {
            types = new Type[4];
            int i = 0;
            types[i++] = new Type('R', 'T');
            types[i++] = new Type('C', 'F');
            types[i++] = new Type('J', 'M');
            types[i++] = new Type('A', 'N');
        }
        private void choose(String s, int c) {
            int it = -1;
            switch(s.charAt(0)) {
                case 'R':
                case 'T':
                    it = 0;
                    break;
                case 'C':
                case 'F':
                    it = 1;
                    break;
                case 'J':
                case 'M':
                    it = 2;
                    break;
                case 'A':
                case 'N':
                    it = 3;
                    break;
            }
            types[it].choose(s, c);
        }
        String solve(String[] survey, int[] choices) {
            String ans = "";
            for (int i = 0; i < survey.length; i++) {
                choose(survey[i], choices[i]);
            }
            for (int i = 0; i < 4; i++) {
                ans += types[i].getType();
            }
            return ans;
        }
    }
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Survey s = new Survey();
        answer = s.solve(survey, choices);
        return answer;
    }
}