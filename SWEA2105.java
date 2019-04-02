
/*
 * 디저트 카페
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA2105 {
	static final int[] dr = { 1, 1, -1, -1 }, dc = { 1, -1, -1, 1 };

	static int N, max, startR, startC;
	static int[][] map;
	static boolean[] dessertType;

	static void go(int step, int cr, int cc, int count) {
		int nr = cr + dr[step];
		int nc = cc + dc[step];

		if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
			return;
		}

		if (step == 3 && nr == startR && nc == startC) {
			if (count > max) {
				max = count;
			}
			return;
		}

		if (dessertType[map[nr][nc]]) {
			return;
		}

		dessertType[map[nr][nc]] = true;
		go(step, nr, nc, count + 1);
		if (step != 3) {
			go(step + 1, nr, nc, count + 1);
		}
		dessertType[map[nr][nc]] = false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = -1;
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					dessertType = new boolean[101];
					dessertType[map[i][j]] = true;
					startR = i;
					startC = j;
					go(0, i, j, 1);
				}
			}

			System.out.printf("#%d %d \n", t, max);
		}
	}
}
