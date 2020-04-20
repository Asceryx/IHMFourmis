package ihm.settings;

import ihm.anthill.mainframe.AnthillPanel;
import jbutton.ButtonIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PlayButton extends JButton implements ActionListener {
    private ButtonIcon start ;
    private ButtonIcon pause;
    private AnthillPanel anthill;
    private boolean playing;

    public PlayButton(AnthillPanel anthill) {
        super();
        this.anthill = anthill;
        this.playing=true;
        this.start=new ButtonIcon(ButtonIcon.IconShape.Start,25,25);
        this.pause=new ButtonIcon(ButtonIcon.IconShape.Pause,25,25);
        this.setIcon(start);
        this.setText("Start ");
        this.setFocusable(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.playing) {
            this.setIcon(this.pause);
            this.setText("Pause");
            this.anthill.getGc().setHide();
            this.anthill.getGc().deactivate();
            this.anthill.pause(this.playing);
            this.playing = false;
        }
        else {
            this.setIcon(start);
            this.setText("Start");
            this.anthill.pause(this.playing);
            this.anthill.getGc().setShow();
            this.anthill.getGc().activate();
            this.playing = true;
        }

    }

    public boolean isPlaying() {
        return playing;
    }
}