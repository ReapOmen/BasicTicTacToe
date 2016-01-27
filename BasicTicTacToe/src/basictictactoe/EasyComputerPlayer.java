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
public class EasyComputerPlayer implements Player {
    
    private int pieceChoice;
    private Gameplay game;
    private Random rnd;
    
    public EasyComputerPlayer(int choice, Gameplay game) {
        
        pieceChoice = choice; 
        this.game = game;
        
    }
    
    public EasyComputerPlayer(int choice) {
        
        pieceChoice = choice;
        
    }
    
    public void makeMove(int i, int j) {
        
        game.record(i, j, new Piece(pieceChoice));
        
    }
    
    public void makeMove(int n) {
        
        rnd = new Random();
        int x = rnd.nextInt(n), y = rnd.nextInt(n);
        while(!game.isLegal(x, y)) {
            
            x = rnd.nextInt(n);
            y = rnd.nextInt(n);
            
        }
        
        makeMove(x, y);
        
    }
    
    public int getChoice() {
        
        return pieceChoice;
        
    }
    
}
