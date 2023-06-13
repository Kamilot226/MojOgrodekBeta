package MojOgrodek;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class RemontFrame extends JFrame{
	
	
	public RemontFrame() {
	int width = 800;
    int height = 300;
    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    //this.setBackground(new Color(0,0,0,0));
    this.setResizable(false);
    //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    Color color;
    GradientPanel panel1 = new GradientPanel();
    add(BorderLayout.CENTER, panel1);

    JLabel labeltytulowy = new JLabel("<html><center>Zmiana koloru<br/>(koszt $50)</center></html>", SwingConstants.CENTER);
    labeltytulowy.setForeground(Color.WHITE);
    labeltytulowy.setFont(new Font("monospaced", Font.BOLD, 32));
    labeltytulowy.setBounds(200, 10, 400, 80);
    
    JButton buttondywan = new RoundRectButton("DYWAN");
    buttondywan.setForeground(Color.WHITE);
    buttondywan.setFont(new Font("monospaced", Font.BOLD, 20));
    buttondywan.setBounds(50,100,200,100);
    panel1.add(buttondywan);
    
    buttondywan.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	if(Garden.getCash()>=50)
     	   	{
	        	Color tmp = House.carpetColor1;
	        	
	            House.carpetColor1 = JColorChooser.showDialog(null, "Pick a color", House.carpetColor1);
	            if(House.carpetColor1 == null)
	            {
	            	House.carpetColor1 = tmp;
	            }
	            
	           House.carpetColor2 = House.carpetColor1.darker().darker();
	           
	           if(House.carpetColor1 != tmp)Garden.setCash(Garden.getCash()-50);
	            revalidate();
     	   }
        }
    });
    
    JButton buttonlozko = new RoundRectButton("ŁÓŻKO");
    buttonlozko.setForeground(Color.WHITE);
    buttonlozko.setFont(new Font("monospaced", Font.BOLD, 20));
    buttonlozko.setBounds(300,100,200,100);
    panel1.add(buttonlozko);
    
    buttonlozko.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	if(Garden.getCash()>=50)
     	   	{
	        	Color tmp = House.bedColor;
	
	            House.bedColor = JColorChooser.showDialog(null, "Pick a color", House.bedColor);
	            
	            if(House.bedColor == null)
	            {
	            	House.bedColor = tmp;
	            }
	            
	            if(House.bedColor != tmp)Garden.setCash(Garden.getCash()-50);
	            
	            revalidate();
     	   	}
        }
    });
    
    JButton buttonsciany = new RoundRectButton("ŚCIANY");
    buttonsciany.setForeground(Color.WHITE);
    buttonsciany.setFont(new Font("monospaced", Font.BOLD, 20));
    buttonsciany.setBounds(550,100,200,100);
    panel1.add(buttonsciany);
    
    buttonsciany.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	if(Garden.getCash()>=50)
     	   	{
	        	Color tmp = House.houseColor;
	        	
	        	House.houseColor = JColorChooser.showDialog(null, "Pick a color", House.houseColor);
	            if(House.houseColor == null)
	            {
	            	House.houseColor = tmp;
	            }
	            
	            if(House.houseColor != tmp)Garden.setCash(Garden.getCash()-50);
	            
	            revalidate();
     	   	}
        }
    });
    
    this.add(panel1);
    panel1.setLayout(null);
    panel1.add(labeltytulowy);
	}
	
	

    
	public static void main(String[] args) {
		RemontFrame remont = new RemontFrame();
		remont.setVisible(true);

	}

}
