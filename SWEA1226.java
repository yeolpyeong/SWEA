
/*
 * 미로1
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA1226 {
	static final int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };
	static boolean flag;

	public static void dfs(int[][] maze, int cx, int cy) {
		maze[cx][cy] = 1;
		for (int d = 0; d < 4; d++) {
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16) {
				continue;
			}

			if (maze[nx][ny] == 1) {
				continue;
			}

			if (maze[nx][ny] == 3) {
				flag = true;
				return;
			}

			dfs(maze, nx, ny);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int T = 0; T < 10; T++) {
			int t = Integer.parseInt(br.readLine());
			int[][] maze = new int[16][16];
			int x = -1, y = -1;
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = line.charAt(j) - '0';
					if (maze[i][j] == 2) {
						x = i;
						y = j;
					}
				}
			}

			flag = false;
			dfs(maze, x, y);

			System.out.printf("#%d %d \n", t, flag ? 1 : 0);
		}
	}
}
