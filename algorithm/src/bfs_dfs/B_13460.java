package bfs_dfs;

import java.util.*;
import java.io.*;

public class B_13460 {

	public static Character[][] map;
	public static boolean[][][][] visit;
	public static int min = 11; //10�� �Ѵ� ���� -1�̱� ����
	public static int N,M; //���ʴ�� ����, ���� ����
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
		// 1. ������ ������.(��ġ�°� ��� x)
		// 2. O�� ������ �����.
		// 3. �Ķ��� ������ O�� �����ٸ� Ž�� ����, ������ ������ �����ٸ� ���� ���, ��� ������ ������ �ʾҴٸ� ��ġ ����
		
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
			
			if(cur.cnt >= 10) { //���� 10�� �Ѿ�� -1 ���
				System.out.println("-1");
				return;
			}
			
			for(int i=0; i<4; i++) {
				//�Ķ���
				int curbx = cur.bx;
				int curby = cur.by;
				while(!(map[curby + dy[i]][curbx + dx[i]] == '#')) { //#�� ���������� ����
					curby += dy[i];
					curbx += dx[i];
					
					if(map[curby][curbx] == 'O') { //���� ������ ������ �ϴ� break
						break;
					}
				}
				
				//������
				int currx = cur.rx;
				int curry = cur.ry;
				while(!(map[curry + dy[i]][currx + dx[i]] == '#')) {
					currx += dx[i];
					curry += dy[i];
					
					if(map[curry][currx] == 'O') {
						break;
					}
				}
				
				if(map[curby][curbx] == 'O') { //���� �Ķ����� �������, �� ���� �ƴϱ� ������ �ѱ�
					continue;
				}
				
				if(map[curry][currx] == 'O') { //���� �������� �������, (������ �̹� �Ķ����� �Ⱥ��� ��쿡�� �� �� ����) ��� ���
					System.out.println(cur.cnt+1);
					return;
				}
				
				if(curby == curry && curbx == currx) {//���� ���� ��ġ�� ���ٸ� ��ġ ����
					
					switch(i) { //������ ���⿡ ����
						case 0: //����
							if(cur.rx < cur.bx) curbx++;
							else currx++;
							
							break;
							
						case 1: //������
							if(cur.rx > cur.bx) curbx--;
							else currx--;
							
							break;
							
						case 2: //��
							if(cur.ry > cur.by) curby--;
							else curry--;
							
							break;
							
						case 3: //�Ʒ�
							if(cur.ry < cur.by) curby++;
							else curry++;
							
							break;						
					}
				}
				
				if(!visit[currx][curry][curbx][curby]) { //�湮���� �ʾҴ� ���,(���ѷ�����) ť�� �߰�
					q.add(new Node(currx, curry, curbx, curby, cur.cnt+1));
				}
			}
		}
		
		System.out.println("-1"); //���� while���� �������� ��� -1�� ���
	}		
}