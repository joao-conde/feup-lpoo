package dk2.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dk2.logic.*;

public class TestDungeonGameLogic {

	char[][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
					
	
	
	//Dungeon lvl
	
	@Test
	public void testMoveHeroIntoFreeCell(){
	
		MapTest_DungeonLvL gameMap = new MapTest_DungeonLvL(5);
		Game dungeon_keeper = new Game();
		
		assertEquals(1,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(1,dungeon_keeper.getMap().getHero().getCol());
		
		dungeon_keeper.getMap().moveHero('S');
		
		assertEquals(2,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(1,dungeon_keeper.getMap().getHero().getCol());
		
	}
	
	
	@Test
	public void testHeroIsCapturedByGuard(){
		
		MapTest_DungeonLvL gameMap = new MapTest_DungeonLvL(5);
		Game dungeon_keeper = new Game();
		
		assertFalse(dungeon_keeper.isHeroDead());
		
		dungeon_keeper.getMap().moveHero('D');
		
		assertTrue(dungeon_keeper.isHeroDead());
	}
	
	
	//Keeper lvl
	

	
}
