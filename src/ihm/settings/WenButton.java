package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class WenButton extends JButton {
    private Font font;
    private String text;
    public WenButton(String text)
    {
        super();
        Box box =Box.createHorizontalBox();
        font=new Font("Serif",Font.ITALIC,14);
        this.setFont(font);
        this.setText(text);
        this.text=text;
        box.add(this);
    }
}