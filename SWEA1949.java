
/*
 * 등산로 조성
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class SWEA1949 {
	static final int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

	static int N, K, maxLen;
	static int[][] map;
	static boolean[][] visited;

	static void dfs(int cr, int cc, int ch, int len, boolean flag) {
		for (int d = 0; d < 4; d++) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}

			if (visited[nr][nc]) {
				continue;
			}

			int nh = map[nr][nc];
			if (nh < ch) {
				visited[nr][nc] = true;
				dfs(nr, nc, nh, len + 1, flag);
				visited[nr][nc] = false;
			} else if (flag) {
				for (int k = 1; k <= K; k++) {
					if (nh - k < ch) {
						visited[nr][nc] = true;
						dfs(nr, nc, nh - k, len + 1, false);
						visited[nr][nc] = false;
						break;
					}
				}
			}
		}
		
		if (len > maxLen) {
			maxLen = len;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int max = -1;
			ArrayList<Integer> start = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max) {
						max = map[i][j];
						start.clear();
						start.add(N * i + j);
					} else if (map[i][j] == max) {
						start.add(N * i + j);
					}
				}
			}

			maxLen = -1;
			for (int s : start) {
				int r = s / N, c = s % N;
				visited = new boolean[N][N];
				visited[r][c] = true;
				dfs(r, c, map[r][c], 1, true);
			}

			System.out.printf("#%d %d \n", t, maxLen);
		}
	}
}
