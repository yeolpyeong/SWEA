
/*
 * 줄기세포배양 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5653 {
	static final int deadCell = 0, activationCell = 1, deactivataionCell = 2;
	static final int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

	static class Cell {
		int K;
		int state;
		int hour;

		public Cell(int K) {
			this.K = K;
			state = deactivataionCell;
			hour = 1;
		}
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void spread(Cell[][] grid) {
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != null && grid[i][j].K > 0) {
					q.add(new Point(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			int cx = q.peek().x;
			int cy = q.peek().y;
			q.poll();

			if (grid[cx][cy].state == deactivataionCell) {
				if (grid[cx][cy].hour == grid[cx][cy].K) {
					grid[cx][cy].state = activationCell;
					grid[cx][cy].hour = 1;
				} else {
					grid[cx][cy].hour += 1;
				}
			} else if (grid[cx][cy].state == activationCell) {
				if (grid[cx][cy].hour == 1) {
					for (int d = 0; d < 4; d++) {
						int nx = cx + dx[d];
						int ny = cy + dy[d];

						if (grid[nx][ny] == null) {
							grid[nx][ny] = new Cell(grid[cx][cy].K);
						}

						if (grid[nx][ny].state == deactivataionCell && grid[nx][ny].hour == 1
								&& grid[nx][ny].K < grid[cx][cy].K) {
							Iterator<Point> it = q.iterator();
							boolean flag = false;
							while (it.hasNext()) {
								Point p = it.next();
								if (p.x == nx && p.y == ny) {
									flag = true;
									break;
								}
							}

							if (!flag) {
								grid[nx][ny] = new Cell(grid[cx][cy].K);
							}
						}
					}
				}

				if (grid[cx][cy].hour == grid[cx][cy].K) {
					grid[cx][cy].state = deadCell;
				} else {
					grid[cx][cy].hour += 1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Cell[][] grid = new Cell[N + 2 * K][M + 2 * K];
			for (int i = K; i < N + K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = K; j < M + K; j++) {
					int k = Integer.parseInt(st.nextToken());
					if (k > 0) {
						grid[i][j] = new Cell(k);
					}
				}
			}

			for (int hour = 1; hour <= K; hour++) {
				spread(grid);
			}

			int count = 0;
			for (int i = 0; i < N + 2 * K; i++) {
				for (int j = 0; j < M + 2 * K; j++) {
					if (grid[i][j] != null && grid[i][j].state > 0) {
						count++;
					}
				}
			}

			System.out.printf("#%d %d \n", t, count);
		}
	}
}
