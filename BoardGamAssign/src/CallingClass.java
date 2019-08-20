import java.util.*;

public class CallingClass {
	public static void main(String[] args) {
		int gameNumber= 10000; 
		int numPlayers; 
		int numMoves; 
		int posBoard; 
		int game;
		
		//DoublyLinkedList<Space> board = new DoublyLinkedList<Space>();
		Scanner input = new Scanner(System.in); 
		
		System.out.println("Type each of the following:");
		System.out.println("Number of players: ");
		numPlayers = input.nextInt(); 
		//System.out.println("Number of desired games: ");
		//gameNumber = input.nextInt(); 
		System.out.println("Game number: ");
		game = input.nextInt();		
		System.out.println("Number of moves: ");
		numMoves = input.nextInt(); 

	
		Space space0 = new Space(0, 0, "START", 0, null);
		Space space1 = new Space(5, 1, "green", 0, null);
		Space space2 = new Space(10, 2, "yellow",0, null);
		Space space3 = new Space(8, 3, "pink", 0, null);
		Space space4 = new Space(10, 4, "red", 0, null);
		Space space5 = new Space(7, 5, "orange", 0, null);
		Space space6 = new Space(5, 6, "green", 0, null);
		Space space7 = new Space(9, 7, "purple", 0, null);
		Space space8 = new Space(10, 8, "yellow", 0, null);
		Space space9 = new Space(6, 9, "pink", 0, null);
		Space space10 = new Space(7, 10, "purple", 0, null);
		Space space11= new Space(10, 11, "yellow", 0, null);
		Space space12 = new Space(6, 12, "red", 0, null);
		Space space13 = new Space(5, 13, "purple", 0, null);
		Space space14= new Space(8, 14, "green", 0, null);
		Space space15 = new Space(9, 15, "orange", 0, null);
		Space space16 = new Space(5, 16, "yellow", 0, null);
		Space space17= new Space(10, 17, "orange", 0, null);
		Space space18= new Space(5, 18, "yellow", 0, null);
		Space space19= new Space(9, 19, "purple", 0, null);
		Space space20= new Space(6, 20, "orange", 0, null);
		Space space21= new Space(8, 21, "green", 0, null);
		Space space22= new Space(7, 22, "red", 0, null);
		Space space23= new Space(10, 23, "pink", 0, null);
		Space space24= new Space(6, 24, "purple", 0, null);
		Space space25= new Space(8, 25, "yellow", 0, null);
		Space space26 = new Space(0, 0, "END", 0, null);
		
		DoublyLinkedList<Space>[] boards = new DoublyLinkedList[gameNumber];
		for(int i = 0; i<gameNumber; i++) {
			boards[i] = new DoublyLinkedList<Space>();
			boards[i].addLast(space0);
			boards[i].addLast(space1);
			boards[i].addLast(space2);
			boards[i].addLast(space3);
			boards[i].addLast(space4);
			boards[i].addLast(space5);
			boards[i].addLast(space6);
			boards[i].addLast(space7);
			boards[i].addLast(space8);
			boards[i].addLast(space9);
			boards[i].addLast(space10);
			boards[i].addLast(space11);
			boards[i].addLast(space12);
			boards[i].addLast(space13);
			boards[i].addLast(space14);
			boards[i].addLast(space15);
			boards[i].addLast(space16);
			boards[i].addLast(space17);
			boards[i].addLast(space18);
			boards[i].addLast(space19);
			boards[i].addLast(space20);
			boards[i].addLast(space21);
			boards[i].addLast(space22);
			boards[i].addLast(space23);
			boards[i].addLast(space24);
			boards[i].addLast(space25);
			boards[i].addLast(space26);

		}
		System.out.println("\n");		
		playGame(numPlayers, gameNumber, boards, game, numMoves);
				
	}

		

		
		
