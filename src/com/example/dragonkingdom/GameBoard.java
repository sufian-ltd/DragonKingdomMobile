package com.example.dragonkingdom;

import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.BoringLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

public class GameBoard extends Activity implements OnClickListener{

	
	Button ib[][];
	String str[][]={
			{"s1H","c1M","k1","d1","c1B","s1L"},
			{"v1","v1","v1","v1","v1","v1"},/**board specification   */
			{" "," "," "," "," "," "},
			{" "," "," "," "," "," "},
			{" "," "," "," "," "," "},
			{" "," "," "," "," "," "},
			{"v2","v2","v2","v2","v2","v2"},
			{"s2L","c2B","d2","k2","c2M","s2H"}
		};
	
	int turn =1;  //player turn
	int row=8,col=6;
	int numberOfPlaying=0;
	
	Vector<Integer>vectorClickPosition; // save position of click over a troops
	
	String firstPlayerTroops="s1Hs1Lc1Bc1Md1k1v1v1f";
	String secondPlayerTroops="s2Hs2Lc2Bc2Md2k2v2v2f";
	int generatingPointsColor[]= {Color.BLUE,Color.DKGRAY,Color.MAGENTA,Color.RED,Color.TRANSPARENT};
	
	boolean firstClick=false;
	
	/*String allTroops[]= {"s1","c1"," ","d1","d2"," ",
		"c2"," ","s2","v1","v2"};*/ /** Randomly select point from this */
	String pointsForFirstPlayer[]= {"s1H","s1L","c1B","c1M","d1","v1"};
	String pointsForSecondPlayer[]= {"s2H","s2L","c2B","c2M","d2","v2"};
			
