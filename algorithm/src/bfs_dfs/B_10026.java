package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_10026 {

	public static int N;
	public static Character[][] map;
	public static boolean[][] visit;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	public static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// ���ϻ����� ���� map�� ���ϻ����� �ƴ� ���� map�� ���� ��������!
		// ���ϻ����̸� R�� G�� �ٲ���
		// ���� ������ R == G (�� �����ڽ����� ǥ������!)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new Character[N][N];
		visit = new boolean[N][N];
		
		int cnt = 0;
		int cnt_rg = 0;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
			
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) {					
					bfs(j, i);
					cnt++;
				}
			}
		}
		
		//�ʱ�ȭ �۾�
		visit = new boolean[N][N];		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'R') map[i][j] = 'G';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) {
					bfs(j, i);
					cnt_rg++;
				}
			}
		}
		
		System.out.println(cnt + " " + cnt_rg);
	}
	
	public static void bfs(int startX, int startY) { 
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX, startY));
		visit[startY][startX] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if(visit[nextY][nextX] || map[n.y][n.x] != map[nextY][nextX]) continue;
				
				q.add(new Node(nextX, nextY));
				visit[nextY][nextX] = true;				
			}
		}
	}

}
