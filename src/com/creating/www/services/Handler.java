/**
 * 
 */
package com.creating.www.services;

import java.util.List;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public interface Handler<T>{
	public T findSource(T alarm);
	public boolean store(T alarm);
	public List<T> findDescends(T alarm);
}
