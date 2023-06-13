package MojOgrodek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static MojOgrodek.Main.startSaveGame;

public class InGameMenuFrame extends JFrame {

    public InGameMenuFrame()
    {
    	this.setTitle("Menu");
        int width = 400;
        int height = 500;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setLayout(new BorderLayout());

        JPanel panel1 =  new GradientPanel();
        add(BorderLayout.CENTER, panel1);

        JLabel label1 = new JLabel("MENU");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("monospaced", Font.BOLD, 40));
        label1.setBounds(145, 10, 150, 100);

        JButton button1 = new RoundRectButton("WZNÓW");
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("monospaced", Font.BOLD, 18));
        button1.setBounds(95,100,200,50);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton button2 = new RoundRectButton("MUZYKA");
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("monospaced", Font.BOLD, 18));
        button2.setBounds(95,170,200,50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MusicFrame musicFrame = new MusicFrame();
                musicFrame.showMusicFrame();
            }

        });

        JButton button3 = new RoundRectButton("ZAPISZ GRĘ");
        button3.setForeground(Color.WHITE);
        button3.setFont(new Font("monospaced", Font.BOLD, 18));
        button3.setBounds(95,240,200,50);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	startSaveGame();
            }

        });

        JButton button4 = new RoundRectButton("WYJDŹ Z GRY");
        button4.setForeground(Color.WHITE);
        button4.setFont(new Font("monospaced", Font.BOLD, 18));
        button4.setBounds(95,310,200,50);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        
        panel1.setLayout(null);

        panel1.setVisible(true);
        panel1.add(label1);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);


    }

    public static void main(String[] args) {
        InGameMenuFrame frame = new InGameMenuFrame();
        frame.setVisible(true);
    }
}