		public static <E> void playGame(int numPlayers, int gameNumber, DoublyLinkedList<E> board[], int game, int numMoves) {
			Scanner input = new Scanner(System.in);
			
			System.out.print("Would you like to display extra boards? Y/N: ");
			String answer = input.nextLine(); 
			Player [] players = null;
			int totalWins = 0;
			int totalMoves = 0; 
			players = new Player[numPlayers];
			for (int i = 0; i<numPlayers; i++) {
				players[i] = new Player(i+1, null,0, 0,0 );
//				System.out.println(players[i].toString());				
			}
			
			for (int a = 0; a<gameNumber; a++) {
				//System.out.println("GAME #: "+(a+1));
				//playGame(numPlayers, gameNumber, board[a]);
				//System.out.println("");
			
			//	int score = 0;
				
				for (int i = 0; i<numPlayers; i++) {
					players[i].setPosition(board[a].findElement(0));
					players[i].setScore(0);
					players[i].setNumMoves(0);
					//System.out.println(players[i].toString());				
				}
				String type = null; 
				totalMoves= 0;
				//System.out.println(board[a].toString());
				while(type!="END") {
					for(int i = 0; i<numPlayers; i++) {
							move(players[i], board[a]);
							totalMoves++;
							if(a+1 == game) {
								if (totalMoves==numMoves) {
									System.out.println("\nGAME NUMBER "+ (a+1)+"\n");
									System.out.println(board[a].toString());
									System.out.println("\n" + "\n");
								}
							}
							if(((Space)players[i].getPosition()).getType().equals("END")) {
								if(players[i].getScore()<40) {
									players[i].setPosition(board[a].findElement(0));
									
									//[i].setNumMoves(0);
								
							
								}
								else{ // players[i] has won
									totalWins++;
									//System.out.println(players[i].getPlayerNumber()+" is winner!! in game number"+ (a+1));
									players[i].setNumWins(players[i].getNumWins()+1);
									double movesToWin = players[i].getMovesToWin();
									double wins = players[i].getNumWins();
									double newMovesToWin = ((movesToWin * (wins-1)) + players[i].getNumMoves()) / wins;
									double roundNM = (double) Math.round(newMovesToWin * 100) / 100;
									players[i].setMovesToWin(roundNM);
									
									//System.out.println(a);
									
									
									//System.out.println(totalMoves + "\n" + "\n");
									if((a+1)%100 == 0&& answer.equalsIgnoreCase("Y")) {
										System.out.println("\n");
										System.out.println("GAME NUMBER "+(a+1));
										System.out.println(board[a].toString());
										System.out.println("\n" + "\n");
									}
									
									type = "END";
									break;
							}
							
					}
					
				}
			
				}
		
			}
			playerStats(players, totalWins);

		}
			
			
		
		public static int roll() {
			int dice = 1+(int)(Math.random()*6);
			return dice; 
			
			
		}
		public static <E> Player move(Player player, DoublyLinkedList<E> board) {
			String type =((Space)player.getPosition()).getType();

			E previousPos = (E) player.getPosition();
			if(previousPos == null) {throw new NullPointerException("Current position is null in the move method...");}
			int diceRoll = roll();
			player.setNumMoves(player.getNumMoves()+1);
			//System.out.println("Player # "+player.getPlayerNumber()+", DR: "+diceRoll);
			
			E newPosition = (board.jump(diceRoll, previousPos));
			if (newPosition == null) { throw new NullPointerException("Position is null"); }
			
			if (((Space)newPosition).getPlayerOnBoard()>0) {
				Player previousPlayer = ((Space)newPosition).getPlayer();
				previousPlayer.setPosition(board.jumpBack(newPosition));
				//System.out.println("Player number "+previousPlayer.getPlayerNumber() +" is now at "+previousPlayer.getPosition());
			}	
			
			player.setScore(player.getScore() +  ((Space) newPosition).getValue());
			if (player.getScore()%4 == 0) {
				newPosition = (E) ((Space)board.findElement(0));
				//System.out.printf("Player number %d is set back\n", (player.getPlayerNumber()));
			}
			
			player.setPosition(newPosition);
			((Space)previousPos).setPlayeronBoard(0);
		
			//System.out.println(player.toString());
			return player;
		}
		
			
		public static void playerStats(Player []players, int totalWins) {
			System.out.println("\n");
			double wins, moves, avgwins;
			for(int i = 0; i<players.length; i++) {
				wins = players[i].getNumWins();
				moves = players[i].getNumMoves();
				avgwins = ((wins/totalWins)*100);
				String str = "Player number "+ players[i].getPlayerNumber()
						+" won "+ (double) Math.round(avgwins * 100) / 100 +" % of the time with " + (players[i].getMovesToWin()) + " average moves per win.";
				System.out.println(str);		
			}
		}
		
		
			
	}


		
		
			

			
		
		
		



		
	
		

		

		

