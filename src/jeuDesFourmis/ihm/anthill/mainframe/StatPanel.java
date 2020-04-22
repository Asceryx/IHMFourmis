package jeuDesFourmis.ihm.anthill.mainframe;

import jeuDesFourmis.ihm.anthill.mainframe.gui.button.ResetButton;
import jeuDesFourmis.ihm.anthill.mainframe.gui.component.GridComponent;
import jeuDesFourmis.ihm.anthill.mainframe.gui.label.StatLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>This panel contains all the elements summarizing actions done on the grid.</b>
 * This panel summarize:
 *     <ul>
 *         <li>A label to sum up the number of ant present in the grid.</li>
 *         <li>A label to sum up the number of seed present in the grid.</li>
 *         <li>A button to reset the grid.</li>
 *         <li>The grid which represents the anthill.</li>
 *     </ul>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see GridComponent
 * @see StatLabel
 * @see ResetButton
 */
public class StatPanel extends JPanel implements ActionListener {
    /**
     * Label to sum up the number of ant.
     */
    private final StatLabel LabelnbAnt;
    /**
     * Label to sum up the number of seed.
     */
    private final StatLabel LabelnbSeed;
    /**
     * Reset button of grid.
     */
    private final ResetButton reset;
    /**
     * The grid component represents the anthill.
     */
    private final GridComponent gridpanel;

    /**
     * Constructor of StatPanel.
     * @param gridpanel
     *      The current grid component which represents the anthill.
     */
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

    /**
     * Get the label which summarize the number of ant.
     *
     * @return StatLabel object.
     */
    public StatLabel getNbAnt() {
        return LabelnbAnt;
    }

    /**
     * Get the label which summarize the number of seed.
     *
     * @return StatLabel object.
     */
    public StatLabel getNbSeed() {
        return LabelnbSeed;
    }

    /**
     * Reset the grid, and the number of ant and seed in StatLabel.
     */
    public void reset() {
        this.gridpanel.reset();
        int nbAnt = this.gridpanel.getGrid().getTotalAnt();
        int nbSeed = this.gridpanel.getGrid().getTotalSeed();
        this.LabelnbAnt.setTextValue(nbAnt);
        this.LabelnbSeed.setTextValue(nbSeed);
    }

    /**
     * Confirm the action to reset, and reset if yes.
     *
     * @param e event.
     */
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
