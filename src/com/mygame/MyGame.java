package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Saksham
 */
public class MyGame extends JFrame implements ActionListener {
    
        JLabel heading, clockLabel;
        Font font = new Font("", Font.BOLD, 40);
        JPanel mainPanel;
        
        JButton []btns = new JButton[9];
        
        
        
           //game instance variable
            int gameChanses[] = {2,2,2,2,2,2,2,2,2};
            int activePlayer = 0;
            
            
            int wps[][] = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
            };
            
            int winner =2;
            
            boolean gameOver = false;
    
    MyGame(){
        System.out.println("Creating instance of game");
        setTitle("TicTacToe game by Saksham Gupta..");
        setSize(850,850);
        ImageIcon icon = new  ImageIcon("src/img/icon.png");
        setIconImage(icon.getImage());
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    
    private void createGUI(){
        
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        
        this.setLayout(new BorderLayout());
        
        //north heading
        
        heading = new JLabel("TicTacToe");
        heading.setIcon(new ImageIcon("src/img/icon.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        
    
        
        
        this.add(heading, BorderLayout.NORTH);
        
        
        //creating object of JLabel for clock
        
        clockLabel = new JLabel("Clock");
        clockLabel.setForeground(Color.white);
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel , BorderLayout.SOUTH);
        
        Thread t = new Thread(){
        
            public void run(){
                
                try{
                    while(true){
                        
                        String datetime = new Date().toLocaleString();
                        
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                     
                }
               
                    catch (Exception e) {


                              }
              
    }
};
   
           t.start();
                   
           
           // Panel Section
           
           
           mainPanel = new JPanel();
           
           mainPanel.setLayout(new GridLayout(3, 3));
           
           
           for (int i = 1; i <= 9; i++) {
            
               JButton btn = new JButton();
              
               btn.setBackground(Color.decode("#90caf9"));
               btn.setFont(font);
               mainPanel.add(btn);
               btns[i-1] = btn;
               btn.addActionListener(this);
               btn.setName((i-1)+"");
               
               
        }
            
           this.add(mainPanel , BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();
        
        String str = currentButton.getName();
        System.out.println(str);

            int name = Integer.parseInt(str.trim());
            
            
            if(gameOver== true){
                JOptionPane.showMessageDialog(this, "Game Over...");
                return;
            }
            
            if(gameChanses[name]==2)
            {
                if(activePlayer==1)
                {
                    currentButton.setIcon(new ImageIcon("src/img/1.png"));
                    gameChanses[name] = activePlayer;
                    activePlayer = 0;
                }
                
                else
                {
                    currentButton.setIcon(new ImageIcon("src/img/0.png"));
                    gameChanses[name] = activePlayer;
                    activePlayer = 1;
                }
                
                
                //find the winner
                
                for(int temp[] : wps){
                    
                    if((gameChanses[temp[0]] == gameChanses[temp[1]]) && (gameChanses[temp[1]] == gameChanses[temp[2]]) && gameChanses[temp[2]] !=2 ){
                        winner = gameChanses[temp[0]];
                        gameOver = true;
                        
                        if(winner == 0 ) JOptionPane.showMessageDialog(this, "Player O has WON the game..");
                        else  JOptionPane.showMessageDialog(this, "Player X has WON the game..");
                        int i = JOptionPane.showConfirmDialog(this, "Click Ok to restart the game");
                        if(i==0){
                            this.setVisible(false);
                            new MyGame();
                        }
                        
                        else if(i==1){
                             JOptionPane.showMessageDialog(this, "TicTacToe by Saksham Gupta... "
                                     + "Thanks for Playing ");
                            System.exit(0);
                        }
                        else {}
                        break;
                    }
                }
                
                // draw logic
                
                int c = 0;
                for(int x : gameChanses){
                    if(x==2){
                        c++;
                        break;
                    }
                }
                if (c==0 && gameOver == false){
                    JOptionPane.showMessageDialog(null, "It's Draw between both the Players");
                   int i = JOptionPane.showConfirmDialog(this ,  "Do you want to restart the game?");
                    if(i==0){
                        this.setVisible(false);
                        new MyGame();
                    }
                    
                    if(i==1){
                        System.exit(0);
                    }
                    else{}
                    
                    gameOver = true;
                    
                }
            }
            
            else {
                JOptionPane.showMessageDialog(this, "Position already occupied");
            }
            
            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
