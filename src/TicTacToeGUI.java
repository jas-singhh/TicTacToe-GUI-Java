package practice.TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


public class TicTacToeGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel titlePanel;
	private JPanel gamePanel;
	private JButton[] buttons;
	private JLabel title;
	
	private boolean p1Turn;
	
	private Random rnd;
	
	public TicTacToeGUI() {
		frame = new JFrame();
		titlePanel = new JPanel();
		gamePanel = new JPanel();
		buttons = new JButton[9];
		title = new JLabel();
		
		rnd = new Random();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe");
		frame.setResizable(false);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new BorderLayout());
		
		title.setForeground(Color.black);
		title.setFont(new Font("Ink Free", Font.BOLD, 25));
		title.setText("Tic-Tac-Toe");
		title.setBounds(0, 0, 500, 100);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.white);
		titlePanel.setBounds(0, 0, 500, 100);
		titlePanel.add(title);
		
		frame.add(titlePanel, BorderLayout.NORTH);
		
		gamePanel.setLayout(new GridLayout(3,3));
		gamePanel.setBackground(Color.white);
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			gamePanel.add(buttons[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 100));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		frame.add(gamePanel);
		
		randomTurn();
	}
	
	private void randomTurn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rndNum = rnd.nextInt(2);// 0 or 1
		if(rndNum == 1) {
			p1Turn = true;
			title.setText("Player X");
		} else {
			p1Turn = false;
			title.setText("Player O");
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < buttons.length; i++) {
			if(e.getSource().equals(buttons[i])) {
				if(p1Turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setText("X");
						title.setText("Player O");
						p1Turn = !p1Turn;
						if(checkWin("X")) {
							title.setText("X wins!");
						}
					} else {
						title.setText("Position is taken");
					}
				} else {
					if(buttons[i].getText() == "") {
						buttons[i].setText("O");
						title.setText("Player X");
						p1Turn = !p1Turn;
						if(checkWin("O")) {
							title.setText("O wins!");
						}
					} else {
						title.setText("Position is taken");
					}
				}
			}
		}
	}
	
	public boolean checkWin(String symbol) {
		if(buttons[0].getText().equals(symbol) && buttons[1].getText().equals(symbol)
				&& buttons[2].getText().equals(symbol)) {
			markPositions(0, 1, 2);
			return true;
		} else if(buttons[3].getText().equals(symbol) && buttons[4].getText().equals(symbol)
		&& buttons[5].getText().equals(symbol)) {
			markPositions(3, 4, 5);
			return true;
		} else if(buttons[6].getText().equals(symbol) && buttons[7].getText().equals(symbol)
				&& buttons[8].getText().equals(symbol)) {
			markPositions(6, 7, 8);
			return true;
		} else if(buttons[0].getText().equals(symbol) && buttons[3].getText().equals(symbol)
				&& buttons[6].getText().equals(symbol)) {
			markPositions(0, 3, 6);
			return true;
		} else if(buttons[1].getText().equals(symbol) && buttons[4].getText().equals(symbol)
				&& buttons[7].getText().equals(symbol)) {
			markPositions(1, 4, 7);
			return true;
		} else if(buttons[2].getText().equals(symbol) && buttons[5].getText().equals(symbol)
				&& buttons[8].getText().equals(symbol)) {
			markPositions(2, 5, 8);
			return true;
		} else if(buttons[0].getText().equals(symbol) && buttons[4].getText().equals(symbol)
				&& buttons[8].getText().equals(symbol)) {
			markPositions(0, 4, 8);
			return true;
		} else if(buttons[2].getText().equals(symbol) && buttons[4].getText().equals(symbol)
				&& buttons[6].getText().equals(symbol)) {
			markPositions(2, 4, 6);
			return true;
		}
		return false;
	}
	
	public void markPositions(int pos1, int pos2, int pos3) {
		for(int i = 0; i < buttons.length; i++) {
			buttons[pos1].setBackground(Color.green);
			buttons[pos2].setBackground(Color.green);
			buttons[pos3].setBackground(Color.green);
			buttons[i].setEnabled(false);
		}
	}

}
