package MojOgrodek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static javax.swing.SwingConstants.CENTER;

public class OpeningFrame extends JFrame
{
	OpeningFrame()
    {
        int width = 1080;
        int height = 720;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel panelCenter = new GradientPanel();

        add(BorderLayout.CENTER, panelCenter);
        
        JLabel authors = new JLabel("Autorzy: Kamil Duszak, Joanna Kurzeja");
        authors.setFont(new Font("monospaced", Font.BOLD, 24));
        authors.setBounds(520,620,560,40);
        panelCenter.add(authors);

        JLabel label1 = new JLabel("MÓJ OGRÓDEK", CENTER);
        label1.setForeground(new Color(0,76,153));
        label1.setFont(new Font("monospaced", Font.BOLD, 80));
        label1.setBounds(0,100,1080,100);
        panelCenter.add(label1);

        JButton button1 = new RoundRectButton("NOWA GRA");
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("monospaced", Font.BOLD, 30));
        button1.setBounds(150,250,380,140);
        panelCenter.add(button1);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChooseCity chooseCity = new ChooseCity();
                chooseCity.setVisible(true);
                setVisible(false);
            }

        });

        JButton button2 = new RoundRectButton("WCZYTAJ GRĘ");
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("monospaced", Font.BOLD, 30));
        button2.setBounds(540,250,380,140);
        panelCenter.add(button2);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LoadFrame load = new LoadFrame();
            	load.setVisible(true);
            	setVisible(false);
            }

        });

        JButton button3 = new RoundRectButton("MUZYKA" );
        button3.setForeground(Color.WHITE);
        button3.setFont(new Font("monospaced", Font.BOLD, 30));
        button3.setBounds(150,430,380,140);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MusicFrame musicFrame = new MusicFrame();
                musicFrame.showMusicFrame();
            }

        });
        panelCenter.add(button3);

        JButton button4 = new RoundRectButton("WYJDŹ Z GRY" );
        button4.setForeground(Color.WHITE);
        button4.setFont(new Font("monospaced", Font.BOLD, 30));
        button4.setBounds(540,430,380,140);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panelCenter.add(button4);

        panelCenter.setLayout(null);
        panelCenter.setVisible(true);

    }

    static public Music music;
    public static void main(String[] args) {
        music = new Music();
        
        music.setFile(OpeningFrame.class.getResource("track.wav").getPath());
        try {
            music.play();
        }
        catch (IOException e)
        {

        }

        OpeningFrame frame2 = new OpeningFrame();
        frame2.setLocationRelativeTo(null);

        frame2.setVisible(true);
    }


}
