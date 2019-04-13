
/*
 * 핀볼 게임
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5650 {
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static class Point {
		int r, c, d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int N, max;
	static int[][] board;

	static int block(int r, int c, int d) {
		int b = board[r][c];
		if (d == 0) {
			switch (b) {
			case 2:
				d = 3;
				break;
			case 3:
				d = 2;
				break;
			default:
				d = 1;
				break;
			}
		} else if (d == 1) {
			switch (b) {
			case 1:
				d = 3;
				break;
			case 4:
				d = 2;
				break;
			default:
				d = 0;
				break;
			}
		} else if (d == 2) {
			switch (b) {
			case 1:
				d = 0;
				break;
			case 2:
				d = 1;
				break;
			default:
				d = 3;
				break;
			}
		} else if (d == 3) {
			switch (b) {
			case 3:
				d = 1;
				break;
			case 4:
				d = 0;
				break;
			default:
				d = 2;
				break;
			}
		}
		return d;
	}

	static void go(Point start) {
		int score = 0;
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			int nd = q.peek().d;
			int nr = q.peek().r + dr[nd];
			int nc = q.peek().c + dc[nd];
			q.poll();

			int b = board[nr][nc];
			if ((nr == start.r && nc == start.c) || b == -1) {
				if (score > max) {
					max = score;
				}
				return;
			} else if (b >= 1 && b <= 5) {
				nd = block(nr, nc, nd);
				score++;
			} else if (b >= 6) {
				loop: for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (board[i][j] == b && !(i == nr && j == nc)) {
							nr = i;
							nc = j;
							break loop;
						}
					}
				}
			}
			q.add(new Point(nr, nc, nd));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N + 2][N + 2];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N + 2; i++) {
				board[i][0] = 5;
				board[i][N + 1] = 5;
				board[0][i] = 5;
				board[N + 1][i] = 5;
			}

			max = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (board[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							go(new Point(i, j, d));
						}
					}
				}
			}

			System.out.printf("#%d %d \n", t, max);
		}
	}
}
