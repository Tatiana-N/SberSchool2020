package org.nta.lessons.lesson15;

import junit.framework.TestCase;
import org.nta.lessons.lesson15.refactoring.*;

/**
 * @author Ben
 *
 */
public class TractorImplTest extends TestCase {

	public void testShouldMoveForward(){
		Tractor tractor = new TractorImpl();
		Command move = new MoveCommand(tractor);
		Command turn = new TurnCommand(tractor);
		tractor.move(move);
		assertEquals(0, tractor.getPosition().getX());
		assertEquals(1, tractor.getPosition().getY());
	}

	public void testShouldTurn(){
		Tractor tractor = new TractorImpl();
		Command move = new MoveCommand(tractor);
		Command turn = new TurnCommand(tractor);
		tractor.move(turn);
		assertEquals(Orientation.EAST, tractor.getOrientation());
		tractor.move(turn);
		assertEquals(Orientation.SOUTH, tractor.getOrientation());
		tractor.move(turn);
		assertEquals(Orientation.WEST, tractor.getOrientation());
		tractor.move(turn);
		assertEquals(Orientation.NORTH, tractor.getOrientation());
	}

	public void testShouldTurnAndMoveInTheRightDirection(){
		Tractor tractor = new TractorImpl();
		Command move = new MoveCommand(tractor);
		Command turn = new TurnCommand(tractor);
		tractor.move(turn);
		tractor.move(move);
		assertEquals(1, tractor.getPosition().getX());
		assertEquals(0, tractor.getPosition().getY());
		tractor.move(turn);
		tractor.move(move);
		assertEquals(1, tractor.getPosition().getX());
		assertEquals(-1, tractor.getPosition().getY());
		tractor.move(turn);
		tractor.move(move);
		assertEquals(0, tractor.getPosition().getX());
		assertEquals(-1, tractor.getPosition().getY());
		tractor.move(turn);
		tractor.move(move);
		assertEquals(0, tractor.getPosition().getX());
		assertEquals(0, tractor.getPosition().getY());
	}
	
	public void testShouldThrowExceptionIfFallsOffPlateau(){
		Tractor tractor = new TractorImpl();
		Command move = new MoveCommand(tractor);
		Command turn = new TurnCommand(tractor);
		tractor.move(move);
		tractor.move(move);
		tractor.move(move);
		tractor.move(move);
		tractor.move(move);
		try{
			tractor.move(move);
			fail("Tractor was expected to fall off the plateau");
		}catch(TractorInDitchException expected){
		}
	}
}
