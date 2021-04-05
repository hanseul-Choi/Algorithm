package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_11725 {
	public static int N;
	//부모를 저장할 배열
	public static int[] parent;
	public static Node[] node; 
	
	public static class Node{
		int val;
		LinkedList<Node> adj;
		boolean visit;
		
		Node(int val) {
			this.val = val;
			this.adj = new LinkedList<>();
			this.visit = false;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		node = new Node[N+1];
		
		for(int i=0; i<N+1; i++) {
			node[i] = new Node(i);
		}
		
		parent = new int[N+1];		
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			addEdge(node[Integer.parseInt(st.nextToken())], node[Integer.parseInt(st.nextToken())]);
		}		
		
		bfs();
		
		for(int i=2; i<N+1; i++) {
			System.out.println(parent[i]);
		}
	}
	
	public static void addEdge(Node n1, Node n2) {
		if(!n1.adj.contains(n2)) n1.adj.add(n2);
		if(!n2.adj.contains(n1)) n2.adj.add(n1);
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		node[1].visit = true;
		q.add(node[1]);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(Node adjacent : cur.adj) {
				if(adjacent.visit) continue;
				
				adjacent.visit = true;
				q.add(adjacent);
				parent[adjacent.val] = cur.val;
			}
		}
	}

}
