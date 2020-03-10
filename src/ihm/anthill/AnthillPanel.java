package ihm.anthill;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AnthillPanel extends JPanel {
    private GridPanel antHillPanel;
    private StatPanel statPanel;
    private int width;
    private int height;

    public AnthillPanel(int width, int height){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        antHillPanel =  new GridPanel(width,height,20,20);
        int totalAnt = antHillPanel.getAntHill().getTotalAnt();
        int totalSeed = antHillPanel.getAntHill().getTotalSeed();

        statPanel = new StatPanel(antHillPanel);
        statPanel.getNbAnt().setTextValue(totalAnt);
        statPanel.getNbSeed().setTextValue(totalSeed);



        this.add(antHillPanel);
        this.add(statPanel);
        this.width = width;
        this.height = height;
    }
    public Dimension getPreferredSize() {
        return new Dimension(this.width,this.height );
    }


    public static void main (String [] args) {
        JFrame frame = new JFrame("Test Anthil");
        AnthillPanel ap =  new AnthillPanel(200,200);
        frame.setPreferredSize(new Dimension(400,300));
        frame.add(ap);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}