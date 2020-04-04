package ihm.settings;

import ihm.anthill.AnthillPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WenButton extends JToggleButton implements ActionListener {
    private Font font;
    private String text;
    private AnthillPanel anthill;


    public WenButton(AnthillPanel anthill)
    {
        super();
        this.anthill =anthill;
        Box box =Box.createHorizontalBox();
        font=new Font("Serif",Font.BOLD,14);
        this.setFont(font);
        this.setText("Loupe");
        this.text=text;
        this.setContentAreaFilled(false);
        addActionListener(this);
        box.add(this);
        this.setFocusable(false);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        boolean selected = this.getModel().isSelected();

        System.out.println("Action - selected=" + selected + "\n");
        if (selected){
            anthill.getGc().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            this.setForeground(Color.green);
        }
        else{
            anthill.getGc().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            this.setForeground(Color.black);
        }

    }
}