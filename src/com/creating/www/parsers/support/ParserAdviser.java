package com.creating.www.parsers.support;

import com.creating.www.beans.elecs.parsers.AlmBeanFromFileParser;
import com.creating.www.parsers.Parser;

public class ParserAdviser {
public Parser<?,?> newParser(){
	return null;
}
public Parser<?,?> newParser(@SuppressWarnings("rawtypes") Class clazz){
	Parser<?,?> parser=null;
	if(clazz.equals(AlmBeanFromFileParser.class)) 
	{
		parser=new AlmBeanFromFileParser();
	}
	return parser;
} 

}
