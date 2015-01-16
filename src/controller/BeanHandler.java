package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Ibean;

public class BeanHandler {
	private static Map<String, List<Ibean>> beanMap = new HashMap<String, List<Ibean>>();

	public static void addBean(String sessionId, Ibean bean) {
		if (beanMap.containsKey(sessionId)) {
			List<Ibean> beans = getBeanList(sessionId);
			if (isExist(bean, beans)) {
				System.out.println("should exist!?");
			} else {
				beans.add(bean);
			}
		} else {
			List<Ibean> beans = new ArrayList<Ibean>();
			beans.add(bean);
			beanMap.put(sessionId, beans);
		}
	}

	private static Ibean getExistBean(Ibean bean, List<Ibean> beans) {
		if (beans == null) {
			return null;
		}
		for (Ibean cur : beans) {
			if (cur.getClass() == bean.getClass()) {
				return cur;
			}
		}
		return null;
	}

	private static boolean isExist(Ibean bean, List<Ibean> beans) {
		Ibean exist = getExistBean(bean, beans);
		if (exist == null) {
			return false;
		}
		return true;

	}

	private static List<Ibean> getBeanList(String sessionId) {
		return beanMap.get(sessionId);
	}

	public static Ibean getBean(String sessionId, Ibean bean) {
		List<Ibean> beans = getBeanList(sessionId);
		return getExistBean(bean, beans);
	}

}
