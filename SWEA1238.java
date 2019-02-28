
/*
 * Contact
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA1238 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = 0;
		while (sc.hasNext()) {
			t++;
			int N = sc.nextInt();
			int start = sc.nextInt();
			boolean[][] adj = new boolean[101][101];
			for (int i = 0; i < N / 2; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj[a][b] = true;
			}

			int[] visited = new int[101];
			for (int i = 1; i <= 100; i++) {
				visited[i] = -1;
			}
			int round = 0;
			visited[start] = round;
			Queue<Integer> q = new LinkedList<>();
			q.add(start);
			while (!q.isEmpty()) {
				round++;
				int qs = q.size();
				while (qs-- > 0) {
					int here = q.poll();

					for (int there = 1; there <= 100; there++) {
						if (adj[here][there] && visited[there] == -1) {
							visited[there] = round;
							q.add(there);
						}
					}
				}
			}

			int max = 0;
			for (int i = 1; i <= 100; i++) {
				if (visited[i] == round - 1) {
					max = i;
				}
			}

			System.out.printf("#%d %d \n", t, max);
		}
	}
}
