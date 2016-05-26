package com.communication.gamelogic;

import java.io.Serializable;
import java.util.ArrayList;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import com.communication.board.CityDTO;
import com.communication.board.MapDTO;

import org.jgrapht.alg.*;


public class GraphMapDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2176312862723064612L;
	private static DijkstraShortestPath shortestPath;
	private ArrayList<CityDTO> cities;
	private MapDTO map;
	private UndirectedGraph<String, DefaultEdge> g;
	
	
	/**
	 * @return the shortestPath
	 */
	public static DijkstraShortestPath getShortestPath() {
		return shortestPath;
	}
	/**
	 * @param shortestPath the shortestPath to set
	 */
	public static void setShortestPath(DijkstraShortestPath shortestPath) {
		GraphMapDTO.shortestPath = shortestPath;
	}
	/**
	 * @return the cities
	 */
	public ArrayList<CityDTO> getCities() {
		return cities;
	}
	/**
	 * @param cities the cities to set
	 */
	public void setCities(ArrayList<CityDTO> cities) {
		this.cities = cities;
	}
	/**
	 * @return the map
	 */
	public MapDTO getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(MapDTO map) {
		this.map = map;
	}
	/**
	 * @return the g
	 */
	public UndirectedGraph<String, DefaultEdge> getG() {
		return g;
	}
	/**
	 * @param g the g to set
	 */
	public void setG(UndirectedGraph<String, DefaultEdge> g) {
		this.g = g;
	}

}