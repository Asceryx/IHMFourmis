package ihm.anthill.mainframe.gui.label;

import java.awt.*;

/**
 * <b>Custom Label of statistic of grid.</b>
 * <p>
 * These value of label should be modified dynamically.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 */
public class StatLabel extends Label {
    /**
     * Default font.
     */
    private final Font font = new Font("Book Antiqua", Font.PLAIN, 12);
    /**
     * Value to be modify.
     */
    private int value;
    /**
     * Fixed legend of label.
     */
    private String legend;

    /**
     * Constructor of StatLabel.
     *
     * @param legend Fixed legend to explain value.
     */
    public StatLabel(String legend) {
        super(legend + " : " + 0);
        this.setFont(font);

        this.value = 0;
        this.legend = legend;
    }

    /**
     * Setter of text. Modify value.
     *
     * @param value Dynamic value of text.
     */
    public void setTextValue(int value) {
        this.setText(legend + " : " + value);
        this.value = value;
    }
}