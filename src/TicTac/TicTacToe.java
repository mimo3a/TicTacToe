package TicTac;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import javax.swing.SingleSelectionModel;


public class TicTacToe extends Applet implements ActionListener {
	
	Button squares [];
	Button newGameButton;
	Label score;
	Label labelWon;
	Label labelLost;
	int emptySquaresLeft =9;
	int wonTimes ;
	int lostTimes ;
	TextField textFieldWon;
	
	// method init is a constructor of applet
	
	public void init() {
//		set applet layout manager font and color
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		this.setSize(320,320);
		
//		the applet font is bold and has a size of 20
		
		Font appletFont = new Font("Monospaset", Font.BOLD, 20);
		this.setFont(appletFont);
		
//		create a button "New game" and register an action listener in it
		
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		
		Panel topPanel = new Panel();
		Font topPanelFont = new Font("Monospaset", Font.ITALIC, 12);
		topPanel.setFont(topPanelFont);
		topPanel.setLayout(new FlowLayout());
		
		
		
		Label labelWon = new Label("You won");
		Label labelLost = new Label("You lost");
		
		topPanel.add(newGameButton);
		topPanel.add(labelWon);		
		topPanel.add(labelLost);
		
		
		
		this.add(topPanel, "North");
		
		Panel centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout(3,3));
		this.add(centerPanel, "Center");
		
		score = new Label("Your turn!");
		this.add(score, "South");
		
		// create an array to store links to 9 buttons
		
		squares = new Button[9];
		
//		create buttons save links to them in an array register them in their listener paint them
// 		to orange and add to the panel
		
		for(int i = 0; i<9; i++) {
			squares[i] = new Button();
			squares[i].addActionListener(this);
			squares[i].setBackground(Color.ORANGE);
			centerPanel.add(squares[i]);
		}
	}
	
//		This method will handle all events
	
	public void actionPerformed(ActionEvent e) {
		
		Button theButton = (Button) e.getSource();
//		it is a button "New Game"
		
		if(theButton == newGameButton) {
			for(int i = 0; i<9; i++) {
				squares[i].setEnabled(true);
				squares[i].setLabel("");
				squares[i].setBackground(Color.ORANGE);
				
				
				
			}
			emptySquaresLeft = 9;
			score.setText("Your turn!");
			
			newGameButton.setEnabled(false);
//			return; // выходим из метода
		}
		String winner = "";
		
//		it is one of squares
		for(int i = 0; i<9; i++) {
			if(theButton == squares[i]) {
				squares[i].setLabel("X");
				squares[i].setEnabled(false);
				winner = lookForWinner();
				System.out.println("winner = " + winner);
				if(!"".equals(winner)) {
					endTheGame();
				} else {
					
					computerMove();
					System.out.println("Hier");
					winner = lookForWinner();
					
					if(!"".equals(winner)) {
						endTheGame();
					}
				}
				break;
			}
		}	// end loop for
		
		if(winner.equals("X")) {
			score.setText("You won!");
			try {
				labelWon.setText(winner);
			} catch (Exception e1) {
				
				System.out.println("Etwas geht nicht richtig");
			}
			wonTimes++;
			System.out.println("Won times" + wonTimes);	
//			textFieldWon.setText(Integer.toString(this.wonTimes));
			
		} else if (winner.equals("O")) {
			score.setText("You lost!");
			lostTimes++;
		} else if(winner.equals("T")) {
			score.setText("It`s a tie!");
		}					
	}		// end of method actionPerformed
	/**
	 * This method is called after each move to find out if there is a winner.
	 * It checks each row, column and diagonal to find three cells with the same label. 
	 * (not empty) "T" = tie, "" = no winner yet
	 */
	String lookForWinner() {
		String theWinner = "";
		emptySquaresLeft --;
		System.out.println("empty Squares = " + emptySquaresLeft);
		
		if(emptySquaresLeft == 0) {
			return "T";
		} 
		
//		check row 1 - array elements 0, 1, 2
		
		if(!squares[0].getLabel().equals("") &&
			
			squares[0].getLabel().equals(squares[1].getLabel())	&&
			squares[0].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[0].getLabel();
			highLightWinner(0,1,2);
			
//			check row 2 - array elements 3, 4, 5
						
		} else if(!squares[3].getLabel().equals("") &&
					squares[3].getLabel().equals(squares[4].getLabel()) &&
					squares[3].getLabel().equals(squares[5].getLabel())) {
				theWinner = squares[3].getLabel();
				highLightWinner(3,4,5);
				
//			check row 3 - array elements 6,7,8			
				
		 } else if(!squares[6].getLabel().equals("") &&
				    squares[6].getLabel().equals(squares[7].getLabel()) &&
				    squares[6].getLabel().equals(squares[8].getLabel())) {
			 theWinner = squares[6].getLabel();
			 highLightWinner(6,7,8);
			 
//			 check row 1 - array elements 0,3,6
			 
		  } else if(!squares[0].getLabel().equals("") &&
				  squares[0].getLabel().equals(squares[3].getLabel()) &&
				  squares[0].getLabel().equals(squares[6].getLabel())) {
			  theWinner = squares[0].getLabel();
			  highLightWinner(0,3,6);
			  
//			  checking the column 2 array elements 1,4,7
			  
		  } else if(!squares[1].getLabel().equals("") &&
				  squares[1].getLabel().equals(squares[4].getLabel()) &&
				  squares[1].getLabel().equals(squares[7].getLabel())) {
			  theWinner = squares[1].getLabel();
			  highLightWinner(1,4,7);
			  
//			  checking the column 3 array elements 2,5,8
			  
		  } else if(!squares[2].getLabel().equals("") &&
				  squares[2].getLabel().equals(squares[5].getLabel()) &&
				  squares[2].getLabel().equals(squares[8].getLabel())) {
			  theWinner = squares[2].getLabel();
			  highLightWinner(2,5,8);
			  
//			  check the first diagonal array elements 0,4,8
			  
		  } else if(!squares[0].getLabel().equals("") &&
				  squares[0].getLabel().equals(squares[4].getLabel()) &&
				  squares[0].getLabel().equals(squares[8].getLabel())) {
			  theWinner = squares[0].getLabel();
			  highLightWinner(0,4,8);
//			  check the second  diagonal array elements 2,4,6
			  
		  } else if (!squares[2].getLabel().equals("") &&
				  squares[2].getLabel().equals(squares[4].getLabel()) &&
				  squares[2].getLabel().equals(squares[6].getLabel())) {
			  theWinner = squares[2].getLabel();
			  highLightWinner(2,4,6);
		  }
			return theWinner;
		}
		
		/**
		 * this method applies a set of rules to find the best computer move
		 * not found - a random cell is selected
		 */
		
		void computerMove() {
			int selectedSquare;
			
//			irst the computer tries to find an empty cell
//			 next to two cells with zeros to win
			
			selectedSquare = findEmptySquare("O");
			
//			if he cannot find two zeroes, then he does not allow him to make a row of three crosses
//			 by placing a zero next to two crosses
			
			if(selectedSquare == -1)  {
				
				selectedSquare = findEmptySquare("X");
			}
			
//			if selectedSquare is still -1 then it occupies the center square
			
			if((selectedSquare == -1) && (squares[4].getLabel().equals(""))) {
				selectedSquare = 4;
			}
//			f you are unlucky with the central cell, then we take a random one
			
			if(selectedSquare == -1) {
				selectedSquare = getRandomSquare();
			}
			
			squares[selectedSquare].setLabel("O");
			squares[selectedSquare].setEnabled(false);
		}
		/**
		 * This method checks each row, column, and diagonal to see if it contains
		 * two cells with the same captions and an empty cell
		 *@param is passed X for user and O for computer
		 *@return number of free cells or -1 if 2 cells are not found
		 * with the same inscriptions
		 */
		int findEmptySquare(String player) {
			int weight[] = new int[9];
			for(int i = 0; i<9; i++) {
				if(squares[i].getLabel().equals("O")) {
					weight[i] = -1;
				} else if(squares[i].getLabel().equals("X")) {
					weight[i] = 1;
				} else {
					weight[i] = 0;
				}				
			}
			int twoWeights = player.equals("O") ? -2 : 2;
			
//			check if there are two identical cells and one empty in row 1
			
			if(weight[0] + weight[1] + weight[2] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[1] == 0)
					return 1;
				else return -2;
			}
