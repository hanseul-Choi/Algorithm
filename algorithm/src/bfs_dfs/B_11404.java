package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_11404 {
		
	public static void main(String[] args) throws IOException {
		// ����� �ִ� 100,000���� ���� X 100,000�� ����̱� ������ long������ ����.
		// ���� ���̾��ٸ� Kruscal �˰����� ����߰�����, �� ������ ��� ������ �ּ� ����̱� ������ ���ͽ�Ʈ�� �̿��Ѵ�.
		// ���ͽ�Ʈ�� ���������� ���� �Ҹ��Ͽ� �÷��̵� �ͼ� �˰����� ���
		// �񱳴� ������ ���� i->j�� ���� i->k->j�� i->j�� ���� �� ���� ������ �̵�
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // ���� ����
		int m = Integer.parseInt(br.readLine()); // ���� ����		
		int[][] map = new int[n+1][n+1]; // ���� ��ο� ��� ���� ���� 2���� �迭		
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i==j) continue;
				map[i][j] = 1000000001;
			}
		}
		
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			map[a][b] = Math.min(map[a][b], v);				
		}		
				
		for(int k=1; k<=n; k++) {		
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map[i][j] == 1000000001) map[i][j] = 0;
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}	
}