
import java.util.ArrayList;

public class BoardStructure {

	int boardWidth = 26;
	int boardHeight = 27;
	char[][] tileType;
	int[][] tilePositionX;
	int[][] tilePositionY;
	
	public BoardStructure() {
		
		tileType = new char[][] {	
			
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	},
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'T', 'x', 'x', 'x', 'x', 'T', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', '0', '0', 'R', 'R', 'R', 'R', '0', '0', '0', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'u', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'x', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'u', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'u', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'd', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'T', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'd', 'd', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'd', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'd', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', 'T', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	}
		};
		
			/*
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	},
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'T', 'x', 'x', 'x', 'x', 'T', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', '0', '0', 'R', 'R', 'R', 'R', '0', '0', '0', 'x', 'R', 'C1', 'C2', 'C3', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'C4', 'C5', 'C6', 'R', 'R', 'x'	},
			{	'x', 'K1', 'K2', 'K3', 'R', 'R', 'R', '0', '0', 'R', 'BA1', 'BA2', 'BA3', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'K4', 'K5', 'K6', 'R', 'R', 'R', '0', '0', 'R', 'BA4', 'BA5', 'BA6', 'R', 'R', 'R', 'R', '0', '0', 'u', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'x', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'u', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'D1', 'D2', 'D3', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'BI1', 'BI2', 'BI3', 'R', 'R', 'x'	},
			{	'x', 'D4', 'D5', 'D6', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'BI4', 'BI5', 'BI6', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'u', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'd', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'R', 'L1', 'L2', 'L3', 'x'	},
			{	'x', 'T', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'L4', 'L5', 'L6', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'd', 'd', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'd', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'd', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'L1', 'L2', 'L3', 'R', 'R', 'R', 'R', '0', '0', 'R', 'H1', 'H2', 'H3', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'L4', 'L4', 'L5', 'R', 'R', 'R', 'R', '0', '0', 'R', 'H4', 'H5', 'H6', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'S1', 'S2', 'S3', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', 'T', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', 'x', 'R', 'R', 'R', 'S4', 'S5', 'S6', 'x'	},
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	}	
		};
		
		Array Kitchen =  new Array[6];
		Array BallRoom =  new Array[6];
		Array Conservatory =  new Array[6];
		Array DiningRoom =  new Array[6];
		Array BilliardRoom =  new Array[6];
		Array Library =  new Array[6];
		Array Lounge =  new Array[6];
		Array Hall =  new Array[6];
		Array Study =  new Array[6];
		*/
		tilePositionX = new int[boardWidth][boardHeight];
		tilePositionY = new int[boardWidth][boardHeight];
		setTilePositions();
		
		printTileTypeBoard();
		
		//printTilePositionBoard();
	}
	
	
	
	public int getPositionX(int x) {
		int value = 0;
		for(int i=0; i<boardHeight; i++) {
			for(int j=0; j<boardWidth; j++) {
				if(x == tilePositionX[i][j]) {
					value = j;
					i=boardHeight;
					j=boardWidth;
				}
			}
		}
		return value;
	}
	public int getPositionY(int y) {
		int value = 0;
		for(int i=0; i<boardWidth; i++) {
			for(int j=0; j<boardHeight; j++) {
				if(y == tilePositionY[i][j]) {
					value = i;
					i=boardHeight;
					j=boardWidth;
				}
			}
		}
		return value;
	}
	public char getCoordinatesType(int x, int y) {
		int a = getPositionX(x);
		int b = getPositionY(y);
		return tileType[b][a];
	}
	
	public void setCoordinatesType(char type, int x, int y) {
		int a = getPositionX(x);
		int b = getPositionY(y);
		tileType[b][a] = type;
	}
	
	
	public void setTilePositions() {
		int t = -22;
		for(int i=0; i<boardWidth; i++) {
			t += 23;
			for(int j=0; j<boardHeight; j++) {
				tilePositionY[i][j] = t; 
			}
		}

		int k = -3;
		for(int i=0; i<boardHeight; i++) {
			k += 23;
			for(int j=0; j<boardWidth; j++) {
				tilePositionX[j][i] = k; 
			}
		}
	}
	
	public void printTileTypeBoard() {
		for(int i=0; i<boardHeight; i++) {
			System.out.println("");
			for(int j=0; j<boardWidth; j++) {
				System.out.print(tileType[i][j] + " ");
			}
		}
		System.out.print("\n\n\n");
	}
	public void printTilePositionBoard() {
		for(int i=0; i<boardWidth; i++) {
			System.out.println("");
			for(int j=0; j<boardHeight; j++) {
				System.out.print(" (" + tilePositionX[i][j] + ", " + tilePositionY[i][j] + ") \t");
			}
		}
	}
	
	
}



















