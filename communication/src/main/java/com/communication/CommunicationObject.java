package com.communication;

import java.io.Serializable;



/**
 * The Class CommunicationObject.
 */
public class CommunicationObject implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8740453071641140457L;
	
	/** The message. */
	private String msg;
	
	/** The object. */
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Gets the object.
	 *
	 * @return the object
	 */
	public Object getObj() {
		return obj;
	}
}
