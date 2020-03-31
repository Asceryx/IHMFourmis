package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class SettingsForm extends JPanel implements Deactivable {
    private final Font fontTitle =new Font("TimesRoman", Font.BOLD,20);
    private final Font SubTitle =new Font("TimesRoman", Font.ITALIC,12);
    private FormLabelText probaF;
    private FormLabelText probaG;
    private FormLabelText probaM;
    private JTextField mapSize;
    private JLabel map_size_describe;


    public SettingsForm() {
        super();
        Box mainBox = Box.createVerticalBox();
        JLabel map_size_title =  new JLabel("Taille de la fourmillière");
        map_size_title.setFont(fontTitle);

        this.map_size_describe =  new JLabel("Taille : ");
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
        mainBox.add(Box.createRigidArea(new Dimension(0,15)));

        mainBox.add(this.probaF);
        mainBox.add(this.probaG);
        mainBox.add(this.probaM);

        this.add(mainBox);
    }

    @Override
    public void activate() {
        this.probaF.activate();
        this.probaG.activate();
        this.probaM.activate();
        this.mapSize.setEnabled(true);
        this.map_size_describe.setForeground(Color.BLACK);
    }

    @Override
    public void deactivate() {
        this.probaF.deactivate();
        this.probaG.deactivate();
        this.probaM.deactivate();
        this.mapSize.setEnabled(false);
        this.map_size_describe.setForeground(Color.GRAY);

    }

    public void setMapSize(int value){
        this.mapSize.setText(Integer.toString(value));
    }

    public int getSizeMap(){
        return Integer.parseInt(this.mapSize.getText());
    }

    public int getProbaFourmis(){
        return this.probaF.getField();
    }

    public int getProbaGraines(){
        return this.probaG.getField();
    }

    public int getProbaMurs(){
        return this.probaM.getField();
    }


    public static void main(String []args) {
        JFrame frame = new JFrame("test");
        SettingsForm f = new SettingsForm();
        JPanel p = new JPanel();
        p.add(f);
        f.deactivate();
        frame.add(p);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

