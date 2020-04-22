package jeuDesFourmis.model;

/**
 * Classe de gestion de fourmis
 * @author abergey
 * @version 1.1
 */
public class Fourmi {
	
    private static int prPREND = 3 ; 
    private static double prPOSE = 1.4 ; 
    private int x,y ; 
    private boolean porteGraine ;
    
    /**
     * Crée une fourmi
     * @param x             Position en X de la fourmi 
     * @param y             Position en Y de la fourmi
     * @param porteGraine     Porte - ou pas - une graine
     */
    public Fourmi(int x, int y, boolean porteGraine){
	this.x = x ;
	this.y = y ; 
	this.porteGraine = porteGraine ; 
    }
    
    /**
     * Crée une fourmi en (1,1) qui ne porte rien
     */
    public Fourmi (){
	this(1,1,false);
    }
	
	
    /**
     * Indique si une fourmi porte une graine. 
     * @return vrai si la fourmi porte une graine
     */
    public boolean porte(){
	return porteGraine;
    }
    /**
     * La fourmi charge une graine si elle n'en avait pas déjà
     */
    public void prend(){
	porteGraine = true ; 
    }
    /**
     * La fourmi pose sa graine si elle en avait une
     */
    public void pose(){
	porteGraine = false ; 
    }

    // Probabilités de chargement et de pose d'une graine

    /**
     * Retourne une probabilité (valeur comprise entre 0.0 et 1.0)
     * en fonction d'un entier superieur ou egal 0
     * Cette proba sera utilisée pour savoir si la fourmi prend une graine
     * en fonction du nombre de graines qui sont autour de la fourmi
     *
     * @param nbGraines entier
     * @return probabilité de prise
     */
    public static double probaPrend (int nbGraines){
	return (1.0/( double) (nbGraines + prPREND));
    }
    /**
     *  Retourne une probabilité (valeur comprise entre 0.0 et 1.0)
     *          en fonction d'un entier superieur ou egal 0
     *  Cette proba sera utilisée pour savoir si la fourmi pose une graine
     *       en fonction du nombre de graines qui sont autour de la fourmi 
     * @param nbGraines   entier 
     * @return probabilité de dépot
     */
    public static double probaPose (int nbGraines){
	return (1.0 -(19.9/((double)nbGraines +20))); 
    }

    /**
     * Retourne la position en X de la fourmi
     * @return				la position en X de la fourmi
     */
    public int  getX(){
	return x ; 
    }

    /**
     * Retourne la position en Y de la fourmi
     * @return				la position en Y de la fourmi. 
     */
    public int  getY(){
	return y ; 
    }
    /**
     * Positionne le X de la fourmi
     * @param x 			la position en X  
     */
    public void  setX(int x){
	this.x = x ; 
    }
    /**
     * Positinone le Y de la fourmi
     * @param y				la position en Y 
     */
    public void  setY(int y ){
	this.y = y ; 
    }
}
