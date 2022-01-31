package TicTac;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class TicTacToe extends Applet implements ActionListener {
	
	Button squares [];
	Button newGameButton;
	Label score;
	Label labelWon;
	Label labelLost;
	int emptySquaresLeft;
	int wonTimes ;
	int lostTimes ;
	TextField textFieldWon;
	
	// метод init это конструктор апплета
	
	public void init() {
//		устанавливаем менеджер расположения апплета шрифт и цвет
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		this.setSize(320,320);
		
//		шрифт апплета жирный и имеет размер 20
		
		Font appletFont = new Font("Monospaset", Font.BOLD, 20);
		this.setFont(appletFont);
		
//		создаем кнопку "New game" и регистрируем в ней слушатель действия
		
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		
		Panel topPanel = new Panel();
		Font topPanelFont = new Font("Monospaset", Font.ITALIC, 12);
		topPanel.setFont(topPanelFont);
		topPanel.setLayout(new FlowLayout());
		
		TextField textFieldWon = new TextField(3);		
//		textFieldWon.setText(Integer.toString(this.wonTimes));
		
		TextField textFieldLost = new TextField(3);			
//		textFieldLost.setText(Integer.toString(this.lostTimes));
		
		Label labelWon = new Label("You won");
		Label labelLost = new Label("You lost");
		
		topPanel.add(newGameButton);
		topPanel.add(labelWon);
		topPanel.add(textFieldWon);
		topPanel.add(labelLost);
		topPanel.add(textFieldLost);
		
		this.add(topPanel, "North");
		
		Panel centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout(3,3));
		this.add(centerPanel, "Center");
		
		score = new Label("Your turn!");
		this.add(score, "South");
		
		// создаем массив для хранения ссылок на 9 кнопок
		
		squares = new Button[9];
		
//		создаем кнопки сохраняем ссылки на них в массиве регистрируем в их слушатель красим их
// 		в оранжевый цвет и добавляем на панель
		
		for(int i = 0; i<9; i++) {
			squares[i] = new Button();
			squares[i].addActionListener(this);
			squares[i].setBackground(Color.ORANGE);
			centerPanel.add(squares[i]);
		}
	}
	
