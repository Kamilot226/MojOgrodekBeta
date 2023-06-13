package MojOgrodek;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;
import javax.swing.text.html.StyleSheet;

public class Main extends JFrame implements Runnable
{
	City miasto;
	PotatoMan kajtek;
	Pupil pupil;
	Garden ogrodek;
	House dom;
	static boolean saveActive=false;
	
	boolean czynny=true, bowlActive=false;
	int timeCounter=0;
	
	public Main(int cityType, String graczImie, String pupilImie)
	{
		int width=1100;
		int height = 763;
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layers = new JLayeredPane();
		add(layers);
		
		miasto = new City(cityType);
		layers.add(miasto);
		layers.setLayer(miasto, JLayeredPane.DEFAULT_LAYER);
		
		pupil = new Pupil();
		layers.add(pupil);
		layers.setLayer(pupil, JLayeredPane.DRAG_LAYER);
		
		kajtek = new PotatoMan();
		layers.add(kajtek);
		layers.setLayer(kajtek, JLayeredPane.DRAG_LAYER);
		
		ogrodek = new Garden(graczImie,pupilImie);
		layers.add(ogrodek);
		layers.setLayer(ogrodek, JLayeredPane.DRAG_LAYER);
		
		dom = new House();
		layers.add(dom);
		layers.setLayer(dom, JLayeredPane.PALETTE_LAYER);
		
		
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				ExecutorService exec = Executors.newFixedThreadPool(4);
				
				exec.execute(kajtek);
				
				exec.execute(pupil);
				
				exec.execute(ogrodek);
				
				exec.shutdown();
			}
		});
	}

	@Override
	public void run()
	{
		while(czynny)
		{
			if(kajtek.atGarden && ogrodek.progress != 3)ogrodek.active=true;
			else ogrodek.active=false;
			
			if(kajtek.atGarden && ogrodek.progress == 3)ogrodek.sellReady=true;
			else ogrodek.sellReady=false;
			
			if(kajtek.atBowl && !dom.foodInBowl)
			{
				dom.foodInBowl=true;
				revalidate();
			}
			
			if(pupil.atBowl && pupil.glodny && dom.foodInBowl)
			{
				pupil.glodny=false;
				dom.foodInBowl=false;
				pupil.atBowl=false;
				revalidate();
			}
			
			if(pupil.pupilTrzymaPilke)
			{
				pupil.xNew = kajtek.xPosition();
				pupil.yNew = kajtek.yPosition();
			}
			
			if(pupil.pupilTrzymaPilke && pupil.xPos-30 <= kajtek.xPos && pupil.xPos+30 >= kajtek.xPos && pupil.yPos-30 <= kajtek.yPos && pupil.yPos+30 >= kajtek.yPos)
			{
				pupil.przezDrzwi=false;
				pupil.naOkolo=false;
			}
			
			if(pupil.xPos-30 <= kajtek.xPos && pupil.xPos+30 >= kajtek.xPos && pupil.yPos-30 <= kajtek.yPos && pupil.yPos+30 >= kajtek.yPos)
			{
				if(kajtek.szczescie < 4 && timeCounter%5 == 0) kajtek.szczescie++;
			}
			
			if(kajtek.glod==0)
			{
				JOptionPane.showMessageDialog(null, "Ziemniak umarł z głodu! Koniec gry!");
				System.exit(0);
				
			}
			
			if(saveActive){saveGame();}
			
			if(timeCounter > 1000000000)timeCounter = 0;//zabezpieczenie
			timeCounter++;

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	static public void startSaveGame()
	{
		saveActive=true;
	}
	
	public void saveGame()
	{
		saveActive=false;
		JFrame saveFrame = new JFrame("Zapis gry");
		saveFrame.setSize(400, 500);
		saveFrame.setVisible(true);
		saveFrame.setLocationRelativeTo(null);
        saveFrame.setResizable(false);
        saveFrame.setLayout(new BorderLayout());
        
        JPanel savePanel =  new GradientPanel();
        saveFrame.add(BorderLayout.CENTER, savePanel);
        savePanel.setLayout(null);
		
        JLabel podajNazwe = new JLabel("<html><center>Podaj nazwę gracza<br/>(powtórzona nazwa nadpisze poprzedni zapis!)</center></html>", SwingConstants.CENTER);
		savePanel.add(podajNazwe);
		podajNazwe.setForeground(Color.WHITE);
        podajNazwe.setFont(new Font("monospaced", Font.BOLD, 18));
		podajNazwe.setBounds(30, 10, 340, 100);
		
		JTextField fileNameTxt = new JTextField();
		savePanel.add(fileNameTxt);
		fileNameTxt.setBounds(95,100,200,50);
		
		JButton saveButton = new RoundRectButton("Zapisz grę");
		savePanel.add(saveButton);
		saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("monospaced", Font.BOLD, 18));
		saveButton.setBounds(95,160,200,50);
		saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try (PrintWriter out = new PrintWriter("src/Saves/"+fileNameTxt.getText()+".txt"))
        		{
        		    //zmienne int
        			out.println
        			(
        					ogrodek.cash +"\n"+
        					kajtek.glod +"\n"+
        					kajtek.energia +"\n"+
        					kajtek.szczescie +"\n"+
        					kajtek.xPos +"\n"+
        					kajtek.yPos +"\n"+
        					pupil.xPos +"\n"+
        					pupil.yPos +"\n"+
        					pupil.xPilka +"\n"+
        					pupil.yPilka +"\n"+
        					ogrodek.progress +"\n"+
        					ogrodek.timeCounter
        			);
        			//zmienne String
        			out.println
        			(
        					ogrodek.playerName +"\n"+
        					ogrodek.dogName
        			);
        			//zmienne boolean
		        			if(pupil.glodny)out.println("1");else out.println("0");
		        			if(dom.foodInBowl)out.println("1");else out.println("0");
		        			if(ogrodek.sellReady)out.println("1");else out.println("0");
        			//zmienne Color
		        	out.println
		        	(
		        			dom.houseColor.getRed() +"\n"+
		        			dom.houseColor.getGreen() +"\n"+
		        			dom.houseColor.getBlue() +"\n"+
		        			dom.carpetColor1.getRed() +"\n"+
		        			dom.carpetColor1.getGreen() +"\n"+
		        			dom.carpetColor1.getBlue() +"\n"+
		        			dom.carpetColor2.getRed() +"\n"+
		        			dom.carpetColor2.getGreen() +"\n"+
		        			dom.carpetColor2.getBlue() +"\n"+
		        			dom.bedColor.getRed() +"\n"+
		        			dom.bedColor.getGreen() +"\n"+
		        			dom.bedColor.getBlue()
		        	);
        		    
        		}
        		catch (FileNotFoundException e1)
        		{
        			e1.printStackTrace();
        		}
            	
				File outputCityFile = new File("src/Saves/"+fileNameTxt.getText()+"City.png");
				try {
					ImageIO.write(miasto.image, "png", outputCityFile);
				} catch (IOException f) {
					System.out.println(f.getMessage());
				}
				
				File outputGardenFile = new File("src/Saves/"+fileNameTxt.getText()+"GardenFlower.png");
				try {
					ImageIO.write(ogrodek.kwiat, "png", outputGardenFile);
				} catch (IOException f) {
					System.out.println(f.getMessage());
				}
				
				File outputProfileFile = new File("src/Saves/"+fileNameTxt.getText()+"Profile.png");
				try {
					ImageIO.write(miasto.kajtek, "png", outputProfileFile);
				} catch (IOException f) {
					System.out.println(f.getMessage());
				}
				
				File outputKajtek1File = new File("src/Saves/"+fileNameTxt.getText()+"Kaj1.png");
				try {
					ImageIO.write(kajtek.kajtek1, "png", outputKajtek1File);
				} catch (IOException f) {
					System.out.println(f.getMessage());
				}
				
				File outputKajtek4File = new File("src/Saves/"+fileNameTxt.getText()+"Kaj4.png");
				try {
					ImageIO.write(kajtek.kajtek4, "png", outputKajtek4File);
				} catch (IOException f) {
					System.out.println(f.getMessage());
				}
            	
                saveFrame.setVisible(false);
            }

        });
		
		JButton cancelButton = new RoundRectButton("Anuluj");
		savePanel.add(cancelButton);
		cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("monospaced", Font.BOLD, 18));
		cancelButton.setBounds(95,280,200,50);
		cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFrame.setVisible(false);
            }

        });
	}
}
