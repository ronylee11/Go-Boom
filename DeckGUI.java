import javax.swing.*;
import java.awt.*;

public class DeckGUI {

    private void buildFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("GO-BOOM!!");
        frame.setSize(1200,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon logo = new ImageIcon("Image/logo.png");
        frame.setIconImage(logo.getImage());

        frame.getContentPane().setBackground(new Color(0x095D0A));
        

    }

    DeckGUI(){
        buildFrame();
    }

    public static void main(String[] args) {
        DeckGUI deckGUI = new DeckGUI();
    }

}

