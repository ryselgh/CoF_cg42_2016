package com.communication;





/**
 * The Class CommunicationObject.
 */
public class CommunicationObject implements SellableSer{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8740453071641140457L;
	
	/** The msg. */
	private String msg;
	
	/** The obj. */
	private Object obj;
	
	/**
	 * Instantiates a new communication object.
	 *
	 * @param s the string
	 * @param o the object
	 */
	public CommunicationObject(String s, Object o){
		this.msg = s;
		this.obj = o;
	}
	
	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Gets the obj.
	 *
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
}
