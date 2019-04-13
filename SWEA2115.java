
/*
 * ¹ú²ÜÃ¤Ãë 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class SWEA2115 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, C, max, maxScore;
	static int[][] map, scoreboard;

	static void getScore(int idx, int r, int c, int honey, int score) {
		if (idx == M) {
			maxScore = Math.max(maxScore, score);
			return;
		}

		int here = map[r][c + idx];
		if (honey + here <= C) {
			getScore(idx + 1, r, c, honey + here, score + here * here);
		}
		getScore(idx + 1, r, c, honey, score);
	}

	static void calculateScoreboard() {
		scoreboard = new int[N][N - M + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				maxScore = 0;
				getScore(0, i, j, 0, 0);
				scoreboard[i][j] = maxScore;
			}
		}
	}

	static void dfs(Stack<Point> stack, int r, int c) {
		if (stack.size() == 2) {
			int score = 0;
			for (Point p : stack) {
				score += scoreboard[p.r][p.c];
			}
			max = Math.max(max, score);
			return;
		}

		for (int i = r; i < N; i++) {
			for (int j = c; j < N - M + 1; j++) {
				stack.push(new Point(i, j));
				dfs(stack, i, j + M);
				stack.pop();
			}
			c = 0;
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
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = 0;
			calculateScoreboard();
			dfs(new Stack<>(), 0, 0);

			System.out.printf("#%d %d \n", t, max);
		}
	}
}
