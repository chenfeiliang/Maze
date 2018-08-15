package com.chen.migong;

import javax.swing.JLabel;

public class Position extends JLabel
{
	protected int xx = 0 ;
	protected int yy = 0 ;
	protected int flag = 0 ;
    
    public int getXx()
	{
		return xx;
	}
	public void setXx(int xx)
	{
		this.xx = xx;
	}
	public int getYy()
	{
		return yy;
	}
	public void setYy(int yy)
	{
		this.yy = yy;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	
}
