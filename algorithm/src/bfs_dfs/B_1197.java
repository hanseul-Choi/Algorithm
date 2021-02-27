package bfs_dfs;

import java.io.*;
import java.util.*;

public class B_1197 {
	public static int[] parent;
	
	public static class Node implements Comparable<Node>{
		int start;
		int end;
		int v;
		
		Node(int start, int end, int v) {
			this.start = start;
			this.end = end;
			this.v = v;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.v - n.v;
		}
	}

	public static void main(String[] args) throws IOException {
		//1. 정점 visit를 생성
		//2. PriorityQueue를 이용하여 최소 가중치부터 꺼낸 후 그래프 연결
		//3. 두 정점이 visit되지 않은 경우, 간선 연결
		//4. 두 간선이 방문된 점이면 넘기기	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> q = new PriorityQueue<>(); 
		
		parent = new int[V+1];
		
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			q.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
						
		long ans = 0;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			int a = find(cur.start);
			int b = find(cur.end);
			
			if(a == b) continue;
			
			union(a,b);
			ans+=cur.v;
		}		
		
		System.out.println(ans);
	}
	
	public static int find(int a) {
		if(a==parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[aRoot] = b;
		} else {
			return;
		}
	}
}