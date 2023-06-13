package MojOgrodek;

import static MojOgrodek.Garden.setFlower;
import static MojOgrodek.Garden.startProgress;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FridgeFrame extends JFrame{

	public FridgeFrame()
	{
		       int width = 600;
		       int height =600;
		       this.setSize(width, height);
		       this.setLocationRelativeTo(null);
		       //this.setBackground(new Color(0,0,0,0));
		       this.setResizable(false);
		       //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		       setLayout(new BorderLayout());

		       GradientPanel panel1 = new GradientPanel();
		       panel1.setBackground(Color.GREEN.darker().darker());
		       panel1.setLayout(null);
		       add(BorderLayout.CENTER, panel1);

		       JLabel labeltytulowy = new JLabel("Co dobrego w lodówce?");
		       labeltytulowy.setForeground(Color.WHITE);
		       labeltytulowy.setFont(new Font("monospaced", Font.BOLD, 30));
		       labeltytulowy.setBounds(100, 10, 400, 70);
		       
		       JButton bstyl1 = new JButton("");
		       ImageIcon img1 = new ImageIcon("src/Grafika/lody.png");
		       Image imgg = img1.getImage() ;
		       Image newimg = imgg.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img1 = new ImageIcon( newimg );
		       bstyl1.setIcon(img1);
		       bstyl1.setBounds(225, 100, 150, 150);
		       bstyl1.setBorder(null);
		       bstyl1.setFocusPainted(true);
		       bstyl1.setContentAreaFilled(false);
		       
		       bstyl1.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	   Garden.fridge.setVisible(false);
		        	   
		           }
		       });
		       
		       JButton bstyl2 = new JButton("");
		       ImageIcon img2 = new ImageIcon("src/Grafika/ananas.png");
		       Image imgg2 = img2.getImage() ;
		       Image newimg2 = imgg2.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img2 = new ImageIcon( newimg2 );
		       bstyl2.setIcon(img2);
		       bstyl2.setBounds(50, 100, 150, 150);
		       bstyl2.setBorder(null);
		       bstyl2.setFocusPainted(true);
		       bstyl2.setContentAreaFilled(false);
		       
		       bstyl2.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	   Garden.fridge.setVisible(false);
		        	   
		           }
		       });

		       JButton bstyl3 = new JButton("");
		       ImageIcon img3 = new ImageIcon("src/Grafika/burgee.png");
		       Image imgg3 = img3.getImage() ;
		       Image newimg3 = imgg3.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img3 = new ImageIcon( newimg3 );
		       bstyl3.setIcon(img3);
		       bstyl3.setBounds(395, 100, 150, 150);
		       bstyl3.setBorder(null);
		       bstyl3.setFocusPainted(true);
		       bstyl3.setContentAreaFilled(false);
		       
		       bstyl3.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	  Garden.fridge.setVisible(false);
		        	   
		           }
		       });
		       
		       JButton bstyl4 = new JButton("");
		       ImageIcon img4 = new ImageIcon("src/Grafika/croissant.png");
		       Image imgg4 = img4.getImage() ;
		       Image newimg4 = imgg4.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img4 = new ImageIcon( newimg4 );
		       bstyl4.setIcon(img4);
		       bstyl4.setBounds(50, 300, 150, 150);
		       bstyl4.setBorder(null);
		       bstyl4.setFocusPainted(true);
		       bstyl4.setContentAreaFilled(false);
		       
		       
		       bstyl4.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	   Garden.fridge.setVisible(false);
		        	   
		           }
		       });

		       JButton bstyl5 = new JButton("");
		       ImageIcon img5 = new ImageIcon("src/Grafika/pizza.png");
		       Image imgg5 = img5.getImage() ;
		       Image newimg5 = imgg5.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img5 = new ImageIcon( newimg5 );
		       bstyl5.setIcon(img5);
		       bstyl5.setBounds(225, 300, 150, 150);
		       bstyl5.setBorder(null);
		       bstyl5.setFocusPainted(true);
		       bstyl5.setContentAreaFilled(false);
		       
		       bstyl5.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	  Garden.fridge.setVisible(false);
		        	   
		           }
		       });
		       
		       JButton bstyl6 = new JButton("");
		       ImageIcon img6 = new ImageIcon("src/Grafika/hotdog.png");
		       Image imgg6 = img6.getImage() ;
		       Image newimg6 = imgg6.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img6 = new ImageIcon( newimg6 );
		       bstyl6.setIcon(img6);
		       bstyl6.setBounds(400, 300, 150, 150);
		       bstyl6.setBorder(null);
		       bstyl6.setFocusPainted(true);
		       bstyl6.setContentAreaFilled(false);
		       
		       bstyl6.addActionListener(new ActionListener(){
		           public void actionPerformed(ActionEvent e)
		           {
		        	   if(Garden.getCash()>=5)
		        	   {
		        		   Garden.setCash(Garden.getCash()-5);
		        		   if(PotatoMan.getHunger()<4)
		        		   {
		        			   PotatoMan.setHunger(PotatoMan.getHunger()+1);
		        		   }
		        	   }
		        	   
		        	   Garden.fridge.setVisible(false);
		        	   
		           }
		       });
		       
		       JLabel labelinfo = new JLabel("każdy posiłek kosztuje 5$");
		       labelinfo.setForeground(Color.WHITE);
		       labelinfo.setFont(new Font("monospaced", Font.BOLD, 30));
		       labelinfo.setBounds(60, 500, 500, 70);

		       panel1.add(labeltytulowy);
		       panel1.add(bstyl1);
		       panel1.add(bstyl2);
		       panel1.add(bstyl3);
		       panel1.add(bstyl4);
		       panel1.add(bstyl5);
		       panel1.add(bstyl6);
		       panel1.add(labelinfo);
	}
	
	
	}


