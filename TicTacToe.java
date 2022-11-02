package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    public static int BOARD_SIZE = 3;
    
    public static enum GameResult {
        Incomplete, XWinner, YWinner, Draw;
    }

    private JButton[][] button = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crosTurn = true;

    TicTacToe() {
        super.setTitle("Tic-Tac-Toe");
        super.setSize(800, 800);
        GridLayout gameGrid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        super.setLayout(gameGrid);
        Font font = new Font("Serif", Font.CENTER_BASELINE, 120);
        for (int rowSize = 0; rowSize < BOARD_SIZE; rowSize++) {
            for (int colSize = 0; colSize < BOARD_SIZE; colSize++) {
                JButton buttons = new JButton("");
                button[rowSize][colSize] = buttons;
                buttons.setFont(font);
                buttons.addActionListener(this);
                super.add(buttons);
            }

        }
        super.setResizable(false);
        super.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        nextMoveInGame(clickedButton);
        GameResult result = this.getGameStatus();
        if(result==GameResult.Incomplete){
            return;
        }
        finalWinner(result);
        int play = JOptionPane.showConfirmDialog(this,"Do you want to play again");
        if (play==JOptionPane.YES_OPTION) {
            for (int i=0;i<BOARD_SIZE;i++){
                for (int j= 0; j < BOARD_SIZE; j++) {
                    button[i][j].setText("");
                }
            }
        } else {
            super.dispose();
        }
    }

    private void finalWinner(GameResult result) {
        if (result==GameResult.XWinner) {
            JOptionPane.showMessageDialog(this,"X has Won");
        } else if(result==GameResult.YWinner) {
            JOptionPane.showMessageDialog(this,"0 is Winner");
        } else {
            JOptionPane.showMessageDialog(this,"Game is Draw");
        }
    }

    private GameResult getGameStatus() {
        String s1 = "";
        String s2 = "";

        int row = 0 ;
        while(row < BOARD_SIZE){
            int col = 0 ;
            while (col< BOARD_SIZE-1) {
                s1 = button[row][col].getText();
                s2 = button[row][col+1].getText();
                if (!s1.equals(s2) || s1.length()==0) {
                    break;
                }
                col++;
            }
            if(col == BOARD_SIZE-1) {
                if(s1.equals("X")) {
                    return GameResult.XWinner;
                } else {
                    return GameResult.YWinner;
                }
            }
            row++;
        }
        // column wise
        int col = 0 ;
        while(col < BOARD_SIZE){
             row = 0 ;
            while (row < BOARD_SIZE-1) {
                s1 = button[row][col].getText();
                s2 = button[row +1][col].getText();
                if (!s1.equals(s2) || s1.length()==0) {
                    break;
                }
                row++;
            }
            if(row == BOARD_SIZE-1) {
                if(s1.equals("X")) {
                    return GameResult.XWinner;
                } else {
                    return GameResult.YWinner;
                }
            }
            col++;
        }
        // Diagonal Check

        if (button[0][0].getText().equals(button[1][1].getText()) && button[0][0].getText().equals(button[2][2].getText()) && button[0][0].getText().length()>0) {
            if (button[0][0].getText().equals("X")) {
                return GameResult.XWinner;
            } else {
                return GameResult.YWinner;
            }
        }

        if (button[2][0].getText().equals(button[1][1].getText()) && button[1][1].getText().equals(button[0][2].getText()) && button[2][0].getText().length()>0) {
            if (button[2][0].getText().equals("X")) {
                return GameResult.XWinner;
            } else {
                return GameResult.YWinner;
            }
        }

        String check = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (button[i][j].getText().length()==0){
                    return GameResult.Incomplete;
                }
            }
        }
        return GameResult.Draw;
    }

    private void nextMoveInGame(JButton clickedButton) {
        String s = clickedButton.getText();
        if (s.length()>0) {
            JOptionPane.showMessageDialog(this, "Invalid Move !");
        } else {
            if (crosTurn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            crosTurn = !crosTurn;
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
