package ihm.settings;

import jbutton.ButtonIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PlayButton extends JButton  {
ButtonIcon start ;
ButtonIcon pause;
SettingsForm form;
private static boolean isPlay;

public  PlayButton()
{
    super();
    PlayButton.isPlay=true;
    start=new ButtonIcon(ButtonIcon.IconShape.Start,25,25);
    pause=new ButtonIcon(ButtonIcon.IconShape.Pause,20,20);
    this.setIcon(start);
    this.setText("start ");
    this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PlayButton btn= (PlayButton) actionEvent.getSource();
            Boolean isPlay= btn.getIsPlay();
            if (isPlay)
            {
                btn.setJButtonIcon(false);
                btn.setIsPlay(false);

            }
            else {
                btn.setJButtonIcon(true);
                btn.setIsPlay(true);


            }
        }
    });

}
    private void setJButtonIcon(Boolean isIconPlay) {
        if (isIconPlay) {
            this.setIcon(start);
            this.setText("start");

        } else {

            this.setIcon(pause);
            this.setText("pause");
        }
    }

    public boolean getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(boolean isPlay) {
        PlayButton.isPlay = isPlay;
    }



    public static void main(String [] args)
    {
        JFrame test =new JFrame("test");
        PlayButton playButton=new PlayButton();
        test.add(playButton);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        test.pack();
        test.setVisible(true);
    }

}