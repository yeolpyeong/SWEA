
/*
 * 프로세서 연결하기
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class SWEA1767 {
	static final int BLANK = 0, CORE = 1, LINE = 2;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, maxCnt, minLen;
	static int[][] map;
	static ArrayList<Integer> cores;

	static int checkPossible(int idx, int d) {
		int possible = 0;
		int nx = cores.get(idx) / N, ny = cores.get(idx) % N;
		while (true) {
			nx += dx[d];
			ny += dy[d];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				break;
			}

			if (map[nx][ny] != BLANK) {
				return 0;
			}

			possible++;
		}
		return possible;
	}

	static void dfs(int idx, int cnt, int len) {
		if (idx == cores.size()) {
			if (cnt > maxCnt) {
				maxCnt = cnt;
				minLen = len;
			} else if (cnt == maxCnt && len < minLen) {
				minLen = len;
			}
			return;
		}

		int cx = cores.get(idx) / N, cy = cores.get(idx) % N;
		int nx = -1, ny = -1;
		for (int d = 0; d < 4; d++) {
			int possible = checkPossible(idx, d);
			nx = cx;
			ny = cy;
			for (int p = 1; p <= possible; p++) {
				nx += dx[d];
				ny += dy[d];
				map[nx][ny] = LINE;
			}
			dfs(idx + 1, possible == 0 ? cnt : cnt + 1, len + possible);
			nx = cx;
			ny = cy;
			for (int p = 1; p <= possible; p++) {
				nx += dx[d];
				ny += dy[d];
				map[nx][ny] = BLANK;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			map = new int[N][N];
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
						continue;
					}

					if (map[i][j] == CORE) {
						cores.add(N * i + j);
					}
				}
			}

			maxCnt = Integer.MIN_VALUE;
			minLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			System.out.printf("#%d %d \n", t, minLen);
		}
	}
}
