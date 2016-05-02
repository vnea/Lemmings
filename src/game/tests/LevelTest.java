package game.tests;

import game.components.Level;
import game.services.LevelService;
import game.contracts.LevelContract;
import game.contracts.PostconditionError;
import game.contracts.PreconditionError;
import game.contracts.InvariantError;
import game.enums.Nature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LevelTest {
	private LevelService level;
	
	@Before
	public void beforeTests(){
		level = new LevelContract(new Level());
	}
	
	///////// Test preconditions /////////
	
	@Test
	// OK
	public void testInitPre1(){
		// operation
		try {
			level.init(30, 30);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: MIN_HEIGHT < h
	public void testInitPre2() {
		boolean excpt = false;
		// operation
		try{
			level.init(LevelService.MIN_HEIGHT-1, 30);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: MIN_WIDTH < w
	public void testInitPre3() {
		boolean excpt = false;
		// operation
		try{
			level.init(30, LevelService.MIN_WIDTH-1);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: h < MAX_HEIGHT
	public void testInitPre4() {
		boolean excpt = false;
		// operation
		try{
			level.init(LevelService.MAX_HEIGHT+1, 30);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO \pre: w < MAX_WIDTH
	public void testInitPre5() {
		boolean excpt = false;
		// operation
		try{
			level.init(30, LevelService.MAX_WIDTH+1);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// OK
	public void testGetNaturePre1() {
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.getNature(20, 20);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: squareExist(h, w)
	public void testGetNaturePre2() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.getNature(40, 40);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		System.out.println(excpt);
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// OK
	public void testGetHEntrancePre1(){
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.getHEntrance();
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: !isEditing()
	public void testGetHEntrancePre2() {
		// init
		level.init(30, 30);
		
		// operation
		level.getHEntrance();
	}
	
	@Test
	// OK
	public void testGetWEntrancePre1(){
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.getWEntrance();
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: !isEditing()
	public void testGetWEntrancePre2() {
		// init
		level.init(30, 30);
		
		// operation
		level.getWEntrance();
	}
	
	@Test
	// OK
	public void testSetNaturePre1() {
		// init
		level.init(30, 30);
		
		// operation
		level.setNature(10, 10, Nature.DIRT);
	}
	
	@Test(expected=PreconditionError.class)
	// KO : \pre: squareExist(h, w)
	public void testSetNaturePre2() {
		// init
		level.init(30, 30);
		
		// operation
		level.setNature(40, 40, Nature.DIRT);
	}
	
	@Test(expected=PreconditionError.class)
	// KO : \pre: isEditing()
	public void testSetNaturePre3() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.setNature(10, 10, Nature.DIRT);
	}
	
	@Test
	// OK
	public void testRemovePre1() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(10, 10, Nature.DIRT);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.remove(10, 10);
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: getNature(h, w) == Nature::DIRT
	public void testRemovePre2() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.remove(10, 10);
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: !isEditing()
	public void testRemovePre3() {
		// init
		level.init(30, 30);
		
		// operation
		level.remove(10, 10);
	}
	
	@Test
	// OK
	public void testBuildPre1() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.build(10, 10);
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: getNature(h, w) == Nature::EMPTY
	public void testBuildPre2() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		level.setNature(10, 10, Nature.DIRT);
		
		// operation
		level.build(10, 10);
	}
	
	@Test(expected=PreconditionError.class)
	// KO \pre: !isEditing()
	public void testBuildPre3() {
		// init
		level.init(30, 30);
		
		// operation
		level.build(10, 10);
		
	}
	
	@Test
	// OK
	public void testGoPlayPre1() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: \forall i in [0, getHeight() - 1],
    //         getNature(i, 0) == Nature::METAL 
	public void testGoPlayPre2() {
		// init
		level.init(30, 30);
		for(int i=1; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: \forall i in [0, getHeight() - 1],
    //         getNature(i, getWidth() - 1) == Nature::METAL
	public void testGoPlayPre3() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-2, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: \forall j \in [0, getWidth() - 1],
    //         getNature(0, j) == Nature::METAL
	public void testGoPlayPre4() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(1, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: \forall j \in [0, getWidth() - 1],
    //         getNature(getHeight() - 1, j) == Nature::METAL
	public void testGoPlayPre5() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth()-1; i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  isEditing()
	public void testGoPlayPre6() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.goPlay(5, 5, 8, 8);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h1, w1) == Nature::EMPTY
	public void testGoPlayPre7() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(5, 5, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h1 - 1, w1) == Nature::EMPTY
	public void testGoPlayPre8() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(4, 5, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h1 + 1, w1) == Nature::EMPTY 
	public void testGoPlayPre9() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(6, 5, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h2, w2) == Nature::EMPTY
	public void testGoPlayPre10() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(8, 8, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h2 - 1, w2) == Nature::EMPTY
	public void testGoPlayPre11() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.METAL);
		level.setNature(7, 8, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
	@Test(expected=PreconditionError.class)
	// KO  \pre: getNature(h2 + 1, w2) == Nature::EMPTY
	public void testGoPlayPre12() {
		// init
		level.init(30, 30);
		for(int i=0; i < level.getHeight(); i++){
			level.setNature(i, 0, Nature.METAL);
			level.setNature(i, level.getWidth()-1, Nature.METAL);
		}
		for(int i=0; i < level.getWidth(); i++){
			level.setNature(0, i, Nature.METAL);
			level.setNature(level.getHeight()-1, i, Nature.METAL);
		}
		level.setNature(9, 8, Nature.DIRT);
		
		// operation
		level.goPlay(5, 5, 8, 8);
	}
	
}
