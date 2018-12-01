/**
 * 
 */
package com.creating.www.services;

import java.util.List;

import com.creating.www.AlarmModel;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public interface Handler {
	public AlarmModel findSource();
	public List<AlarmModel> findDescends();
}
