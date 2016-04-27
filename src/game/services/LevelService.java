package game.services;

import game.enums.Nature;

public interface LevelService {
    //**CONSTANT(S)**********************************************************//
	
	/** Min and max height of the level */
	final static int MIN_HEIGHT = 19;
	final static int MAX_HEIGHT = 51;
	
	/** Min and max width of the level */
	final static int MIN_WIDTH = 19;
	final static int MAX_WIDTH = 51;
	
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
	
	/** Height coordinate exit
	 * \pre: !isEditing()
	 * */
	public int getHExit();
	
	/** Width coordiinate exit
	 * \pre: !isEditing()
	 * */
	public int getWExit();
	
    //***********************************************************************//
	
	
    //**INVARIANT(S)*********************************************************// /* <---------------------- TO CHANGE (LACK OF INVARIANTS) */
	
	/**\inv: \forall i \in [0, getHeight()[ ^ \forall j in [0, getWidth()[,
	 *         squareExist(, i, j) */
	
    //***********************************************************************//

	
    //**INIT*****************************************************************//
	
	/** Initialisation
	 * \pre: MIN_HEIGHT < h < MAX_HEIGHT ^ MIN_WIDTH < w < MIN_WIDTH
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
	 */
	public void setNature(int h, int w, Nature n);
	
	/** Switch to play mode
	 * \pre: \forall i in [0, getHeight() - 1],
	 *         getNature(i, 0) == Nature::METAL ^ getNature(i, getWidth() - 1) == Nature::METAL
	 * \pre: \forall j \in [0, getWidth() - 1],
	 *         getNature(0, j) == Nature::METAL ^ getNature(getHeight() - 1, j) == Nature::METAL
	 * \pre: isEditing()
	 * \post: !isEditing()
	 */
	public void goPlay();
	
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
	
	/** Define the entrance
	 * \pre: getNature(h, w) == Nature::EMPTY
	 * \pre: getNature(h - 1, w) == Nature::EMPTY
	 * \pre: getNature(h + 1, w) == Nature::EMPTY
	 * \post: getHEntrance() == h
	 * \post: getWEntrance() == w
	 */
	public void defEntrance(int h, int w);
	
	/** Define the exit
     * \pre: getNature(h, w) == Nature::EMPTY
	 * \pre: getNature(h - 1, w) == Nature::EMPTY
	 * \pre: getNature(h + 1, w) == Nature::EMPTY
	 * \post: getHExit() == h
	 * \post: getWExit() == w
	 */
	public void defExit(int h, int w);
	
    //***********************************************************************//
}
