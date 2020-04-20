package ihm.anthill.mainframe;

import ihm.anthill.mainframe.gui.button.ResetButton;
import ihm.anthill.mainframe.gui.component.GridComponent;
import ihm.anthill.mainframe.gui.label.StatLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatPanel extends JPanel implements ActionListener {

    private final StatLabel LabelnbAnt;
    private final StatLabel LabelnbSeed;
    private final ResetButton reset;
    private final GridComponent gridpanel;

    public StatPanel(GridComponent gridpanel) {
        this.gridpanel = gridpanel;

        JPanel panelLabel = new JPanel();
        JPanel panelButton = new JPanel();
        reset = new ResetButton();

        panelLabel.setLayout(new BoxLayout(panelLabel, BoxLayout.PAGE_AXIS));
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.LINE_AXIS));

        LabelnbSeed = new StatLabel("Nombre de graine");
        LabelnbAnt = new StatLabel("Nombre de fourmi");

        panelLabel.add(LabelnbSeed);
        panelLabel.add(LabelnbAnt);
        panelButton.add(reset, BorderLayout.CENTER);

        reset.addActionListener(this);


        this.setLayout(new BorderLayout());
        this.add(panelLabel, BorderLayout.WEST);
        this.add(panelButton, BorderLayout.EAST);
    }

    public StatLabel getNbAnt() {
        return LabelnbAnt;
    }

    public StatLabel getNbSeed() {
        return LabelnbSeed;
    }

    public void reset() {
        this.gridpanel.reset();
        int nbAnt = this.gridpanel.getGrid().getTotalAnt();
        int nbSeed = this.gridpanel.getGrid().getTotalSeed();
        this.LabelnbAnt.setTextValue(nbAnt);
        this.LabelnbSeed.setTextValue(nbSeed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            int confirmReset = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment réinitialiser la fourmillière  ? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmReset == 0) {
                reset();
            }
        }
    }
}
