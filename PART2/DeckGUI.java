import javax.swing.*;
import java.awt.*;

public class DeckGUI {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JPanel panel1 = new JPanel();
    JButton playBtn = new JButton("Play");
    JButton resumeBtn = new JButton("Resume");
    JButton quitBtn = new JButton("Quit");
    
    public void Menu(){
        label.setText("Menu");
        label.setLocation(0,0);
        label.setSize(1200,200);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Times New Roman",Font.BOLD,100));
        
        //Set the title of the window and the size of the frame
        frame.setTitle("GO-BOOM!!");
        frame.setSize(1200,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        //Set the icon of the frame
        ImageIcon logo = new ImageIcon("Image/logo.png");
        frame.setIconImage(logo.getImage());
        
        //set panel
        panel1.add(label);
        panel1.setBounds(0,0,1200,700);
        frame.add(panel1);

        //set buttons of the panel
        panel1.setLayout(null);

        playBtn.setSize(100,50);
        playBtn.setLocation(550,300);

        resumeBtn.setSize(100,50);
        resumeBtn.setLocation(550,400);

        quitBtn.setSize(100,50);
        quitBtn.setLocation(550,500);
        
        panel1.add(playBtn);
        panel1.add(resumeBtn);
        panel1.add(quitBtn);
        
        //set the background color of the frame
        panel1.setBackground(new Color(0x095D0A));
        //frame.getContentPane().setBackground(new Color(0x095D0A));
        
        
        frame.setVisible(true);
    }


    DeckGUI(){
        Menu();
    }

    public static void main(String[] args) {
        DeckGUI deckGUI = new DeckGUI();
    }

}

