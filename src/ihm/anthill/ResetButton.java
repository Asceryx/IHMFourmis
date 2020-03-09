package ihm.anthill;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton implements ActionListener {
    public ResetButton() {
        super("Réinitialiser le plateau");
        addActionListener(this);
    }

    /**
     * @TODO : Implémentation du reset du plateau :
     *           - Effacement du plateau
     *           - Mise à jour des variables
     **/
    private void reset()
    {
        System.out.println("Réinitialisation du plateau");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reset();
    }
}