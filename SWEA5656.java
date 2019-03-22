
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
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, W, H, count, max;
	static int[][] map;

	static void crush(int[][] map, int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		q.add(new Point(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int cx = q.peek().x;
			int cy = q.peek().y;
			q.poll();

			int power = map[cx][cy];
			map[cx][cy] = 0;
			count++;

			if (power == 1) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				for (int p = 1; p < power; p++) {
					int nx = cx + p * dx[d];
					int ny = cy + p * dy[d];

					if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
						continue;
					}

					if (map[nx][ny] == 0 || visited[nx][ny]) {
						continue;
					}

					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
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
			Stack<Integer> order = new Stack<>();
			dfs(order);

			System.out.printf("#%d %d \n", t, total - max);
		}
	}
}
