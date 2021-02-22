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
		//14502의 연구소 문제와 비슷하게 생각
		//완탐으로 벽을 안부순 것과 벽을 하나씩 부숴서 최소 경로를 확인하고 출력
		//시간초과가 안걸릴까? 역시!
		//벽을 부술 수 있는 경우와 벽을 부수지 못하는 경우 2가지로 생각하여 접근하자
		
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
			
			if(n.x == M-1 && n.y == N-1) return visit[n.y][n.x][n.b]; //만약 끝까지 갔다면, 그때의 값을 반환
			
			for(int i=0; i<4; i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) continue; //범위에 벗어난 경우
				if(visit[nextY][nextX][n.b] != 0) continue; // 방문한 경우
				
				if(map[nextY][nextX] == 0) { //만약 갈 수 있다면
					visit[nextY][nextX][n.b] = visit[n.y][n.x][n.b] + 1; // 0이든 1의 세계이든 그쪽에서 1을 증가 
					q.add(new Node(nextX, nextY, n.b)); //큐에 삽입
				}
				
				if(map[nextY][nextX] == 1 && n.b == 0) { //만약 벽인데, 아직 한번도 뚫지 않았다면
					visit[nextY][nextX][1] = visit[n.y][n.x][0] + 1; //1의 세계로 넘어가 추가
					q.add(new Node(nextX, nextY, 1)); //1의 세계로 큐에 삽입
				}
			}
		}
		
		
		return -1; //만약 여기까지 도달했다면, 이는 갈 수 없는 경우이기에 -1을 반환
	}
}
