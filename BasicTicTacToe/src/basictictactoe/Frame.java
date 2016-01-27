/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictictactoe;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 *
 * @author robert
 */
public class Frame extends JFrame {
    
    private JButton[][] jbButtons;
    private JMenuBar jmb;
    private JMenu jmFile;
    private JMenuItem jmiReset;
    private JPanel panel;
    private Gameplay game;
    private int n;
    
    public Frame() {
        
        super("TicTacToe");
        n = 3;
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createPanels();
        createMenuBar();
        createButtons();
        
        game = new Gameplay(new HumanPlayer(Piece.CROSS), this);
        
        setSize(300, 300);
        setVisible(true);
        
    }
    
    public Frame(int n) {
        
        super("TicTacToe");
        this.n = n;
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createPanels();
        createMenuBar();
        createButtons();
        
        game = new Gameplay(n, new HumanPlayer(Piece.CROSS), this);
        
        setSize(600, 600);
        setVisible(true);
        
    }
    
    private void createButtons() {
        
        jbButtons = new JButton[n][n];
        
        Handler h = new Handler();
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j){
                
                jbButtons[i][j] = new JButton();
                jbButtons[i][j].setBackground(Color.WHITE);
                panel.add(jbButtons[i][j]);
                jbButtons[i][j].addActionListener(h);
            }

    }
    
    private void createPanels() {
        
        panel = new JPanel();

        GridLayout gridLayout = new GridLayout(n, n);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        panel.setLayout(gridLayout);
        panel.setBackground(Color.BLACK);
        getContentPane().add(panel);
        
    }
    
    private void createMenuBar() {
        
        jmb = new JMenuBar();
        
        jmFile = new JMenu("File");
        
        jmiReset = new JMenuItem("Reset");
        jmiReset.addActionListener(new Handler());

        jmFile.add(jmiReset);
        jmb.add(jmFile);
        getContentPane().add(jmb, BorderLayout.NORTH);

    }
    
    public void tie() {
        
        JOptionPane.showMessageDialog(this, "This is a tie!", "Wut?", JOptionPane.NO_OPTION);
        
    }
    
    public void winner(String win) {
        
        JOptionPane.showMessageDialog(this, "The winner is " + win + "!", "Woah!", JOptionPane.NO_OPTION);        
        
    }
    
    public void reset() {
        
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j) {
                
                jbButtons[i][j].setText("");
                jbButtons[i][j].setBackground(Color.WHITE);
                
            }
        
    }
    
    public void recordMove(int i, int j, int piece) {
        
        if(piece == Piece.CIRCLE) {
            jbButtons[i][j].setText("O");
            jbButtons[i][j].setBackground(Color.ORANGE);
        }
        else {
            
            jbButtons[i][j].setText("X");
            jbButtons[i][j].setBackground(Color.RED);
        }
        
    }
    
    private class Handler implements ActionListener {
    
        public void actionPerformed(ActionEvent e) {
        
            if(e.getSource() == jmiReset) {
                
                reset();
                game = new Gameplay(new HumanPlayer(Piece.CROSS), Frame.this);
                
            }
            else {
            
                for(int i = 0; i < n; ++i)
                    for(int j = 0; j < n; ++j)
                        if(e.getSource() == jbButtons[i][j])
                            game.play(i, j);
                                        //System.out.println("NOOOOOOOOOO");
 
            }
        
        }
        
    }
}
