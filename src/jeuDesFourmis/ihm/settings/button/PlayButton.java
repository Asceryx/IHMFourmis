package jeuDesFourmis.ihm.settings.button;

import jbutton.ButtonIcon;
import jeuDesFourmis.ihm.anthill.mainframe.AnthillPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * <b>Button to play/pause animation</b>
 * <p>
 * By default, button is pause. When we clicking on button, animation is playing, when we clicking another time in
 * button, animation is pause.
 * </p>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class PlayButton extends JButton implements ActionListener {
    /**
     * Icon of start.
     */
    private ButtonIcon start;
    /**
     * Icon of stop.
     */
    private ButtonIcon pause;
    /**
     * Anthill object.
     */
    private AnthillPanel anthill;
    /**
     * Play/pause animation.
     */
    private boolean playing;

    /**
     * Constructor of PlayButton.
     *
     * @param anthill Anthill object.
     */
    public PlayButton(AnthillPanel anthill) {
        super();
        this.anthill = anthill;
        this.playing = true;
        this.start = new ButtonIcon(ButtonIcon.IconShape.Start, 25, 25);
        this.pause = new ButtonIcon(ButtonIcon.IconShape.Pause, 25, 25);
        this.setIcon(start);
        this.setText("Start ");
        this.setFocusable(false);
        addActionListener(this);
    }

    /**
     * Changing status of button (play/pause).
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.playing) {
            this.setIcon(this.pause);
            this.setText("Pause");
            this.anthill.getGc().setHide();
            this.anthill.getGc().deactivate();
            this.anthill.pause(this.playing);
            this.playing = false;
        } else {
            this.setIcon(start);
            this.setText("Start");
            this.anthill.pause(this.playing);
            this.anthill.getGc().setShow();
            this.anthill.getGc().activate();
            this.playing = true;
        }

    }

    /**
     * Getting if button is play or pause.
     *
     * @return Boolean.
     */
    public boolean isPlaying() {
        return playing;
    }
}