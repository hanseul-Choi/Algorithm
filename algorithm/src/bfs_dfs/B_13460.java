package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_13460 {

	public static Character[][] map;
	public static boolean[][][][] visit;
	public static int min = 11; //10을 넘는 순간 -1이기 때문
	public static int N,M; //차례대로 세로, 가로 길이
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	
	public static class Node {
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;
		
		Node(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 1. 구슬을 굴린다.(겹치는거 상관 x)
		// 2. O를 만나면 멈춘다.
		// 3. 파란색 구슬이 O에 빠졌다면 탐색 종료, 빨간색 구슬만 빠졌다면 정답 출력, 어느 구슬도 빠지지 않았다면 위치 조정
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Character[N][M];
		
		int redx=0, redy=0, bluex=0, bluey=0;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'R') {
					redx = j;
					redy = i;
				}
				
				if(map[i][j] == 'B') {
					bluex = j;
					bluey = i;
				}								
			}
		}		
			
		Node Ball = new Node(redx, redy, bluex, bluey, 0);		
		visit = new boolean[M][N][M][N];
		
		bfs(Ball);
		
	}
	
	public static void bfs(Node ball) {
		Queue<Node> q = new LinkedList<>();	
		q.add(ball);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			visit[cur.rx][cur.ry][cur.bx][cur.by] = true;
			
			if(cur.cnt >= 10) { //만약 10번 넘어가면 -1 출력
				System.out.println("-1");
				return;
			}
			
			for(int i=0; i<4; i++) {
				//파란공
				int curbx = cur.bx;
				int curby = cur.by;
				while(!(map[curby + dy[i]][curbx + dx[i]] == '#')) { //#을 만날때까지 굴림
					curby += dy[i];
					curbx += dx[i];
					
					if(map[curby][curbx] == 'O') { //만약 구멍을 만나면 일단 break
						break;
					}
				}
				
				//빨간공
				int currx = cur.rx;
				int curry = cur.ry;
				while(!(map[curry + dy[i]][currx + dx[i]] == '#')) {
					currx += dx[i];
					curry += dy[i];
					
					if(map[curry][currx] == 'O') {
						break;
					}
				}
				
				if(map[curby][curbx] == 'O') { //만약 파란공이 빠진경우, 이 경우는 아니기 때문에 넘김
					continue;
				}
				
				if(map[curry][currx] == 'O') { //만약 빨간공이 빠진경우, (위에서 이미 파란공이 안빠진 경우에만 올 수 있음) 결과 출력
					System.out.println(cur.cnt+1);
					return;
				}
				
				if(curby == curry && curbx == currx) {//만약 둘의 위치가 같다면 위치 조정
					
					switch(i) { //굴리는 방향에 따라
						case 0: //왼쪽
							if(cur.rx < cur.bx) curbx++;
							else currx++;
							
							break;
							
						case 1: //오른쪽
							if(cur.rx > cur.bx) curbx--;
							else currx--;
							
							break;
							
						case 2: //위
							if(cur.ry > cur.by) curby--;
							else curry--;
							
							break;
							
						case 3: //아래
							if(cur.ry < cur.by) curby++;
							else curry++;
							
							break;						
					}
				}
				
				if(!visit[currx][curry][curbx][curby]) { //방문하지 않았던 경우,(무한루프라서) 큐에 추가
					q.add(new Node(currx, curry, curbx, curby, cur.cnt+1));
				}
			}
		}
		
		System.out.println("-1"); //만약 while문을 빠져나간 경우 -1을 출력
	}		
}