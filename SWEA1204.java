
/*
 * 최빈수 구하기
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1204 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int t = Integer.parseInt(br.readLine());
			int[] score = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 1000; i++) {
				score[Integer.parseInt(st.nextToken())]++;
			}

			int mode = -1, count = 0;
			for (int i = 100; i >= 0; i--) {
				if (score[i] > count) {
					mode = i;
					count = score[i];
				}
			}

			System.out.printf("#%d %d \n", t, mode);
		}
	}
}
