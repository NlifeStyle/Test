package com.dao;

import java.util.List;
import com.util.DBUtils;

public class DaoImp implements IDao<String> {

	private DBUtils dbUtil;

	public DaoImp() {
		dbUtil = new DBUtils();
	}

	@Override
	public boolean save(String o) {
		// TODO Auto-generated method stub
		int count = dbUtil.executeUpdate(o);
		return count > 0;
	}

	@Override
	public List<List<String>> search(String o) {
		// TODO Auto-generated method stub
		return dbUtil.getListResult(o);

	}

	@Override
	public boolean delete(String o) {
		// TODO Auto-generated method stub
		int count = dbUtil.executeUpdate(o);
		if (count > 0) {
			return true;
		} else {
			if (search(o).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(String o) {
		// TODO Auto-generated method stub
		int count = dbUtil.executeUpdate(o);
		return count > 0;
	}

	public static void main(String[] args) {
		IDao<String> dao = new DaoImp();
		boolean result;
		List<List<String>> listResult;
		result = dao
				.save("insert into jasonlocaltest (id,name) values('2','goodluck')");
		result = dao
				.update("update jasonlocaltest set name='updateTest' where id='1'");
		listResult = dao.search("select * from jasonlocaltest");
		result = dao.delete("delete from jasonlocaltest where id='2'");
	}

}
