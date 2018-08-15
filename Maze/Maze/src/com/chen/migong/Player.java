package com.chen.migong;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends Position
{
/*	private int startX;
    private int startY;*/  
	public Player()
	{
		xx = 0;
		yy = 0;
		flag = 1;
		this.setIcon(new ImageIcon("./img/qiu.png"));
	}	
/*    public int getStartX()
	{
		return startX;
	}
	public void setStartX(int startX)
	{
		this.startX = startX;
	}
	public int getStartY()
	{
		return startY;
	}*/
/*	public void setStartY(int startY)
	{
		this.startY = startY;
	}*/

	public void moveXX(int xx)
	{
		this.xx +=xx;
	}
	
	public void moveYY(int yy)
	{
		this.yy += yy;
	}
    
}
