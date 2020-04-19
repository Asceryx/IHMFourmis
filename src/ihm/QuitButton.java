package ihm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitButton extends JButton implements ActionListener {
    public QuitButton() {
        super();
        Box box=Box.createHorizontalBox();
        box.add(Box.createGlue());
        this.setText("Quitter");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int quit = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ? ", null, JOptionPane.YES_NO_OPTION);
        if (quit == 0)
            System.exit(0);
    }
}
