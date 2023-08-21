import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시스템 입력 받는 객체
		StringBuffer sb = new StringBuffer(); // 출력할 문자열을 저장해놓는 객체
		StringTokenizer st; // 입력받은 문자열을 파싱하는 객체

		int N = Integer.parseInt(br.readLine()); // N 분기

		int[][] tasks = new int[N][2]; // 업무 정보를 저장하는 배열

		for (int i = 0; i < N; i++) { // N번 입력 받기
			st = new StringTokenizer(br.readLine()); // 입력으로 파싱 객체 초기화
			int f = Integer.parseInt(st.nextToken()); // 업무정보 확인 변수
			if (f == 1) { // 업무정보가 있는지 확인
				int score = Integer.parseInt(st.nextToken()); // 점수
				int runTime = Integer.parseInt(st.nextToken()); // 예상 소요 시간
				tasks[i][0] = score; // 점수 할당
				tasks[i][1] = runTime; // 예상 소요 시간 할당
			}
		}

		Stack<int[]> taskStack = new Stack<int[]>(); // 밀린 업무를 저장할 자료구조, 스택
		int[] currTask = null; // 현재 수행해야할 업무
		int totalScore = 0; // 총 점수

		for (int i = 0; i < N; i++) { // 모든 업무 순차적으로 처리
			
			// 업무 선택
			if (currTask == null) { // 작업중이던 업무가 없을 때,
				if (tasks[i][0] > 0) { // 현재 주어진 업무가 있다면
					currTask = tasks[i]; // 현재 주어진 업무 선택
				} else if (!taskStack.isEmpty()) { // 현재 주어진 업무가 없고 이전 업무가 있다면
					currTask = taskStack.pop(); // 이전 업무 선택
				}
			} else { 				// 작업중이던 업무가 있을 때,
				if (tasks[i][0] > 0) { // 현재 주어진 업무가 있다면
					taskStack.push(currTask); // 작업중이던 업무 저장
					currTask = tasks[i]; // 현재 주어진 업무 선택
				}
			}
			
			// 업무 수행
			if (currTask != null) { // 진행중인 업무가 있다면
				currTask[1]--; // 업무 수행
				if (currTask[1] == 0) { // 수행을 완료했다면
					totalScore += currTask[0]; // 점수 합산
					currTask = null; // 현재 업무 비우기
				}
			}
		}
		
		System.out.println(totalScore); // 총 점수 출력

	}

}