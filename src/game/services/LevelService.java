package game.services;

import game.enums.Nature;

public interface LevelService {
    //**CONSTANT(S)**********************************************************//
    
    /** Min and max height of the level */
    final static int MIN_HEIGHT = 15;
    final static int MAX_HEIGHT = 50;
    
    /** Min and max width of the level */
    final static int MIN_WIDTH = 15;
    final static int MAX_WIDTH = 50;
    
    //***********************************************************************//
    
    
    //**OBSERVATOR(S)********************************************************//
    
    /** Height of the level */
    public int getHeight();
    
    /** Width of the level */
    public int getWidth();
    
    /** Is in editing mode */
    public boolean isEditing();
    
    /** Get the nature of the square
     * \pre: squareExist(h, w)
     * */
    public Nature getNature(int h, int w);
    
    /** Check if the square(h, w) is an obstacle
     * \pre: squareExist(h, w)
     * */
    public boolean isAnObstacle(int h, int w);
    
    /** Tells if the square exists */
    public boolean squareExist(int h, int w);
    
    /** Height coorinate entrance
     * \pre: !isEditing()
     * */
    public int getHEntrance();
    
    /** Width coorinate entrance
     * \pre: !isEditing()
     * */
    public int getWEntrance();
    
    //***********************************************************************//
    
    
    //**INVARIANT(S)*********************************************************// 
    
    /** \inv: \forall i \in [0, getHeight()[ ^ \forall j \in [0, getWidth()[,
     *         squareExist(i, j) */
   
    /** \inv: \forall i \in [0 ≤ i < getHeight()[ ^ \forall j \in [0, getWidth()[,
     *       isAnObstacle(i, j) = (getNature(i, j) == Nature::DIRT or getNature(i, j) = Nature::METAL)
     *                            (minimisation)
     */
    
    //***********************************************************************//

    
    //**INIT*****************************************************************//
    
    /** Initialisation
     * \pre: MIN_HEIGHT < h < MAX_HEIGHT ^ MIN_WIDTH < w < MAX_WIDTH
     * \post: getHeight() == h
     * \post: getWidth() == w
     * \post: isEditing()
     * \post: \forall i \in [0, getHeight()[ ^ \forall j in [0, getWidth()[,
     *         getNature(i, j) == Nature::EMPTY
     */
    public void init(int h, int w);
    
    //***********************************************************************//

    
    //**OPERATOR(S)**********************************************************//
    
    /** Set a new nature
     * \pre: isEditing()
     * \pre: squareExist(h, w)
     * \pre: n != Nature::ENTRANCE ^ n != Nature::EXIT
     */
    public void setNature(int h, int w, Nature n);
    
    /** Change a square nature from Nature::DIRT to Nature::EMPTY
     * \pre: !isEditing()
     * \pre: getNature(h, w) == Nature::DIRT
     * \post: getNature(h, w) == Nature::EMPTY
     */
    public void remove(int h, int w);
    
    /** Change a square nature from Nature::EMPTY to Nature::DIRT
     * \pre: !isEditing()
     * \pre: getNature(h, w) == Nature::EMPTY
     * \post: getNature(h, w) == Nature::DIRT
     */
    public void build(int h, int w);
    
    /** Switch to play mode
     * \pre: \forall i in [0, getHeight() - 1],
     *         getNature(i, 0) == Nature::METAL ^ getNature(i, getWidth() - 1) == Nature::METAL
     * \pre: \forall j \in [0, getWidth() - 1],
     *         getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1, j) == Nature::METAL
     * \pre: isEditing()
     * \pre: getNature(h1, w1) == Nature::EMPTY ^ getNature(h1 - 1, w1) == Nature::EMPTY ^ getNature(h1 + 1, w1) == Nature::EMPTY
     * \pre: getNature(h2, w2) == Nature::EMPTY ^ getNature(h2 - 1, w2) == Nature::EMPTY ^ getNature(h2 + 1, w2) == Nature::EMPTY
     * \pre: h1 != h2 v w1 != w2
     * \post: !isEditing()
     * \post: getHEntrance() == h1
     * \post: getWEntrance() == w1
     * \post: getNature(h1, w1) == Nature::ENTRANCE
     * \post: getNature(h2, w2) == Nature::EXIT
     */
    public void goPlay(int h1, int w1, int h2, int w2);
    
    //***********************************************************************//
}
