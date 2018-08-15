package com.chen.migong;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Note extends JLabel
{ 
	  private ImageIcon noteImage = null;
	  
      public Note()
      {
    	  noteImage = new ImageIcon("./img/note.jpg");
      }
      
      public void paintComponent(Graphics g){
    	  g.drawImage(this.noteImage.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
      }
}
