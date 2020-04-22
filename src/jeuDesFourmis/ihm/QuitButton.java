package jeuDesFourmis.ihm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>Button to quit application</b>
 * <p>
 * This button ask confirmation before quit.
 * </p>
 */
public class QuitButton extends JButton implements ActionListener {
    /**
     * Constructor of QuitButton.
     */
    public QuitButton() {
        super();
        Box box = Box.createHorizontalBox();
        box.add(Box.createGlue());
        this.setText("Quitter");
        this.addActionListener(this);
        this.setFocusable(false);
    }

    /**
     * Quit the application after confirmation.
     *
     * @param actionEvent event.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int quit = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ? ", "Quitter", JOptionPane.YES_NO_OPTION);
        if (quit == 0)
            System.exit(0);
    }
}
