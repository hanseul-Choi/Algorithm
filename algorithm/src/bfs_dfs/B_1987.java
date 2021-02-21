package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_1987 {
	public static int R,C;
	public static Character[][] map;
	public static boolean[] visit;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int max = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken()); //����
		C = Integer.parseInt(st.nextToken()); //����
		map = new Character[R][C];
		visit = new boolean[26]; //26���� ���ĺ��� �����ϹǷ�
		
		for(int i=0; i<R; i++) {
			String s = br.readLine(); //���پ� �Է�
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}		
		
		visit[map[0][0] - 65] = true; //������ �湮 ǥ��
		dfs(0,0,1); // ù°������ 1
		
		System.out.println(max);
		
	}
	
	public static void dfs(int x, int y,int cnt) {
		if(cnt > max) max = cnt;
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX < 0 || nextY < 0 || nextX >= C || nextY >= R) continue;
			if(visit[map[nextY][nextX] - 65]) continue; //65�� ���� 0���� ����
			
			
			visit[map[nextY][nextX] - 65] = true;
			dfs(nextX, nextY, cnt+1);		
			visit[map[nextY][nextX] - 65] = false;
		}
	}
}
