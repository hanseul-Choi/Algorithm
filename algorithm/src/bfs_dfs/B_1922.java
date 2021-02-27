package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_1922 {
	
	public static class Node implements Comparable<Node>{
		int st;
		int ed;
		int v;
		
		Node(int st, int ed, int v) {
			this.st = st;
			this.ed = ed;
			this.v = v;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.v - n.v;
		}
	}
	
	public static int[] parent;
	
	public static void main(String[] args) throws IOException {
		//시작점이 없는 최소 비용 간선 문제이기 때문에 크루스칼 알고리즘을 적용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		long ans = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			int start = find(cur.st);
			int end = find(cur.ed);
			
			if(start == end) continue;
			
			union(start, end);
			ans+=cur.v;
		}
		
		System.out.println(ans);
	}
	
	public static int find(int a) {
		if(a == parent[a]) return a;
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
