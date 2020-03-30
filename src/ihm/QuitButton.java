package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitButton extends JButton implements ActionListener {
    public QuitButton() {
        super();
        Box box=Box.createHorizontalBox();
        box.add(Box.createGlue());
        this.setText("Quit");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int quit = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ? ", null, JOptionPane.YES_NO_OPTION);
        if (quit == 0)
            System.exit(0);
    }
    public static void main (String [] args)
    {
        JFrame frame =new JFrame("test quit");
        Box box=Box.createHorizontalBox();
        box.add(Box.createGlue());
        QuitButton quit=new QuitButton();
        box.add(quit);
        box.add(Box.createGlue());
        frame.add(box, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
