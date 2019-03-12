
/*
 * 보물상자 비밀번호
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658 {
	static char[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			numbers = new char[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				numbers[i] = line.charAt(i);
			}

			Set<Integer> set = new TreeSet<>();
			for (int i = 0; i < N / 4; i++) {
				for (int j = 0; j < 4; j++) {
					String hex = "";
					for (int k = 0; k < N / 4; k++) {
						hex += numbers[(i + N / 4 * j + k) % N];
					}
					set.add(Integer.parseInt(hex, 16));
				}
			}

			K = set.size() - K;
			for (int s : set) {
				if (K-- == 0) {
					System.out.printf("#%d %d \n", t, s);
				}
			}
		}
	}
}
