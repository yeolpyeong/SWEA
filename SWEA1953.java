
/*
 * 탈주범 검거
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class SWEA1953 {
	static final int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 }, type1 = { 0, 1, 2, 3 }, type2 = { 0, 1 },
			type3 = { 2, 3 }, type4 = { 1, 2 }, type5 = { 0, 2 }, type6 = { 0, 3 }, type7 = { 1, 3 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, L, count;
	static int[][] map;

	static int[] returnDirection(int type) {
		int[] dirs = new int[4];
		switch (type) {
		case 1:
			dirs = type1;
			break;
		case 2:
			dirs = type2;
			break;
		case 3:
			dirs = type3;
			break;
		case 4:
			dirs = type4;
			break;
		case 5:
			dirs = type5;
			break;
		case 6:
			dirs = type6;
			break;
		case 7:
			dirs = type7;
			break;
		}
		return dirs;
	}

	static boolean checkPossible(int d, int type) {
		switch (d) {
		case 0:
			if (type == 3 || type == 5 || type == 6) {
				return false;
			}
			break;
		case 1:
			if (type == 3 || type == 4 || type == 7) {
				return false;
			}
			break;
		case 2:
			if (type == 2 || type == 4 || type == 5) {
				return false;
			}
			break;
		case 3:
			if (type == 2 || type == 6 || type == 7) {
				return false;
			}
			break;
		}
		return true;
	}

	static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(start);
		visited[start.r][start.c] = true;
		while (L-- > 1) {
			int qs = q.size();
			while (qs-- > 0) {
				int cr = q.peek().r, cc = q.peek().c;
				q.poll();

				int[] dirs = returnDirection(map[cr][cc]);
				int nr, nc;
				for (int d : dirs) {
					nr = cr + dr[d];
					nc = cc + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}

					if (map[nr][nc] == 0 || visited[nr][nc]) {
						continue;
					}

					if (checkPossible(d, map[nr][nc])) {
						q.add(new Point(nr, nc));
						visited[nr][nc] = true;
						count++;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			count = 1;
			bfs(new Point(R, C));

			System.out.printf("#%d %d \n", t, count);
		}
	}
}
