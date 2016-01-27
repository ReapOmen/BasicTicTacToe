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
public class Piece {
    
    public static final int CROSS = 1, EMPTY = 0, CIRCLE = -1;
    private int value;
    
    public Piece(int piece) throws IllegalArgumentException {
        
        if(piece < -1 || piece > 1)
            throw new IllegalArgumentException();
        
        value = piece;
        
    }
    
    public int getValue() {
        
        return value;
        
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(o instanceof Piece)
            if(((Piece)o).value == this.value && this.value != Piece.EMPTY)
                return true;
        
        return false;
    }
    
    @Override
    public String toString() {
        
        if(value == CROSS)
            return "Cross";
        else
            if(value == CIRCLE)
                return "Circle";
        
        return "Empty";
        
    }
    
}
