package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class WenButton extends JToggleButton {
    private final Font font = new Font("Serif", Font.BOLD, 14);


    public WenButton()
    {
        super("Loupe");
        this.setFont(font);
        this.setFocusable(false);
    }


    public boolean getStatus() {
        return this.getModel().isSelected();
    }

}