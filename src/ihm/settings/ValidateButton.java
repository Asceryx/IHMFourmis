package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class ValidateButton extends JButton implements Deactivable {
    private Font font;

    public ValidateButton() {
        super();
        Box box=Box.createHorizontalBox();
        font=new Font("Serif",Font.BOLD,14);
        this.setText("Valider");
        this.setFont(font);
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