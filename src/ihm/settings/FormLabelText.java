package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class FormLabelText extends JLabel {
    public Font font ;
    private String text ;
    public FormLabelText(String text)
    {
        super();
        font=new Font("Serif",Font.BOLD,14);
        this.setFont(font);
        this.setText(text);

    }
}