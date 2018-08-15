package com.chen.migong;

public class Map
{  
	private static int map[][][] = 
    {
      {
	      {2,0,0,0,2,0,2,0,0,0,2,2,0,0,0,0},	
	      {0,0,0,0,0,0,2,0,2,0,0,0,2,0,2,3},
	      {2,0,0,0,0,0,0,2,0,0,2,0,0,0,0,2},	
	      {0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0},	
	      {0,2,0,2,0,2,2,2,2,0,0,2,0,0,0,0},	
	      {2,0,0,0,0,0,0,2,0,2,0,2,2,0,0,2},
	      {2,0,2,2,2,0,2,0,0,0,0,0,0,2,2,2},	
	      {0,0,0,0,2,0,0,0,2,0,2,2,0,0,0,0},
	      {2,2,0,2,3,2,0,2,0,0,0,0,2,0,0,0},	
	      {0,0,0,0,0,2,2,0,0,0,2,0,0,0,2,0},
	      {2,0,0,2,2,2,0,0,0,2,0,0,0,0,0,2},	
	      {0,0,0,2,0,2,2,0,2,0,0,2,0,2,0,2},      
	      {0,0,2,0,0,0,0,0,0,2,0,0,2,0,0,0},	
	      {2,0,2,0,0,2,0,0,2,2,0,2,0,0,0,2},
	      {2,2,0,0,0,2,0,0,0,0,2,2,0,0,0,0},	
	      {0,2,2,0,2,2,0,0,0,2,0,0,2,2,0,0},   
	      {0,0,0,0,2,3,2,2,0,0,0,0,0,2,0,0},	
	      {0,0,0,2,0,0,0,0,0,0,2,0,0,0,0,2},
	      {0,0,0,2,0,0,0,0,2,0,2,0,0,2,0,0},	
	      {0,0,0,2,0,2,2,0,0,0,0,0,0,0,0,2}       
     }
    };
    
    public static int[][] getMap(int i)
	{
		return map[i];
	}    
    
    //获取其中宝物数量
    public static int getTreasureNum(int map[][])
    {
	     int num = 0 ; 
	    
	   	 for(int i = 0 ; i<map.length;i++)
	   	 {
		    	 for(int j=0; j< map[0].length;j++)
		    	 {	    		 
	                if(map[i][j]==3)
	                {
	                	num++;
	                }
	
		    	 }
	   	 }
	   	 return num;
    }
}
