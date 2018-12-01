package com.creating.www;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.impl.CallBackImpl;
import com.creating.www.impl.PONAlarm;
import com.creating.www.parsers.AlarmModelFromAlmBeanParser;
import com.creating.www.parsers.Parser;

public class CallBack extends CallBackImpl {
    /**
	 * @throws RemoteException
	 */
	static final Parser<AlmBean,AlarmModel> parser=new AlarmModelFromAlmBeanParser();
	public CallBack() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	static Logger logger=LogManager.getLogger(CallBack.class.getName());

	@Override
	public void onReport(List<AlmBean> arg0) {
		arg0.forEach((e)->{
			//logger.debug("[{}] [{}]","PON-ALARM",((PONAlarm)e).getId());
			AlarmModel am=parser.parse(e);
			//logger.debug("[{}] [{}]","PON-MODEL",am);
		});
	}

}
