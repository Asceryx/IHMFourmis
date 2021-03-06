package jeuDesFourmis.ihm.settings;

import jeuDesFourmis.ihm.anthill.mainframe.AnthillPanel;
import jeuDesFourmis.ihm.settings.button.PlayButton;
import jeuDesFourmis.ihm.settings.button.ValidateButton;
import jeuDesFourmis.ihm.settings.button.WenButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>Panel for settings.</b>
 * Panel to set up and interact the elements of the anthill :
 * <ul>
 *     <li>Running/Stopping the animation.</li>
 *     <li>Zooming in anthill.</li>
 *     <li>Getting and apply settings in the anthill.</li>
 * </ul>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class AnthillSettingsPanel extends JPanel implements ActionListener {
    /**
     * Button to run the animation.
     */
    private final PlayButton play;
    /**
     * Button to activate a zoom.
     */
    private final WenButton loupe;
    /**
     * Form to set up the anthill (size, number of ants...)
     */
    private final SettingsForm form;
    /**
     * Validate button to get and apply setting up of anthill.
     */
    private final ValidateButton valider;
    /**
     * anthill object.
     */
    private final AnthillPanel anthill;

    /**
     * Constructor of AnthillSettingsPanel.
     *
     * @param anthill Current anthill panel.
     */
    public AnthillSettingsPanel(AnthillPanel anthill) {
        super();
        this.play = new PlayButton(anthill);
        this.valider = new ValidateButton();
        this.loupe = new WenButton();
        this.form = new SettingsForm();
        this.form.setMapSize(anthill.getGc().getGridHeight());
        this.anthill = anthill;

        this.valider.addActionListener(this);
        this.play.addActionListener(this);
        this.loupe.addActionListener(this);

        Box horizPLay = Box.createHorizontalBox();
        Box horizLoupe = Box.createHorizontalBox();
        Box horizValider = Box.createHorizontalBox();

        horizPLay.add(play, BorderLayout.CENTER);
        horizValider.add(valider, BorderLayout.CENTER);
        horizLoupe.add(loupe, BorderLayout.CENTER);

        Box box = Box.createVerticalBox();
        box.add(horizPLay);
        box.add(horizLoupe);
        box.add(form);
        box.add(horizValider);
        this.add(box);
    }

    /**
     * Methods calling when animation is being to run.
     */
    private void playEvent() {
        if (this.play.isPlaying()) {
            this.form.deactivate();
            this.valider.deactivate();
        } else {
            this.form.activate();
            this.valider.activate();
        }
    }

    /**
     * Methods calling when we validate settings
     */
    private void validateEvent() {
        this.form.deactivate();
        this.anthill.resize(this.form.getSizeMap());
        this.anthill.generation(this.form.getProbaGraines(), this.form.getProbaFourmis(), this.form.getProbaMurs());
        this.form.activate();
    }

    /**
     * Methods calling when we use wen.
     */
    private void loupeEvent() {
        boolean selected = this.loupe.getStatus();
        if (selected) {
            anthill.getGc().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            anthill.getGc().deactivate();
            anthill.getGc().zoomActivate();
            this.loupe.setForeground(Color.gray);
        } else {
            anthill.getGc().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            if (!(anthill.isRunning())) {
                anthill.getGc().activate();
            }
            anthill.getGc().zoomDeactivate();
            this.loupe.setForeground(Color.black);
        }
    }

    /**
     * Event of different button.
     * <ul>
     *     <li>for play/pause button : running/pause animation.</li>
     *     <li>for validate button : apply, with confirmation, modification on anthill.</li>
     *     <li>for wen button : zooming in area</li>
     * </ul>
     *
     * @param e event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.play) {
            this.playEvent();
        } else if (e.getSource() == this.valider) {
            int validate = JOptionPane.showConfirmDialog(null, "Voulez changer les paramètres ? ", null, JOptionPane.YES_NO_OPTION);
            if (validate == 0)
                this.validateEvent();
        } else if (e.getSource() == this.loupe) {
            this.loupeEvent();
        }
    }
}