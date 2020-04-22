package jeuDesFourmis.ihm.settings.button;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Toggle Button to enable/disabled wen.</b>
 * <p>
 * When we clicking under this button, wen is activate and if we clicking a new time, wen is deactivate.
 * </p>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class WenButton extends JToggleButton {
    /**
     * Font of text button.
     */
    private final Font font = new Font("Serif", Font.BOLD, 14);

    /**
     * Constructor of WenButton.
     */
    public WenButton() {
        super("Loupe");
        this.setFont(font);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(80, 50));
    }

    /**
     * Getting status of toggle button, if it is selected or not.
     *
     * @return boolean.
     */
    public boolean getStatus() {
        return this.getModel().isSelected();
    }

}