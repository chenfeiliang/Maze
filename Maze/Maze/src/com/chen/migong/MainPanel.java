package com.chen.migong;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * ÃÔ¹¬Ö÷½çÃæ
 */
public class MainPanel extends JPanel{
      private ImageIcon mainImage = null;

      public MainPanel(){
    	  mainImage = new ImageIcon("./img/background.jpg");	  
      }
      
      public void paintComponent(Graphics g){
    	  g.drawImage(this.mainImage.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
      }
}
