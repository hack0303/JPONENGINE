/**
 * 
 */
package com.creating.www.config.resources;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public interface Generator<T> {
public T generate(); 

public void fresh();
}
