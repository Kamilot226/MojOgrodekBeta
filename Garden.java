package MojOgrodek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.*;  
import java.util.*;  
import javax.imageio.*;
import java.io.*;

public class Garden extends JPanel implements Runnable
{
	static public int progress=0/*0 - nic, 1 - nasiona, 2- łodyżki, 3 - kwiaty*/;
	static public boolean czynny=true, active=false, sellReady=false;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
	JLabel imie, pupil, data, hajs, info;
	static public int cash=50, timeCounter=0;
	public Color dirt = new Color(110, 68, 14);
	static public BufferedImage nasiona, lodyga, kwiat;
	static public FridgeFrame fridge;
	static public RemontFrame remont;
	static public WardrobePanel szafa;
	static String playerName, dogName;
	 public Garden(String graczImie, String pupilImie)
	{
		int width=1080;
		int height = 720;
		this.setSize(width, height);
		setLayout(null);
		setOpaque(false);
		
		playerName=graczImie;
		dogName=pupilImie;
        
        imie = new JLabel(playerName, SwingConstants.CENTER);
		imie.setFont(new Font("monospaced", Font.BOLD, 23));
		add(imie);
		imie.setBounds(10,95,138,130);
		
		pupil = new JLabel(dogName, SwingConstants.CENTER);
		pupil.setFont(new Font("monospaced", Font.BOLD, 23));
		add(pupil);
		pupil.setBounds(10,217,138,247);
		
		data = new JLabel(formatter.format(new Date()), SwingConstants.CENTER);
		data.setFont(new Font("monospaced", Font.BOLD, 20));
		add(data);
		data.setBounds(10,455,200,474);
		
		hajs = new JLabel("$"+cash, SwingConstants.CENTER);
		hajs.setFont(new Font("monospaced", Font.BOLD, 25));
		add(hajs);
		hajs.setBounds(10,417,138,440);
		
		info = new JLabel("Wciśnij 'E', aby wykonać czynność.", SwingConstants.CENTER);
		info.setFont(new Font("monospaced", Font.BOLD, 20));
		//info.setForeground(new Color(255,255,255));
		info.setBackground(new Color(255,255,255));
		info.setOpaque(true);
		add(info);
		info.setBounds(223,673,425,40);
		
		getImages();
		
		repaint();
		
		Action activate = new AbstractAction()
		{
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	if(active && !sellReady)
	        	{
	        		PlantsMenu plantsMenu = new PlantsMenu();
	        		plantsMenu.setVisible(true);
	        	}
	        	if(sellReady)
	        	{
	        		progress=0;
	        		cash+=25;
	        		if(PotatoMan.szczescie < 4)PotatoMan.szczescie++;
	        	}
	        	if(PotatoMan.atFridge) {
	        		fridge = new FridgeFrame();
	        		fridge.setVisible(true);
	        	}
	        	
	        	if(PotatoMan.atRemont)
	        	{
	        		remont = new RemontFrame();
	        		remont.setVisible(true);
	        	}
	        	
	        	if(PotatoMan.atWardrobe)
	        	{
	        		szafa = new WardrobePanel();
	        		szafa.setVisible(true);
	        	}
	        }
		};
		bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "activate", KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), activate);
	}
	
	public void getImages()
	{
		try {
			nasiona = ImageIO.read(getClass().getResourceAsStream("/Grafika/nasiona.png"));
			lodyga = ImageIO.read(getClass().getResourceAsStream("/Grafika/lodygi.png"));
			kwiat = ImageIO.read(getClass().getResourceAsStream("/Grafika/kwiatek1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void bindKeyStroke(int condition, String name, KeyStroke keyStroke, Action action)
	{
        InputMap im = getInputMap(condition);
        ActionMap am = getActionMap();

        im.put(keyStroke, name);
        am.put(name, action);
    }
	
	static public void setFlower(BufferedImage flower)
	{
		kwiat=flower;
	}
	
	static public void setCash(int money)
	{
		cash=money;
	}
	
	static public int getCash()
	{
		return cash;
	}
	
	static public void startProgress()
	{
		progress=1;
	}

	public void paintComponent(Graphics g)
    {
		imie.setText(playerName);
		pupil.setText(dogName);
		if(progress==0)
		{
			g.setColor(Color.black);
			g.fillRect(504, 259, 108, 187);
			g.setColor(dirt);
			g.fillRect(511, 266, 94, 173);
		}
    	if(progress==1)
    	{
    		Graphics2D g2d = (Graphics2D) g;
        	g2d.drawImage(nasiona, 504, 259, 108, 187, (ImageObserver) this);
    	}
    	if(progress==2)
    	{
    		Graphics2D g2d = (Graphics2D) g;
        	g2d.drawImage(lodyga, 504, 259, 108, 187, (ImageObserver) this);
    	}
    	if(progress==3)
    	{
    		Graphics2D g2d = (Graphics2D) g;
        	g2d.drawImage(lodyga, 504, 259, 108, 187, (ImageObserver) this);
        	g2d.drawImage(kwiat, 519, 266, 33, 33, (ImageObserver) this);
        	g2d.drawImage(kwiat, 562, 280, 33, 33, (ImageObserver) this);
        	g2d.drawImage(kwiat, 514, 329, 33, 33, (ImageObserver) this);
        	g2d.drawImage(kwiat, 565, 327, 33, 33, (ImageObserver) this);
        	g2d.drawImage(kwiat, 526, 385, 33, 33, (ImageObserver) this);
        	g2d.drawImage(kwiat, 565, 380, 33, 33, (ImageObserver) this);
    	}
    }

	@Override
	public void run()
	{
		while(czynny)
		{
			Date date = new Date();
			data.setText(formatter.format(date));
			hajs.setText("$"+cash);
			
			if(timeCounter > 1000000000)timeCounter = 0;//zabezpieczenie
			timeCounter++;
			if(timeCounter%150 == 0)
			{
				if(progress < 3 && progress!=0)progress++;
			}
			
			try
			{
				Thread.sleep(75);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			repaint();
		}
	}
}
