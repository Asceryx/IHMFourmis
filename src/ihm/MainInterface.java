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
        AnthillSettingsPanel panel=new AnthillSettingsPanel(this, ah);
        QuitButton quit=new QuitButton();


        JPanel main = new JPanel(new BorderLayout());
        JPanel content = new JPanel(new BorderLayout());
        JPanel quitpanel = new JPanel();
        JPanel ahpanel = new JPanel();
        JPanel settingpanel = new JPanel();

        ahpanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        settingpanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        ahpanel.add(ah);
        settingpanel.add(panel);

        content.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        content.setBorder(BorderFactory.createLineBorder(Color.RED));
        content.add(ahpanel,BorderLayout.WEST);
        content.add(settingpanel,BorderLayout.EAST);

        quitpanel.add(quit);
        main.add(content,BorderLayout.CENTER);
        main.add(quitpanel, BorderLayout.SOUTH);

        this.add(main);


        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        //this.setMinimumSize(new Dimension(200,200));

        this.pack() ;
        this.setVisible(true);
    }

    public static void main (String [] args) {
        MainInterface frame = new MainInterface("test");

    }
}