package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

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


public class AllTest {
	
	private Worm testWorm;
	private static World world;
	private static final double EPS = Util.DEFAULT_EPSILON;

	
	@Before
	public void setup() throws IllegalArgumentException{
		boolean[][] passable = new boolean[2000][3000];
		for(int i = 5; i < 1995; i++){
			for(int j = 5; j < 2995; j++){
				passable[i][j] = true;
			}
		}
		world = new World(15, 10, passable, new Random(9000));
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
		testWorm.setY(1);
		assertEquals(1, testWorm.getY(), EPS);
	}
	
	@Test
	public void testGetName(){
		assertTrue("Not the correct name", testWorm.getName().equals("Matt"));
	}
	
	@Test
	public void testIsValidName(){
		assertTrue("Name is invalid", Worm.isValidName("Peter"));
		assertTrue("Name is invalid", Worm.isValidName("123Jack"));
		assertTrue("Name is invalid", Worm.isValidName("Jack Wolfskin"));
		assertFalse("Name is invalid", Worm.isValidName("Jack &&é Wolfskin"));

	}
	
	@Test
	public void testRename() throws IllegalArgumentException{
		testWorm.rename("Geoff");
		assertTrue("Name is not correct", testWorm.getName().equals("Geoff"));
	}
	
	@Test
	public void testGetMass(){
		assertEquals(69.5077, testWorm.getMass(), EPS);
	}
	
	@Test
	public void testGetRadius(){
		assertEquals(0.25, testWorm.getRadius(), EPS);
	}
	
	@Test
	public void testGetMinimualRadius(){
		assertEquals(0.25, Worm.getMinimalRadius(), EPS);
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
		assertEquals(0.25, testWorm.getRadius(), EPS);
	}
	
	@Test
	public void testGetOrientation(){
		assertEquals(Math.PI, testWorm.getOrientation(), EPS);
	}
	
	@Test
	public void testSetOrientation(){
		testWorm.setOrientation(-Math.PI);
		assertEquals(Math.PI, testWorm.getOrientation(), EPS);
		testWorm.setOrientation(Math.PI/2);
		assertEquals(Math.PI/2, testWorm.getOrientation(), EPS);
	}
	
	@Test
	public void testGetMaxActionPoints(){
		assertEquals(70, testWorm.getMaxActionPoints(), EPS);
	}
	
	@Test
	public void testGetActionPoints(){
		assertEquals(70, testWorm.getActionPoints(), EPS);
	}
	
	public void testSetActionPoints(){
		testWorm.setActionPoints(50);
		assertEquals(50, testWorm.getActionPoints(), EPS);
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
		assertEquals(1031.638, testWorm.getJumpForce(), EPS);
	}
	
	@Test
	public void testCanJump(){
		assertTrue("Cannot jump", testWorm.canJump());
		testWorm.setActionPoints(0);
		assertFalse("wrong!", testWorm.canJump());
	}
	
	@Test
	public void testTurnFinished(){
		world.start();
		testWorm.setActionPoints(0);
		assertNotEquals(world.getActiveWorm(), testWorm);
	}
	
	@Test
	public void testSetHP(){
		testWorm.setHitPoints(50);
		assertEquals(50, testWorm.getHitPoints(), EPS);
		testWorm.setHitPoints(-22);
		assertEquals(0, testWorm.getHitPoints(), EPS);
	}
	
	@Test
	public void testDimensions(){
		assertTrue("Not valid!", world.validHeight(Double.MAX_VALUE));
		assertFalse("Valid!", world.validHeight(-10));
		assertTrue("Not valid!", world.validWidth(Double.MAX_VALUE));
		assertFalse("Valid!", world.validWidth(-10));
	}
	
	@Test
	public void testTeams(){
		Teams team = new Teams("TestTeam");
		testWorm.setTeam(team);
		assertEquals("TestTeam", testWorm.getTeam().getTName());
	}
	
	@Test
	public void testWeapons(){
		assertEquals("Rifle", testWorm.getWeapon());
	}

	@Test
	public void testGame(){
		Teams team2 = new Teams("Test2");
		world.addWorm();
		world.addWorm();
		world.addWorm();
		world.start();
		assertFalse("Wrong!", world.isFinished());
		assertFalse("Wrong!", world.getWorms().isEmpty());
		assertFalse("Wrong!", world.getActiveWorm() == null);
	}
	
}
