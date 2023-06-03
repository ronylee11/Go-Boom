import javax.swing.*;
import java.awt.*;

public class DeckGUI {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JPanel panel1 = new JPanel();
    public void Menu(){
        label.setText("Menu");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Times New Roman",Font.BOLD,100));
        frame.add(label);
        //Set the title of the window and the size of the frame
        frame.setTitle("GO-BOOM!!");
        frame.setSize(1200,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set the icon of the frame
        ImageIcon logo = new ImageIcon("Image/logo.png");
        frame.setIconImage(logo.getImage());
        
        //set the background color of the frame
        frame.getContentPane().setBackground(new Color(0x095D0A));
        
        
        frame.setVisible(true);
    }


    DeckGUI(){
        Menu();
    }

    public static void main(String[] args) {
        DeckGUI deckGUI = new DeckGUI();
    }

}

