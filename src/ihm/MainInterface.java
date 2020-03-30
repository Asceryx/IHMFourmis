package ihm;

import ihm.anthill.AnthillPanel;
import ihm.settings.AnthillSettingsPanel;

import javax.swing.*;
import java.awt.*;

public class MainInterface extends JFrame {
    public MainInterface(String title){
        super(title);
        Runnable runnable = new AnthillPanel(11,11,100);

        AnthillPanel ah = (AnthillPanel)runnable;
        AnthillSettingsPanel panel=new AnthillSettingsPanel(ah);


        Box box=Box.createHorizontalBox();
        box.add(Box.createGlue());
        QuitButton quit=new QuitButton();
        box.add(quit);
        box.add(Box.createGlue());
        this.add(box, BorderLayout.SOUTH);

        this.add(ah,BorderLayout.WEST);
        this.add(panel,BorderLayout.EAST);

        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200,200));



    }

    public static void main (String [] args) {
        MainInterface frame = new MainInterface("test");


        frame.pack() ;
        frame.setVisible(true);
    }
}