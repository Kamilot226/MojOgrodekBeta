package MojOgrodek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class LoadFrame extends JFrame
{
	LoadFrame()
	{
		setTitle("Odczyt gry");
		setSize(400, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
	    
	    JPanel loadPanel =  new GradientPanel();
	    add(BorderLayout.CENTER, loadPanel);
	    loadPanel.setLayout(null);
		
	    JLabel podajNazwe = new JLabel("<html><center>Podaj nazwę gracza</center></html>", SwingConstants.CENTER);
	    loadPanel.add(podajNazwe);
	    podajNazwe.setForeground(Color.WHITE);
	    podajNazwe.setFont(new Font("monospaced", Font.BOLD, 18));
		podajNazwe.setBounds(30, 10, 340, 100);
		
		JTextField fileNameTxt = new JTextField();
		loadPanel.add(fileNameTxt);
		fileNameTxt.setBounds(95,100,200,50);
		
		JButton loadButton = new RoundRectButton("Wczytaj grę");
		loadPanel.add(loadButton);
		loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("monospaced", Font.BOLD, 18));
		loadButton.setBounds(95,160,200,50);
		loadButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Main city1 = new Main(1,"Kajtek","Pupilek");
                city1.setVisible(true);
                SwingUtilities.invokeLater(new Runnable()
                {
        			@Override
        			public void run()
        			{
        				ExecutorService exec = Executors.newFixedThreadPool(4);
        				exec.execute(city1);
        				exec.shutdown();
        			}
        		});
                
	        	String zapis = "";
	        	try {
					zapis = readFile("src/Saves/"+fileNameTxt.getText()+".txt", StandardCharsets.UTF_8);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	//System.out.println(zapis);
	        	
	        	StringTokenizer st1 = new StringTokenizer(zapis);
	        		//zmienne int
	    					Garden.cash=Integer.parseInt(st1.nextToken());
	    					PotatoMan.glod=Integer.parseInt(st1.nextToken());
	    					PotatoMan.energia=Integer.parseInt(st1.nextToken());
	    					PotatoMan.szczescie=Integer.parseInt(st1.nextToken());
	    					PotatoMan.xPos=Integer.parseInt(st1.nextToken());
	    					PotatoMan.yPos=Integer.parseInt(st1.nextToken());
	    					Pupil.xPos=Integer.parseInt(st1.nextToken());
	    					Pupil.yPos=Integer.parseInt(st1.nextToken());
	    					Pupil.xPilka=Integer.parseInt(st1.nextToken());
	    					Pupil.yPilka=Integer.parseInt(st1.nextToken());
	    					Garden.progress=Integer.parseInt(st1.nextToken());
	    					Garden.timeCounter=Integer.parseInt(st1.nextToken());
	    			//zmienne String

	    					Garden.playerName=st1.nextToken();
	    					Garden.dogName=st1.nextToken();
	    			//zmienne boolean
		        			if(st1.nextToken()=="1")Pupil.glodny=true; else Pupil.glodny=false;
		        			if(st1.nextToken()=="1")House.foodInBowl=true; else House.foodInBowl=false;
		        			if(st1.nextToken()=="1")Garden.sellReady=true; else Garden.sellReady=false;
	    			//zmienne Color
		        			House.houseColor = new Color(Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()));
		        			House.carpetColor1 = new Color(Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()));
		        			House.carpetColor2 = new Color(Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()));
		        			House.bedColor = new Color(Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()) , Integer.parseInt(st1.nextToken()));
	        	
		        			
		        			try {
		        				PotatoMan.kajtek1 = ImageIO.read(getClass().getResourceAsStream("/Saves/"+fileNameTxt.getText()+"Kaj1.png"));
		        				PotatoMan.kajtek4 = ImageIO.read(getClass().getResourceAsStream("/Saves/"+fileNameTxt.getText()+"Kaj4.png"));
		        				City.image = ImageIO.read(getClass().getResourceAsStream("/Saves/"+fileNameTxt.getText()+"City.png"));
		        				City.kajtek = ImageIO.read(getClass().getResourceAsStream("/Saves/"+fileNameTxt.getText()+"Profile.png"));
		        				Garden.kwiat = ImageIO.read(getClass().getResourceAsStream("/Saves/"+fileNameTxt.getText()+"GardenFlower.png"));
		        			}catch(IOException f) {
		        				f.printStackTrace();
		        			}
		        
		        revalidate();
	        	
				setVisible(false);
	        }
	
	    });
		
		JButton cancelButton = new RoundRectButton("Anuluj");
		loadPanel.add(cancelButton);
		cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("monospaced", Font.BOLD, 18));
		cancelButton.setBounds(95,280,200,50);
		cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        	OpeningFrame open = new OpeningFrame();
	        	open.setVisible(true);
	        }
	
	    });
	}
	
	static String readFile(String path, Charset encoding)
			  throws IOException
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
}
