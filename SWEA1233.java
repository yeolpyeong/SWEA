
/*
 * 사칙연산 유효성 검사 
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1233 {
	static final String operator = "+-*/";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean flag = true;
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int tokens = st.countTokens();
				if (tokens == 4) {
					st.nextToken();
					if (!operator.contains(st.nextToken())) {
						flag = false;
					}
					st.nextToken();
					st.nextToken();
				} else if (tokens == 2) {
					st.nextToken();
					if (operator.contains(st.nextToken())) {
						flag = false;
					}
				}
			}

			System.out.printf("#%d %d \n", t, flag ? 1 : 0);
		}
	}
}