//		Этот метод будет обрабатывать все события	
	
	public void actionPerformed(ActionEvent e) {
		
		Button theButton = (Button) e.getSource();
//		Это кнопка "New Game"
		
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
		
//		это одна из клеток
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
					winner = lookForWinner();
					
					if(!"".equals(winner)) {
						endTheGame();
					}
				}
				break;
			}
		}	// конец цикла for
		
		if(winner.equals("X")) {
			score.setText("You won!");
			labelWon.setText(winner);
			wonTimes++;
			System.out.println("Won times" + wonTimes);	
//			textFieldWon.setText(Integer.toString(this.wonTimes));
			
		} else if (winner.equals("O")) {
			score.setText("You lost!");
			lostTimes++;
		} else if(winner.equals("T")) {
			score.setText("It`s a tie!");
		}					
	}		// кшнец метода actionPerformed
	/**
	 * Этот метод вызывается после каждого хода что-бы узнать есть-ли победитель
	 * Он проверяет каждый ряд колонку и диагональ, что-бы найти три клетки с одинаковыми надписями 
	 * (не пустыми) "T" = ничья, "" = еще нет победителя 
	 */
	String lookForWinner() {
		String theWinner = "";
		emptySquaresLeft --;
		
		if(emptySquaresLeft == 0) {
			return "T";
		}
		
//		проверяем ряд 1 - элементы массива 0, 1, 2
		
		if(!squares[0].getLabel().equals("") &&
			
			squares[0].getLabel().equals(squares[1].getLabel())	&&
			squares[0].getLabel().equals(squares[2].getLabel())) {
			theWinner = squares[0].getLabel();
			highLightWinner(0,1,2);
			
//			провкряем ряд 2 - элементы массива 3, 4, 5
						
		} else if(!squares[3].getLabel().equals("") &&
					squares[3].getLabel().equals(squares[4].getLabel()) &&
					squares[3].getLabel().equals(squares[5].getLabel())) {
				theWinner = squares[3].getLabel();
				highLightWinner(3,4,5);
				
//			проверяем ряд 3 - элементы массива 6,7,8				
				
		 } else if(!squares[6].getLabel().equals("") &&
				    squares[6].getLabel().equals(squares[7].getLabel()) &&
				    squares[6].getLabel().equals(squares[8].getLabel())) {
			 theWinner = squares[6].getLabel();
			 highLightWinner(6,7,8);
			 
//			 проверяем колонку 1 - элементы массива 0,3,6
			 
		  } else if(!squares[0].getLabel().equals("") &&
				  squares[0].getLabel().equals(squares[3].getLabel()) &&
				  squares[0].getLabel().equals(squares[6].getLabel())) {
			  theWinner = squares[0].getLabel();
			  highLightWinner(0,3,6);
			  
//			  проверяем колонку 2 элементы массива 1,4,7
			  
		  } else if(!squares[1].getLabel().equals("") &&
				  squares[1].getLabel().equals(squares[4].getLabel()) &&
				  squares[1].getLabel().equals(squares[7].getLabel())) {
			  theWinner = squares[1].getLabel();
			  highLightWinner(1,4,7);
			  
//			  проверяем колонку 3 элементы массива 2,5,8
			  
		  } else if(!squares[2].getLabel().equals("") &&
				  squares[2].getLabel().equals(squares[5].getLabel()) &&
				  squares[2].getLabel().equals(squares[8].getLabel())) {
			  theWinner = squares[2].getLabel();
			  highLightWinner(2,5,8);
			  
//			  проверяем первую диагональ элементы массива 0,4,8
			  
		  } else if(!squares[0].getLabel().equals("") &&
				  squares[0].getLabel().equals(squares[4].getLabel()) &&
				  squares[0].getLabel().equals(squares[8].getLabel())) {
			  theWinner = squares[0].getLabel();
			  highLightWinner(0,4,8);
//			  проверяем вторую диагональ элементы массива 2,4,6
			  
		  } else if (!squares[2].getLabel().equals("") &&
				  squares[2].getLabel().equals(squares[4].getLabel()) &&
				  squares[2].getLabel().equals(squares[6].getLabel())) {
			  theWinner = squares[2].getLabel();
			  highLightWinner(2,4,6);
		  }
			return theWinner;
		}
		
		/**
		 * этод метод применяет набор правил что=бы найти лучший компьютерный ход
		 * не найден - выбирается случайная клетка
		 */
		
		void computerMove() {
			int selectedSquare;
			
//			сначала компьютер пытается найти пустую клетку
// 			рядом с двумя клетками с ноликами чтобы выиграть
			
			selectedSquare = findEmptySquare("O");
			
//			если он не может найти два нолика то не дает сделать ряд из трех крестиков
//			поместив нолик рядом с лвумя крестиками
			
			if(selectedSquare == -1)  {
				
				selectedSquare = findEmptySquare("X");
			}
			
//			если selectedSquare все еще равен -1 то занимает центральную клетку
			
			if((selectedSquare == -1) && (squares[4].getLabel().equals(""))) {
				selectedSquare = 4;
			}
//			если не повезло с центральной клеткой то занимаем случайную
			
			if(selectedSquare == -1) {
				selectedSquare = getRandomSquare();
			}
			
			squares[selectedSquare].setLabel("O");
			squares[selectedSquare].setEnabled(false);
		}
		/**
		 * Этот метод проверяет каждый ряд, колонку и диагональ, что-бы узнать, есть ли в ней
		 * две клетки с одинаковыми надписями и пустой клеткой
		 * @param передается Х для пользователя и О для компьютера
		 * @return количество свободных клеток или -1 если не найдено 2 клетки 
		 * с одинаковыми надписями
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
			
//			проверяем есть ли в ряду 1 две одинаковые клетки и одна пустая
			
			if(weight[0] + weight[1] + weight[2] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[1] == 0)
					return 1;
				else return -2;
			}
//			проверяем есть ли в ряду 2 две одинаковые клетки и одна пустая
			
			if(weight[3] + weight[4] + weight[5] == twoWeights) {
				if(weight[3] == 0)
					return 3;
				else if(weight[4] == 0)
					return 4;
				else return 5;			
			}
			
//			проверяем есть ли в ряду 2 две одинаковые клетки и одна пустая
			
			if(weight[6] + weight[7] + weight[8] == twoWeights) {
				if(weight[6] == 0)
					return 6;
				else if(weight[7] == 0)
					return 7;
				else return 8;			
			}
			
//			проверяем есть ли в колонке 1  две одинаковые клетки и одна пустая	
			
			if(weight[0] + weight[3] + weight[6] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[3] == 0)
					return 3;
				else return 6;			
			}
			
//			проверяем есть ли в колонке 2  две одинаковые клетки и одна пустая	
			
			if(weight[1] + weight[4] + weight[7] == twoWeights) {
				if(weight[1] == 0)
					return 1;
				else if(weight[4] == 0)
					return 4;
				else return 7;			
			}
			
//			проверяем есть ли в колонке 3  две одинаковые клетки и одна пустая
			
			if(weight[2] + weight[5] + weight[8] == twoWeights) {
				if(weight[2] == 0)
					return 2;
				else if(weight[5] == 0)
					return 5;
				else return 8;			
			}
			
//			проверяем есть ли в диагонали 1  две одинаковые клетки и одна пустая
			
			if(weight[0] + weight[4] + weight[8] == twoWeights) {
				if(weight[0] == 0)
					return 0;
				else if(weight[4] == 0)
					return 4;
				else return 8;			
			}
			
//			проверяем есть ли в диагонали 2 две одинаковые клетки и одна пустая	
			
			if(weight[2] + weight[4] + weight[6] == twoWeights) {
				if(weight[2] == 0)
					return 2;
				else if(weight[4] == 0)
					return 4;
				else return 6;			
			}
			
//			не найдено двух одинаковых соседних клеток
			
			return -1;			
		} // конец метода findEmptySquare()
		
		/**
		 * Этот метод выбирает любую пустую клетку
		 * @return случайно выбраный еомер клетки
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
		} // конец метода getRandomSquare
		
		/**
		 * Этот метод выделяет выигравшую линию
		 * @param первая, вторая и третья клетка для выделения
		 */
		
		void highLightWinner(int win1, int win2, int win3) {
			
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);			
		}
		
//		делаем недоступными клетки и доступной енопку "New Game"
		
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
		
	} //конец класса

