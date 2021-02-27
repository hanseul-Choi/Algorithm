package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_2644 {
	
	public static class Node {
		int idx;
		LinkedList<Node> adj;
		int cnt;
		
		Node(int idx) {
			this.idx = idx;
			this.adj = new LinkedList<>();
			this.cnt = 0;
		}				
	}
	
	public static int a,b;
	public static Node[] node;
	public static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		// 촌수계산이므로 dfs로 접근하자.
		// cnt 는 시작된 정점에서부터의 촌수 계산
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());//사람 수
		node = new Node[n+1];
		visit = new boolean[n+1];
		
		for(int i=1; i<=n; i++) {
			node[i] = new Node(i);
		}
		
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken()); //시작노드
		b = Integer.parseInt(st.nextToken()); //끝노드
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			 
			if(!node[y].adj.contains(node[x])) node[y].adj.add(node[x]); // 이웃 노드 연결	
			if(!node[x].adj.contains(node[y])) node[x].adj.add(node[y]);
		}
		
		if(a==b) node[b].cnt = 0;
		else {
			dfs(node[a]);
			if(node[b].cnt == 0) node[b].cnt = -1; //못찾은 경우
		}
				
		System.out.println(node[b].cnt);
	}
	
	public static void dfs(Node st) {
		Stack<Node> s = new Stack<>();
		s.add(st);
		visit[st.idx] = true;
		
		while(!s.isEmpty()) {
			Node cur = s.pop();	
			
			if(cur.idx == b) return;
			
			for(Node curNode : cur.adj) {				
				
				if(visit[curNode.idx]) continue;				
				
				visit[curNode.idx] = true;
				curNode.cnt = cur.cnt+1;				
				s.add(curNode);				
			}			
		}
	}

}
