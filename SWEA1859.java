
/*
 * 백만 장자 프로젝트
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc
 */

import java.util.Scanner;

public class SWEA1859 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int[] price = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				price[i] = sc.nextInt();
			}

			int max = Integer.MIN_VALUE;
			long sum = 0;
			for (int i = N; i >= 1; i--) {
				if (price[i] > max) {
					max = price[i];
				} else {
					sum += max - price[i];
				}
			}

			System.out.printf("#%d %d \n", t, sum);
		}
	}
}
