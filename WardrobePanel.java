package MojOgrodek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static MojOgrodek.PotatoMan.changeKajtek;

public class WardrobePanel extends JFrame {

	public BufferedImage image7, image4, image3, image0, kajtek1, kajtek4, image8, image11, image15, image12;
	
	public WardrobePanel()
	{
		       int width = 600;
		       int height = 550;
		       this.setSize(width, height);
		       this.setLocationRelativeTo(null);
		       //this.setBackground(new Color(0,0,0,0));
		       this.setResizable(false);
		       //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		       setLayout(new BorderLayout());
		       getImages();

		       GradientPanel panel1 = new GradientPanel();
		       panel1.setBackground(Color.GREEN.darker().darker());
		       panel1.setLayout(null);
		       add(BorderLayout.CENTER, panel1);

		       JLabel labeltytulowy = new JLabel("Wybór stroju");
		       labeltytulowy.setForeground(Color.WHITE);
		       labeltytulowy.setFont(new Font("monospaced", Font.BOLD, 35));
		       labeltytulowy.setBounds(150, 10, 400, 70);
		       
		       JButton bstyl1 = new JButton("");
		       ImageIcon img1 = new ImageIcon("src/Grafika/image3.png");
		       Image imgg = img1.getImage() ;
		       Image newimg = imgg.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img1 = new ImageIcon( newimg );
		       bstyl1.setIcon(img1);
		       bstyl1.setBounds(225, 100, 150, 150);
		       bstyl1.setBorder(null);
		       bstyl1.setFocusPainted(true);
		       bstyl1.setContentAreaFilled(false);
		       bstyl1.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                changeKajtek(image3, image0);
		                City.changeProfile(image3);
		                revalidate();
		            }
		        });
		       
		       JButton bstyl2 = new JButton("");
		       ImageIcon img2 = new ImageIcon("src/Grafika/kajtek1.png");
		       Image imgg2 = img2.getImage() ;
		       Image newimg2 = imgg2.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img2 = new ImageIcon( newimg2 );
		       bstyl2.setIcon(img2);
		       bstyl2.setBounds(50, 100, 150, 150);
		       bstyl2.setBorder(null);
		       bstyl2.setFocusPainted(true);
		       bstyl2.setContentAreaFilled(false);
		       bstyl2.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                changeKajtek(kajtek1, kajtek4);
		                City.changeProfile(kajtek1);
		                revalidate();
		            }
		        });

		       JButton bstyl3 = new JButton("");
		       ImageIcon img3 = new ImageIcon("src/Grafika/image7.png");
		       Image imgg3 = img3.getImage() ;
		       Image newimg3 = imgg3.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img3 = new ImageIcon( newimg3 );
		       bstyl3.setIcon(img3);
		       bstyl3.setBounds(395, 100, 150, 150);
		       bstyl3.setBorder(null);
		       bstyl3.setFocusPainted(true);
		       bstyl3.setContentAreaFilled(false);
		       bstyl3.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                changeKajtek(image7, image4);
		                City.changeProfile(image7);
		                revalidate();
		            }
		        });
		       
		       JButton bstyl4 = new JButton("");
		       ImageIcon img4 = new ImageIcon("src/Grafika/image8.png");
		       Image imgg4 = img4.getImage() ;
		       Image newimg4 = imgg4.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img4 = new ImageIcon( newimg4 );
		       bstyl4.setIcon(img4);
		       bstyl4.setBounds(50, 300, 150, 150);
		       bstyl4.setBorder(null);
		       bstyl4.setFocusPainted(true);
		       bstyl4.setContentAreaFilled(false);
		       bstyl4.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                changeKajtek(image8, image11);
		                City.changeProfile(image8);
		                revalidate();
		            }
		        });

		       JButton bstyl5 = new JButton("");
		       ImageIcon img5 = new ImageIcon("src/Grafika/image15.png");
		       Image imgg5 = img5.getImage() ;
		       Image newimg5 = imgg5.getScaledInstance( 150, 150,  java.awt.Image.SCALE_SMOOTH ) ;
		       img5 = new ImageIcon( newimg5 );
		       bstyl5.setIcon(img5);
		       bstyl5.setBounds(225, 300, 150, 150);
		       bstyl5.setBorder(null);
		       bstyl5.setFocusPainted(true);
		       bstyl5.setContentAreaFilled(false);
		       bstyl5.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                changeKajtek(image15, image12);
		                City.changeProfile(image15);
		                revalidate();
		            }
		        });

		       panel1.add(labeltytulowy);
		       panel1.add(bstyl1);
		       panel1.add(bstyl2);
		       panel1.add(bstyl3);
		       panel1.add(bstyl4);
		       panel1.add(bstyl5);
	}
	
	public void getImages()
	{
		try {
			image4 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image4.png"));
			image7 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image7.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image3.png"));
			image0 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image0.png"));
			kajtek1 = ImageIO.read(getClass().getResourceAsStream("/Grafika/kajtek1.png"));
			kajtek4 = ImageIO.read(getClass().getResourceAsStream("/Grafika/kajtek4.png"));
			image8 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image8.png"));
			image11 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image11.png"));
			image15 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image15.png"));
			image12 = ImageIO.read(getClass().getResourceAsStream("/Grafika/image12.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        WardrobePanel frame = new WardrobePanel();
        frame.setVisible(true);
    }
}
