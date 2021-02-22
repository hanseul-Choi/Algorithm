package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_2206 {

	public static int N, M;
	public static int[][] map;
	public static int[][][] visit;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	public static int min = -1;
	
	public static class Node {
		int x;
		int y;		
		int b;
		
		Node(int x, int y, int b) {
			this.x = x;
			this.y = y;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//14502�� ������ ������ ����ϰ� ����
		//��Ž���� ���� �Ⱥμ� �Ͱ� ���� �ϳ��� �ν��� �ּ� ��θ� Ȯ���ϰ� ���
		//�ð��ʰ��� �Ȱɸ���? ����!
		//���� �μ� �� �ִ� ���� ���� �μ��� ���ϴ� ��� 2������ �����Ͽ� ��������
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new int[N][M][2];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - '0';			
			}
		}	
		
		System.out.println(bfs());
						
	} 
	
	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		visit[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			if(n.x == M-1 && n.y == N-1) return visit[n.y][n.x][n.b]; //���� ������ ���ٸ�, �׶��� ���� ��ȯ
			
			for(int i=0; i<4; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) continue; //������ ��� ���
				if(visit[nextY][nextX][n.b] != 0) continue; // �湮�� ���
				
				if(map[nextY][nextX] == 0) { //���� �� �� �ִٸ�
					visit[nextY][nextX][n.b] = visit[n.y][n.x][n.b] + 1; // 0�̵� 1�� �����̵� ���ʿ��� 1�� ���� 
					q.add(new Node(nextX, nextY, n.b)); //ť�� ����
				}
				
				if(map[nextY][nextX] == 1 && n.b == 0) { //���� ���ε�, ���� �ѹ��� ���� �ʾҴٸ�
					visit[nextY][nextX][1] = visit[n.y][n.x][0] + 1; //1�� ����� �Ѿ �߰�
					q.add(new Node(nextX, nextY, 1)); //1�� ����� ť�� ����
				}
			}
		}
		
		
		return -1; //���� ������� �����ߴٸ�, �̴� �� �� ���� ����̱⿡ -1�� ��ȯ
	}
}
