package com.creating.www.spi;

import com.creating.www.AlarmModel;

public class AlarmSearcherTask implements Runnable{

	private AlarmModel alarmModel;
	
	public AlarmSearcherTask(AlarmModel alarmModel) {
		super();
		this.alarmModel = alarmModel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
