package ihm.settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsForm extends JPanel implements Deactivatable {
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
    public int val1,val2,val3,val4;
    public PlayButton playButton;
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
        txt1=new JTextField(""+val1);
        txt2=new JTextField(""+val2);
        txt3=new JTextField(""+val3);
        txt4=new JTextField(""+val4);
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
    @Override
    public void setEditable(boolean b) {
        txt1.setEnabled(b);
        txt2.setEnabled(b);
        txt3.setEnabled(b);
        txt4.setEnabled(b);
    }

    public int getVal1()
    {
        return val1;
    }
    public int getVal2()
    {
        return val2;
    }
    public int getVal3()
    {
        return val3;
    }
    public int getVal4()
    {
        return val4;
    }
    public void setVal1(int val1)
    {
        this.val1=val1;
    }
    public void setVal2(int val2)
    {
        this.val2=val2;
    }
    public void setVal3(int val3)
    {
        this.val3=val3;
    }
    public void setVal4(int val4)
    {
        this.val4=val4;
    }
    public static void main(String []args) {
        JFrame frame = new JFrame("test");
        //    frame.setLayout(new BorderLayout());
        SettingsForm test = new SettingsForm();
        frame.add(test, BorderLayout.EAST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
}

