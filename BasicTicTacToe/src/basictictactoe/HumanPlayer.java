/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictictactoe;

/**
 *
 * @author robert
 */
public class HumanPlayer implements Player {
    
    private int pieceChoice;
    private Gameplay game;
    
    public HumanPlayer(int choice) {
        
        pieceChoice = choice;
        
    }
    
    public HumanPlayer(int choice, Gameplay game) {
        
        pieceChoice = choice; 
        this.game = game;
        
    }
    
    public void setGameplay(Gameplay g) {
        
        game = g;
        
    }
    
    public void makeMove(int i, int j) {
        
        if(game.isLegal(i, j))
            game.record(i, j, new Piece(pieceChoice));
        
    }
    
    public int getChoice() {
        
        return pieceChoice;
        
    }
    
}
