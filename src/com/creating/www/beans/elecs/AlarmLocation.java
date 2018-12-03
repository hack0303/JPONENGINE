package com.creating.www.beans.elecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * */
public class AlarmLocation implements Comparable<AlarmLocation>,LayerDescriptionInfo<List<ElecUnit>>{
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
	public AlarmLocation() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public AlarmLocation(ElecUnit[] elecUnits) {
		this.locationInfo=Arrays.asList(elecUnits);
	}
    /**
	 * @param dest
	 */
	public AlarmLocation(List<ElecUnit> dest) {
		this.locationInfo=dest;
	}

	public boolean isFather4Other(AlarmLocation location) 
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
    public List<ElecUnit> fatherLocation()
    {
    	if(this.locationInfo.size()==1) return null;
    	List<ElecUnit> dest=new ArrayList<ElecUnit>();
    	Collections.copy(dest,this.locationInfo);
    	dest.remove(dest.size()-1);
    	return dest;
    }
	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#childElecTypes()
	 */
	@Override
	public ElecType[] childElecTypes() {
		// TODO Auto-generated method stub
		return locationInfo.get(locationInfo.size()-1).childElecTypes();
	}
	private ElecUnit originElecUnit=null;
	public ElecUnit getLastestElecUnit() 
	{
		if(originElecUnit!=null) 
		{
			return originElecUnit;
		}else 
		{
			ElecUnit local=locationInfo.get(locationInfo.size()-1);
			if(local==null) System.out.println("delete xxxxxxxxxxxxxxxxxxxxxxxx");
			originElecUnit=local;
			return local;
		}
	}
	private ElecUnit rootNode=null;
	public ElecUnit getRootNode() 
	{
		if(rootNode!=null) 
		{
			return rootNode;
		}else 
		{
			ElecUnit local=this.locationInfo.get(0);
			rootNode=local;
			return local;
		}
	}
	public void storeElecUnit(Map<ElecUnit,Set<ElecUnit>> elecStructure) 
	{
		int length=this.locationInfo.size();
		if(length==1) return;
		int indexRange=length-2;
		for(int i=0;i<=indexRange;i++) 
		{
			ElecUnit key=this.locationInfo.get(i);
			Set<ElecUnit> value=elecStructure.get(key);
			if(value==null) { value=new CopyOnWriteArraySet<ElecUnit>();
			elecStructure.put(key, value);
			}
			//if(this.locationInfo.get(i+1)==null) System.out.println("---------------");
			value.add(this.locationInfo.get(i+1));
			
		}
	}
	public AlarmLocation up() 
	{
		if(this.locationInfo.size()==1) return null;
		ElecUnit[] origin=(ElecUnit[]) this.locationInfo.toArray();
		int length=origin.length-1;
		ElecUnit[] dest_array=new ElecUnit[length];
		System.arraycopy(origin, 0, dest_array, 0, length);
		List<ElecUnit> dest=Arrays.asList(dest_array);
		return new AlarmLocation(dest);
	} 
	public Set<AlarmLocation> upAll()
	{
		if(this.getLastestElecUnit().type.toIndex()==NET_LAYER_INDEX) return null;
		Set<AlarmLocation> alarmLocations=new HashSet<AlarmLocation>();
		AlarmLocation al=this;
		do{
			al=al.up();
			if(al!=null) alarmLocations.add(al);
		}while(al!=null);
		if(alarmLocations.size()==0) return null;
		return alarmLocations;
	}
	public Set<AlarmLocation> down(Set<ElecUnit> elecUnit) 
	{
		if(this.locationInfo.size()>=ONU_LAYER_Index) return null;
		if(elecUnit==null||elecUnit.size()==0) return null;
		Set<AlarmLocation> als=new HashSet<AlarmLocation>();
		for(ElecUnit eu:elecUnit) {
		List<ElecUnit> dest=new ArrayList<>();
		Collections.copy(dest,this.locationInfo);
		dest.add(eu);
		als.add(new AlarmLocation(dest));
		}
		return als;
	} 
	static final int NET_LAYER_INDEX=ElecType.NET.toIndex();
	static final int ONU_LAYER_Index=ElecType.ONU.toIndex();
	static final int PONINTERFACE_INDEX=ElecType.PONINTERFACE.toIndex();
	public Set<AlarmLocation> downAll(Map<ElecUnit,Set<ElecUnit>> elecStructure)
	{
		if(elecStructure==null) return null;
		Set<AlarmLocation> outterAlarmLocations=new HashSet<AlarmLocation>();//外层存储集合or最终结果集合
		ElecUnit origin_ElecUnit=getLastestElecUnit();
		int current=0;
		try{current=origin_ElecUnit.type.toIndex();
		}catch(Exception e) 
		{
		  // System.out.println(origin_ElecUnit+":"+this.locationInfo);
		}
		
		if(current>=ONU_LAYER_Index) return null;
				Set<ElecUnit> nextLayerUnits=elecStructure.get(origin_ElecUnit);
			if(nextLayerUnits!=null) 
			{
				Object[] _nextLayerUnits=nextLayerUnits.toArray();
				//if(nextLayerUnits.contains(null)) System.out.println("contains null");
				//ElecUnit[] elecUnits=new ElecUnit[nextLayerUnits.size()];
				//nextLayerUnits.toArray(elecUnits);
                for(Object obj:_nextLayerUnits) 
				{
                	ElecUnit eu=(ElecUnit) obj;
                	System.out.println(eu);
					if(eu==null) { System.out.println(this+":"+"----------"+nextLayerUnits+"-------------");}
					ElecUnit[] src=new ElecUnit[this.locationInfo.size()];
					this.locationInfo.toArray(src);
					ElecUnit[] _dest=new ElecUnit[src.length];
					System.arraycopy(src, 0, _dest, 0, src.length);
					List<ElecUnit> dest=new ArrayList<>(Arrays.asList(_dest));
					dest.add(eu);
					AlarmLocation aAlarmLocation=new AlarmLocation(dest);
					outterAlarmLocations.add(aAlarmLocation);
					Set<AlarmLocation> innerAlarmLocations=aAlarmLocation.downAll(elecStructure);
					if(innerAlarmLocations!=null)
					outterAlarmLocations.addAll(innerAlarmLocations);
				}
			}
			if(outterAlarmLocations.size()>0)
			return outterAlarmLocations;
			else return null;
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
	public int compareTo(AlarmLocation o) {
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
