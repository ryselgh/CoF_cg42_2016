package gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.*;

import board.City;
import board.King;
import board.Map;


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
			int cost = 0;
			shortestPath.findPathBetween(g, map.getKing().getLocation().getName(), arrivalCity.getName());
			cost = Integer.parseInt(Double.toString(shortestPath.getPathLength()));
			return cost*2;
		}

}





