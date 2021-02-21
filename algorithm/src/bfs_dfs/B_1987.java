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
		
		R = Integer.parseInt(st.nextToken()); //세로
		C = Integer.parseInt(st.nextToken()); //가로
		map = new Character[R][C];
		visit = new boolean[26]; //26개의 알파벳이 존재하므로
		
		for(int i=0; i<R; i++) {
			String s = br.readLine(); //한줄씩 입력
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}		
		
		visit[map[0][0] - 65] = true; //시작점 방문 표기
		dfs(0,0,1); // 첫째점부터 1
		
		System.out.println(max);
		
	}
	
	public static void dfs(int x, int y,int cnt) {
		if(cnt > max) max = cnt;
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX < 0 || nextY < 0 || nextX >= C || nextY >= R) continue;
			if(visit[map[nextY][nextX] - 65]) continue; //65를 빼면 0부터 시작
			
			
			visit[map[nextY][nextX] - 65] = true;
			dfs(nextX, nextY, cnt+1);		
			visit[map[nextY][nextX] - 65] = false;
		}
	}
}
