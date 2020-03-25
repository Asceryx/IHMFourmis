package ihm.settings;

import javax.swing.*;
import java.awt.*;

// First commit wadie
public class ValidateButton extends JButton implements Deactivatable {
    /* public JButton valider;*/
     private Font font;

     public ValidateButton()
     {
         super();
         Box box=Box.createHorizontalBox();
        /*valider =new JButton("Valider");*/
        font=new Font("Serif",Font.ITALIC,14);
        this.setText("Valider");
        this.setFont(font);
        box.add(this);

     }


    @Override
    public void setEditable(boolean b) {

    }
}