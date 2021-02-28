package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_11404 {
		
	public static void main(String[] args) throws IOException {
		// 비용이 최대 100,000개의 버스 X 100,000의 비용이기 때문에 long형으로 받자.
		// 점과 점이었다면 Kruscal 알고리즘을 사용했겠지만, 한 점에서 모든 정점의 최소 비용이기 때문에 다익스트라를 이용한다.
		// 다익스트라도 간선개수가 많아 불리하여 플로이드 와샬 알고리즘을 사용
		// 비교는 다음과 같음 i->j로 갈때 i->k->j와 i->j를 비교해 더 작은 쪽으로 이동
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 도시 개수
		int m = Integer.parseInt(br.readLine()); // 버스 개수		
		int[][] map = new int[n+1][n+1]; // 정점 경로와 비용 값을 가진 2차원 배열		
		
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