package com.creating.www.beans.codes;

import com.creating.www.beans.elecs.AlarmLocation;

public class AlarmCode {
private Integer code;//告警码
private AlarmLocation location;//告警定位信息
public AlarmCode(Integer code, AlarmLocation location) {
	super();
	this.code = code;
	this.location = location;
}
public Integer getCode() {
	return code;
}
public void setCode(Integer code) {
	this.code = code;
}
public AlarmLocation getLocation() {
	return location;
}
public void setLocation(AlarmLocation location) {
	this.location = location;
}
@Override
public String toString() {
	return "AlarmCode [code=" + code + ", location=" + location + "]";
}
/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return code.intValue();
	}
	/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof AlarmCode)) return false;
			AlarmCode after=(AlarmCode) obj;
			return this.code.equals(after.getCode());
		}
}
