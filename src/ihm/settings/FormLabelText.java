package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class FormLabelText extends JPanel implements Deactivable {
    private final Font fontTitle =new Font("TimesRoman", Font.BOLD,14);
    private final Font SubTitle =new Font("TimesRoman", Font.ITALIC,12);
    private JLabel label_title;
    private JLabel label_description;
    private JSpinner field;

    public FormLabelText(String title, String description,  int defaultValue)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.label_title =new JLabel(title);
        this.label_description = new JLabel(description);
        SpinnerModel model = new SpinnerNumberModel(defaultValue, 0, 999, 5);
        this.field = new JSpinner (model);


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
        mainBox.add(Box.createRigidArea(new Dimension(0,5)));
        mainBox.add(n);
        mainBox.add(Box.createRigidArea(new Dimension(0,10)));

        this.add(mainBox);
    }

    public int getField(){
        return (int)this.field.getValue();
    }


    @Override
    public void activate() {
        this.field.setEnabled(true);
        this.label_title.setForeground(Color.BLACK);
    }

    @Override
    public void deactivate() {
        this.field.setEnabled(false);
        this.label_title.setForeground(Color.GRAY);
        this.label_description.setForeground(Color.GRAY);
    }

    public static void main(String []args) {
        JFrame frame = new JFrame("test");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        Box mainBox = Box.createVerticalBox();
        FormLabelText f1 = new FormLabelText("Probabilité de fourmis", "", 0);
        FormLabelText f2 = new FormLabelText("Probabilité de murs","1 chance sur ", 12);
        f1.setAlignmentX(Box.LEFT_ALIGNMENT);
        f2.setAlignmentX(Box.LEFT_ALIGNMENT);
        mainBox.add(f1);
        mainBox.add(f2);
        f1.deactivate();
        //f1.activate();

        JPanel p = new JPanel();
        p.add(mainBox);

        frame.add(p);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 500));
        frame.pack();
        frame.setVisible(true);
    }
}