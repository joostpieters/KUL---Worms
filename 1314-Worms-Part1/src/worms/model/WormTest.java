package worms.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import worms.util.Util;

/**
 * Class used to test the Worm class.
 * 
 * @version   1.0
 * @author 	  Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub:	  https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 */


public class WormTest {
	
	private Worm testWorm;
	private World world;
	private static final double EPS = Util.DEFAULT_EPSILON;

	
	@Before
	public void setup() throws IllegalArgumentException{
		testWorm = new Worm(world, 8, 5, Math.PI, 0.5, "Matt");
	}
	
	@Test
	public void testGetX(){
		assertEquals(8, testWorm.getX(), EPS);
		}
	
	@Test
	public void testSetX(){
		testWorm.setX(9);
		assertEquals(9, testWorm.getX(), EPS);
	}
	
	@Test
	public void testGetY(){
		assertEquals(5, testWorm.getY(), EPS);
	}
	
	@Test
	public void testSetY(){
		testWorm.setY(6);
		assertEquals(6, testWorm.getY(), EPS);
	}
	
	@Test
	public void testGetName(){
		assertTrue("Not the correct name", testWorm.getName().equals("Matt"));
	}
	
	@Test
	public void testIsValidName(){
		assertTrue("Name is invalid", Worm.isValidName("Peter"));
		assertFalse("Name is invalid", Worm.isValidName("123Jack"));
		assertTrue("Name is invalid", Worm.isValidName("Jack Wolfskin"));
	}
	
	@Test
	public void testRename() throws IllegalArgumentException{
		testWorm.rename("Geoff");
		assertTrue("Name is not correct", testWorm.getName().equals("Geoff"));
	}
	
	@Test
	public void testGetMass(){
		assertEquals(556.0618997, testWorm.getMass(), EPS);
	}
	
	@Test
	public void testGetRadius(){
		assertEquals(0.5, testWorm.getRadius(), EPS);
	}
	
	@Test
	public void testGetMinimualRadius(){
		assertEquals(0.25, testWorm.getMinimalRadius(), EPS);
	}
	
	@Test
	public void testSetRadius(){
		try{
			testWorm.setRadius(0.10);
			fail();
		}
		catch(IllegalArgumentException e){
			
		}
		testWorm.setRadius(0.70);
		assertEquals(0.70, testWorm.getRadius(), EPS);
	}
	
	@Test
	public void testGetOrientation(){
		assertEquals(Math.PI, testWorm.getOrientation(), EPS);
	}
	
	@Test
	public void testSetOrientation(){
		testWorm.setOrientation(-Math.PI);
		assertEquals(-Math.PI, testWorm.getOrientation(), EPS);
		testWorm.setOrientation(Math.PI/2);
		assertEquals(Math.PI/2, testWorm.getOrientation(), EPS);
	}
	
	@Test
	public void testGetMaxActionPoints(){
		assertEquals(556, testWorm.getMaxActionPoints(), EPS);
	}
	
	@Test
	public void testGetActionPoints(){
		assertEquals(556, testWorm.getActionPoints(), EPS);
	}
	
	public void testSetActionPoints(){
		testWorm.setActionPoints(50);
		assertEquals(50, testWorm.getActionPoints(), EPS);
	}

	@Test
	public void testCanMove(){
		assertTrue("Cannot make this move", testWorm.canMove(5));
		assertFalse("Cannot make this move", testWorm.canMove(999));
	}
	
	@Test
	public void testMoveHorizontal() throws IllegalArgumentException {
		Worm worm = new Worm(0, 0, 0, 1, "Jan");
		worm.move(5);
		assertEquals(5, worm.getX(), EPS);
		assertEquals(0, worm.getY(), EPS);
	}

	@Test
	public void testMoveVertical() throws IllegalArgumentException {
		Worm worm = new Worm(0, 0, Math.PI / 2,  1, "Hoet");
		worm.move(5);
		assertEquals(0, worm.getX(), EPS);
		assertEquals(5, worm.getY(), EPS);
	}
	
	@Test
	public void testCanTurn(){
		assertTrue("Cannot turn", testWorm.canTurn(Math.PI));
		assertFalse("Cannot turn", testWorm.canTurn(7000*Math.PI));
	}

	@Test
	public void testTurn(){
		testWorm.turn(-(Math.PI/2));
		assertEquals((Math.PI/2), testWorm.getOrientation(), EPS);
	}
	
	@Test
	public void testGetJumpForce(){
		assertEquals(8233.104428549, testWorm.getJumpForce(), EPS);
	}
	
	@Test
	public void testGetJumpTime(){
		assertEquals(1.8489733E-16, testWorm.getJumpTime(), EPS);
	}
	
	@Test
	public void testGetJumpStep() throws IllegalArgumentException{
		assertArrayEquals(new double[]{4.2984766, 3.77416875}, testWorm.getJumpStep(0.5), EPS);
	}
	
	@Test
	public void testCanJump(){
		assertTrue("Cannot jump", testWorm.canJump());
	}
	
	@Test
	public void testJump() throws IllegalArgumentException{
		Worm worm = new Worm(0,0,(Math.PI/4),0.5,"Matt");
		worm.jump();
		assertEquals(5.5885649, worm.getX(), EPS);
		assertEquals(0, worm.getY(), EPS);
	}
}
