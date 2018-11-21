package com.creating.www.beans.elecs;

import java.util.List;

public class Location implements ElecInfo<List<Object>>{
	public List<Object> getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(List<Object> locationInfo) {
		this.locationInfo = locationInfo;
	}

	private List<Object> locationInfo;

	@Override
	public List<Object> getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
