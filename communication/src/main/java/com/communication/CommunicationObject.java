package com.communication;

import java.io.Serializable;


public class CommunicationObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8740453071641140457L;
	private String msg;
	private SerObject obj;
	
	public CommunicationObject(String s, SerObject o){
		this.msg = s;
		this.obj = o;
	}
	public String getMsg() {
		return msg;
	}
	public Object getObj() {
		return obj;
	}
}
