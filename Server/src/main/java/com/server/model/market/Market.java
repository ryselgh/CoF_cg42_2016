package com.server.model.market ;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	private ArrayList<OnSaleInterface> objectsOnSale;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Market() {
		objectsOnSale = new ArrayList<OnSaleInterface>();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public OnSaleInterface getObjOnSale(int arrayPointer) {
			return objectsOnSale.get(arrayPointer);	
	}

	public void addObj(OnSaleInterface o)
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
	
}

