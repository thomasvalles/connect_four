package hw4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GUICF extends CFGame{
	private CFPlayer player1;
	private CFPlayer player2;
	private GameBoard this_board;
	
	/**
	 * human/ai constructor
	 * @param ai the ai to play
	 */
	public GUICF(CFPlayer ai) {
		//set up GUI
		JFrame outer = new JFrame("Conenct Four");	
		outer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		outer.setLayout(new BorderLayout());
		outer.setSize(620,500);
		
		Container pane = outer.getContentPane();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 7));
		JButton play1 = new JButton("\u2193");
		JButton play2 = new JButton("\u2193");
		JButton play3 = new JButton("\u2193");
		JButton play4 = new JButton("\u2193");
		JButton play5 = new JButton("\u2193");
		JButton play6 = new JButton("\u2193");
		JButton play7 = new JButton("\u2193");
		
		play1.addActionListener(new Play1());
		play2.addActionListener(new Play2());
		play3.addActionListener(new Play3());
		play4.addActionListener(new Play4());
		play5.addActionListener(new Play5());
		play6.addActionListener(new Play6());
		play7.addActionListener(new Play7());
		
		panel1.add(play1);
		panel1.add(play2);
		panel1.add(play3);
		panel1.add(play4);
		panel1.add(play5);
		panel1.add(play6);
		panel1.add(play7);
		
		pane.add(panel1, BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		this_board = new GameBoard();
		panel2.add(this_board);
		outer.add(panel2);
		
		if(Math.random() < 0.5) { //50% chance of ai going first
			player1 = ai;
			playGUI(player1.nextMove(this));
		}
		else { //50% chance of ai going second
			player2 = ai;
		}	
		outer.setVisible(true);		
	}
	
	/**
	 * ai/ai constructor
	 * @param ai1 the first ai
	 * @param ai2 the second ai
	 */
	public GUICF(CFPlayer ai1, CFPlayer ai2) {
		if(Math.random() < 0.5) { //50% chance first ai goes first
			player1 = ai1;
			player2 = ai2;
		}
		else { //50% chance second ai goes first
			player1 = ai2;
			player2 = ai1;
		}
		
		//set up the GUI
		JFrame outer = new JFrame("Conenct Four");	
		outer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		outer.setLayout(new BorderLayout());
		outer.setSize(620,500);
		
		Container pane = outer.getContentPane();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JButton play = new JButton("Play");
		play.addMouseListener(new PlayButton());
		
		panel1.add(play, BorderLayout.NORTH);
		pane.add(panel1, BorderLayout.CENTER);
		
		this_board = new GameBoard();
		panel1.add(this_board, BorderLayout.CENTER);
	
		outer.setVisible(true);
	
	}
	
	/**
	 * Helper function to get state of game
	 * @return the current CFGame object
	 */
	private CFGame getThis(){
		return this;
	}
	
	/**
	 * play a move 
	 * @param c the column to play
	 * @return true of valid move
	 */
	private boolean playGUI(int c) {
		int[][] state = getState(); 
		int color = -100; //initialize color to bad value
		int row = -1; //initialize row to bad value
		
		
		for(int i = 5; i >= 0; --i) { //find the playable row
			if(state[c-1][i] == 0) {
				row = i;
				break;
			}
		}
		
		if(play(c)) { //if you can play the column
			if(!isRedTurn()) { //if red just went
				color = 1;
			}
			else { //black just went
				color = -1;
			}
			this_board.paint(row, c-1, color); //paint that row and column
			checkWin(); 
			return true;
		}
		else { //couldn't play the column
			return false;
		}
	}

	/**
	 * checks for win, outputs winning message
	 */
	private void checkWin() {
		if(isGameOver()) { //if the game is over
			String winnerName = "";
			if(winner() == 0) { //if draw
				winnerName = "Draw";
			}
			else if(winner() == 1) { //if red won
				if(player1 == null) { //if red was the human
					winnerName = "You";
				}
				else { //red was ai
					winnerName = player1.getName();
				}
			}
			else { //if black won
				if(player2 == null) { //if black was human
					winnerName = "You";
				}
				else { //black was ai
					winnerName = player2.getName();
				}
			}
			
			//set up winning GUI
			JFrame frame = new JFrame("GAMEOVER");	
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.setSize(400,300);
			
			JLabel winningText = new JLabel(winnerName + " won the game!");
			winningText.setHorizontalAlignment(JLabel.CENTER);
			frame.add(winningText, BorderLayout.CENTER);
			frame.setVisible(true);
		}
	}
	
	//play button class for ai/ai
	final class PlayButton implements MouseListener{
		@Override
		/**
		 * on click, make the move
		 */
		public void mouseClicked(MouseEvent arg0) {
			if(isRedTurn()) {
				if(!isGameOver()) {
					while(!playGUI(player1.nextMove(getThis()))) {} //player 1 play
				}
			}
			else {
				if(!isGameOver()) {
					while(!playGUI(player2.nextMove(getThis()))) {} //player 2 play
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	
	//button 1 action class
	private class Play1 implements ActionListener{
		
		/**
		 * plays the move if button 1 clicked, same across all columns
		 */
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) { //if the game isn't over
				if(player1 instanceof CFPlayer) { //if player 1 is the ai
					if(playGUI(1)) { //play this column
						if(!isGameOver()) { //if the game isn't over
							while(!playGUI(player1.nextMove(getThis()))); //have the ai go
						}
					}
				}
				else { //player 2 is the ai
					if(playGUI(1)) { //play this column
						if(!isGameOver()) { //if the game isn't over
							while(!playGUI(player2.nextMove(getThis()))); //have the ai go
						}
					}		
				}
			}
		}
	}
	
	private class Play2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(2)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(2)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	private class Play3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(3)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(3)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	private class Play4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(4)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(4)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	private class Play5 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(5)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(5)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	private class Play6 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(6)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(6)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	private class Play7 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isGameOver()) {
				if(player1 instanceof CFPlayer) {
					if(playGUI(7)) {
						if(!isGameOver()) {
							while(!playGUI(player1.nextMove(getThis())));
						}
					}
				}
				else {
					if(playGUI(7)) {
						if(!isGameOver()) {
							while(!playGUI(player2.nextMove(getThis())));
						}
					}		
				}
			}
		}
	}
	
	
	
	private class GameBoard extends javax.swing.JPanel{
		private JLabel[][] slots = new JLabel[6][7];
		private GameBoard(){
			this.setLayout(new GridLayout(6, 7));
			for(int i = 0; i < 6; ++i) {
				for(int j = 0; j < 7; ++j) {
					slots[i][j] = new JLabel();
					slots[i][j].setLayout(new BorderLayout());
					slots[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
					slots[i][j].setOpaque(true);
					slots[i][j].setBackground(Color.WHITE);
					this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					this.add(slots[i][j], BorderLayout.SOUTH);
				}
			}
			this.setVisible(true);
		}
		
		private void paint(int x, int y, int color) {
			if(color == 1) {
				slots[x][y].setBackground(Color.RED);
			}
			else if(color == -1) {
				slots[x][y].setBackground(Color.BLACK);
			}
		}
		
	}
}
