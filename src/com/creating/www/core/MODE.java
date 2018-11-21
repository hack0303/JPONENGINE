package com.creating.www.core;

public enum MODE {
    DEV(1,"开发者模式"),PRODUCTION(2,"生产环境");
	private int _MODE_ID=0;
	private String _MODE_INFO="";
	MODE(int modeId,String modeInfo) 
	{
		this._MODE_ID=modeId;
		this._MODE_INFO=modeInfo;
	}
	public int id() 
	{
		return this._MODE_ID;
	}
	public String info() 
	{
		return this._MODE_INFO;
	}
	public static MODE asMode(int modeId) 
	{
		switch(modeId) 
		{
		case 1:{return DEV;}
		case 2:{return PRODUCTION;}
		default:{return DEV;}
		}
	}
	
}
