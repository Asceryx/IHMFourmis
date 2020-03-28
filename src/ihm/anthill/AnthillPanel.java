package ihm.anthill;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AnthillPanel extends JPanel implements Runnable {
    private Grid grid;
    private GridComponent gc;
    private StatPanel sp;
    private boolean running;

    public AnthillPanel(int width, int height, int probSeed, int probAnt, int probWall, int delayRefresh) {
        super();
        JPanel panel = new JPanel();
        this.setLayout(new BorderLayout());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(panel,BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.gc = new GridComponent(4,4);
        this.gc.generation(50,10,60);
        this.grid = gc.getGrid();
        this.running = false;


        panel.add(gc);

        this.sp = new StatPanel(this.gc);
        this.add(this.sp, BorderLayout.SOUTH);
    }


    public void pause(boolean ispause)
    {
        this.running=ispause;
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.grid = gc.getGrid();
            this.gc.update();
            this.sp.getNbSeed().setTextValue(this.grid.getTotalSeed());
            this.sp.getNbAnt().setTextValue(this.grid.getTotalAnt());
        }
    }

    public static void main (String [] args) {
        JFrame frame = new JFrame("Test Anthil");


        // Initilaisation du thread
        Runnable runnable = new AnthillPanel(200,200,30, 30, 30,100);
        Thread thread = new Thread(runnable);
        //Lancement du thread
        thread.start();
        //Par défault, l'animation ne se lance PAS

        //on lance l'animation
        ((AnthillPanel)runnable).pause(false);
        //on arrete l'animation
        ((AnthillPanel)runnable).pause(true);

        frame.add((AnthillPanel)runnable);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200,200));
        frame.pack() ;
        frame.setVisible(true);

    }
}
