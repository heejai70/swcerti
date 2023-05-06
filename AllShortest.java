import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AllShortest {

	final static int INFINITY = 9999;	// 987654321
	final static int MAX_N = 1000;
	
	static int N;
	static int[][] D = new int[MAX_N+1][MAX_N+1];
	
	public static void initailizeDistance(int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				D[i][j] = INFINITY;
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("allShortest_input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();		
			initailizeDistance(N);
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					D[i][j] = sc.nextInt();
					
					if( i != j && D[i][j] == 0) {
						D[i][j] = INFINITY;
					}
				}
			}
			
//			printArray(D, N);
			// Floyd Algorithm
			for(int k = 1; k <= N; k++) {		// °æÀ¯Áö
				for(int i = 1; i <= N; i++) {	// Ãâ¹ßÁö
					if(k == i) continue;
					for(int j = 1; j <= N; j++) {	// µµÂøÁö
						if( j == k || j == i) continue;
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			
//			printArray(D, N);
			
			// ÇàÀÇ ÇÕÀÌ ÃÖ¼ÒÀÎ Çà Ã£±â
			int minIdx = 0;
			int minVal = 999999999;
			for(int i = 1; i <= N; i++) {
				int rowSum = 0;
				for(int j = 1; j <= N; j++) {
					rowSum += D[i][j];
				}
				if(minVal > rowSum) {
					minVal = rowSum;
					minIdx = i;
				}
			}
			
			System.out.printf("#%d %d \n", tc, minIdx);
		
		}
		sc.close();

	}

	public static void printArray(int[][] D, int n) {
		System.out.println();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				System.out.printf("%d ", D[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
