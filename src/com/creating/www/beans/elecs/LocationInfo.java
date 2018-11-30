package com.creating.www.beans.elecs;

import java.util.Arrays;
import java.util.List;


/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * */
public class LocationInfo implements LayerDescriptionInfo<List<ElecUnit>>{
	public List<ElecUnit> getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(List<ElecUnit> locationInfo) {
		this.locationInfo = locationInfo;
	}
    
	private List<ElecUnit> locationInfo;

	@Override
	public List<ElecUnit> getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 */
	public LocationInfo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public LocationInfo(ElecUnit[] elecUnits) {
		this.locationInfo=Arrays.asList(elecUnits);
	}

	@Override
	public String toString() {
		return "LocationInfo [locationInfo=" + locationInfo + "]";
	}
	
}
