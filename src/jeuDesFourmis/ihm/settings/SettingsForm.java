package jeuDesFourmis.ihm.settings;

import jeuDesFourmis.ihm.settings.label.FormLabelText;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Anthill modification form.</b>
 * We call form a set of fields.
 * This form contains :
 * <ul>
 *     <li>a ground for map size.</li>
 *     <li>a ground for ant probability.</li>
 *     <li>a ground for seed probability.</li>
 *     <li>a ground for wall probability.</li>
 * </ul>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class SettingsForm extends JPanel implements Deactivable {
    /**
     * Font for main title of ground.
     */
    private final Font fontTitle = new Font("TimesRoman", Font.BOLD, 20);
    /**
     * Font for sub title of ground.
     */
    private final Font SubTitle = new Font("TimesRoman", Font.ITALIC, 12);
    /**
     * a ground for ant probability.
     */
    private FormLabelText probaF;
    /**
     * a ground for seed probability.
     */
    private FormLabelText probaG;
    /**
     * a ground for wall probability.
     */
    private FormLabelText probaM;
    /**
     * a ground for map size.
     */
    private JTextField mapSize;
    /**
     * a legend for map size ground.
     */
    private JLabel map_size_describe;

    /**
     * Constructor of SettingsForm.
     */
    public SettingsForm() {
        super();
        Box mainBox = Box.createVerticalBox();
        JLabel map_size_title = new JLabel("Taille de la fourmillière");
        map_size_title.setFont(fontTitle);

        this.map_size_describe = new JLabel("Taille : ");
        this.map_size_describe.setFont(SubTitle);
        this.mapSize = new JTextField(6);

        Box box_size = Box.createHorizontalBox();
        box_size.add(map_size_describe);
        box_size.add(this.mapSize);

        JPanel size = new JPanel(new BorderLayout());
        size.add(box_size, BorderLayout.WEST);


        JPanel size_title = new JPanel(new BorderLayout());
        size_title.add(map_size_title, BorderLayout.WEST);


        JLabel map_generate_title = new JLabel("Paramètre de génération");
        map_generate_title.setFont(fontTitle);
        this.probaF = new FormLabelText("Probabilité de fourmis", "1 chance sur ", 0);
        this.probaG = new FormLabelText("Probabilité de graines", "1 chance sur ", 0);
        this.probaM = new FormLabelText("Probabilité de murs", "1 chance sur ", 0);
        this.probaF.setAlignmentX(Box.LEFT_ALIGNMENT);
        this.probaG.setAlignmentX(Box.LEFT_ALIGNMENT);
        this.probaM.setAlignmentX(Box.LEFT_ALIGNMENT);
        size_title.setAlignmentX(Box.LEFT_ALIGNMENT);
        size.setAlignmentX(Box.LEFT_ALIGNMENT);
        mainBox.add(size_title);
        mainBox.add(size);
        mainBox.add(Box.createRigidArea(new Dimension(0,15)));

        mainBox.add(map_generate_title);
        mainBox.add(Box.createRigidArea(new Dimension(0, 15)));

        mainBox.add(this.probaF);
        mainBox.add(this.probaG);
        mainBox.add(this.probaM);

        this.add(mainBox);
    }

    /**
     * User can interact with the form.
     */
    @Override
    public void activate() {
        this.probaF.activate();
        this.probaG.activate();
        this.probaM.activate();
        this.mapSize.setEnabled(true);
        this.map_size_describe.setForeground(Color.BLACK);
    }

    /**
     * User can't interact with the form.
     */
    @Override
    public void deactivate() {
        this.probaF.deactivate();
        this.probaG.deactivate();
        this.probaM.deactivate();
        this.mapSize.setEnabled(false);
        this.map_size_describe.setForeground(Color.GRAY);

    }

    /**
     * Set a value in map size ground.
     *
     * @param value integer value.
     */
    public void setMapSize(int value) {
        this.mapSize.setText(Integer.toString(value));
    }

    /**
     * Get a size value in map size ground.
     *
     * @return integer size value.
     */
    public int getSizeMap() {
        int value = Integer.parseInt(this.mapSize.getText());
        if (value <= 0) {
            value = 1;
        }
        return value;
    }

    /**
     * Get probability ant value.
     *
     * @return Integer probability ant value.
     */
    public int getProbaFourmis() {
        return this.probaF.getField();
    }

    /**
     * Get probability seed value.
     *
     * @return Integer probability seed value.
     */
    public int getProbaGraines() {
        return this.probaG.getField();
    }

    /**
     * Get probability wall value.
     *
     * @return Integer probability wall value.
     */
    public int getProbaMurs() {
        return this.probaM.getField();
    }
}

