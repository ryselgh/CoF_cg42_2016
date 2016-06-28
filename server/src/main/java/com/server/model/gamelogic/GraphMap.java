package com.server.model.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import com.server.model.board.City;

import com.server.model.board.Map;

import org.jgrapht.alg.*;



/**
 * The Class GraphMap.
 */
public class GraphMap {

	/** The shortest path. */
	private static DijkstraShortestPath<?, ?> shortestPath;
	
	/** The cities. */
	private ArrayList<City> cities;
	
	/** The map. */
	private Map map;
	
	/** The g. */
	private UndirectedGraph<String, DefaultEdge> g;

	/**
	 * Instantiates a new graph map.
	 *
	 * @param map the map
	 */
	public GraphMap(Map map){
		this.map = map;
		this.g = new SimpleGraph<String, DefaultEdge>     (DefaultEdge.class);
		this.createGraph();
	}

	/**
	 * Adds the vertex.
	 *
	 * @param city the city
	 */
	public void addVertex(City city)  {

		g.addVertex(city.getName());
	}

	/**
	 * Adds the edge.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 */
	public void addEdge(String v1,String v2) {
		g.addEdge(v1, v2);
	}

	/**
	 * Gets the graph.
	 *
	 * @return the graph
	 */
	public UndirectedGraph<String, DefaultEdge> getGraph() {
		return g;
	}


	/**
	 * Creates the graph.
	 */
	public void createGraph(){
		cities = new ArrayList<City>(Arrays.asList(map.getCity()));
		for (City c: cities){
			g.addVertex(c.getName());
		}
		for(City c:cities)
			for(int i=0;i<c.getCloseCity().length;i++)
				g.addEdge(c.getName(), c.getCloseCity()[i]);


	}

	/**
	 * Shortest path cost.
	 *
	 * @param arrivalCity the arrival city
	 * @return the int
	 */
	public int shortestPathCost(City arrivalCity){
		List<DefaultEdge> path = shortestPath.findPathBetween(g, map.getKing().getLocation().getName(), arrivalCity.getName());
		return path.size()*2;
	}

}