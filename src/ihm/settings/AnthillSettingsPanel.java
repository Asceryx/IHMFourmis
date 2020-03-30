package ihm.settings;

import ihm.anthill.AnthillPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnthillSettingsPanel extends JPanel implements ActionListener{
    private PlayButton play;
    private WenButton loupe;
    private SettingsForm form ;
    private ValidateButton valider;
    private AnthillPanel anthill;
    public AnthillSettingsPanel(AnthillPanel anthill)
    {
        super();
        this.play=new PlayButton(anthill);
        this.valider=new ValidateButton();
        this.loupe=new WenButton("loupe");
        this.form=new SettingsForm();
        this.anthill = anthill;

        this.valider.addActionListener(this);
        this.play.addActionListener(this);

        Box horizPLay=Box.createHorizontalBox();
        Box horizLoupe=Box.createHorizontalBox();
        Box horizValider=Box.createHorizontalBox();

        horizPLay.add(play,BorderLayout.CENTER);
        horizValider.add(valider,BorderLayout.CENTER);
        horizLoupe.add(loupe,BorderLayout.CENTER);

        Box box=Box.createVerticalBox();
        box.add(horizPLay);
        box.add(horizLoupe);
        box.add(form);
        box.add(horizValider);
        this.add(box);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.play) {
            this.form.setEditable(!(this.play.isPlaying()));
            this.valider.setEditable(!(this.play.isPlaying()));
        }
        else if(e.getSource() == this.valider){
            this.form.setEditable(false);
            this.anthill.getGc().reset();
            this.anthill.generation(this.form.getVal1(),this.form.getVal2(),this.form.getVal3());
            this.form.setEditable(true);
        }
    }
}