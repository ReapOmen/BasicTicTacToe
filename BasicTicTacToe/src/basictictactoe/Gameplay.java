/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictictactoe;

import java.util.Random;

/**
 *
 * @author robert
 */
public class Gameplay {
    
    private Piece[][] board;
    private Player player1, player2;
    private boolean winner, turnOfPlayer1;
    private int n;
    private Frame frame;
    private int moves;
    
    public Gameplay(int N, Player p, Frame f) {
        
        n = N;
        board = new Piece[n][n];
        moves = n * n;
        frame = f;
        player1 = p;
        ((HumanPlayer)player1).setGameplay(this);
        player2 = new EasyComputerPlayer(-player1.getChoice(), this);
        
        if(player1.getChoice() == Piece.CROSS)
            turnOfPlayer1 = true;
        else
            turnOfPlayer1 = false;
        
        winner = false;
        
        Piece piece = new Piece(Piece.EMPTY);
        for(int i = 0 ; i < n; ++i)
            for(int j = 0 ; j < n; ++j)
                board[i][j] = piece;        
    }
    
    public Gameplay(Player p, Frame f) {
        
        n = 3;
        board = new Piece[n][n];
        moves = n * n;
        frame = f;
        player1 = p;
        ((HumanPlayer)player1).setGameplay(this);
        player2 = new EasyComputerPlayer(-player1.getChoice(), this);
        
        if(player1.getChoice() == Piece.CROSS)
            turnOfPlayer1 = true;
        else
            turnOfPlayer1 = false;
        
        winner = false;
        
        Piece piece = new Piece(Piece.EMPTY);
        for(int i = 0 ; i < n; ++i)
            for(int j = 0 ; j < n; ++j)
                board[i][j] = piece;        
    }
    
    public void record(int i, int j, Piece p) {
        
        board[i][j] = p;
        frame.recordMove(i, j, p.getValue());
        --moves;
        turnOfPlayer1 = !turnOfPlayer1;
        checkWinner(i, j);
        
    }
    
    public void play(int i, int j) {
        
        if(player2 instanceof EasyComputerPlayer) {
            
            player1.makeMove(i, j);
            if(!winner && !turnOfPlayer1)
                ((EasyComputerPlayer)player2).makeMove(n);
            
        }
        
        if(winner)
            reset();
        
    }
   
    public void reset() {
        
        Piece piece = new Piece(Piece.EMPTY);
        for(int i = 0 ; i < n; ++i)
            for(int j = 0 ; j < n; ++j)
                board[i][j] = piece;
        frame.reset();
        turnOfPlayer1 = true;
        winner = false;
        moves = n * n;
        
    }
    
    private void checkTie() {
        
        if(moves <= 0 && !winner) {
            
            frame.tie();
            winner = true;
        
        }
        
    }
    

    
    private void checkWinner(int i, int j) {
        
        
        boolean nInARow1 = true;
        for(int x = 0; x < n-1; ++x)
            if(!(board[i][x].equals(board[i][x+1])))
                nInARow1 = false;
        
        boolean nInARow2 = true;
        for(int x = 0; x < n-1; ++x)
            if(!(board[x][j].equals(board[x+1][j])))
                nInARow2 = false;

        boolean nInARow3 = true;
        for(int x = 0; x < n-1 ; ++x)
            if(!(board[x][x].equals(board[x+1][x+1])))
                nInARow3 = false;

        boolean nInARow4 = true;
        for(int x = 1, y = n - 2; x < n ; ++x, --y)
            if(!(board[x][y].equals(board[x-1][y+1])))
                nInARow4 = false;
        
        if(nInARow1 || nInARow2 || nInARow3 || nInARow4) {
        
            Piece p = board[i][j];
            if(p.getValue() == player2.getChoice())
                frame.winner("Computer");
            else
                frame.winner("Player1");
            
            winner = true;        
        }
        checkTie();
                
    }
    
    public boolean isLegal(int i , int j) {
        
        if(board[i][j].getValue() == Piece.EMPTY)
            return true;
        
        return false;
        
    }
    
}
