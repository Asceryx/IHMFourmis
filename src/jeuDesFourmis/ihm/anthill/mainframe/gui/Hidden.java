package jeuDesFourmis.ihm.anthill.mainframe.gui;

/**
 * <b>Interface defining if an element should be hidden.</b>
 * <p>
 * Show/Hide a element.
 * For instance, hiding a grid in a anthill.
 * </p>
 *
 * @author Julien Hayrault
 * @version 1.0
 */
public interface Hidden {
    /**
     * Hide a element.
     */
    void setHide();

    /**
     * Show a element
     */
    void setShow();
}