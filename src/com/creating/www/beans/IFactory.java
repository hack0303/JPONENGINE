/**
 * 
 */
package com.creating.www.beans;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public interface IFactory<IN,OUT> {
public OUT newTarget(IN in);
public void clear();
}
