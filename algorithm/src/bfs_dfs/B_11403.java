package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_11403 {
	public static int N;
	public static int[][] map,answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer = new int[N][N];
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			bfs(i);
		}		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
	}	
	
	public static void bfs(int start) {
		boolean[] visit = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {			
			if(map[start][i] == 1) {				
				q.add(i);
				visit[i] = true;				
			}
		}
		
		while(!q.isEmpty()) {
			int next = q.poll();
			
			for(int i=0; i<N; i++) {  
				if(map[next][i] == 1 && !visit[i]) {
					q.add(i);
					visit[i] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			if(visit[i]) {
				answer[start][i] = 1;
			} else {
				answer[start][i] = 0;
			}
		}
	}
}
