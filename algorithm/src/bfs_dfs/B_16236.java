package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_16236 {

	public static int N;	
	public static int[][] map;
	// up, left, down, right
	public static int[] dx = {0, -1, 0, 1};
	public static int[] dy = {-1, 0, 1, 0};
	
	//available fish
	public static PriorityQueue<Fish> available_fish;
	
	//fish size
	public static int age = 2;
	public static int eat = 2;
	
	//answer
	public static int answer = 0;
	
	public static class Node{
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;			
		}
	}
	
	//available fish
	public static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int dist;
		
		Fish(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.dist == o.dist) {
				if(this.y == o.y) {
					return this.x - o.x;
				}
				
				return this.y - o.y;
			}
			
			return this.dist - o.dist;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// set Map
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		
		while(true) {
			available_fish = new PriorityQueue<>();
			boolean[][] visited = new boolean[N][N];
			int[][] distances = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 9) {
						bfs(j, i, visited, distances);
						map[i][j] = 0;
					}
				}
			}
			
			//만약 먹을 수 있는 물고기가 없다면, 빠져나옴
			if(available_fish.isEmpty()) {
				break;
			}
			
			Fish fish = available_fish.poll();
			eat--;
			
			//만약 크기만큼 다먹으면 커짐
			if(eat == 0) {
				age++;
				eat = age;
			}
			
			//먹은 위치로 이동
			map[fish.y][fish.x] = 9;
			answer += fish.dist;
		}
			
		System.out.println(answer);
	}
	
	public static void bfs(int startX, int startY, boolean[][] visit, int[][] distance) {
		Queue<Node> q = new LinkedList<>();
		visit[startY][startX] = true;
		distance[startY][startX] = 0;
		q.add(new Node(startX, startY));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if(visit[nextY][nextX] || map[nextY][nextX] > age) continue;
				
				visit[nextY][nextX] = true;
				distance[nextY][nextX] = distance[cur.y][cur.x] + 1;
				q.add(new Node(nextX, nextY));
				
				//만약 물고기가 먹을 수 있다면,
				if(map[nextY][nextX] != 0 && map[nextY][nextX] != age) {
					available_fish.add(new Fish(nextX, nextY, distance[nextY][nextX]));
				}
			}		
		}
	}

}
