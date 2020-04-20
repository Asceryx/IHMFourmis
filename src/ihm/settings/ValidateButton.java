package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class ValidateButton extends JButton implements Deactivable {
    private final Font font = new Font("Serif", Font.BOLD, 14);

    public ValidateButton() {
        super("Valider");
        Box box=Box.createHorizontalBox();
        this.setFont(this.font);
        this.setFocusable(false);
        box.add(this);
    }

    @Override
    public void activate() {
        this.setEnabled(true);
    }

    @Override
    public void deactivate() {
        this.setEnabled(false);
    }

}