package jeuDesFourmis.ihm.settings.label;

import jeuDesFourmis.ihm.settings.Deactivable;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Field to set up a propriety of anthill.</b>
 * This field contains the following :
 * <ul>
 *     <li>A main title, at the top of JSpinner, to describe what this field sets.</li>
 *     <li>A sub title, at the left of JSpinner, to explain what is the value.</li>
 *     <li>A JSpinner.</li>
 * </ul>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public class FormLabelText extends JPanel implements Deactivable {
    /**
     * Font of main title.
     */
    private final Font fontTitle = new Font("TimesRoman", Font.BOLD, 14);
    /**
     * Font of sub title.
     */
    private final Font SubTitle = new Font("TimesRoman", Font.ITALIC, 12);
    /**
     * Main title JLabel.
     */
    private JLabel label_title;
    /**
     * Sub title JLabel.
     */
    private JLabel label_description;
    /**
     * JSpinner.
     */
    private JSpinner field;

    /**
     * Constructor of FormLabelText.
     *
     * @param title        Main title of FormLabelText.
     * @param description  Sub title of FormLabelText.
     * @param defaultValue Default value of JSpinner.
     */
    public FormLabelText(String title, String description, int defaultValue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.label_title = new JLabel(title);
        this.label_description = new JLabel(description);
        SpinnerModel model = new SpinnerNumberModel(defaultValue, 0, 9999, 5);
        this.field = new JSpinner(model);


        Box box2 = Box.createHorizontalBox();
        box2.add(this.label_description);
        box2.add(this.field);


        JPanel n = new JPanel(new BorderLayout());
        n.add(box2, BorderLayout.WEST);

        Box mainBox = Box.createVerticalBox();

        JPanel m = new JPanel(new BorderLayout());
        m.add(this.label_title, BorderLayout.WEST);


        this.label_title.setFont(fontTitle);
        this.label_description.setFont(SubTitle);


        mainBox.add(m);
        mainBox.add(Box.createRigidArea(new Dimension(0, 5)));
        mainBox.add(n);
        mainBox.add(Box.createRigidArea(new Dimension(0, 10)));

        this.field.setFocusable(false);
        this.add(mainBox);
    }

    /**
     * Getting value of FormLabelText
     *
     * @return Integer value of JSpinner.
     */
    public int getField() {
        return (int) this.field.getValue();
    }

    /**
     * User can interact with the FormLabelText.
     */
    @Override
    public void activate() {
        this.field.setEnabled(true);
        this.label_title.setForeground(Color.BLACK);
        this.label_description.setForeground(Color.BLACK);
    }

    /**
     * User can't interact with the FormLabelText.
     */
    @Override
    public void deactivate() {
        this.field.setEnabled(false);
        this.label_title.setForeground(Color.GRAY);
        this.label_description.setForeground(Color.GRAY);
    }
}