package com.creating.www.beans.elecs;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.List;


/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * */
public class LocationInfo implements Comparable<LocationInfo>,LayerDescriptionInfo<List<ElecUnit>>{
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
    public boolean isFather4Other(LocationInfo location) 
    {
		return false;
    }
	@Override
	public String toString() {
		return "LocationInfo [locationInfo=" + locationInfo + "]";
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#fatherElecTypes()
	 */
	@Override
	public ElecType[] fatherElecTypes() {
		// TODO Auto-generated method stub
		return locationInfo.get(locationInfo.size()-1).fatherElecTypes();
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#childElecTypes()
	 */
	@Override
	public ElecType[] childElecTypes() {
		// TODO Auto-generated method stub
		return locationInfo.get(locationInfo.size()-1).childElecTypes();
	}
	
	public ElecUnit getLastestElecUnit() 
	{
      return locationInfo.get(locationInfo.size()-1);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(LocationInfo o) {
		int a,b=0;
		a=locationInfo.size();
		b=o.locationInfo.size();
		int comp=0;
		int min=0;
		if(a>b) 
		{
			min=b;
			comp=1;
			
		}else if(a==b) { 
			min=a;
		    comp=0;	
		}
		else 
		{
			min=a;
			comp=-1;
		}
		int _comp=0;
		for(int i=0;i<min;i++) 
		{
			_comp=this.locationInfo.get(i).compareTo(o.locationInfo.get(i));
			if(_comp!=0) return _comp;
		}
		return comp;
	}
}
