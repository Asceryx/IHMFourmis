package ihm.settings;

import ihm.anthill.AnthillPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WenButton extends JToggleButton {
    private final Font font =new Font("TimesRoman", Font.BOLD,14);
    private AnthillPanel anthill;


    public WenButton()
    {
        super();
        this.anthill =anthill;
        this.setFont(font);
        this.setText("Loupe");
        this.setContentAreaFilled(false);
        this.setFocusable(false);
    }


    public boolean getStatus() {
        return this.getModel().isSelected();
    }

}