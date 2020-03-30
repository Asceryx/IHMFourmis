package ihm.anthill;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AnthillPanel extends JPanel implements Runnable {
    private Grid grid;
    private GridComponent gc;
    private StatPanel sp;
    private volatile boolean running;
    private int delayRefresh;

    public AnthillPanel(int width, int height, int delayRefresh) {
        super();
        this.gc = new GridComponent(width,height);
        this.grid = gc.getGrid();
        this.sp = new StatPanel(this.gc);
        this.running = false;
        this.delayRefresh = delayRefresh;




        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(this.gc);


        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.NORTH);
        this.add(this.sp, BorderLayout.SOUTH);

        Thread thread = new Thread(this);
        thread.start();
    }



    public void pause(boolean ispause) {
        this.running=ispause;
    }

    public void generation(int probSeed, int probAnt, int probWall){
        this.gc.generation(probSeed,probAnt,probWall);
    }

    public int getDelayRefresh(){
        return this.delayRefresh;
    }

    public void setDelayRefresh(int delay){
        this.delayRefresh = delay;
    }

    public GridComponent getGc() {
        return gc;
    }

    @Override
    public void run() {
        while (true) {
            if(this.running){
                try {
                    Thread.sleep(this.delayRefresh);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                this.grid = gc.getGrid();
                this.gc.update();
                this.sp.getNbSeed().setTextValue(this.grid.getTotalSeed());
                this.sp.getNbAnt().setTextValue(this.grid.getTotalAnt());
            }
        }
    }

    public static void main (String [] args) {
        JFrame frame = new JFrame("Test Anthil");


        // Initilaisation du thread
        Runnable runnable = new AnthillPanel(5,5,100);



        AnthillPanel anthill = (AnthillPanel)runnable;
        //Par défault, l'animation ne se lance PAS

        //on lance l'animation
        anthill.pause(true);
        //on arrete l'animation
        //anthill.pause(false);

        frame.add(anthill);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(5,5));
        frame.pack() ;
        frame.setVisible(true);

    }
}
