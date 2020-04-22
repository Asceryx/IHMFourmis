package jeuDesFourmis;

import jeuDesFourmis.ihm.QuitButton;
import jeuDesFourmis.ihm.anthill.mainframe.AnthillPanel;
import jeuDesFourmis.ihm.settings.AnthillSettingsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Main interface.</b>
 * <p>
 * This interface launch all elements and threads.
 * </p>
 */
public class main extends JFrame {
    /**
     * Constructor of main.
     *
     * @param title Title of frame.
     */
    public main(String title) {
        super(title);
        AnthillPanel runnable = new AnthillPanel(100, 100, 400);

        AnthillSettingsPanel panel = new AnthillSettingsPanel(runnable);
        QuitButton quit = new QuitButton();


        JPanel main = new JPanel(new BorderLayout());
        JPanel content = new JPanel(new BorderLayout());
        JPanel quitpanel = new JPanel();
        JPanel ahpanel = new JPanel();
        JPanel settingpanel = new JPanel();

        ahpanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        settingpanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        ahpanel.add(runnable);
        settingpanel.add(panel);

        content.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        content.setBorder(BorderFactory.createLineBorder(Color.RED));
        content.add(ahpanel, BorderLayout.WEST);
        content.add(settingpanel, BorderLayout.EAST);

        quitpanel.add(quit);
        main.add(content, BorderLayout.CENTER);
        main.add(quitpanel, BorderLayout.SOUTH);

        this.add(main);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 200));

        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        main frame = new main("Jeu des fourmis");
        frame.setLocationRelativeTo(frame.getParent());

    }
}