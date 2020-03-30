package ihm.settings;

import ihm.anthill.AnthillPanel;
import jbutton.ButtonIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PlayButton extends JButton implements Runnable,ActionListener {
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
            this.setText("Pause ");
            this.anthill.pause(true);
            this.playing = false;
        }
        else {
            this.setIcon(start);
            this.setText("Start ");
            this.anthill.pause(false);
            this.playing = true;
        }

    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public static void main(String [] args)
    {
        /*
        JFrame test =new JFrame("test");
        PlayButton playButton=new PlayButton();
        test.add(playButton);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.pack();
        test.setVisible(true);

         */
    }

    @Override
    public void run() {

    }
}