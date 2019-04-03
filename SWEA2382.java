
/*
 * 미생물 격리 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class SWEA2382 {
	static final int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 };

	static class Pair {
		int n, d;

		public Pair(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}

	static class Point implements Comparable<Point> {
		int r, c, n, d;

		public Point(int r, int c, int n, int d) {
			this.r = r;
			this.c = c;
			this.n = n;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			return o.n - this.n;
		}
	}

	static int N;

	static int move(int M, PriorityQueue<Point> pq) {
		int count = 0;
		while (M-- > 0) {
			Pair[][] map = new Pair[N][N];
			while (!pq.isEmpty()) {
				int cr = pq.peek().r;
				int cc = pq.peek().c;
				int cn = pq.peek().n;
				int cd = pq.peek().d;
				pq.poll();

				int nr = cr + dr[cd];
				int nc = cc + dc[cd];

				if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
					cn /= 2;
					cd = cd % 2 == 0 ? cd - 1 : cd + 1;
				}

				if (cn == 0) {
					continue;
				}

				if (map[nr][nc] == null) {
					map[nr][nc] = new Pair(cn, cd);
				} else {
					map[nr][nc].n += cn;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null) {
						pq.add(new Point(i, j, map[i][j].n, map[i][j].d));
						if (M == 0) {
							count += map[i][j].n;
						}
					}
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
			int K = Integer.parseInt(st.nextToken());
			PriorityQueue<Point> pq = new PriorityQueue<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				pq.add(new Point(r, c, n, d));
			}

			int count = move(M, pq);

			System.out.printf("#%d %d \n", t, count);
		}
	}
}
