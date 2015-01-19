package controller;

import java.util.ArrayList;
import java.util.List;
import bean.Ibean;

public class BeanHandler {
	private List<Ibean> beanList = new ArrayList<Ibean>();
	private Ibean exist;

	public void addBean(Ibean bean) {
		if (isExist(bean, beanList)) {
			System.out.println("should exist!?");
			beanList.remove(exist);
		}
		beanList.add(bean);
	}

	private Ibean getExistBean(Ibean bean, List<Ibean> beans) {
		exist = null;
		if (beans == null) {
			return null;
		}
		for (Ibean cur : beans) {
			if (cur.getClass() == bean.getClass()) {
				exist = cur;
				break;
			}
		}
		return exist;
	}

	private boolean isExist(Ibean bean, List<Ibean> beans) {
		Ibean exist = getExistBean(bean, beans);
		if (exist == null) {
			return false;
		}
		return true;

	}

	public Ibean getBean(Ibean bean) {
		return getExistBean(bean, beanList);
	}

}
