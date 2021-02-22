package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_7562 {
	
	public static int l;
	public static int[][] map;
	public static boolean[][] visit;
	
	public static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		// ���ѷ����� �Ű������ �־��� ĭ�� ���� �ֱ� ������ ������ ��..
		// �ּҷ� �����ϴ� ������ map�� ���ڸ� �׾ư��� ����� ���� ���ø���
		// dy�� dx�� �̿��Ͽ� ����Ʈ �̵��� ��Ÿ���� ���� ����Ʈ
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Integer> al = new ArrayList<>();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<testCase; tc++) {
			
			l = Integer.parseInt(br.readLine());
			map = new int[l][l];	
			visit = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			bfs(x1, y1);					
						
			st = new StringTokenizer(br.readLine(), " ");
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
						
			al.add(map[y2][x2]);
		}
		
		for(int i=0; i<al.size(); i++) System.out.println(al.get(i));
	}

	public static void bfs(int startX, int startY) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX, startY));
		visit[startY][startX] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for(int i=0; i<8; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
			
				if(nextX < 0 || nextY < 0 || nextX >= l || nextY >= l) continue;
				if(visit[nextY][nextX]) continue;
				
				visit[nextY][nextX] = true;
				map[nextY][nextX] = map[n.y][n.x] + 1;
				q.add(new Node(nextX, nextY));
			}			
		}		
	}
}
