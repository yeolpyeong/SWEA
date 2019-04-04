
/*
 * 점심 식사시간
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class SWEA2383 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int[][] map = new int[N][N];
			ArrayList<Point> people = new ArrayList<>();
			ArrayList<Point> stairs = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						people.add(new Point(i, j));
					} else if (map[i][j] >= 2) {
						stairs.add(new Point(i, j));
					}
				}
			}

			int min = Integer.MAX_VALUE;
			int all = (int) Math.pow(2, people.size());
			for (int i = 0; i < all; i++) {
				String temp = Integer.toBinaryString(i);
				while (temp.length() < people.size()) {
					temp = "0" + temp;
				}

				LinkedList<Integer> goStair0 = new LinkedList<>();
				LinkedList<Integer> goStair1 = new LinkedList<>();
				for (int j = 0; j < people.size(); j++) {
					int moving = Math.abs(people.get(j).r - stairs.get(temp.charAt(j) - '0').r)
							+ Math.abs(people.get(j).c - stairs.get(temp.charAt(j) - '0').c);
					if (temp.charAt(j) == '0') {
						goStair0.add(moving);
					} else {
						goStair1.add(moving);
					}
				}

				int minute = 1;
				LinkedList<Integer> stair0 = new LinkedList<>();
				LinkedList<Integer> stair1 = new LinkedList<>();
				int qs;
				while (true) {
					minute++;

					qs = stair0.size();
					while (qs-- > 0) {
						int time = stair0.poll() - 1;
						if (time > 0) {
							stair0.add(time);
						}
					}

					qs = stair1.size();
					while (qs-- > 0) {
						int time = stair1.poll() - 1;
						if (time > 0) {
							stair1.add(time);
						}
					}

					qs = goStair0.size();
					while (qs-- > 0) {
						int time = goStair0.poll() - 1;
						if (time > 0) {
							goStair0.add(time);
						} else if (stair0.size() < 3) {
							stair0.add(map[stairs.get(0).r][stairs.get(0).c]);
						} else {
							goStair0.add(time + 1);
						}
					}

					qs = goStair1.size();
					while (qs-- > 0) {
						int time = goStair1.poll() - 1;
						if (time > 0) {
							goStair1.add(time);
						} else if (stair1.size() < 3) {
							stair1.add(map[stairs.get(1).r][stairs.get(1).c]);
						} else {
							goStair1.add(time + 1);
						}
					}

					if (stair0.isEmpty() && stair1.isEmpty() && goStair0.isEmpty() && goStair1.isEmpty()) {
						break;
					}
				}

				if (minute < min) {
					min = minute;
				}
			}

			System.out.printf("#%d %d \n", t, min);
		}
	}
}
