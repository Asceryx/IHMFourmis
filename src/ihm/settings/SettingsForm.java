package ihm.settings;

import javax.swing.*;
import java.awt.*;

public class SettingsForm extends JPanel {
    public FormLabelText parametre;
    public FormLabelText taille;
    public FormLabelText probabiliteG;
    public FormLabelText probabiliteF;
    public FormLabelText probabiliteMur;
    public ValidateButton valider;
    public JTextField txt1;
    public JTextField txt2;
    public JTextField txt3;
    public JTextField txt4;

    public SettingsForm()
    {   super();

        Box box=Box.createVerticalBox();
       // Box horizValider=Box.createHorizontalBox();
        parametre=new FormLabelText("Paramétres plateau :");

        taille =new FormLabelText("Taille du plateau :");
        probabiliteG=new FormLabelText("Probabilité de graine :");
        probabiliteF=new FormLabelText("Probabilité de Fourmi :");
        probabiliteMur=new FormLabelText("Probabilité de mur :");
        valider=new ValidateButton();
        txt1=new JTextField("20");
        txt2=new JTextField("20");
        txt3=new JTextField("20");
        txt4=new JTextField("20");

       box.add(parametre);
       box.add(taille);
       box.add(txt1);
       box.add(probabiliteG);
       box.add(txt2);
       box.add(probabiliteF);
       box.add(txt3);
       box.add(probabiliteMur);
       box.add(txt4);
       this.add(box);

    }
    public static void main(String []args)
    {
        JFrame frame=new JFrame("test");
    //    frame.setLayout(new BorderLayout());
        SettingsForm test=new SettingsForm();
        frame.add(test,BorderLayout.EAST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));
        frame.pack();
        frame.setVisible(true);
    }
}