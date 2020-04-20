package ihm.anthill.mainframe.gui.button;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Reset custom button.</b>
 */
public class ResetButton extends JButton {
    private final Font fontTitle = new Font("Serif", Font.BOLD, 14);

    public ResetButton() {
        super("Réinitialiser");
        this.setFont(this.fontTitle);
        this.setFocusable(false);
    }
}
