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
            this.anthill.getGc().reset();
            this.anthill.generation(this.form.getProbaFourmis(),this.form.getProbaGraines(),this.form.getProbaMurs());
            this.form.activate();
        }
    }

    public static void main(String []args) {
        JFrame frame = new JFrame("test");
        AnthillSettingsPanel ap = new AnthillSettingsPanel(null);
        JPanel p = new JPanel();
        p.add(ap);
        frame.add(p);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}