package com.spring.user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("asyncTaskService")
public class AsyncTaskService {

	public static List<Map<String, String>> lockedIdList = new ArrayList();
	public static int initFlag = 0;
	
	@Async
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		do {
			for (int i = 0; i < lockedIdList.size(); i++) {
				Iterator iterator = lockedIdList.get(i).keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Date d1;
					try {
						d1 = format.parse(lockedIdList.get(i).get(key));
						Date d2 = new Date();
						long diff = d2.getTime() - d1.getTime();
						long diffSeconds = diff / 1000 % 60;
//						System.out.println(diffSeconds);
						if (diffSeconds > 10) {
							System.out.println(">>> terminating " + key);
							release(key);
//							System.exit(0);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			if (isEmpty()) {
//				System.out.println(">>>>>> empty!");
			}
		} while (true);
	}

	public void add(String inputId) {
		Map<String, String> map = new HashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String to = fm.format(date);
		map.put(inputId, to);
		lockedIdList.add(map);
	}

	public void print() {
		for (int i = 0; i < lockedIdList.size(); i++) {
			Iterator iterator = lockedIdList.get(i).keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				System.out.println(i + "th key:" + key);
			}
		}
	}

	public boolean isExist(String inputId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < lockedIdList.size(); i++) {
			Iterator iterator = lockedIdList.get(i).keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (key.equals(inputId)) {
					return true;
				}
			}
		}
		return false;
	}

	public String lock(String inputId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void release(String inputId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < lockedIdList.size(); i++) {
			Iterator iterator = lockedIdList.get(i).keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (key.equals(inputId)) {
					lockedIdList.remove(lockedIdList.get(i));
				}
			}
		}
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (lockedIdList.size() == 0) ? true : false;
	}
}
