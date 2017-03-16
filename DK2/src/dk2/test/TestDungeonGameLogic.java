package dk2.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dk2.logic.*;

public class TestDungeonGameLogic {

	/* TEST MAP'S
	 
	   Dungeon_map = {{'X','X','X','X','X'},
				      {'X','H',' ','G','X'},
					  {'I',' ',' ',' ','X'},
					  {'I','k',' ',' ','X'},
					  {'X','X','X','X','X'}}; 
					  
	   Keeper_map = {{'X','X','X','X','X','X','X'},
	   				 {'X',' ',' ',' ',' ',' ','X'},
	   				 {'I',' ','H',' ','O','*','X'},
	   				 {'X',' ','k',' ',' ',' ','X'},
	   				 {'X',' ',' ',' ',' ',' ','X'},
	   				 {'X',' ',' ',' ',' ',' ','X'},
	   				 {'X','X','X','X','X','X','X'}};
		
					 					  
					  */
	
	
					
	
	
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
		assertTrue(dungeon_map.isOnLever());
		
		assertTrue(((MapTest_DungeonLvL)dungeon_keeper.getMap()).getLever().isActive());
		
		for(int i = 0; i < dungeon_keeper.getMap().getDoor().length; i++){
			assertTrue(dungeon_keeper.getMap().getDoor()[i].isOpen());			
		}
		
	}
	
	@Test
	public void testHeroExits_DL(){
		
		Game dungeon_keeper = new Game();
		MapTest_DungeonLvL dungeon_map = new MapTest_DungeonLvL(5);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		dungeon_map.moveHero('S');
		dungeon_map.moveHero('S');
		
		assertTrue(dungeon_map.isOnLever());
		
		assertFalse(dungeon_map.hasHeroWon());
		
		for(int i = 0; i < dungeon_map.getDoor().length; i++){
			assertTrue(dungeon_map.getDoor()[i].isOpen());
		}
		
		dungeon_keeper.getMap().moveHero('A');
		
		assertTrue(dungeon_map.hasHeroWon());
		
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

	
	@Test(timeout=1000)
	public void testOgreRandomBehaviour(){
		
		Game dungeon_keeper = new Game();
		MapTest_KeeperLvL dungeon_map = new MapTest_KeeperLvL(7);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		dungeon_keeper.getMap().advanceTurn();
		
		boolean expectedPos = false;
				
		int ogre_lin = dungeon_map.getOgre().getLin();
		int ogre_col = dungeon_map.getOgre().getCol();
		
		if(ogre_lin == 1 || ogre_lin == 2 || ogre_lin == 3){
			if(ogre_col == 3 || ogre_col == 4 || ogre_col == 5)
				expectedPos = true;	
		}
		
		assertTrue(expectedPos);	
		
	}

	
	@Test
	public void testOgreStun(){
		
		Game dungeon_keeper = new Game();
		MapTest_KeeperLvL dungeon_map = new MapTest_KeeperLvL(7);
		
		dungeon_map.buildMaze();
		dungeon_keeper.addMap(dungeon_map);
		dungeon_keeper.setCurrentMap(dungeon_keeper.getNumberOfMaps()-1);
		
		dungeon_map.getHero().setSymbol('A');
		
		assertFalse(dungeon_map.getOgre().getStunned());
		
		dungeon_map.moveHero('D');
		dungeon_map.stunOgres();
		
		assertTrue(dungeon_map.getOgre().getStunned());
		
	}
	
}
