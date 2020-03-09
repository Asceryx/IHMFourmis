package ihm.anthill;

import java.awt.Font;
import java.awt.Label;

public class StatLabel extends Label {
    private final Font font = new Font("Book Antiqua", Font.PLAIN, 12);
    private int value;
    private String legend;

    public StatLabel(String legend) {
        super(legend + " : " + 0);
        this.setFont(font);

        this.value = 0;
        this.legend = legend;
    }
    public void setTextValue(int value) {
        this.setText(legend + " : " + value);
        this.value = value;
    }

    public int getTextValue() {
        return this.value;
    }
}