	Handler myHandler;
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);
        getIntent();
        vectorClickPosition=new Vector<Integer>();
        ib=new Button[row][col];
        
        initializeImageButton();
        //troopsNameINitialize();
        generatePoints();
        //changeColorForEveryTurn();
	}
	public void initializeImageButton()
	{
		ib[0][0]=(Button) findViewById(R.id.btn1);
		ib[0][1]=(Button) findViewById(R.id.btn2);
		ib[0][2]=(Button) findViewById(R.id.btn3);
		ib[0][3]=(Button) findViewById(R.id.btn4);
		ib[0][4]=(Button) findViewById(R.id.btn5);
		ib[0][5]=(Button) findViewById(R.id.btn6);
		ib[1][0]=(Button) findViewById(R.id.btn7);
		ib[1][1]=(Button) findViewById(R.id.btn8);
		ib[1][2]=(Button) findViewById(R.id.btn9);
		ib[1][3]=(Button) findViewById(R.id.btn10);
		ib[1][4]=(Button) findViewById(R.id.btn11);
		ib[1][5]=(Button) findViewById(R.id.btn12);
		ib[2][0]=(Button) findViewById(R.id.btn13);
		ib[2][1]=(Button) findViewById(R.id.btn14);
		ib[2][2]=(Button) findViewById(R.id.btn15);
		ib[2][3]=(Button) findViewById(R.id.btn16);
		ib[2][4]=(Button) findViewById(R.id.btn17);
		ib[2][5]=(Button) findViewById(R.id.btn18);
		ib[3][0]=(Button) findViewById(R.id.btn19);
		ib[3][1]=(Button) findViewById(R.id.btn20);
		ib[3][2]=(Button) findViewById(R.id.btn21);
		ib[3][3]=(Button) findViewById(R.id.btn22);
		ib[3][4]=(Button) findViewById(R.id.btn23);
		ib[3][5]=(Button) findViewById(R.id.btn24);
		ib[4][0]=(Button) findViewById(R.id.btn25);
		ib[4][1]=(Button) findViewById(R.id.btn26);
		ib[4][2]=(Button) findViewById(R.id.btn27);
		ib[4][3]=(Button) findViewById(R.id.btn28);
		ib[4][4]=(Button) findViewById(R.id.btn29);
		ib[4][5]=(Button) findViewById(R.id.btn30);
		ib[5][0]=(Button) findViewById(R.id.btn31);
		ib[5][1]=(Button) findViewById(R.id.btn32);
		ib[5][2]=(Button) findViewById(R.id.btn33);
		ib[5][3]=(Button) findViewById(R.id.btn34);
		ib[5][4]=(Button) findViewById(R.id.btn35);
		ib[5][5]=(Button) findViewById(R.id.btn36);
		ib[6][0]=(Button) findViewById(R.id.btn37);
		ib[6][1]=(Button) findViewById(R.id.btn38);
		ib[6][2]=(Button) findViewById(R.id.btn39);
		ib[6][3]=(Button) findViewById(R.id.btn40);
		ib[6][4]=(Button) findViewById(R.id.btn41);
		ib[6][5]=(Button) findViewById(R.id.btn42);
		ib[7][0]=(Button) findViewById(R.id.btn43);
		ib[7][1]=(Button) findViewById(R.id.btn44);
		ib[7][2]=(Button) findViewById(R.id.btn45);
		ib[7][3]=(Button) findViewById(R.id.btn46);
		ib[7][4]=(Button) findViewById(R.id.btn47);
		ib[7][5]=(Button) findViewById(R.id.btn48);
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				ib[i][j].setOnClickListener(this);		
			}
		}
	}
	
	/*public void troopsNameINitialize()
	{
		/**
         * initialize troops to name
         * into array
         **
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				ib[i][j].setText(str[i][j]);
			}		
		}
	}*/
	
 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(turn==1)
				{
					if(firstClick && ib[i][j]==v)
					{
						vectorClickPosition.add(i);
						vectorClickPosition.add(j);
						moveSound();
					}
					else if(firstPlayerTroops.contains(str[i][j]) && ib[i][j]==v)
					{
						firstClick=true;
						moveSound();
						if(str[i][j].equals("v1") || str[i][j].equals("v1f"))
						{
							playing(i, j, 0, 0);
							return;
						}
						vectorClickPosition.add(i);
						vectorClickPosition.add(j);
					}
				}
				else if(turn==2)
				{
					if(firstClick && ib[i][j]==v)
					{
						moveSound();
						vectorClickPosition.add(i);
						vectorClickPosition.add(j);
						
					}
					else if(secondPlayerTroops.contains(str[i][j]) && ib[i][j]==v)
					{
						firstClick=true;
						moveSound();
						if(str[i][j].equals("v2") || str[i][j].equals("v2f"))
						{
							playing(i, j, 0, 0);
							return;
						}
						vectorClickPosition.add(i);
						vectorClickPosition.add(j);
					}
				}
			}
		}
		if(vectorClickPosition.size()==4)
		{
			playing(vectorClickPosition.get(0),vectorClickPosition.get(1),
					vectorClickPosition.get(2), vectorClickPosition.get(3));
		}
	}
	
	public void playing(int fRow,int fCol,int sRow,int sCol)
	{
		/*if(!isValidMovingPositions(fRow, fCol, sRow, sCol, str[fRow][fCol]))
		{
			Toast.makeText(getApplicationContext(), "false",Toast.LENGTH_SHORT).show();
			return;
		}*/
		vectorClickPosition.removeAllElements();
		firstClick=false;
		String temp="";
		if(turn==1)
			temp=firstPlayerTroops;
		else if(turn==2)
			temp=secondPlayerTroops;
		if(str[fRow][fCol].equalsIgnoreCase("d1") || str[fRow][fCol].equalsIgnoreCase("d2"))
		{
			if(!dragonMoving(fRow, fCol, sRow, sCol, temp))
			{
				//Toast.makeText(getApplicationContext(), "invalid",Toast.LENGTH_SHORT).show();
				return;
			}
		}
		else if(str[fRow][fCol].equalsIgnoreCase("s1H") ||
				str[fRow][fCol].equalsIgnoreCase("s2H") ||
				str[fRow][fCol].equalsIgnoreCase("s2L") ||
				str[fRow][fCol].equalsIgnoreCase("s1L"))
		{
			if(!shivMoving(fRow, fCol, sRow, sCol, temp))
			{
				//Toast.makeText(getApplicationContext(), "invalid",Toast.LENGTH_SHORT).show();
				return;
			}
		}
		else if(str[fRow][fCol].equalsIgnoreCase("c1M") || 
				str[fRow][fCol].equalsIgnoreCase("c2M") || 
				str[fRow][fCol].equalsIgnoreCase("c1B") || 
				str[fRow][fCol].equalsIgnoreCase("c2B"))
		{
			if(!cannonMoving(fRow, fCol, sRow, sCol, temp))
			{
				//Toast.makeText(getApplicationContext(), "invalid",Toast.LENGTH_SHORT).show();
				return;
			}
		}
		else if(str[fRow][fCol].equalsIgnoreCase("v1") || str[fRow][fCol].equalsIgnoreCase("v2")
				|| str[fRow][fCol].equalsIgnoreCase("v1f") || str[fRow][fCol].equalsIgnoreCase("v2f"))
		{
			if(!vikingsPosition(fRow, fCol, sRow, sCol, temp))
			{
				//Toast.makeText(getApplicationContext(), "invalid",Toast.LENGTH_SHORT).show();
				return;
			}
		}
		else if(str[fRow][fCol].equalsIgnoreCase("k1") || str[fRow][fCol].equalsIgnoreCase("k2"))
		{
			if(!kingMoving(fRow, fCol, sRow, sCol, temp))
			{
				//Toast.makeText(getApplicationContext(), "invalid",Toast.LENGTH_SHORT).show();
				return;
			}
		}
		if(turn==1)
			turn=2;
		else if(turn==2)
			turn=1;
		numberOfPlaying=numberOfPlaying+1;
		if(numberOfPlaying%4==0)
		{
			generatePoints();
		}
		changeColorForEveryTurn();	
	}
	
	
	/****
	 * scanning positions
	 * 
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public boolean vikingsPosition(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		boolean validMoving=false;
		
		int prevPosition=fRow;
		if(temp.equalsIgnoreCase(firstPlayerTroops))
		{
			if(fRow==(row-1))
			{
				str[fRow][fCol]="v1f";
			}
			else if(fRow==0)
			{
				str[fRow][fCol]="v1";
			}
			
			if(str[fRow][fCol].equals("v1f"))
			{
				--fRow;
			}
			else 
			{
				++fRow;
			}
			validMoving=vikingsMoving(fRow,fCol,prevPosition,secondPlayerTroops);
		}
		else if(temp.equalsIgnoreCase(secondPlayerTroops))
		{
			if(fRow==0)
			{
				str[fRow][fCol]="v2f";
			}
			else if(fRow==(row-1))
			{
				str[fRow][fCol]="v2";
			}
			
			if(str[fRow][fCol].equals("v2f"))
			{
				++fRow;
			}
			else
			{
				--fRow;
			}
			validMoving=vikingsMoving(fRow,fCol,prevPosition,firstPlayerTroops);
		}
		return validMoving;
		
	}
	
	/****
	 * move forward only one square
	 * @param fRow
	 * @param fCol
	 * @param temp is against player troops
	 * @param prevPosition
	 */
	
	public boolean vikingsMoving(int fRow,int fCol,int prevPosition,String temp)
	{
		boolean validMoving=false;
		
		if(fCol>0 && temp.contains(str[fRow][fCol-1]))
		{
			congratulationMessage(fCol, fRow,fRow, fCol-1);
			movingTroops(prevPosition, fCol, fRow, fCol-1, temp);
			validMoving=true;
		}
		else if(fCol<(col-1) && temp.contains(str[fRow][fCol+1]))
		{
			congratulationMessage(fCol, fRow,fRow, fCol+1);
			movingTroops(prevPosition, fCol, fRow, fCol+1, temp);
			validMoving=true;
		}
		else if(fCol>0 && str[fRow][fCol-1].equals("*"))
		{
			starFoundWhilemovingTroops(prevPosition, fCol, fRow, fCol-1, temp);
			validMoving=true;
		}
		else if(fCol<(col-1) && str[fRow][fCol+1].equals("*"))
		{
			starFoundWhilemovingTroops(prevPosition, fCol, fRow, fCol+1, temp);
			validMoving=true;
		}
		else if(str[fRow][fCol].equals("*"))
		{
			starFoundWhilemovingTroops(prevPosition, fCol, fRow, fCol, temp);
			validMoving=true;
		}
		else if(str[fRow][fCol].equals(" "))
		{
			movingTroops(prevPosition, fCol, fRow, fCol, temp);
			validMoving=true;
		}
		
		return validMoving;
	}
	
	/****
	 * cannon moving only in diagonal both way
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public boolean cannonMoving(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		
		/***
		 * cannon moving from
		 * upper left to lower right
		 */
		int toatalMove=20,numberOfDestroy=2;
		if(str[fRow][fCol].equalsIgnoreCase("c1B") ||
				str[fRow][fCol].equalsIgnoreCase("c2B"))
		{
			toatalMove=4;
			numberOfDestroy=2;
		}
		boolean validMoving=false;
		
		if(Math.abs(fRow-sRow)!=Math.abs(fCol-sCol))
		{
			return false;
		}
		
		if(fRow<sRow && fCol<sCol)
		{
			for(int i=fRow+1,j=fCol+1;;i++,j++)
			{
				/***
				 * return when same player 
				 * troops found
				 */
				
				if(temp.contains(str[i][j])) 
				{
					if(i==fRow+1)
					{
						validMoving=false;
						break;
					}
					else
					{
						validMoving=true;
						break;
					}
				}
				
				else if(str[i][j]=="*")
				{
					starFoundWhilemovingTroops(i-1, j-1, i, j, temp);
					validMoving=true;
					break;
				}
				
				else if(str[i][j]==" ")
				{
					movingTroops(i-1, j-1, i, j, temp);
					validMoving=true;
				}
				/***
				 * found against troop
				 * to destroy and return
				 */
				else
				{
					
					congratulationMessage(i-1, j-1,i,j);	
					if(str[i-1][j-1].equalsIgnoreCase("c1M"))
					{
						//Toast.makeText(GameBoard.this, "work", Toast.LENGTH_LONG).show();
						magicForFirstPlayer(i-1, j-1, i, j, temp);
						return true;
					}
					else if(str[i-1][j-1].equalsIgnoreCase("c2M"))
					{
						magicForSecondPlayer(i-1, j-1, i, j, temp);
						return true;
					}
					else if(str[i-1][j-1].equalsIgnoreCase("c1B") ||
							str[i-1][j-1].equalsIgnoreCase("c2B"))
					{
						--numberOfDestroy;
						
						movingTroops(i-1, j-1, i, j, temp);
						validMoving=true;
						//Toast.makeText(GameBoard.this,numberOfDestroy+"", Toast.LENGTH_LONG).show();
						if(numberOfDestroy<=0)
							break;
						
					}
					else	
					{
						
						movingTroops(i-1, j-1, i, j, temp);
						validMoving=true;
						
						break;
					}
				}
				if(stopMoving(i, j, sRow, sCol, temp))
				{
					validMoving=true;
					break;
				}
				--toatalMove;
				if(toatalMove<=0)
					return true;
			}
		}
		/***
		 * cannon moving from
		 * lower right to upper left
		 */
		else if(fRow>sRow && fCol>sCol)
		{
			for(int i=fRow-1,j=fCol-1;;i--,j--)
			{
				/***
				 * return when same player 
				 * troops found
				 */
				
				if(temp.contains(str[i][j])) 
				{
					if(i==fRow-1)
					{
						validMoving=false;
						break;
					}
				}
				
				else if(str[i][j]=="*")
				{
					starFoundWhilemovingTroops(i+1, j+1, i, j, temp);
					validMoving=true;
					break;
				}
				
				else if(str[i][j]==" ")
				{
					movingTroops(i+1, j+1, i, j, temp);
					validMoving=true;
				}
				/***
				 * found against troop
				 * to destroy and return
				 */
				else
				{
					congratulationMessage(i+1, j+1,i, j);
					if(str[i+1][j+1].equalsIgnoreCase("c1M"))
					{
						magicForFirstPlayer(i+1, j+1, i, j, temp);
						return true;
					}
					else if(str[i+1][j+1].equalsIgnoreCase("c2M"))
					{
						magicForSecondPlayer(i+1, j+1, i, j, temp);
						return true;
					}
					else if(str[i+1][j+1].equalsIgnoreCase("c1B") ||
							str[i+1][j+1].equalsIgnoreCase("c2B"))
					{
						--numberOfDestroy;
						
						movingTroops(i+1, j+1, i, j, temp);
						validMoving=true;
						//Toast.makeText(GameBoard.this,numberOfDestroy+"", Toast.LENGTH_LONG).show();
						if(numberOfDestroy<=0)
							break;
					}
					else
					{
						movingTroops(i+1, j+1, i, j, temp);
						validMoving=true;
						break;
					}
				}
				if(stopMoving(i, j, sRow, sCol, temp))
				{
					validMoving=true;
					break;
				}
				--toatalMove;
				if(toatalMove<=0)
					return true;
			}
		}
		/***
		 * cannon moving from
		 * lower left to upper right
		 */
		else if(fRow>sRow && fCol<sCol)
		{
			for(int i=fRow-1,j=fCol+1;;i--,j++)
			{
				/***
				 * return when same player 
				 * troops found
				 */
				ib[i][j].setPressed(true);
				if(temp.contains(str[i][j])) 
				{
					if(i==fRow-1)
					{
						validMoving=false;
						break;
					}
				}
				
				else if(str[i][j]=="*")
				{
					starFoundWhilemovingTroops(i+1, j-1, i, j, temp);
					validMoving=true;
					break;
				}
				
				else if(str[i][j]==" ")
				{
					movingTroops(i+1, j-1, i, j, temp);
					validMoving=true;
				}
				/***
				 * found against troop
				 * to destroy and return
				 */
				else
				{
					congratulationMessage(i+1, j-1,i, j);
					if(str[i+1][j-1].equalsIgnoreCase("c1M"))
					{
						magicForFirstPlayer(i+1, j-1, i, j, temp);
						return true;
					}
					else if(str[i+1][j-1].equalsIgnoreCase("c2M"))
					{
						magicForSecondPlayer(i+1, j-1, i, j, temp);
						return true;
					}
					else if(str[i+1][j-1].equalsIgnoreCase("c1B") ||
							str[i+1][j-1].equalsIgnoreCase("c2B"))
					{
						--numberOfDestroy;
						
						movingTroops(i+1, j-1, i, j, temp);
						validMoving=true;
						//Toast.makeText(GameBoard.this,numberOfDestroy+"", Toast.LENGTH_LONG).show();
						if(numberOfDestroy<=0)
							break;
					}
					else
					{
						movingTroops(i+1, j-1, i, j, temp);
						validMoving=true;
						break;
					}
				}
				if(stopMoving(i, j, sRow, sCol, temp))
				{
					validMoving=true;
					break;
				}
				--toatalMove;
				if(toatalMove<=0)
					return true;
			}
		}
		/***
		 * cannon moving from
		 * upper right to lower left
		 */
		else if(fRow<sRow && fCol>sCol)
		{
			for(int i=fRow+1,j=fCol-1;;i++,j--)
			{
				/***
				 * return when same player 
				 * troops found
				 */
				
				ib[i][j].setPressed(true);
				if(temp.contains(str[i][j])) 
				{
					if(i==fRow+1)
					{
						validMoving=false;
						break;
					}
				}
				
				else if(str[i][j]=="*")
				{
					starFoundWhilemovingTroops(i-1, j+1, i, j, temp);
					validMoving=true;
					break;
				}
				
				else if(str[i][j]==" ")
				{
					movingTroops(i-1, j+1, i, j, temp);
					validMoving=true;
				}
				/***
				 * found against troop
				 * to destroy and return
				 */
				else
				{
					congratulationMessage(i-1, j+1,i, j);
					if(str[i-1][j+1].equalsIgnoreCase("c1M"))
					{
						magicForFirstPlayer(i-1, j+1, i, j, temp);
						return true;
					}
					else if(str[i-1][j+1].equalsIgnoreCase("c2M"))
					{
						magicForSecondPlayer(i-1, j+1, i, j, temp);
						return true;
					}
					else if(str[i-1][j+1].equalsIgnoreCase("c1B") ||
							str[i-1][j+1].equalsIgnoreCase("c2B"))
					{
						--numberOfDestroy;
						movingTroops(i-1, j+1, i, j, temp);
						validMoving=true;
						//Toast.makeText(GameBoard.this,numberOfDestroy+"", Toast.LENGTH_LONG).show();
						if(numberOfDestroy<=0)
							break;
					}
					else
					{
						movingTroops(i-1, j+1, i, j, temp);
						validMoving=true;
						break;
					}
				}
				if(stopMoving(i, j, sRow, sCol, temp))
				{
					validMoving=true;
					break;
				}
				--toatalMove;
				if(toatalMove<=0)
					return true;
			}
		}
		return validMoving;
	}
	/****
	 * change troops to first player
	 * from second player
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public void magicForFirstPlayer(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		String changeto="";
		if(str[sRow][sCol].equalsIgnoreCase("s2l"))
		{
			changeto="s1l";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("s2H"))
		{
			changeto="s1H";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("c2M"))
		{
			changeto="c1M";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("c2B"))
		{
			changeto="c1B";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("d2"))
		{
			changeto="d1";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("v2"))
		{
			changeto="v1";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("v2f"))
		{
			changeto="v1f";
		}
		str[sRow][sCol]=changeto;
		//ib[sRow][sCol].setText(str[sRow][sCol]);
		setImageOnButton(changeto, sRow, sCol);
		str[fRow][fCol]=" ";
		//ib[fRow][fCol].setText(" ");
		ib[fRow][fCol].setBackgroundResource(R.drawable.e);
	}
	
	/****
	 * change troops to second player
	 * from first player
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public void magicForSecondPlayer(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		String changeto="";
		if(str[sRow][sCol].equalsIgnoreCase("s1l"))
		{
			changeto="s2l";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("s1H"))
		{
			changeto="s2H";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("c1M"))
		{
			changeto="c2M";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("c1B"))
		{
			changeto="c2B";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("d1"))
		{
			changeto="d2";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("v1"))
		{
			changeto="v2";
		}
		else if(str[sRow][sCol].equalsIgnoreCase("v1f"))
		{
			changeto="v2f";
		}
		str[sRow][sCol]=changeto;
		//ib[sRow][sCol].setText(str[sRow][sCol]);
		setImageOnButton(changeto, sRow, sCol);
		str[fRow][fCol]=" ";
		//ib[fRow][fCol].setText(" ");
		ib[fRow][fCol].setBackgroundResource(R.drawable.e);
	}
	
	/****
	 * shiv moving in same row and column
	 * this method is same as dragonMoving()
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public boolean shivMoving(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		if(movingdragonAndShivInSameRow(fRow, fCol, sRow, sCol, temp))
			return true;
		else
			return false;
	}
	
	/****
	 * dragon movement like as shiv and cannon movenment
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public boolean dragonMoving(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		
		if(movingdragonAndShivInSameRow(fRow, fCol, sRow, sCol, temp))
			return true;
		
		
		/***
		 * dragon diagonal moving
		 * like cannon
		 */
		else if(cannonMoving(fRow, fCol, sRow, sCol, temp))
			return true;
		return false;
	}
	
	/****
	 * 
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	
	public boolean kingMoving(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		if(Math.abs(fRow-sRow)>1 || Math.abs(fCol-sCol)>1)
			return false;
		if(temp.contains(str[sRow][sCol]))
			return false;
		movingTroops(fRow, fCol, sRow, sCol, temp);
		return true;
	}
	
	/*****
	 * moving dragon and shiv in same row
	 * this method is used for dragon moving and shiv moving 
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public boolean movingdragonAndShivInSameRow(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		int totalMove=20;
		if(str[fRow][fCol].equalsIgnoreCase("s1L") || 
				str[fRow][fCol].equalsIgnoreCase("s2L"))
		{
			totalMove=4;
		}
		
		/**
		 *  moving in same row
		 */
		boolean validMoving=false;
		
		if(fRow==sRow)
		{
			
			/***
			 * moving in same row from left
			 * to right.......
			 */
			if(fCol<sCol)
			{
				for(int i=fCol+1;;i++)
				{
					/***
					 * return when same player 
					 * troops found
					 */
					
					if(temp.contains(str[fRow][i])) 
					{
						if(i==fCol+1)
						{
							validMoving=false;
							break;
						}
						else
						{
							validMoving=true;
							break;
						}
					}
					
					else if(str[fRow][i]=="spell")
					{
						starFoundWhilemovingTroops(fRow, i-1, fRow, i, temp);
						validMoving=true;
						break;
					}
					
					/***
					 * move troops when board
					 * is empty
					 */
					
					else if(str[fRow][i]==" ")
					{
						movingTroops(fRow, i-1, fRow, i, temp);
						validMoving=true;
					}
					
					/***
					 * found against troop
					 * to destroy and return
					 */
					else
					{
						congratulationMessage(fRow, i-1 ,fRow, i);
						movingTroops(fRow, i-1, fRow, i, temp);
						validMoving=true;
						break;
					}
					if(stopMoving(fRow, i, sRow, sCol, temp))
					{
						validMoving=true;
						break;
					}
					--totalMove;
					if(totalMove<=0)
						return true;
				}
			}
			
			/***
			 * moving in same row from right
			 * to left.......
			 */
			
			else if(sCol<fCol)
			{
				for(int i=fCol-1;;i--)
				{
					
					/***
					 * return when same player 
					 * troops found
					 */
					
					if(temp.contains(str[fRow][i]))
					{
						if(i==fCol-1)
						{
							validMoving=false;
							break;
						}
						else
						{
							validMoving=true;
							break;
						}
					}
					
					else if(str[fRow][i]=="*")
					{
						starFoundWhilemovingTroops(fRow, i+1, fRow, i, temp);
						validMoving=true;
						break;
					}
					
					/***
					 * move troops when board
					 * is empty
					 */
					
					else if(str[fRow][i]==" ")
					{
						movingTroops(fRow, i+1, fRow, i, temp);
						validMoving=true;
					}
					/***
					 * found against troop
					 * to destroy and return
					 */
					
					else
					{
						congratulationMessage(fRow, i+1,fRow, i);
						movingTroops(fRow, i+1, fRow, i, temp);
						validMoving=true;
						break;
					}
					if(stopMoving(fRow, i, sRow, sCol, temp))
					{
						validMoving=true;
						break;
					}
					--totalMove;
					if(totalMove<=0)
						return true;
				}
			}
			
		}
		
		/***
		 * moving in same column
		 */
		else if(fCol==sCol)
		{
			/***
			 * moving in same from
			 * upper to lower
			 */
			if(fRow<sRow)
			{				
				for(int i=fRow+1;;i++)
				{
					
					/***
					 * return when same player 
					 * troops found
					 */
					if(temp.contains(str[i][fCol]))
					{
						if(i==fRow+1)
						{
							validMoving=false;
							break;
						}
						else
						{
							validMoving=true;
							break;
						}
					}
					
					else if(str[i][fCol]=="*")
					{
						starFoundWhilemovingTroops(i-1, fCol, i, sCol, temp);
						validMoving=true;
						break;
					}
					
					/***
					 * move troops when board
					 * is empty
					 */
					else if(str[i][fCol]==" ")
					{
						movingTroops(i-1, fCol, i, sCol, temp);
						validMoving=true;
					}
					/***
					 * found against troop
					 * to destroy and return
					 */
					
					else
					{
						congratulationMessage(i-1, fCol, i, sCol);
						movingTroops(i-1, fCol, i, sCol, temp);
						validMoving=true;
						break;
					}
					if(stopMoving(i, fCol, sRow, sCol, temp))
					{
						validMoving=true;
						break;
					}
					--totalMove;
					if(totalMove<=0)
						return true;
				}
			}
			
			/***
			 * moving from 
			 * lower to upper
			 */
			else if(sRow<fRow)
			{
				for(int i=fRow-1;;i--)
				{
					/***
					 * return when same player 
					 * troops found
					 */
					
					if(temp.contains(str[i][fCol]))
					{
						if(i==fRow-1)
						{
							validMoving=false;
							break;
						}
						else
						{
							validMoving=true;
							break;
						}
					}
					
					else if(str[i][fCol]=="*")
					{
						starFoundWhilemovingTroops(i+1, fCol, i, fCol, temp);
						validMoving=true;
						break;
					}
					
					/***
					 * move troops when board
					 * is empty
					 */
					
					else if(str[i][fCol]==" ")
					{
						movingTroops(i+1, fCol, i, fCol, temp);
						validMoving=true;
					}
					/***
					 * found against troop
					 * to destroy and return
					 */
					
					else
					{
						congratulationMessage(i+1, fCol,i, fCol);
						movingTroops(i+1, fCol, i, fCol, temp);
						validMoving=true;
						break;
					}
					if(stopMoving(i, fCol, sRow, sCol, temp))
					{
						validMoving=true;
						break;
					}
					--totalMove;
					if(totalMove<=0)
						return true;
				}
			}
		}
		return validMoving;
	}
	
	/*****
	 * check the moving positions is valid
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param troops
	 */
	/*public boolean isValidMovingPositions(int fRow,int fCol,int sRow,int sCol,String troops)
	
	{
		if(troops.equalsIgnoreCase("v1") || troops.equalsIgnoreCase("v2"))
		{
			return true;
		}
		else if(troops.equalsIgnoreCase("c1") || troops.equalsIgnoreCase("c2"))
		{
			if(Math.abs(fRow-sRow)==Math.abs(fCol-sCol))
			{
				return true;
			}
		}
		else if(troops.equalsIgnoreCase("d1") || troops.equalsIgnoreCase("d2"))
		{
			if(Math.abs(fRow-sRow)==Math.abs(fCol-sCol))
			{
				return true;
			}
			else if((fRow==sRow && fCol!=sCol) || 
					(fRow!=sRow && fCol==sCol))
			{
				return true;
			}
		}
		else if(troops.equalsIgnoreCase("s1") || troops.equalsIgnoreCase("s2"))
		{
			if((fRow==sRow && fCol!=sCol) || 
					(fRow!=sRow && fCol==sCol))
			{
				return true;
			}
		}
		else if(troops.equalsIgnoreCase("k1") || troops.equalsIgnoreCase("k2"))
		{
			if((fRow==sRow && fCol==sCol-1))
		}
		firstClick=false;
		vectorClickPosition.removeAllElements();
		return false;
	}*/
	
	/*****
	 * define troops moving changing their position
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public void starFoundWhilemovingTroops(int fRow,int fCol,
			int sRow,int sCol,String temp)
	{
		/*****
		 * star sound .............
		 */
		MediaPlayer song;
		song = MediaPlayer.create(this, R.raw.match);
		song.start();
		String store="";
		
		
		if(turn==1)
		{
			store=pointsForFirstPlayer[new Random().nextInt(6)];
			if(store.equals("v1") && str[fRow][fCol].equals("v1f"))
				store="v1f";
		}
		else if(turn==2)
		{
			store=pointsForSecondPlayer[new Random().nextInt(6)];
			if(store.equals("v2") && str[fRow][fCol].equals("v2f"))
				store="v2f";
		}
		
			
		str[sRow][sCol]=store;
		//ib[sRow][sCol].setText(store);
		setImageOnButton(store, sRow, sCol);
		
		str[fRow][fCol]=" ";
		//ib[fRow][fCol].setText(" ");
		ib[fRow][fCol].setBackgroundResource(R.drawable.e);
	}
	
	/*****
	 * define troops moving changing their position
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	public void movingTroops(final int fRow,final int fCol,
			final int sRow,final int sCol,final String temp)
	{
		
		/*myHandler = new Handler();
		myHandler.postDelayed(new Runnable() {
						
			@Override
			public void run() {
				// TODO Auto-generated method stub
				str[sRow][sCol]=str[fRow][fCol];
				ib[sRow][sCol].setText(str[sRow][sCol]);
				setImageOnButton(str[sRow][sCol], sRow, sCol);
				
				str[fRow][fCol]=" ";
				ib[fRow][fCol].setText(" ");
				ib[fRow][fCol].setBackgroundResource(R.drawable.e);
				//finish();				
			}
			
		},1000);*/
		/*new CountDownTimer(3000,2000) {
			public void onFinish()
			{
				str[sRow][sCol]=str[fRow][fCol];
				ib[sRow][sCol].setText(str[sRow][sCol]);
				setImageOnButton(str[sRow][sCol], sRow, sCol);
				
				str[fRow][fCol]=" ";
				ib[fRow][fCol].setText(" ");
				ib[fRow][fCol].setBackgroundResource(R.drawable.e);
				return;
			}
			public void onTick(long a)
			{
				
			}
		}.start();*/
		//congratulationMessage(fRow,fCol,sRow, sCol);
		str[sRow][sCol]=str[fRow][fCol];
		//ib[sRow][sCol].setText(str[sRow][sCol]);
		setImageOnButton(str[sRow][sCol], sRow, sCol);
		
		str[fRow][fCol]=" ";
		//ib[fRow][fCol].setText(" ");
		ib[fRow][fCol].setBackgroundResource(R.drawable.e);
	}
	
	public void setImageOnButton(String str,int x,int y)
	{
		if(str.equalsIgnoreCase("s1H"))
		{
			ib[x][y].setBackgroundResource(R.drawable.s1h);
		}
		else if(str.equalsIgnoreCase("s1L"))
		{
			ib[x][y].setBackgroundResource(R.drawable.s1l);
		}
		else if(str.equalsIgnoreCase("s2H"))
		{
			ib[x][y].setBackgroundResource(R.drawable.s2h);
		}
		else if(str.equalsIgnoreCase("s2L"))
		{
			ib[x][y].setBackgroundResource(R.drawable.s2l);
		}
		else if(str.equalsIgnoreCase("c1M"))
		{
			ib[x][y].setBackgroundResource(R.drawable.c1m);
		}
		else if(str.equalsIgnoreCase("c2M"))
		{
			ib[x][y].setBackgroundResource(R.drawable.c2m);
		}
		else if(str.equalsIgnoreCase("c1B"))
		{
			ib[x][y].setBackgroundResource(R.drawable.c1b);
		}
		else if(str.equalsIgnoreCase("c2B"))
		{
			ib[x][y].setBackgroundResource(R.drawable.c2b);
		}
		else if(str.equalsIgnoreCase("d1"))
		{
			ib[x][y].setBackgroundResource(R.drawable.d1);
		}
		else if(str.equalsIgnoreCase("k1"))
		{
			ib[x][y].setBackgroundResource(R.drawable.k1);
		}
		else if(str.equalsIgnoreCase("v1") || str.equalsIgnoreCase("v1f"))
		{
			ib[x][y].setBackgroundResource(R.drawable.v1);
		}
		else if(str.equalsIgnoreCase("d2"))
		{
			ib[x][y].setBackgroundResource(R.drawable.d2);
		}
		else if(str.equalsIgnoreCase("k2"))
		{
			ib[x][y].setBackgroundResource(R.drawable.k2);
		}
		else if(str.equalsIgnoreCase("v2") || str.equalsIgnoreCase("v2f"))
		{
			ib[x][y].setBackgroundResource(R.drawable.v2);
		}
		else if(str.equalsIgnoreCase(" "))
			ib[x][y].setBackgroundResource(R.drawable.e);
	}

	
	
	public void generatePoints()
	{
		int numberOfEmptyCell=searchTotalemptySquare();
		int generatePoint=0;
		if(numberOfEmptyCell>20)
			generatePoint=6;
		else if(numberOfEmptyCell>12)
			generatePoint=4;
		else if(numberOfEmptyCell>8)
			generatePoint=2;
		int countPoint=0;
		if(generatePoint!=0)
		{
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
					if(str[i][j]==" " || str[i][j]=="*")
					{
						int x=new Random().nextInt(5);
						if(x==2)
						{
							str[i][j]="*";
							ib[i][j].setBackgroundResource(R.drawable.start);
							++countPoint;
						}
						else
						{
							if(str[i][j]=="*")
							{
								str[i][j]=" ";
								//ib[i][j].setText(" ");
								ib[i][j].setBackgroundResource(R.drawable.e);
							}
						}
						if(countPoint>=generatePoint)
							return;
					}
				}
			}
		}
		
	}
	
	public int searchTotalemptySquare()
	{
		int empty=0;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(str[i][j]==" ")
				{
					++empty;
				}
			}
		}
		return empty;
	}
	
	/****
	 * moving until destination found
	 * and then stop
	 * @param fRow
	 * @param fCol
	 * @param sRow
	 * @param sCol
	 * @param temp
	 */
	
	public boolean stopMoving(int fRow,int fCol,int sRow,int sCol,String temp)
	{
		if(fRow==sRow && fCol==sCol)
			return true;
		else
			return false;
	}
	
	public void changeColorForEveryTurn()
	{
		if(turn==1)
		{
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
					ib[i][j].setTextSize(20);
					
					if(firstPlayerTroops.contains(str[i][j]))
					{
						ib[i][j].setTextColor(Color.CYAN);
						//ib[i][j].setPressed(true);
					}
					else if(secondPlayerTroops.contains(str[i][j]))
					{
						ib[i][j].setTextColor(Color.WHITE);
						//ib[i][j].setPressed(false);
					}
					else if(str[i][j]=="*")
					{
						ib[i][j].setTextColor(Color.MAGENTA);
						ib[i][j].setTextSize(45);
						//ib[i][j].setPressed(false);
					}
				}
			}
		}
		else
		{
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
					ib[i][j].setTextSize(20);
					
					if(secondPlayerTroops.contains(str[i][j]))
					{
						ib[i][j].setTextColor(Color.CYAN);
						//ib[i][j].setPressed(true);
					}
					else if(firstPlayerTroops.contains(str[i][j]))
					{
						ib[i][j].setTextColor(Color.WHITE);
						//ib[i][j].setPressed(false);
					}
					else if(str[i][j]=="*")
					{
						ib[i][j].setTextColor(Color.YELLOW);
						ib[i][j].setTextSize(45);
						//ib[i][j].setPressed(false);
					}
				}
			}
		}
	}
	public void congratulationMessage(int x,int y,int i,int j)
	{
		playingMusic(x, y);
		if(str[i][j].equalsIgnoreCase("k1") || str[i][j].equalsIgnoreCase("k2"))
		{
			Intent intent=new Intent(GameBoard.this,Congratulation.class);
			finish();
			startActivity(intent);
		}
	}
	public void playingMusic(int x,int y)
	{
		if(str[x][y].equalsIgnoreCase("s1h") || str[x][y].equalsIgnoreCase("s2h")
				|| str[x][y].equalsIgnoreCase("s1l")|| str[x][y].equalsIgnoreCase("s2l"))
		{
			MediaPlayer song;
			song = MediaPlayer.create(this, R.raw.ship);
			song.start();
		}
		else if(str[x][y].equalsIgnoreCase("c1m") || str[x][y].equalsIgnoreCase("c2m"))
		{
			MediaPlayer song;
			song = MediaPlayer.create(this, R.raw.wizard);
			song.start();
		}
		else if(str[x][y].equalsIgnoreCase("c1b") || str[x][y].equalsIgnoreCase("c2b"))
		{
			MediaPlayer song;
			song = MediaPlayer.create(this, R.raw.wolf);
			song.start();
		}
		else if(str[x][y].equalsIgnoreCase("v1") || str[x][y].equalsIgnoreCase("v2")
				|| str[x][y].equalsIgnoreCase("v1f")|| str[x][y].equalsIgnoreCase("v2f"))
		{
			MediaPlayer song;
			song = MediaPlayer.create(this, R.raw.vikings);
			song.start();
		}
		
		else if(str[x][y].equalsIgnoreCase("d1") || str[x][y].equalsIgnoreCase("d2"))
		{
			MediaPlayer song;
			song = MediaPlayer.create(this, R.raw.dragonmain);
			song.start();
		}
	}
	public void moveSound()
	{
		/*MediaPlayer song;
		song = MediaPlayer.create(this, R.raw.xyz);
		song.start();*/
	}
}
