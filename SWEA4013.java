
/*
 * 특이한 자석 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA4013 {
	static int[][] cogwheels;

	static boolean[] checkRotable(int n, int d) {
		boolean[] rotable = new boolean[4];
		for (int i = 1; i < 4; i++) {
			if (cogwheels[i][3] != cogwheels[i + 1][7]) {
				rotable[i] = true;
			}
		}
		return rotable;
	}

	static void rotate(int n, int d) {
		if (d == 1) {
			int temp = cogwheels[n][8];
			for (int i = 8; i >= 2; i--) {
				cogwheels[n][i] = cogwheels[n][i - 1];
			}
			cogwheels[n][1] = temp;
		} else if (d == -1) {
			int temp = cogwheels[n][1];
			for (int i = 1; i <= 7; i++) {
				cogwheels[n][i] = cogwheels[n][i + 1];
			}
			cogwheels[n][8] = temp;
		}
	}

	static void solve(boolean[] rotable, boolean[] rotated, int n, int d) {
		rotate(n, d);
		rotated[n] = true;
		switch (n) {
		case 1:
			if (rotable[n] && !rotated[n + 1]) {
				solve(rotable, rotated, n + 1, -d);
			}
			break;
		case 4:
			if (rotable[n - 1] && !rotated[n - 1]) {
				solve(rotable, rotated, n - 1, -d);
			}
			break;
		default:
			if (rotable[n] && !rotated[n + 1]) {
				solve(rotable, rotated, n + 1, -d);
			}
			if (rotable[n - 1] && !rotated[n - 1]) {
				solve(rotable, rotated, n - 1, -d);
			}
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());
			cogwheels = new int[5][9];
			StringTokenizer st;
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 8; j++) {
					cogwheels[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				solve(checkRotable(n, d), new boolean[5], n, d);
			}

			int score = 0;
			for (int i = 1; i <= 4; i++) {
				score += Math.pow(2, i - 1) * cogwheels[i][1];
			}

			System.out.printf("#%d %d \n", t, score);
		}
	}
}
