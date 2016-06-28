package com.communication;




public class CommunicationObject implements SellableSer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8740453071641140457L;
	private String msg;
	private Object obj;
	
	public CommunicationObject(String s, Object o){
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
