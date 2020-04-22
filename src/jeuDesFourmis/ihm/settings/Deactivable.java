package jeuDesFourmis.ihm.settings;

/**
 * <b>Interface describe if element should be deactivate/activate.</b>
 * <p>
 * deactivate means a element is appears but can no longer interact with the user.
 * </p>
 *
 * @author Torjmen Wadie
 * @version 1.0
 */
public interface Deactivable {
    /**
     * User can interact with the element.
     */
    void activate();

    /**
     * User can't interact with the element.
     */
    void deactivate();
}