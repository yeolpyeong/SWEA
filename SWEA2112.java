
/*
 * 보호 필름 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class SWEA2112 {
	static int D, W, K, min;
	static int[][] film;

	static void inject(int[][] copy, int type, int depth) {
		for (int i = 0; i < W; i++) {
			copy[depth][i] = type;
		}
	}

	static boolean check(int[][] copy) {
		for (int j = 0; j < W; j++) {
			boolean flag = false;
			for (int i = 0; i <= D - K; i++) {
				int sum = 0;
				for (int k = 0; k < K; k++) {
					sum += copy[i + k][j];
				}

				if (sum == 0 || sum == K) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				return false;
			}
		}
		return true;
	}

	static void dfs(Stack<Integer> stack, int idx, int count) {
		if (stack.size() == count) {
			int[][] copy = new int[D][W];
			for (int i = 0; i < D; i++) {
				copy[i] = film[i].clone();
			}

			int len = (int) Math.pow(2, count);
			for (int i = 0; i < len; i++) {
				String temp = Integer.toBinaryString(i);
				while (temp.length() < Integer.toBinaryString(len).length() - 1) {
					temp = "0" + temp;
				}

				for (int j = 0; j < count; j++) {
					inject(copy, temp.charAt(j) - '0', stack.get(j));
				}

				if (check(copy)) {
					min = count;
				}
			}
			return;
		}

		for (int i = idx; i < D; i++) {
			stack.push(i);
			dfs(stack, i + 1, count);
			stack.pop();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = -1;
			for (int i = 0; i <= D; i++) {
				dfs(new Stack<>(), 0, i);

				if (min != -1) {
					break;
				}
			}

			System.out.printf("#%d %d \n", t, min);
		}
	}
}
