package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_2252 {
			
	public static void main(String[] args) throws IOException {
		// �̰� �� �׷����̷�����?
		// => ���������̱� ����
		// ����� Ŭ������ �����Ͽ� �Ϸ��� �Ͽ����� �ð��ʰ��� �ɸ�
		// �迭�̶�� �ڷᱸ���� 2���� �ξ� �ذ��� �����Ͽ���.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> g[] = new LinkedList[N+1];
		int indegree[] = new int[N+1]; //���� ���� �׷���
		
		for(int i=1; i<=N; i++) {
			g[i] = new LinkedList<>(); //����� �׷����� ����Ʈ�� �߰�
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
