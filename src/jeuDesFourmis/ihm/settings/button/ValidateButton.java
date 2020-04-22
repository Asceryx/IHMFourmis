package jeuDesFourmis.ihm.settings.button;

import jeuDesFourmis.ihm.settings.Deactivable;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Button to validate form.</b>
 * <p>
 * This button allowing to send settings to anthill.
 * </p>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class ValidateButton extends JButton implements Deactivable {
    /**
     * Font of text in button.
     */
    private final Font font = new Font("Serif", Font.BOLD, 14);

    /**
     * Constructor of button.
     */
    public ValidateButton() {
        super("Valider");
        Box box = Box.createHorizontalBox();
        this.setFont(this.font);
        this.setFocusable(false);
        box.add(this);
    }

    /**
     * User can interact with the button.
     */
    @Override
    public void activate() {
        this.setEnabled(true);
    }

    /**
     * User can't interact with the button.
     */
    @Override
    public void deactivate() {
        this.setEnabled(false);
    }

}