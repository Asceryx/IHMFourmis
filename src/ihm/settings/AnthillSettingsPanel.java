package ihm.settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnthillSettingsPanel extends JPanel implements ActionListener {
    public PlayButton play;
    public WenButton loupe;
    public SettingsForm form ;
    public ValidateButton valider;
    public AnthillSettingsPanel()
    {
        super();
        play=new PlayButton();
        valider=new ValidateButton();
        loupe=new WenButton("loupe");

        Box horizPLay=Box.createHorizontalBox();
        Box horizLoupe=Box.createHorizontalBox();
        Box horizValider=Box.createHorizontalBox();

        horizPLay.add(play,BorderLayout.CENTER);
        horizValider.add(valider,BorderLayout.CENTER);
        horizLoupe.add(loupe,BorderLayout.CENTER);
        form=new SettingsForm();
        Box box=Box.createVerticalBox();
  //  box.add(Box.createGlue());
        box.add(horizPLay);
        box.add(horizLoupe);
        box.add(form);
        box.add(horizValider);
    //    box.add(Box.createGlue());
       // this.setLayout(gr);
        this.add(box);
    }
    public static void main(String [] args)
    {
        JFrame frame=new JFrame("test");
        AnthillSettingsPanel panel=new AnthillSettingsPanel();
        frame.add(panel,BorderLayout.EAST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setPreferredSize(new Dimension(400,400));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==valider)
        {
            form.setEnabled(false);
        }
    }
}