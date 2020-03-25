package ihm.anthill;

import javax.swing.*;
import java.awt.BorderLayout;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class StatPanel extends JPanel {
    private StatLabel nbAnt;
    private StatLabel nbSeed;
    private JPanel panelLabel;
    private JPanel panelButton;
    private ResetButton reset;

    public StatPanel(GridComponent gridpanel) {
        panelLabel = new JPanel();
        panelButton = new JPanel();
        panelLabel.setLayout(new BoxLayout(panelLabel,BoxLayout.PAGE_AXIS));
        panelButton.setLayout(new BoxLayout(panelButton,BoxLayout.LINE_AXIS));

        nbSeed = new StatLabel("Nombre de graine");
        nbAnt = new StatLabel("Nombre de fourmi");
        panelLabel.add(nbSeed);
        panelLabel.add(nbAnt);

        reset = new ResetButton(gridpanel,nbSeed,nbAnt);
        panelButton.add(reset,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(panelLabel,BorderLayout.WEST);
        this.add(panelButton,BorderLayout.EAST);
    }

    public StatLabel getNbAnt()
    {
        return nbAnt;
    }

    public StatLabel getNbSeed()
    {
        return nbSeed;
    }


    public static void main (String [] args) {
        JFrame frame = new JFrame("Test StatPanel");
        GridComponent g = new GridComponent(300,300);
        g.generation(30,30,30);
        frame.add(new StatPanel(g));
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
