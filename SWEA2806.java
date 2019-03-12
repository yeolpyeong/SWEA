
/*
 * N-Queen 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GKs06AU0DFAXB
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2806 {
	static int N, count;
	static int[] cols;

	static boolean promising(int l) {
		for (int i = 0; i < l; i++) {
			if (cols[i] == cols[l]) {
				return false;
			}

			if (l - i == Math.abs(cols[l] - cols[i])) {
				return false;
			}
		}

		return true;
	}

	static void dfs(int l) {
		if (l == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			cols[l] = i;
			if (promising(l)) {
				dfs(l + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			cols = new int[N];

			count = 0;
			dfs(0);

			System.out.printf("#%d %d \n", t, count);
		}
	}
}
