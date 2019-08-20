import java.util.*;

public class Main {
	static final int red =1, blue =2;
	static int NUM_COL = 4;
	static int NUM_ROW = 4;
	static Scanner input = new  Scanner(System.in);
	static int p1=0, p2 = 0;
	static long count = 0; 
	static int firstplayer;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Type in a single digit game board length: ");
		NUM_COL= input.nextInt();
		NUM_ROW = NUM_COL;
		
		
		for (int i =0; i<NUM_COL; i++) {
			int[][] list = new int[NUM_COL][NUM_COL];
			firstplayer = red;
			p1=0;p2=0;count=0;
			list[NUM_COL-1][i] = red;
			System.out.println("\n\n");
			//System.out.println(list );
			PlayToWin(list, blue);
			System.out.println("Column Number: "+(1+i));
			System.out.println("-------------------------");
			System.out.println ("NetWins:" + (p1-p2));
			System.out.println("Red wins: "+p1 +" Blue wins:"+p2);
			System.out.println ("Recursion calls: " + count);
			
			}
		}
	
	//This is the regular "play" method that continues recursively regardless of a win
	public static int Play(int[][] inlist, int plr) {
		count++;
		int res=checkBoard(inlist,plr);
		// 0 - board full, 1= blue wins  -1 = red wins wins   2-keep playing
		if (res < 3) { 
			if (res == 0) {return 0;
			} else {
				if (res == firstplayer) {p1++; return 1;} else {p2++; return -1;}
			}
		}	
		res = 0;

		// for each space that can be the next move
		//    make a copy of board (next lines)	

		//   update the board for this move
		for (int row = NUM_COL-1; row>=0;row-- ){
			for (int col = NUM_COL-1; col>=0;col--  ){
//				if (inlist[row][col] == 0){
				if (inlist[row][col] == 0 && (row == NUM_COL-1||(row+1<=NUM_COL-1 && inlist[row+1][col]>0))) {

					int[][] clonelist = new int[NUM_COL][NUM_COL];
					for (int x = 0;x <NUM_COL;x++  ){
						for (int y = 0; y<NUM_COL;y++  ){
							clonelist[x][y] = inlist[x][y];
		
						}
					}
					clonelist[row][col] = plr;
					Play(clonelist, 3-plr);
					

//					break;
				}
			}
		}
		

		//  recursively call Play
		return res;
	}
	
		
	//This is the extra credit method which stops once a win is reached
	public static int PlayToWin(int[][] inlist, int plr) {
		count++;
		int res=checkBoard(inlist,plr);
		// 0 - board full, 1= blue wins  -1 = red wins wins   2-keep playing
		if (res < 3) { 
			if (res == 0) {return 0;
			} else {
				if (res == firstplayer) {p1++; return 1;} else {p2++; return -1;}
			}
		}	
		res = 0;

		//Creating a list of clonelists 
		ArrayList<int[][]> cloneLists = new ArrayList<int[][]>();
		//   update the board for this move
		for (int row = NUM_COL-1; row>=0;row-- ){
			for (int col = NUM_COL-1; col>=0;col--  ){
				//if the position is empty and the row is either the bottom row or there is a piece beneath it then update board
				if (inlist[row][col] == 0 && (row == NUM_COL-1||(row+1<=NUM_COL-1 && inlist[row+1][col]>0))) {

					int[][] clonelist = new int[NUM_COL][NUM_COL];
					for (int x = 0;x <NUM_COL;x++  ){
						for (int y = 0; y<NUM_COL;y++  ){
							clonelist[x][y] = inlist[x][y];
		
						}
					}
					//add the clonelist to the list
					clonelist[row][col] = plr;
					cloneLists.add(clonelist);
					//if the board is a win then do not call Play recursively for all clonelists 
					if (checkBoard(clonelist,3-plr)==plr) {
//						System.out.println(clr + " Won");
						PlayToWin(clonelist, 3-plr);
						return res;
					}
				}
			}
		}
		//if the game is not a win, keep calling recursively for each clonelist in the list 
		for(int[][] clonelist : cloneLists) {
			PlayToWin(clonelist, 3- plr);
		}
		return res;
	}
	
	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COL ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COL ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	public static int checkBoard(int[][] inlist ,int clr){
		int chkclr = 3-clr;
		for (int i = 0 ; i<NUM_COL; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COL; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_ROW)  { return chkclr;}	 
				//	System.out.println("Player " + chkclr + "returned");
				}  else {
					colcnt =0;
				}
			}
		}
		for (int i = 0 ; i<NUM_COL; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COL; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COL; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_ROW)  {return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		colcnt = 0;
		for (int i = 0 ; i<NUM_COL; i++ ) {
			if (inlist[NUM_COL-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_ROW)  { return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}



			
		
		
		
	
	
	

}
