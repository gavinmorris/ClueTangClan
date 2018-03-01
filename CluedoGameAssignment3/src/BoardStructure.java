
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
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', '0', '0', 'R', 'R', 'R', 'R', '0', '0', '0', 'x', 'R', 'S', 'S', 'S', 'R', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', '0', '0', 'R', 'R', 'S', 'S', 'S', 'R', 'R', 'R', '0', '0', 'R', 'S', 'S', 'S', 'R', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', '0', '0', 'R', 'R', 'S', 'S', 'S', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'u', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'x', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'u', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'r', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'u', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', 'R', 'R', 'd', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'u', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', '0', '0', 'r', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'T', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'x', '0', '0', '0', '0', '0', '0', '0', '0', 'R', 'R', 'd', 'd', 'R', 'R', '0', '0', '0', 'R', 'R', 'R', 'R', 'R', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'd', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', '0', '0', '0', '0', '0', '0', 'T', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'l', '0', '0', '0', '0', '0', '0', '0', '0', 'x', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'R', 'R', 'R', 'R', 'R', 'R', '0', '0', 'd', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', 'R', '0', '0', 'R', 'S', 'S', 'S', 'R', 'R', '0', '0', 'R', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'R', 'S', 'S', 'S', 'R', 'R', 'R', '0', '0', 'R', 'S', 'S', 'S', 'R', 'R', '0', '0', 'R', 'R', 'R', 'S', 'S', 'S', 'R', 'x'	},
			{	'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', 'T', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x', '0', 'x', 'R', 'R', 'R', 'R', 'R', 'R', 'x'	},
			{	'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'	}
		};
		
		tilePositionX = new int[boardWidth][boardHeight];
		tilePositionY = new int[boardWidth][boardHeight];
		setTilePositions();
		
		printTileTypeBoard();
		
		//printTilePositionBoard();
		
		SetSlots();
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
	
	public void SetSlots() {
		int iterate = 0;
		
		int KitchenSlotsStartingx = 66;
		int KitchenSlotsStartingy = 70;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				//Set the slot as empty
				Board.KitchenSlots[0][iterate] = 0;
				//Set the coords of each slots
				Board.KitchenSlots[1][iterate] = KitchenSlotsStartingx + i*23;
				Board.KitchenSlots[2][iterate] = KitchenSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int BallRoomSlotsStartingx = 273;
		int BallRoomSlotsStartingy = 70;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.BallRoomSlots[0][iterate] = 0;
				Board.BallRoomSlots[1][iterate] = BallRoomSlotsStartingx + i*23;
				Board.BallRoomSlots[2][iterate] = BallRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int ConservatorySlotsStartingx = 480;
		int ConservatorySlotsStartingy = 47;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.ConservatorySlots[0][iterate] = 0;
				Board.ConservatorySlots[1][iterate] = ConservatorySlotsStartingx + i*23;
				Board.ConservatorySlots[2][iterate] = ConservatorySlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int DiningRoomSlotsStartingx = 66;
		int DiningRoomSlotsStartingy = 254;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.DiningRoomSlots[0][iterate] = 0;
				Board.DiningRoomSlots[1][iterate] = DiningRoomSlotsStartingx + i*23;
				Board.DiningRoomSlots[2][iterate] = DiningRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int BilliardRoomSlotsStartingx = 503;
		int BilliardRoomSlotsStartingy = 254;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.BilliardRoomSlots[0][iterate] = 0;
				Board.BilliardRoomSlots[1][iterate] = BilliardRoomSlotsStartingx + i*23;
				Board.BilliardRoomSlots[2][iterate] = BilliardRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int LibrarySlotsStartingx = 503;
		int LibrarySlotsStartingy = 392;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.LibrarySlots[0][iterate] = 0;
				Board.LibrarySlots[1][iterate] = LibrarySlotsStartingx + i*23;
				Board.LibrarySlots[2][iterate] = LibrarySlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int LoungeSlotsStartingx = 66;
		int LoungeSlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.LoungeSlots[0][iterate] = 0;
				Board.LoungeSlots[1][iterate] = LoungeSlotsStartingx + i*23;
				Board.LoungeSlots[2][iterate] = LoungeSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int HallSlotsStartingx = 273;
		int HallSlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.HallSlots[0][iterate] = 0;
				Board.HallSlots[1][iterate] = HallSlotsStartingx + i*23;
				Board.HallSlots[2][iterate] = HallSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int StudySlotsStartingx = 503;
		int StudySlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.StudySlots[0][iterate] = 0;
				Board.StudySlots[1][iterate] = StudySlotsStartingx + i*23;
				Board.StudySlots[2][iterate] = StudySlotsStartingy + j*23;
				iterate++;
			}
		}
	}
}



