//			check if there are two identical cells and one empty in row 2
			
			if(weight[3] + weight[4] + weight[5] == twoWeights) {
				if(weight[3] == 0)
					return 3;
				else if(weight[4] == 0)
					return 4;
				else return 5;			
			}
			
//			check if there are two identical cells and one empty in row 3
			
			if(weight[6] + weight[7] + weight[8] == twoWeights) {
				if(weight[6] == 0)
					return 6;
				else if(weight[7] == 0)
					return 7;
				else return 8;			
			}
			
//			check if there are two identical cells and one empty in column 1
			
			if(weight[0] + weight[3] + weight[6] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[3] == 0)
					return 3;
				else return 6;			
			}
			
//			check if there are two identical cells and one empty in column 2	
			
			if(weight[1] + weight[4] + weight[7] == twoWeights) {
				if(weight[1] == 0)
					return 1;
				else if(weight[4] == 0)
					return 4;
				else return 7;			
			}
			
//			check if there are two identical cells and one empty in column 3
			
			if(weight[2] + weight[5] + weight[8] == twoWeights) {
				if(weight[2] == 0)
					return 2;
				else if(weight[5] == 0)
					return 5;
				else return 8;			
			}
			
//			check if there are two identical cells and one empty in diagonal 1
			
			if(weight[0] + weight[4] + weight[8] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[4] == 0)
					return 4;
				else return 8;			
			}
			
//			check if there are two identical cells and one empty in diagonal 2
			
			if(weight[2] + weight[4] + weight[6] == twoWeights) {
				if(weight[2] == 0)
					return 2;
				else if(weight[4] == 0)
					return 4;
				else return 6;			
			}
			
//			no two identical adjacent cells found
			
			return -1;			
		} // end of findEmptySquare() method
		
		/**
		 * This method selects any empty cell
		 * @return randomly selected cell number
		 */
		
		int getRandomSquare() {
			boolean gotEmptySquare = false;
			int selectedSquare = -1;
			
			do {
				selectedSquare = (int) (Math.random() * 9);
				if(squares[selectedSquare].getLabel().equals("")) {
					gotEmptySquare = true; // что-бы закончить цикл					
				}
			} while (!gotEmptySquare);
			
			
			return selectedSquare;
		} // end of method getRandomSquare
		
		/**
		 * This method highlights the winning line
		 * @param first, second and third cells to highlight
		 */
		
		void highLightWinner(int win1, int win2, int win3) {
			
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);			
		}
		
//		we make the cells not available and the "New Game" button available
		
		void endTheGame() {
			
			newGameButton.setEnabled(true);
			for(int i = 0; i<9; i++) {
				squares[i].setEnabled(false);
			}
			System.out.println("New Game");
		}
		
		public static void main(String[] args) {
			new TicTacToe();
		}
		
	} //end of class

