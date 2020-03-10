package ihm.anthill;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton implements ActionListener {
    private Grid grid;
    private Graphics2D graphicGrid;
    private boolean showGrid;
    private GridPanel panel;
    private StatLabel LabelAnt;
    private StatLabel LabelSeed;

    public ResetButton(GridPanel gridpanel, StatLabel nbSeed, StatLabel nbAnt) {
        super("Réinitialiser");
        this.panel = gridpanel;
        this.LabelAnt = nbAnt;
        this.LabelSeed = nbSeed;
        addActionListener(this);
    }



    public void reset() {
        this.panel.resetPanel();
        int nbAnt = this.panel.getAntHill().getTotalAnt();
        int nbSeed = this.panel.getAntHill().getTotalSeed();
        this.LabelAnt.setTextValue(nbAnt);
        this.LabelSeed.setTextValue(nbSeed);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reset();
    }
}