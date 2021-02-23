package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_2252 {
			
	public static void main(String[] args) throws IOException {
		// 이게 왜 그래프이론이지?
		// => 위상정렬이기 때문
		// 노드라는 클래스를 선언하여 하려고 하였으나 시간초과가 걸림
		// 배열이라는 자료구조를 2개로 두어 해결이 가능하였다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> g[] = new LinkedList[N+1];
		int indegree[] = new int[N+1]; //연결 당한 그래프
		
		for(int i=1; i<=N; i++) {
			g[i] = new LinkedList<>(); //연결된 그래프를 리스트로 추가
		}
		
		for(int i=0; i<M; i++) {			
			st = new StringTokenizer(br.readLine(), " ");
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			g[A].add(B);
			indegree[B]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
				System.out.print(i + " ");
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int n : g[cur]) {
				indegree[n]--;
				if(indegree[n] == 0) {
					q.add(n);
					System.out.print(n + " ");
				}
			}
		}
	}	
}
