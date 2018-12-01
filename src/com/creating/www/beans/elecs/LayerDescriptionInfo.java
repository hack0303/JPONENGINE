package com.creating.www.beans.elecs;

public interface LayerDescriptionInfo<T> {
public T getDescription();
public ElecType[] fatherElecTypes();
public ElecType[] childElecTypes();
}
