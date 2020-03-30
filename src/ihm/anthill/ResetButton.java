package ihm.anthill;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton implements ActionListener {

    private Grid grid;
    private Graphics2D graphicGrid;
    private boolean showGrid;
    private GridComponent panel;
    private StatLabel LabelAnt;
    private StatLabel LabelSeed;

    public ResetButton(GridComponent gridpanel, StatLabel nbSeed, StatLabel nbAnt) {
        super("Réinitialiser");
        this.panel = gridpanel;
        this.LabelAnt = nbAnt;
        this.LabelSeed = nbSeed;
        this.setFocusable(false);
        addActionListener(this);
    }



    public void reset() {
        this.panel.reset();
        int nbAnt = this.panel.getGrid().getTotalAnt();
        int nbSeed = this.panel.getGrid().getTotalSeed();
        this.LabelAnt.setTextValue(nbAnt);
        this.LabelSeed.setTextValue(nbSeed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reset();
    }
}
