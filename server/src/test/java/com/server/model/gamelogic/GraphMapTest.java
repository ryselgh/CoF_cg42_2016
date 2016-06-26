package com.server.model.gamelogic;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.*;
import static org.junit.Assert.*;
import com.communication.values.CityColor;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.board.Map;

public class GraphMapTest {
	
	Game game;
	String[] players;
	
	@Before
	public void setUp()
		throws Exception {
		players = new String[3];
		players[0] = "1";
		players[1] = "2";
		players[2] = "3";
		game = new Game(3, true, null, players);
		
		
	}
	
	
	@Test
	public void testGraphMap_1()
		throws Exception {
		Map map = game.getMap();

		GraphMap result = new GraphMap(map);

		
		assertNotNull(result);
	}

//	@Test
//	public void testAddEdge_1()
//		throws Exception {
//		Map map = game.getMap();
//		GraphMap fixture = new GraphMap(map);
//		String v1 = "";
//		String v2 = "";
//
//		fixture.addEdge(v1, v2);
//
//		
//	}
//
//	@Test
//	public void testAddVertex_1()
//		throws Exception {
//		Map map = game.getMap();
//		GraphMap fixture = new GraphMap(map);
//		String v1 = fixture.addVertex(map.getCity()[4]);
//		Vertex v2 = fixture.addVertex(map.getCity()[8]);
//		fixture.addEdge(v1, v2);
//		
//
//		
//		
//		
//	}



	@Test
	public void testGetGraph()
		throws Exception {
		Map map = game.getMap();
		GraphMap fixture = new GraphMap(map);

		UndirectedGraph<String, DefaultEdge> result = fixture.getGraph();

		
		assertNotNull(result);
	}

	@Test
	public void testShortestPathCost()
		throws Exception {
		Map map = game.getMap();
		GraphMap fixture = new GraphMap(map);		
		

		int result = fixture.shortestPathCost(map.getCity()[4]);

		
		assertEquals(4, result);
	}
	
	@Test
	public void testShortestPathCostFromTheShiftedKingCity()
		throws Exception {
		Map map = game.getMap();
		GraphMap fixture = new GraphMap(map);
		game.getMap().getKing().setLocation(game.getMap().getCity()[4]);
		

		int result = fixture.shortestPathCost(map.getCity()[14]);

		
		assertEquals(6, result);
	}

	

	

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GraphMapTest.class);
	}
}