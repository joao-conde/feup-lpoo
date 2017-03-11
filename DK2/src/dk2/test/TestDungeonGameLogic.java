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
	public void testMoveHeroIntoFreeCell_DL(){
	
		Game dungeon_keeper = new Game();
		MapTest_DungeonLvL dungeon_map = new MapTest_DungeonLvL(5);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertEquals(1,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(1,dungeon_keeper.getMap().getHero().getCol());
		
		dungeon_keeper.getMap().moveHero('S');
		
		assertEquals(2,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(1,dungeon_keeper.getMap().getHero().getCol());
		
	}
	
	
	@Test
	public void testHeroIsCapturedByGuard(){
		
		Game dungeon_keeper = new Game();
		MapTest_DungeonLvL dungeon_map = new MapTest_DungeonLvL(5);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertFalse(dungeon_keeper.isHeroDead());
		
		dungeon_keeper.getMap().moveHero('D');
		
		assertTrue(dungeon_keeper.isHeroDead());
	}
	
	//checks if lever was set active and doors open
	@Test
	public void testHeroTurnsLever(){
		
		Game dungeon_keeper = new Game();
		MapTest_DungeonLvL dungeon_map = new MapTest_DungeonLvL(5);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertFalse(((MapTest_DungeonLvL)dungeon_keeper.getMap()).getLever().isActive());
		
		for(int i = 0; i < dungeon_keeper.getMap().getDoor().length; i++){
			assertFalse(dungeon_keeper.getMap().getDoor()[i].isOpen());			
		}
		
		
		dungeon_keeper.getMap().moveHero('S');
		dungeon_keeper.getMap().moveHero('S');
		dungeon_keeper.getMap().heroReachedKey();
		
		assertTrue(((MapTest_DungeonLvL)dungeon_keeper.getMap()).getLever().isActive());
		
		for(int i = 0; i < dungeon_keeper.getMap().getDoor().length; i++){
			assertTrue(dungeon_keeper.getMap().getDoor()[i].isOpen());			
		}
		
	}
	
	
	//Keeper lvl
	
	
    @Test
	public void testMoveHeroIntoFreeCell_KL(){ 
	
		Game dungeon_keeper = new Game();
		MapTest_KeeperLvL dungeon_map = new MapTest_KeeperLvL(7);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertEquals(2,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(2,dungeon_keeper.getMap().getHero().getCol());
		
		dungeon_keeper.getMap().moveHero('W');
		
		assertEquals(1,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(2,dungeon_keeper.getMap().getHero().getCol());
		
		dungeon_keeper.getMap().moveHero('A');
		
		assertEquals(1,dungeon_keeper.getMap().getHero().getLin());
		assertEquals(1,dungeon_keeper.getMap().getHero().getCol());
		
	}
	
	
	@Test
	public void testHeroIsCapturedByOgre(){
		
		Game dungeon_keeper = new Game();
		MapTest_KeeperLvL dungeon_map = new MapTest_KeeperLvL(7);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertFalse(dungeon_keeper.isHeroDead());
		
		dungeon_keeper.getMap().moveHero('D');
		
		assertTrue(dungeon_keeper.isHeroDead());
	}

	@Test
	public void testHeroPicksUpKey(){
		
		Game dungeon_keeper = new Game();
		MapTest_KeeperLvL dungeon_map = new MapTest_KeeperLvL(7);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		assertFalse(dungeon_keeper.getMap().getHero().getHasKey());
				
		dungeon_keeper.getMap().moveHero('S');
		dungeon_keeper.getMap().heroReachedKey();
		
		assertTrue(dungeon_keeper.getMap().getHero().getHasKey());
		
	}
}
