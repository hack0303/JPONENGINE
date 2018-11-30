/**
 * 
 */
package com.creating.www.beans.codes;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class AlarmCodePair {
private Integer codePairId;
private AlarmCode source;
private AlarmCode descend;

public AlarmCodePair(Integer codePairId, AlarmCode source, AlarmCode descend) {
	super();
	this.codePairId = codePairId;
	this.source = source;
	this.descend = descend;
}
public Integer getCodePairId() {
	return codePairId;
}
public void setCodePairId(Integer codePairId) {
	this.codePairId = codePairId;
}
public AlarmCode getSource() {
	return source;
}
public void setSource(AlarmCode source) {
	this.source = source;
}
public AlarmCode getDescend() {
	return descend;
}
public void setDescend(AlarmCode descend) {
	this.descend = descend;
}
}
