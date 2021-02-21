package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_7569 {
	public static int M, N, H;
	public static int[][][] map;
	public static boolean[][][] visit;
	public static int max = 0;
	public static int[] dx = {0, 0, -1, 1, 0, 0};
	public static int[] dy = {0, 0, 0, 0, 1, -1};
	public static int[] dz = {1, -1, 0, 0, 0, 0}; //������� �� �Ʒ� ���� ������ �� ��
	
	public static class Node{
		int x;
		int y;
		int z;
		
		Node(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;			
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken()); // x��
		N = Integer.parseInt(st.nextToken()); // y��
		H = Integer.parseInt(st.nextToken()); // z��
		
		map = new int[H][N][M];
		visit = new boolean[H][N][M];
		
		for(int i=0; i<H; i++) {			
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int k=0; k<M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		bfs();
		
		if(max != 0) max--; // max�� 0�� ���� �丶�䰡 ���̰� �ȵǴ� ����̹Ƿ� �״�� 0 ���
		
//		for(int i=0; i<H; i++) {
//			for(int j=0; j<N; j++) {
//				for(int k=0; k<M; k++) {
//					System.out.print(map[i][j][k] + " ");;
//				}				
//				System.out.println();
//			}
//		}
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(map[i][j][k] == 0) max = -1; //���� ���̰� �ȵ� �丶�䰡 �ִٸ� -1
				}				
			}
		}
		
		System.out.println(max);
	}

	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(map[i][j][k] == 1) {
						q.add(new Node(k, j, i));
						visit[i][j][k] = true;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for(int i=0; i<6; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				int nextZ = n.z + dz[i];
				
				if(nextX < 0 || nextY < 0 || nextZ <0 || nextX >= M || nextY >= N || nextZ >= H) continue;
				if(visit[nextZ][nextY][nextX] || map[nextZ][nextY][nextX] != 0) continue;
				
				visit[nextZ][nextY][nextX] = true;
				q.add(new Node(nextX, nextY, nextZ));
				map[nextZ][nextY][nextX] = map[n.z][n.y][n.x] + 1;
				
				if(max < map[nextZ][nextY][nextX]) max = map[nextZ][nextY][nextX];
			}
		}
	}
}
