package jeuDesFourmis.ihm.anthill.mainframe;

import jeuDesFourmis.ihm.anthill.mainframe.gui.component.GridComponent;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Panel for Anthill.</b>
 * <p>
 * Integration and animation of GridComponent in Panel.
 * This panel is located on the left of main frame.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 * @see GridComponent
 */
public class AnthillPanel extends JPanel implements Runnable {
    /**
     * Current GridComponent.
     */
    private final GridComponent gc;
    /**
     * Current StatPanel.
     */
    private final StatPanel sp;
    /**
     * Thread start / stop variable.
     */
    private volatile boolean running;
    /**
     * Refresh time (in milliseconds).
     */
    private int delayRefresh;

    /**
     * Constructor of AnthillPanel.
     * @param width
     *      Size in width.
     * @param height
     *      Size in height
     * @param delayRefresh
     *      Time in milliseconds of refresh.
     */
    public AnthillPanel(int width, int height, int delayRefresh) {
        super();
        this.gc = new GridComponent(width, height);
        this.sp = new StatPanel(this.gc);

        this.running = false;
        this.delayRefresh = delayRefresh;


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(this.gc);


        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.NORTH);
        this.add(this.sp, BorderLayout.SOUTH);

        Thread thread = new Thread(this);
        thread.start();
    }


    /**
     * Pausing the thread.
     *
     * @param ispause Enable/Disable pause boolean
     */
    public void pause(boolean ispause) {
        this.running = ispause;
    }

    /**
     * Generate random ants,walls and seed.
     *    <ul>
     *        <li>probSeed means there is a 1 in probSeed chance of seed spawning.</li>
     *        <li>probAnt means there is a 1 in probAnt chance of ant spawning.</li>
     *        <li>probWall means there is a 1 in probWall chance of wall spawning.</li>
     *    </ul>
     *
     * @param probSeed Probability to spawn seeds
     * @param probAnt  Probability to spawn ants
     * @param probWall Probability to spawn wall
     */
    public void generation(int probSeed, int probAnt, int probWall) {
        this.gc.generation(probSeed, probAnt, probWall);
    }

    /**
     * Resize the gridComponent.
     *
     * @param size New size of grid component.
     */
    public void resize(int size) {
        this.gc.resize(size);
    }

    /**
     * Get a time delay of refresh.
     *
     * @return Time in milliseconds of refresh.
     */
    public int getDelayRefresh() {
        return this.delayRefresh;
    }

    /**
     * Set a time delay of refresh.
     *
     * @param delay Time in milliseconds of refresh.
     */
    public void setDelayRefresh(int delay) {
        this.delayRefresh = delay;
    }

    /**
     * Getting the gridComponent in this panel.
     *
     * @return The gridComponent.
     */
    public GridComponent getGc() {
        return gc;
    }

    /**
     * Running element of thread.
     * This thread do :
     *      <ul>
     *          <li>Count and update total seed in anthill. (not the one carried by the ants) </li>
     *          <li>Count and update total ants in anthill.</li>
     *          <li>Update gridComponent.</li>
     *          <li>If zoom frame is exists, update zoom grid component.</li>
     *      </ul>
     * It is running every delayRefresh.
     */
    @Override
    public void run() {
        while (true) {
            this.sp.getNbSeed().setTextValue(this.gc.getGrid().getTotalSeed());
            this.sp.getNbAnt().setTextValue(this.gc.getGrid().getTotalAnt());
            if (this.running) {
                this.gc.update();
                if (this.gc.isZoom() && this.gc.getZoomcomponent() != null) {
                    this.gc.getZoomcomponent().update();
                }
                try {
                    Thread.sleep(this.delayRefresh);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Getting if thread is running (not in pause).
     *
     * @return boolean.
     */
    public boolean isRunning() {
        return this.running;
    }
}
