package com.server.model.market ;

import java.util.ArrayList;
import java.util.Iterator;

import com.communication.market.MarketDTO;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Market implements Iterator
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<OnSale> objectsOnSale;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Market() {
		objectsOnSale = new ArrayList<OnSale>();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public OnSale getObjOnSale(int arrayPointer) {
			return objectsOnSale.get(arrayPointer);	
	}
	
	public ArrayList<OnSale> getObjectsOnSale() {
		return objectsOnSale;	
}
	
	public void addObj(OnSale o)
	{
		objectsOnSale.add(o);
	}
	
	public void removeObj(int o)
	{
		objectsOnSale.remove(o);
	}
	
	public int getObjNumber()
	{
		return objectsOnSale.size();
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MarketDTO toDTO(){
		for(OnSale os : objectsOnSale){
			
		}
		return null;
	}
	
}

