package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_1520 {
	
	public static int N,M; //���ʴ�� ����, ����
	public static int[][] map;
	public static int[][] dp;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	public static int cnt = 0;

	public static class Node{
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
		// ��͸� �̿��Ͽ� ���� ���踦 ����� �� ���迡�� ������ �����ϸ� ī��Ʈ�� �÷�����
		// �ð����⵵�� ����Ǳ� �� => ����
		// �׷��� �״� ����� �̿��غ���?
		// memoization���� �ذ������� ������ �ʿ�
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i=0; i<M; i++) {		
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));	
	
	}
	
	public static int dfs(int x, int y) {
		
		dp[y][x] = 0;
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
			if(map[y][x] <= map[nextY][nextX]) continue;
		
			if(nextY == M-1 && nextX == N-1) {
				dp[y][x]++;
			}
			
			if(dp[nextY][nextX] >= 0) {
				dp[y][x] += dp[nextY][nextX];
			} else {
				dp[y][x] += dfs(nextX, nextY);
			}
		}	
		
		return dp[y][x];
	}	
}
