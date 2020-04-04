package ihm.settings;

import ihm.anthill.AnthillPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnthillSettingsPanel extends JPanel implements ActionListener {
    private PlayButton play;
    private WenButton loupe;
    private SettingsForm form ;
    private ValidateButton valider;
    private AnthillPanel anthill;
    private JFrame frame;
    public AnthillSettingsPanel(JFrame frame, AnthillPanel anthill)
    {
        super();
        this.frame = frame;
        this.play=new PlayButton(anthill);
        this.valider=new ValidateButton();
        this.loupe=new WenButton(anthill);
        this.form=new SettingsForm();
        this.form.setMapSize(anthill.getGc().getGridHeigth());
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
            if (this.play.isPlaying()){
                this.form.deactivate();
                this.valider.deactivate();
            }
            else{
                this.form.activate();
                this.valider.activate();
            }

        }
        else if(e.getSource() == this.valider){
            this.form.deactivate();
            this.anthill.resize(this.form.getSizeMap());
            this.anthill.generation(this.form.getProbaFourmis(),this.form.getProbaGraines(),this.form.getProbaMurs());
            this.form.activate();
        }
    }
}