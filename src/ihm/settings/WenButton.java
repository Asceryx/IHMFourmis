package ihm.settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WenButton extends JButton  {
    private Font font;
    private String text;

    public class LoupeAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            settingFrame();
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        private void settingFrame(){
          
            JFrame frame=new JFrame();
            frame.setPreferredSize(new Dimension(400,400));
            frame.setMaximumSize(new Dimension(400,400));
            frame.setLocationRelativeTo(null);
            frame.setTitle("Zoom on certain cells ");
        }

    }
    public WenButton(String text)
    {
        super();
        Box box =Box.createHorizontalBox();
        font=new Font("Serif",Font.ITALIC,14);
        this.setFont(font);
        this.setText(text);
        this.text=text;
        this.addMouseListener(new LoupeAction());
        box.add(this);
    }
    public static void main (String [] args)
    {
        JFrame frame =new JFrame("loupe button");
        WenButton loupe=new WenButton("Loupe");
        frame.add(loupe);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }




}