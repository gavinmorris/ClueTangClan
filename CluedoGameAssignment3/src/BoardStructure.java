
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
	
	//Easier to create slots than typing them individually
	//Takes the StartingX and StartingY as the first slot then adds 5 more in a 2x3 grid
	public void SetSlots() {
		int iterate = 0;
		
		int KitchenSlotsStartingx = 66;
		int KitchenSlotsStartingy = 70;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				//Set the slot as empty
				Board.RoomSlots.get(0)[0][iterate] = 0;
				//Set the coords of each slots
				Board.RoomSlots.get(0)[1][iterate] = KitchenSlotsStartingx + i*23;
				Board.RoomSlots.get(0)[2][iterate] = KitchenSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int BallRoomSlotsStartingx = 273;
		int BallRoomSlotsStartingy = 70;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(1)[0][iterate] = 0;
				Board.RoomSlots.get(1)[1][iterate] = BallRoomSlotsStartingx + i*23;
				Board.RoomSlots.get(1)[2][iterate] = BallRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int ConservatorySlotsStartingx = 480;
		int ConservatorySlotsStartingy = 47;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(2)[0][iterate] = 0;
				Board.RoomSlots.get(2)[1][iterate] = ConservatorySlotsStartingx + i*23;
				Board.RoomSlots.get(2)[2][iterate] = ConservatorySlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int DiningRoomSlotsStartingx = 66;
		int DiningRoomSlotsStartingy = 254;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(3)[0][iterate] = 0;
				Board.RoomSlots.get(3)[1][iterate] = DiningRoomSlotsStartingx + i*23;
				Board.RoomSlots.get(3)[2][iterate] = DiningRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int BilliardRoomSlotsStartingx = 503;
		int BilliardRoomSlotsStartingy = 254;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(4)[0][iterate] = 0;
				Board.RoomSlots.get(4)[1][iterate] = BilliardRoomSlotsStartingx + i*23;
				Board.RoomSlots.get(4)[2][iterate] = BilliardRoomSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int LibrarySlotsStartingx = 503;
		int LibrarySlotsStartingy = 392;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(5)[0][iterate] = 0;
				Board.RoomSlots.get(5)[1][iterate] = LibrarySlotsStartingx + i*23;
				Board.RoomSlots.get(5)[2][iterate] = LibrarySlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int LoungeSlotsStartingx = 66;
		int LoungeSlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(6)[0][iterate] = 0;
				Board.RoomSlots.get(6)[1][iterate] = LoungeSlotsStartingx + i*23;
				Board.RoomSlots.get(6)[2][iterate] = LoungeSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int HallSlotsStartingx = 273;
		int HallSlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(7)[0][iterate] = 0;
				Board.RoomSlots.get(7)[1][iterate] = HallSlotsStartingx + i*23;
				Board.RoomSlots.get(7)[2][iterate] = HallSlotsStartingy + j*23;
				iterate++;
			}
		}
		
		iterate=0;
		int StudySlotsStartingx = 503;
		int StudySlotsStartingy = 530;
		for(int j = 0;j<2;j++) {
			for(int i = 0;i<3;i++) {
				Board.RoomSlots.get(8)[0][iterate] = 0;
				Board.RoomSlots.get(8)[1][iterate] = StudySlotsStartingx + i*23;
				Board.RoomSlots.get(8)[2][iterate] = StudySlotsStartingy + j*23;
				iterate++;
			}
		}
	}
}



















