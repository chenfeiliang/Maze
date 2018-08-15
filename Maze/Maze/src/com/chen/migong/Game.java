package com.chen.migong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Game extends JFrame implements KeyListener ,ActionListener,MouseListener
{
         //组件声明
	     private JPanel mainPanel = null;
	     private Player player = null;
	     private int map[][] = Map.getMap(0);
	     private Position position [][] = new Position[20][16];
	     private Dark dark [][] = new Dark[20][16];	 
	     private Note note = null;
	     private int treasureNum = Map.getTreasureNum(map);
	     private JButton driveButton = new JButton();
	     private JButton darkButton = new JButton();
	     private Font tfont = new Font("宋体",1,30);
	     private JLabel treasureNumLabel = new JLabel();
	     
	     private boolean isDriveDark = false;
	     private boolean isLookMap = true;
	     
	     //时间
	 	 private JLabel time;// 显示 时间
	 	 private Timer timer;
	 	 private int hour = 0 ,mnt = 0, scd = 0;// 分、秒
	     private int lookTime= 5;
	     
	 	/**
	 	 * 添加time监听 对时间 进行运算
	 	 *
	 	 */
	 	 public void initTime()
	 	 {
	 		 ActionListener keepTime = new ActionListener()
	 		 {	
				public void actionPerformed(ActionEvent e)
				{
					String str = "" ;
					
					scd++;
					
					if(scd>=60)
					{
						mnt++;
						scd=0;
					}
					
					if(mnt>=60)
					{
						hour++;
						mnt=0;
					}
					
				    if(hour<10)
				    {
				    	str = "0" + hour; 
				    }
				    else
				    {
				    	str = "" + hour;
				    }
				    
				    if(mnt<10)
				    {
				    	str=str+":"+"0"+mnt;
				    }
				    else
				    {
				    	str=str+":"+mnt;
				    }
				    
				    if(scd<10)
				    {
				    	str=str+":"+"0"+scd;
				    }
				    else
				    {
				    	str=str+":"+scd;		    	
				    }
					
				    time.setText(str);
			    	 //看地图
				    if(isLookMap)
				    {
					    JOptionPane.showMessageDialog(null, "你将有"+String.valueOf(lookTime)+"秒钟查看地图\n"+"请抓紧时间");	
					    isLookMap=false;
				    }
			    	lookMap(); 
				    gameOver();
				}
	 		 };
	 		 this.timer = new Timer(1000,keepTime);
	 		 this.time = new JLabel("00:00:00");
	 		 this.time.setForeground(Color.RED);
	 		 this.time.setFont(new Font("宋体",1,25));
	 		 this.time.setBounds(160, 410, 200, 20);
	 		 note.add(time);
	         timer.start();
	 	 }
	 	 
	 	 public void showTreasureNum()
	 	 {
	    	 this.treasureNumLabel.setText(String.valueOf(this.treasureNum));
	 	 }
	 	 
	 	 public void lookMap()
	 	 {
	 		 if(time.getText().equals("00:00:0"+String.valueOf(lookTime)))
	 		 {		
	 			    this.createPlayer();
		 			this.recoverDark();
	 		 }
	 	 }
	     public void initAll() {
	    	 //清空布局
	    	 Container con = this.getContentPane();
	    	 con.setLayout(null);
	    	 
	    	 //主面板
	    	 this.mainPanel = new MainPanel();
	    	 this.mainPanel.setBounds(0, 0,1000, 800);
	    	 mainPanel.setLayout(null);
	    	 con.add(this.mainPanel); 
	    	 
	    	 //说明面板
	    	 this.note = new Note();
	    	 this.note.setBounds(1000, 0, 300, 1000);
	    	 note.setLayout(null);
	    	 con.add(this.note);
	    	 
	    	 //显示宝物数量
	    	 this.treasureNumLabel.setBounds(170, 225, 20, 30);
	    	 this.treasureNumLabel.setFont(tfont);
	    	 this.treasureNumLabel.setForeground(Color.red);
	    	 showTreasureNum();
	    	 note.add(treasureNumLabel);
	    	 
	    	 //显示时间
	    	 initTime();
	    	 
	    	 //驱除迷雾按钮
	    	this.driveButton.setBounds(80, 500, 150, 40);	
	        this.driveButton.setContentAreaFilled(false);//背景透明
	    	this.driveButton.setBorderPainted(false);//去边界
	        this.driveButton.setIcon(new ImageIcon("./img/driveButton.png"));
            this.driveButton.addActionListener(this);
            this.driveButton.setFocusable(false);
	    	note.add(driveButton);

	    	 //恢复迷雾按钮
	    	this.darkButton.setBounds(80, 570, 150, 40);	
	        this.darkButton.setContentAreaFilled(false);//背景透明
	    	this.darkButton.setBorderPainted(false);//去边界
	        this.darkButton.setIcon(new ImageIcon("./img/darkButton.png"));
            this.darkButton.addActionListener(this);
            this.darkButton.setFocusable(false);
	    	note.add(darkButton);
	    	
	    	//生成迷雾
		    	createDark();    		
	    	
		   //生成宝物
 		        this.createTreasure();
 		        
	       //生成迷宫墙
		         createWall();
		          
			this.driveDark();
	 		 
	     }
	     
	//寻宝者随机定位
	public void createPlayer()
	{
   	 player = new Player ();
   	 boolean pflag = true;
   	 while(pflag)
   	 {
	    	int px =  (int) (Math.random() * 20); 
	    	int py =  (int) (Math.random() * 16); 
	    	System.out.println(px);
	    	System.out.println(py);
		    if(map[px][py]==0)
		    {
		    	 System.out.println(px);
		         System.out.println(py);
		    	 player.setXx(px);
		    	 player.setYy(py);
	             player.setBounds(player.getXx()*50,player.getYy()*50,50,50);
		    	 mainPanel.add(this.player);
		    	 this.addKeyListener(this);
		    	 pflag = false;
		    	 try
				 {
		    		 this.PlayerMove(px, py);
				 } 
		    	 catch (Exception e)
				 {
					
				 }
		    
	    	 }  		 
   	 }		
	}
	
	//生成宝物
	public void createTreasure()
	{
	   	 //生成宝物
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
	                if(map[i][j]==3)
	                {
	               	 position[i][j] = new Treasure();
	               	 position[i][j].setXx(i);
	               	 position[i][j].setYy(j);
	               	 position[i][j].setSize(50,50);
	               	 mainPanel.add(position[i][j]);
	               	 this.setPosition(position[i][j]);
	                }
		    	 }
	   	 }		
	}
	
	//生成迷宫墙
	public void createWall()
	{
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
	                if(map[i][j]==2)
	                {
	               	 position[i][j] = new Wall();
	               	 position[i][j].setXx(i);
	               	 position[i][j].setYy(j);
	               	 position[i][j].setSize(50,50);
	               	 mainPanel.add(position[i][j]);
	               	 this.setPosition(position[i][j]);	
	                }
		    	 }
	   	 }		
	}
	
	//生成迷雾   
	public void createDark()
	{
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
		    		     dark[i][j] = new Dark();
		    		     dark[i][j].setXx(i);
		    		     dark[i][j].setYy(j);
		    		     dark[i][j].setSize(50,50);
	               	     mainPanel.add(dark[i][j]);
	               	     this.setPosition(dark[i][j]);	
		    	 }
	   	 }
	}
	
	//恢复迷雾
	public void recoverDark()
	{
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
		    		     dark[i][j].setVisible(true);
		    	 }
	   	 }		
	   	 this.isDriveDark= false;
	   	 this.PlayerMove(1,1);
	}	
	
	//驱除迷雾
	public void driveDark()
	{
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
		    		     dark[i][j].setVisible(false);
		    	 }
	   	 }		
	     this.isDriveDark = true;
	}
	
	//找到宝物
	public void findTreasure()
	{
		 if(this.map[player.getXx()][player.getYy()]==3)
		 {
				position[player.getXx()][player.getYy()].setVisible(false);
				this.map[player.getXx()][player.getYy()]=0;
				this.treasureNum--;
				JOptionPane.showMessageDialog(this, "找到一个宝物");
				showTreasureNum();
				gameOver();
		 }  
	}
	
	public void playAgain()
	{
		hour=0;
		mnt=0;
		scd=0;
		System.out.println(Map.getTreasureNum(map));
		this.treasureNum = Map.getTreasureNum(map);
		initAll();		
	}
	
	//游戏结束
	public void gameOver()
	{
		if(this.treasureNum==0)
		{	
			JOptionPane.showMessageDialog(this, "恭喜你成功找出了所有的宝物\n本局用时 : "+time.getText());
/*			int flag = JOptionPane.showConfirmDialog(this, "再来一局吧!", "消息", JOptionPane.YES_NO_OPTION);
			if (flag == JOptionPane.YES_OPTION)
			{
				this.playAgain();
			} 
			else
			{
				System.exit(0);
			}*/
			System.exit(0);
		}
		if(time.getText().equals("00:10:00"))
		{
			JOptionPane.showMessageDialog(this, "时间已到");		
			System.exit(0);
		}
	}
	     
    public void PlayerMove(int x, int y)
	    {	    	 	 
	    	player.setLocation(50*player.getXx(),50*player.getYy());
	    	
	    	if(!this.isDriveDark)
	    	{
		    	//前一步恢复迷雾
		    	dark[x][y].setVisible(true);		    	
			    if(x-1>=0)
			    {
				    dark[x-1][y].setVisible(true);	    		
			    }
			    
			    if(x+1<20)
			    {
			    	dark[x+1][y].setVisible(true);    		
			    }	    		
		    	
			    if(y+1<16)
			    {
			    	dark[x][y+1].setVisible(true);    		
			    }
			    
			    if(y-1>=0)
			    {
			    	dark[x][y-1].setVisible(true);    		
			    }
			    
			    //下一步驱散迷雾	    
		    	dark[player.getXx()][player.getYy()].setVisible(false);	
		    	
			    if(player.getXx()-1>=0)
			    {
				    dark[player.getXx()-1][player.getYy()].setVisible(false);	    		
			    }
			    
			    if(player.getXx()+1<20)
			    {
			    	dark[player.getXx()+1][player.getYy()].setVisible(false);    		
			    }	    		
		    	
			    if(player.getYy()+1<16)
			    {
			    	dark[player.getXx()][player.getYy()+1].setVisible(false);    		
			    }
			    
			    if(player.getYy()-1>=0)
			    {
			    	dark[player.getXx()][player.getYy()-1].setVisible(false);    		
			    }	  	    		
	    	}
	     }
	     
	     public void setPosition(Position p)
	     {
	    	p.setLocation(50*p.getXx(),50*p.getYy());
	     }	   
	     
	     
	     
	     
	     public Game() {
	    	 initAll();		    	 
	    	 this.setVisible(true);
	    	 this.setSize(1317,847);    	 
		     this.setTitle("迷宫游戏");	
		     this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		     
		     
			 //把程序放在屏幕中间
			 Dimension  frameSize = this.getSize();
			 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   
			 int center_X = screenSize.width/2;
			 int center_Y = screenSize.height/2;	    
			 this.setLocation(center_X - frameSize.width/2,center_Y-frameSize.height/2 );		     
	     }

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==this.driveButton)
			{
				this.driveDark();
			}
			if(e.getSource()==this.darkButton)
			{
				this.recoverDark();
			}
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			if(!(hour==0&&mnt==0&&scd<lookTime))
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_W:
						if (this.player.getYy() >0)
						{
					        if(this.map[player.getXx()][player.getYy()-1]==0||this.map[player.getXx()][player.getYy()-1]==3)
					        {
								int beforeX = player.getXx();
								int beforeY = player.getYy();
								this.player.moveYY(-1);
								this.PlayerMove(beforeX,beforeY);
								findTreasure();
					        }
					       
						}
						break;					
						
					case KeyEvent.VK_S:
						if (this.player.getYy()<15)
						{
					        if(this.map[player.getXx()][player.getYy()+1]==0||this.map[player.getXx()][player.getYy()+1]==3)
					        {
								int beforeX = player.getXx();
								int beforeY = player.getYy();
								this.player.moveYY(1);
								this.PlayerMove(beforeX,beforeY);	
								findTreasure();
					        }
						}
						break;	
						
					case KeyEvent.VK_A:
						if (this.player.getXx()>0)
						{
					        if(this.map[player.getXx()-1][player.getYy()]==0||this.map[player.getXx()-1][player.getYy()]==3)
					        {
								int beforeX = player.getXx();
								int beforeY = player.getYy();
								this.player.moveXX(-1);
								this.PlayerMove(beforeX,beforeY);	
								findTreasure();
					        }
						}
						break;						
					case KeyEvent.VK_D:
						if (this.player.getXx()<19)
						{
					        if(this.map[player.getXx()+1][player.getYy()]==0||this.map[player.getXx()+1][player.getYy()]==3)
					        {
								int beforeX = player.getXx();
								int beforeY = player.getYy();
								this.player.moveXX(1);
								this.PlayerMove(beforeX,beforeY);	
								findTreasure();
					        }
						}
						break;						
				}				
			}	
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub
			
		}
}
