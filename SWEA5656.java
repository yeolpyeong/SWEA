
/*
 * 벽돌 깨기
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656 {
	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, W, H, count, max;
	static int[][] map;

	static void crush(int[][] map, int r, int c) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		q.add(new Point(r, c));
		visited[r][c] = true;
		while (!q.isEmpty()) {
			int cr = q.peek().r;
			int cc = q.peek().c;
			q.poll();

			int power = map[cr][cc];
			map[cr][cc] = 0;
			count++;

			if (power == 1) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				for (int p = 1; p < power; p++) {
					int nr = cr + p * dr[d];
					int nc = cc + p * dc[d];

					if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
						continue;
					}

					if (map[nr][nc] == 0 || visited[nr][nc]) {
						continue;
					}

					q.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}

	static void moveDown(int[][] map) {
		int step;
		for (int j = W - 1; j >= 0; j--) {
			step = 0;
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] == 0) {
					step++;
				} else if (step > 0) {
					map[i + step][j] = map[i][j];
					map[i][j] = 0;
				}
			}
		}
	}

	static void dropABead(Stack<Integer> order) {
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			copy[i] = map[i].clone();
		}

		for (int j : order) {
			for (int i = 0; i < H; i++) {
				if (copy[i][j] > 0) {
					crush(copy, i, j);
					moveDown(copy);
					break;
				}
			}
		}
	}

	static void dfs(Stack<Integer> order) {
		if (order.size() == N) {
			count = 0;
			dropABead(order);

			if (count > max) {
				max = count;
			}
			return;
		}

		for (int i = 0; i < W; i++) {
			order.push(i);
			dfs(order);
			order.pop();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int total = 0;
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int brick = Integer.parseInt(st.nextToken());
					if (brick > 0) {
						total++;
					}
					map[i][j] = brick;
				}
			}

			max = Integer.MIN_VALUE;
			dfs(new Stack<>());

			System.out.printf("#%d %d \n", t, total - max);
		}
	}
}
