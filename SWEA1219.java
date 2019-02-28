
/*
 * ±Ê√£±‚ 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14geLqABQCFAYD
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA1219 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int t = sc.nextInt();
			int n = sc.nextInt();
			boolean[][] adj = new boolean[100][100];
			for (int i = 0; i < n; i++) {
				adj[sc.nextInt()][sc.nextInt()] = true;
			}

			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[100];
			q.add(0);
			visited[0] = true;
			while (!q.isEmpty()) {
				int here = q.poll();
				for (int there = 0; there < 100; there++) {
					if (adj[here][there] && !visited[there]) {
						visited[there] = true;
						q.add(there);
					}
				}
			}
			
			System.out.printf("#%d %d \n", t, visited[99] ? 1 : 0);
		}
	}
}
