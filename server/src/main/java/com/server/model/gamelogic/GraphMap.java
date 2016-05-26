package com.server.model.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import com.server.model.board.City;
import com.server.model.board.King;
import com.server.model.board.Map;

import org.jgrapht.alg.*;


public class GraphMap {

	private static DijkstraShortestPath shortestPath;
	private ArrayList<City> cities;
	private Map map;
	private UndirectedGraph<String, DefaultEdge> g;

	public GraphMap(Map map){
		this.map = map;
		this.g = new SimpleGraph<String, DefaultEdge>     (DefaultEdge.class);
		this.createGraph();
	}

	public void addVertex(City city)  {

		g.addVertex(city.getName());
	}

	public void addEdge(String v1,String v2) {
		g.addEdge(v1, v2);
	}

	public UndirectedGraph<String, DefaultEdge> getGraph() {
		return g;
	}


	public void createGraph(){
		cities = new ArrayList<City>(Arrays.asList(map.getCity()));
		for (City c: cities){
			g.addVertex(c.getName());
		}
		for(City c:cities)
			for(int i=0;i<c.getCloseCity().length;i++)
				g.addEdge(c.getName(), c.getCloseCity()[i]);


	}

	public int shortestPathCost(City arrivalCity){
		List<DefaultEdge> path = shortestPath.findPathBetween(g, map.getKing().getLocation().getName(), arrivalCity.getName());
		return path.size()*2;
	}

}