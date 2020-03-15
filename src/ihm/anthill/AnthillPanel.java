package ihm.anthill;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AnthillPanel extends JPanel {

    public AnthillPanel(int width, int height, int probSeed, int probAnt, int probWall) {
        super();
        JPanel panel = new JPanel();
        this.setLayout(new BorderLayout());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(panel,BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridComponent gc = new GridComponent(100,100);
        gc.generation(20,20,20);
        Grid g = gc.getGrid();


        panel.add(gc);
        StatPanel sp = new StatPanel(gc);
        this.add(sp, BorderLayout.SOUTH);

        sp.getNbSeed().setTextValue(g.getTotalSeed());
        sp.getNbAnt().setTextValue(g.getTotalAnt());
    }



    public static void main (String [] args) {
        JFrame frame = new JFrame("Test Anthil");
        AnthillPanel ap =  new AnthillPanel(200,200,30, 30, 30);

        frame.add(ap);

        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200,200));
        frame.pack() ;
        frame.setVisible(true);
    }
}
