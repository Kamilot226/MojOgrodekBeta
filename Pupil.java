package MojOgrodek;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Pupil extends JPanel implements Runnable, MouseListener
{
	static public boolean czynny = true, glodny = false, atBowl=false, aport = false, cooldown = false, przezDrzwi=false, naOkolo=false, pupilTrzymaPilke = false;
	static public int xPos, yPos, xPilka, yPilka;
	public int xVel, yVel, xNew, yNew;
	public int screenWidth, screenHeight;
	public int pupilWidth, pupilHeight;
	public BufferedImage image, pupil, pupilPilka, pilka;
	int timeCounter=0;
	
	public Pupil()
	{
		screenWidth=1080;
		screenHeight = 720;
		this.setSize(screenWidth, screenHeight);
		pupilWidth = 75;
		pupilHeight = 75;
		xPos=400;
		yPos=400;
		xNew=410;
		yNew=420;
		xVel=1;
		yVel=1;
		xPilka=600;
		yPilka=88;
		setOpaque(false);
		
		addMouseListener(this);
		
		try{
			pupil = ImageIO.read(getClass().getResourceAsStream("/Grafika/pupil.png"));
			pupilPilka = ImageIO.read(getClass().getResourceAsStream("/Grafika/pupilPilka.png"));
			pilka = ImageIO.read(getClass().getResourceAsStream("/Grafika/pilka.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		image=pupil;
	}

	@Override
	public void run()
	{
		while(czynny)
		{
			Random r = new Random();
			
			if(xPos < xNew+3 && xPos > xNew-3 && !glodny && !aport)
			{
				xNew=xPos+r.nextInt(80)-40;
				if(xNew<0)xNew=200;
				if(xNew>getWidth())xNew=getWidth()-200;
			}
			if(yPos < yNew+3 && yPos > yNew-3 && !glodny && !aport)
			{
				yNew=yPos+r.nextInt(80)-40;
				if(yNew<0)yNew=200;
				if(yNew>getHeight())yNew=getHeight()-200;
			}
			
			if((xNew > 648 && yNew > 540) || (xNew < 160 && yNew > 650) || (xNew < 160 && yNew < 370 ) || (xNew < 220 && yNew < 710))
			{
				xNew=xPos;
				yNew=yPos;
			}
			
			if(((xNew > 648 && xNew < 1015 && yNew > 79 && yNew < 446) || (xNew < pupilWidth || xNew > getWidth()-pupilWidth/2 || yNew < 5 || yNew > getHeight())) && !glodny && !aport)
			{
				xNew=xPos;
				yNew=yPos;
			}
			
			if((xPos > 648 && xPos < 1015 && yPos > 79 && yPos < 446) && !glodny && !aport)
			{
				xNew=640;
				yNew=185;
			}
			
			
			if(glodny)
			{
				xNew=770;
				yNew=205;
			}
			
			if(xPos==770 && yPos==205)
			{
				atBowl=true;
			}
			
			if(aport && !glodny)
			{
				xVel=4;
				yVel=4;
				
				if(!pupilTrzymaPilke && (przezDrzwi || naOkolo) && xPos < xNew+8 && xPos > xNew-8 && yPos < yNew+8 && yPos > yNew-8)
				{
					xNew=xPilka;
					yNew=yPilka;
				}
				
				if(pupilTrzymaPilke && naOkolo && xPos > 648 && !przezDrzwi)
				{
					xNew=640;
					yNew=yPos;
				}
				
				if(pupilTrzymaPilke && naOkolo && xNew > 648 && (yNew < 79 || yNew> 446) && xPos < 648)
				{
					xNew=640;
				}
				
				if(pupilTrzymaPilke && (((xNew > 648 && xNew < 1015 && yNew > 79 && yNew < 446)/*kajtek w domu*/ && !(xPos > 648 && xPos < 1015 && yPos > 79 && yPos < 446)/*pupil nie w domu*/)) || (!(xNew > 648 && xNew < 1015 && yNew > 79 && yNew < 446)/*kajtek nie w domu*/ && (xPos > 648 && xPos < 1015 && yPos > 79 && yPos < 446)/*pupil w domu*/))
				{
					xNew=647;
					yNew=185;
					przezDrzwi=true;
				}
				
				if(!przezDrzwi && !naOkolo && pupilTrzymaPilke && xPos < xNew+15 && xPos > xNew-15 && yPos < yNew+15 && yPos > yNew-15)
				{
					image=pupil;
					pupilTrzymaPilke=false;
					aport=false;
					xPilka=xNew;
					yPilka=yNew;
					pupilHeight=75;
					cooldown=true;
				}
				
				if(xPos < xNew+20 && xPos > xNew-20 && yPos < yNew+20 && yPos > yNew-20)
				{
					xVel=1;
					yVel=1;
					if(pupilTrzymaPilke)naOkolo=false;
				}
				
				if(!cooldown && xPos < xNew+3 && xPos > xNew-3 && yPos < yNew+3 && yPos > yNew-3)
				{
					image=pupilPilka;
					pupilTrzymaPilke=true;
					pupilHeight=82;
					xVel=4;
					yVel=4;
					try
					{
						Thread.sleep(120);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					if(pupilTrzymaPilke && xNew > 648 && (yNew < 79 || yNew> 446) && xPos < 648)
					{
						naOkolo=true;
					}
				}
				
				cooldown=false;
			}
			
			if(xPos<xNew)xPos+=xVel;
			if(xPos>xNew)xPos-=xVel;
			if(yPos<yNew)yPos+=yVel;
			if(yPos>yNew)yPos-=yVel;
			
			if (xPos < pupilWidth/2) xPos = pupilWidth/2;
			if (xPos + pupilWidth/2 > getWidth()) xPos = getWidth() - pupilWidth/2;
			if (yPos < 5) yPos = 5;
			if (yPos + pupilHeight > getHeight()+pupilHeight) yPos = getHeight();
			
			if(timeCounter > 1000000000)timeCounter = 0;//zabezpieczenie
			timeCounter++;
			if(timeCounter%1000 == 0)
			{
				glodny=true;
			}
			
			try
			{
				Thread.sleep(60);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			repaint();
		}
	}
	
	public void paintComponent(Graphics g)
    {       
    	  Graphics2D g2d = (Graphics2D) g;
    	  g2d.drawImage(image, xPos-(pupilWidth/2), yPos-pupilHeight, pupilWidth, pupilHeight, (ImageObserver) this);
    	  if(!pupilTrzymaPilke)g2d.drawImage(pilka, xPilka, yPilka, 31, 31, (ImageObserver) this);
    }

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getX()>1008 && e.getY()>22 && e.getX()<1058 && e.getY()<72)
		{
			InGameMenuFrame inGameMenu = new InGameMenuFrame();
			inGameMenu.setVisible(true);
		}
		else if(e.getX() > 1015 && e.getY() > 79 && e.getY() < 446) {}
		else
		{
			aport=true;
			xPilka=e.getX()-15;
			yPilka=e.getY()-15;
			xNew=e.getX();
			yNew=e.getY();
			
			if((xPilka > 648 && xPilka < 1015 && yPilka > 79 && yPilka < 446)/*pilka w domu*/ && !(xPos > 648 && xPos < 1015 && yPos > 79 && yPos < 446)/*pupil nie w domu*/)
			{
				przezDrzwi=true;
				xNew=647;
				yNew=185;
			}
			
			if(xPos > 648/*pupil po prawej*/ && !(xPos > 648 && xPos < 1015 && yPos > 79 && yPos < 446)/*pupil nie w domu*/ && xPilka < 648/*pilka po lewej*/)
			{
				xNew=640;
				yNew=yPos;
				naOkolo=true;
			}
			
			if(xPilka > 648 /*pilka po prawej*/&& !(xPilka > 648 && xPilka < 1015 && yPilka > 79 && yPilka < 446)/*pilka nie w domu*/)
			{
				xNew=640;
				naOkolo=true;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
