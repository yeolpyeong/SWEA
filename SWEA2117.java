
/*
 * 홈 방법 서비스
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA2117 {
	static int N;
	static int[][] map;

	static int countHouses(int r, int c, int k) {
		int count = 0;
		for (int i = -(k - 1); i < k; i++) {
			int w = k - Math.abs(i);
			for (int j = -(w - 1); j < w; j++) {
				if (r + i < 0 || c + j < 0 || r + i >= N || c + j >= N) {
					continue;
				}

				if (map[r + i][c + j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k < 2 * N; k++) {
						int count = countHouses(i, j, k);
						if (M * count >= k * k + (k - 1) * (k - 1) && count > max) {
							max = count;
						}
					}
				}
			}

			System.out.printf("#%d %d \n", t, max);
		}
	}
}