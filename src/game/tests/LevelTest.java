package game.tests;

import game.components.Level;
import game.services.LevelService;
import game.contracts.LevelContract;
import game.contracts.PostconditionError;
import game.contracts.PreconditionError;
import game.contracts.InvariantError;
import game.enums.Nature;

import static org.junit.Assert.assertTrue;
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
			level.init(LevelService.MAX_HEIGHT, LevelService.MAX_WIDTH);
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
			level.getNature(29, 29);
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
			level.getNature(30, 30);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// OK
	public void testIsAnObstaclePre1() {
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.isAnObstacle(0, 0);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: squareExist(h, w)
	public void testIsAnObstaclePre2() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.isAnObstacle(-1, -1);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
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
		try{
			level.getHEntrance();
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: !isEditing()
	public void testGetHEntrancePre2() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.getHEntrance();
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
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
		try{
			level.getWEntrance();
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: !isEditing()
	public void testGetWEntrancePre2() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.getWEntrance();
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// OK
	public void testSetNaturePre1() {
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.setNature(0, 0, Nature.DIRT);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO : \pre: squareExist(h, w)
	public void testSetNaturePre2() {
		boolean excpt = false; 
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.setNature(-1, -1, Nature.DIRT);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO : \pre: isEditing()
	public void testSetNaturePre3() {
		boolean excpt = false;
		
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
		try{
			level.setNature(10, 10, Nature.DIRT);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO : \pre: n != Nature::ENTRANCE
	public void testSetNaturePre4() {
		boolean excpt = false; 
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.setNature(10, 10, Nature.ENTRANCE);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO : \pre: n != Nature::EXIT
	public void testSetNaturePre5() {
		boolean excpt = false; 
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.setNature(10, 10, Nature.EXIT);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
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
		try{
			level.remove(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: getNature(h, w) == Nature::DIRT
	public void testRemovePre2() {
		boolean excpt = false;
		
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
		try{
			level.remove(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO \pre: !isEditing()
	public void testRemovePre3() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.remove(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
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
		try{
			level.build(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO \pre: getNature(h, w) == Nature::EMPTY
	public void testBuildPre2() {
		boolean excpt = false;
		
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
		try{
			level.build(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO \pre: !isEditing()
	public void testBuildPre3() {
		boolean excpt = false;
		
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.build(10, 10);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			assertTrue("No exception must be raised.", false);
		}
	}
	
	@Test
	// KO  \pre: \forall i in [0, getHeight() - 1],
    //         getNature(i, 0) == Nature::METAL 
	public void testGoPlayPre2() {
		boolean excpt = false;
		
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
		level.setNature(0, 0, Nature.EMPTY);
		
		// operation
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: \forall i in [0, getHeight() - 1],
    //         getNature(i, getWidth() - 1) == Nature::METAL
	public void testGoPlayPre3() {
		boolean excpt = false;
		
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
		level.setNature(level.getHeight()-1, 0, Nature.EMPTY);
		
		// operation
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: \forall j \in [0, getWidth() - 1],
    //         getNature(0, j) == Nature::METAL
	public void testGoPlayPre4() {
		boolean excpt = false;
		
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
		level.setNature(0, level.getWidth()-1, Nature.EMPTY);
		
		// operation
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	
	@Test
	// KO  \pre: \forall j \in [0, getWidth() - 1],
    //         getNature(getHeight() - 1, j) == Nature::METAL
	public void testGoPlayPre5() {
		boolean excpt = false;
		
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
		level.setNature(level.getHeight()-1, level.getWidth()-1, Nature.EMPTY);
		
		// operation
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  isEditing()
	public void testGoPlayPre6() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: getNature(h1, w1) == Nature::EMPTY
	public void testGoPlayPre7() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: getNature(h1 - 1, w1) == Nature::EMPTY
	public void testGoPlayPre8() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	
	@Test
	// KO  \pre: getNature(h1 + 1, w1) == Nature::EMPTY 
	public void testGoPlayPre9() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: getNature(h2, w2) == Nature::EMPTY
	public void testGoPlayPre10() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: getNature(h2 - 1, w2) == Nature::EMPTY
	public void testGoPlayPre11() {
		boolean excpt = false;
		
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	@Test
	// KO  \pre: getNature(h2 + 1, w2) == Nature::METAL
	public void testGoPlayPre12() {
		boolean excpt = false;
		
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
		level.setNature(9, 8, Nature.DIRT);
		
		// operation
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){}
		catch(PreconditionError e){
			excpt = true;
		}
		assertTrue("A PreconditionError must be raised.", excpt);
	}
	
	
	
	
	///////// Test transitions /////////
	
	@Test
	// OK
	public void testInitTrans1(){
		// operation
		try {
			level.init(LevelService.MAX_HEIGHT, LevelService.MAX_WIDTH);
		}
		catch(PostconditionError | InvariantError e){
			assertTrue("No exception must be raised.", false);
		}
		catch(PreconditionError e){}
	}
	
	@Test
	// OK
	public void testSetNatureTrans1() {
		// init
		level.init(30, 30);
		
		// operation
		try{
			level.setNature(0, 0, Nature.DIRT);
		}
		catch(PostconditionError | InvariantError e){
			assertTrue("No exception must be raised.", false);
		}
		catch(PreconditionError e){}
	}
	
	@Test
	// OK
	public void testRemoveTrans1() {
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
		try{
			level.remove(10, 10);
		}
		catch(PostconditionError | InvariantError e){
			assertTrue("No exception must be raised.", false);
		}
		catch(PreconditionError e){}
	}
	
	@Test
	// OK
	public void testBuildTrans1() {
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
		try{
			level.build(10, 10);
		}
		catch(PostconditionError | InvariantError e){
			assertTrue("No exception must be raised.", false);
		}
		catch(PreconditionError e){}
	}
	
	@Test
	// OK
	public void testGoPlayTrans1() {
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
		try{
			level.goPlay(5, 5, 8, 8);
		}
		catch(PostconditionError | InvariantError e){
			assertTrue("No exception must be raised.", false);
		}
		catch(PreconditionError e){}
	}
	
	
	///////// Test remarkable state /////////
	// OK
	public void testSetDirtTwice() {
		// init
		level.init(30, 30);
		
		
		level.setNature(10, 10, Nature.DIRT);
		
		// operation
		try{
			level.setNature(10, 10, Nature.DIRT);
		}
		catch(PostconditionError | InvariantError | PreconditionError e){
		}
		assertTrue("Case's nature must be dirt", 
								level.getNature(10, 10) == Nature.DIRT);
		
	}
	
}
